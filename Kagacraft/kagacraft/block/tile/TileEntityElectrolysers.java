package kagacraft.block.tile;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.network.INetworkTileEntityEventListener;

import java.util.Arrays;
import java.util.List;

import kagacraft.api.KagacraftRecipes;
import kagacraft.main.KagacraftItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityElectrolysers extends TileEntityBase implements IInventory, IEnergySink, INetworkTileEntityEventListener
{
	private ItemStack[] inv;
	public int maxWaitTime = 120;
	private int maxEnergy = 390;
	private int energy = 0;
	private boolean initnal = false;

	public TileEntityElectrolysers()
	{
		this.inv = new ItemStack[3];
	}

	@Override
	public void updateEntity()
	{
		if(!initnal)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.initnal = true;
		}
		
		boolean flag = false;
		if(isWork())
		{
			this.waitTime--;
			this.energy -= 4;

			if(this.waitTime == 0 && !this.worldObj.isRemote)
			{
				if((this.inv[0] != null && this.inv[2] != null && this.inv[0].itemID == KagacraftItems.testTube.itemID) && (this.inv[2].itemID == KagacraftItems.testTube.itemID ))
				{
					if(KagacraftRecipes.getElectrolysersRecipe().containsKey(Arrays.asList(this.inv[1].itemID, this.inv[1].getItemDamage())))
					{
						List<ItemStack> items = KagacraftRecipes.getElectrolysersRecipe().get(Arrays.asList(this.inv[1].itemID, this.inv[1].getItemDamage()));
						List<Integer> stacks = KagacraftRecipes.getElectrolysersStacks().get(Arrays.asList(this.inv[1].itemID, this.inv[1].getItemDamage()));
						if(stacks.get(0) == this.inv[0].stackSize && stacks.get(1) == this.inv[2].stackSize)
						{
							if(this.inv[1].stackSize == 1)
							{
								this.inv[1] = null;
							}
							else
							{
								this.inv[1].stackSize--;
							}
							this.inv[0] = new ItemStack(items.get(0).itemID, stacks.get(0), items.get(0).getItemDamage());
							this.inv[2] = new ItemStack(items.get(1).itemID, stacks.get(1), items.get(1).getItemDamage());
						}
					}
					flag = true;
				}
				this.waitTime = 0;
			}
			if(this.inv[1] == null || this.inv[0] == null && this.inv[0] == null)
			{
				this.waitTime = 0;
				flag = true;
			}
			if(this.inv[0] != null && this.inv[0].itemID != KagacraftItems.testTube.itemID)
			{
				this.waitTime = 0;
			}
			if(this.inv[2] != null && this.inv[2].itemID != KagacraftItems.testTube.itemID)
			{
				this.waitTime = 0;
			}
			if(this.energy <= 0 && !this.worldObj.isRemote)
			{
				this.waitTime = 0;
			}
		}
		else if(!isWork() && this.inv[0] != null && this.inv[1] != null && this.inv[2] != null && this.energy > 0)
		{
			if((this.inv[0].itemID == KagacraftItems.testTube.itemID) && (this.inv[2].itemID == KagacraftItems.testTube.itemID ))
			{
				if(KagacraftRecipes.getElectrolysersRecipe().containsKey(Arrays.asList(this.inv[1].itemID, this.inv[1].getItemDamage())))
				{
					List<Integer> items = KagacraftRecipes.getElectrolysersStacks().get(Arrays.asList(this.inv[1].itemID, this.inv[1].getItemDamage()));
					if(items.get(0) == this.inv[0].stackSize && items.get(1) == this.inv[2].stackSize)
					{
						this.waitTime = this.maxWaitTime;
					}
				}
			}
		}

		if(flag)
		{
			this.onInventoryChanged();
		}
	}

	public boolean isWork()
	{
		return waitTime > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length)
			{
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.waitTime = tagCompound.getInteger("waitTime");
		this.energy = tagCompound.getInteger("energy");
	}


	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		tagCompound.setInteger("waitTime", this.waitTime);
		tagCompound.setInteger("energy", this.energy);
	}


	@Override
	public int getSizeInventory()
	{
		return this.inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.inv[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize <= amt)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}


	@Override
	public void invalidate()
	{
		super.invalidate();
		if(this.initnal)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.initnal = false;
		}
	}

	@Override
	public String getInvName()
	{
		return "kagacraft.electrolysers";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}


	public ItemStack[] getInv()
	{
		return inv;
	}

	public void setInv(ItemStack[] inv)
	{
		this.inv = inv;
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction)
	{
		return true;
	}

	@Override
	public boolean isAddedToEnergyNet()
	{
		return this.initnal;
	}

	@Override
	public int demandsEnergy()
	{
		return this.maxEnergy - this.energy;
	}

	@Override
	public int injectEnergy(Direction directionFrom, int amount)
	{
		if (this.energy >= this.maxEnergy)
		{
			return amount;
		}
		this.energy += amount;
		return 0;
	}

	@Override
	public int getMaxSafeInput()
	{
		return 32;
	}

	@Override
	public void onNetworkEvent(int event)
	{

	}

}
