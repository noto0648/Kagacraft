package kagacraft.block.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public class TileEntitySynthesis extends TileEntityBase implements IInventory
{
	private ItemStack[] inv;
	public int maxWaitTime = 100;

	public TileEntitySynthesis()
	{
		this.inv = new ItemStack[9];
	}

	@Override
	public void updateEntity()
	{
		boolean flag = false;

		if(isWorking())
		{
			if(this.waitTime > 0)
			{
				this.waitTime--;
			}
			if(this.waitTime == 0  && !this.worldObj.isRemote)
			{
				List<ItemStack> stackList = toStackList();
				HashMap<List<Integer>, List<ItemStack>> map = KagacraftRecipes.getDecompserRecipe();
				Object key = getkey(stackList);
				if(key != null)
				{
					List<Integer> _key = (List<Integer>)key;
					ItemStack stack = new ItemStack(_key.get(0), 1, _key.get(1));
					addSlot(stack, 7);
					int stackSize = 0;
					for(int i = 0; i < 7; i++)
					{
						if(this.inv[i] != null)
						{
							stackSize += this.inv[i].stackSize;
						}
					}
					addSlot(new ItemStack(KagacraftItems.testTube, stackSize), 8);
					for(int i = 0; i < 7; i++)
					{
						if(this.inv[i] != null)
						{
							this.inv[i] = null;
						}
					}
					flag = true;
				}
				this.waitTime = 0;
			}
			
			boolean startFlag = false;
			for(int i = 0; i < 7; i++)
			{
				if(this.inv[i] != null)
				{
					startFlag = true;
				}
			}
			if(!startFlag)
			{
				this.waitTime = 0;
			}
		}
		else if(!isWorking())
		{
			boolean startFlag = false;
			for(int i = 0; i < 7; i++)
			{
				if(this.inv[i] != null)
				{
					startFlag = true;
				}
			}
			if(startFlag)
			{
				List<ItemStack> stackList = toStackList();
				HashMap<List<Integer>, List<ItemStack>> map = KagacraftRecipes.getDecompserRecipe();
				List<Integer> key = (List<Integer>)getkey(stackList);
				if(key != null)
				{
					if(this.inv[7] == null)
					{
						this.waitTime = this.maxWaitTime;
					}
					else if(this.inv[7].itemID == key.get(0) && this.inv[7].getItemDamage() == key.get(1))
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

	private List<ItemStack> toStackList()
	{
		List<ItemStack> stackList = new ArrayList<ItemStack>();
		for(int i = 0; i < 7; i++)
		{
			if(this.inv[i] != null)
			{
				stackList.add(ItemStack.copyItemStack(this.inv[i]));
			}
		}
		return stackList;
	}

	private Object getkey(List<ItemStack> stackList)
	{
		HashMap<List<Integer>, List<ItemStack>> map = KagacraftRecipes.getDecompserRecipe();

		Object key = null;
		for (Iterator it = map.keySet().iterator(); it.hasNext();)
		{
			List<Integer> k = (List<Integer>)it.next ();
			Object v = map.get (k);
			if (stackList.toString().equals(v.toString()))
			{
				key = k;
				break;
			}
		}
		return key;
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
		this.waitTime = tagCompound.getInteger("waitTime");
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
		return "kagacraft.sysnthesis";
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
