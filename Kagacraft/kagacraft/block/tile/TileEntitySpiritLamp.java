package kagacraft.block.tile;

import java.util.Arrays;
import java.util.Random;

import kagacraft.api.KagacraftRecipes;
import kagacraft.block.BlockSpiritLamp;
import kagacraft.main.Chemicals;
import kagacraft.main.KagacraftItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntitySpiritLamp extends TileEntityBase implements IInventory
{
	private ItemStack[] inv;
	public int maxWaitTime = 50;
	public int fuel;
	public int maxFuel = 2000;

	public TileEntitySpiritLamp()
	{
		this.inv = new ItemStack[3];
	}

	@Override
	public void updateEntity()
	{
		boolean flag = false;
		if(this.inv[1] != null)
		{
			if(getFuelTime(ItemStack.copyItemStack(this.inv[1])) != 0 && this.fuel + getFuelTime(ItemStack.copyItemStack(this.inv[1])) < this.maxFuel)
			{
				this.fuel += getFuelTime(ItemStack.copyItemStack(this.inv[1]));
				if(this.inv[1].stackSize == 1)
				{
					this.inv[1] = null;
				}
				else
				{
					this.inv[1].stackSize--;
				}
			}
		}

		if(isWorking())
		{
			this.fuel--;
			if(this.fuel == 0)
			{
				this.waitTime = 0;
			}
			
			this.waitTime--;
			
			if(this.waitTime <= 0 && !this.worldObj.isRemote)
			{
				if(KagacraftRecipes.getLampRecipe().containsKey(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage())))
				{
					ItemStack i = KagacraftRecipes.getLampRecipe().get(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage()));
					addSlot(i, 2);
					
					if(this.inv[0].stackSize == 1)
					{
						this.inv[0] = null;
						BlockSpiritLamp.blockUpdate(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
					}
					else
					{
						this.inv[0].stackSize--;
						
					}
				}
				flag = true;
			}
			if(this.inv[0] == null)
			{
				waitTime = 0;
			}
		}
		else if(this.inv[0] != null && this.fuel > 0)
		{
			if(KagacraftRecipes.getLampRecipe().containsKey(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage())))
			{
				this.waitTime = this.maxWaitTime;
				BlockSpiritLamp.blockUpdate(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if(flag)
		{
			this.onInventoryChanged();
		}
	}

	private boolean addSlot(ItemStack item, int by)
	{
		int stackSize = item.stackSize;
		for(int i = by; i < by + 1; i++)
		{
			if(this.inv[i] == null)
			{
				this.inv[i] = ItemStack.copyItemStack(item);
				return true;
			}
			else if(this.inv[i].itemID == item.itemID && this.inv[i].getItemDamage() == item.getItemDamage())
			{
				int stacks = 64 - this.inv[i].stackSize;
				for(int j = stacks; j > 0; j--)
				{
					stackSize--;
					this.inv[i].stackSize++;
					if(stackSize == 0)
					{
						return true;
					}
					if(this.inv[i].stackSize == 64)
					{
						break;
					}
				}
			}
		}

		Random rand = new Random();

		float rx = rand.nextFloat() * 0.8F + 0.1F;
		float ry = rand.nextFloat() * 0.8F + 0.1F;
		float rz = rand.nextFloat() * 0.8F + 0.1F;

		EntityItem entityItem = new EntityItem(this.worldObj, this.xCoord + rx, this.yCoord + ry, this.zCoord + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

		if (item.hasTagCompound())
		{
			entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
		}

		float factor = 0.05F;
		entityItem.motionX = rand.nextGaussian() * factor;
		entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
		entityItem.motionZ = rand.nextGaussian() * factor;
		this.worldObj.spawnEntityInWorld(entityItem);
		return true;
	}

	public int getFuelTime(ItemStack item)
	{
		if(KagacraftItems.chemicals.itemID == item.itemID && Chemicals.methanol.id == item.getItemDamage())
		{
			return 100;
		}
		if(KagacraftItems.chemicals.itemID == item.itemID && Chemicals.ethanol.id == item.getItemDamage())
		{
			return 100;
		}
		return 0;
	}

	public boolean isWorking()
	{
		return this.waitTime > 0;
	}

	public boolean isTestTube()
	{
		return this.inv[0] != null;
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
		this.fuel = tagCompound.getInteger("fuel");
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
		tagCompound.setInteger("fuel", this.fuel);
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
	public String getInvName()
	{
		return "kagacraft.spiritLamp";
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

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, var1);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.customParam1);
	}
}
