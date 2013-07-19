package kagacraft.block.tile;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kagacraft.api.KagacraftRecipes;
import kagacraft.main.KagacraftItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDecomposer extends TileEntityElectricMachine implements IInventory
{
	private ItemStack[] inv;
	public int maxWaitTime = 120;

	public TileEntityDecomposer()
	{
		this.inv = new ItemStack[11];
	}

	@Override
	public void onUpdate()
	{
		boolean flag = false;
		if(isWorking())
		{
			if(this.waitTime > 0)
			{
				this.waitTime--;
			}
			this.energy -= 3;
			
			if(this.waitTime == 0 && !this.worldObj.isRemote)
			{
				if(this.inv[0] != null&& KagacraftRecipes.getDecompserRecipe().containsKey(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage())))
				{
					List<ItemStack> stack = KagacraftRecipes.getDecompserRecipe().get(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage()));
					List<Integer> _stack = KagacraftRecipes.getDecompserStacks().get(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage()));
					int youkyuu = 0;
					for(int i = 0; i < _stack.size(); i++)
					{
						youkyuu += _stack.get(i);
					}
					if(this.inv[1] != null && this.inv[1].stackSize >= youkyuu)
					{
						for(int i = 0; i < stack.size(); i++)
						{
							if(i + 2 > 11)
							{
								break;
							}
							if(addSlot(new ItemStack(stack.get(i).itemID, _stack.get(i), stack.get(i).getItemDamage())))
							{
								if(this.inv[1].stackSize - stack.get(i).stackSize == 0)
								{
									this.inv[1] = null;
								}
								else
								{
									this.inv[1].stackSize -= stack.get(i).stackSize;
								}
							}
						}
					}
				}
				if(this.inv[0] == null)
				{
					this.waitTime = 0;
				}
				if(this.inv[0] != null && this.inv[0].stackSize == 1)
				{
					this.inv[0] = null;
				}
				else
				{
					this.inv[0].stackSize--;
				}
				this.waitTime = 0;
				flag = true;
			}
			if(this.inv[0] == null)
			{
				this.waitTime = 0;
			}
			if(this.energy <= 0)
			{
				this.waitTime = 0;
			}
		}
		else if(this.inv[0] != null && this.inv[1] != null && this.inv[1].itemID == KagacraftItems.testTube.itemID && isEnergy())
		{
			if(KagacraftRecipes.getDecompserRecipe().containsKey(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage())))
			{
				List<Integer> _stack = KagacraftRecipes.getDecompserStacks().get(Arrays.asList(this.inv[0].itemID, this.inv[0].getItemDamage()));
				int youkyuu = 0;
				for(int i = 0; i < _stack.size(); i++)
				{
					youkyuu += _stack.get(i);
				}
				if(this.inv[1].stackSize >= youkyuu)
				{
					this.waitTime = this.maxWaitTime;
				}
			}
		}

		if(flag)
		{
			this.onInventoryChanged();
		}
	}

	private boolean addSlot(ItemStack item)
	{
		int stackSize = item.stackSize;
		for(int i = 2; i < this.inv.length; i++)
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


	public boolean isWorking()
	{
		return this.waitTime > 0;
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
	public String getInvName()
	{
		return "kagacraft.decomposer";
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



}
