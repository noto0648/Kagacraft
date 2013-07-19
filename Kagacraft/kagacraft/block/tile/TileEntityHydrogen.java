package kagacraft.block.tile;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.network.NetworkHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kagacraft.api.Atoms;
import kagacraft.main.ClientPacketHandler;
import kagacraft.main.KagacraftItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityHydrogen extends TileEntityBase implements IInventory, IEnergySource, INetworkDataProvider, INetworkUpdateListener
{
	private ItemStack[] inv;
	private boolean initnal = false;
	private int storage = 0;
	private int production = 0;
	public int maxStorage = 4000;
	public int maxWaitTime = 80;
	public int hatuden = 16;
	private static List fields = Arrays.asList(new String[0]);

	public TileEntityHydrogen()
	{
		this.inv = new ItemStack[4];
	}

	@Override
	public void updateEntity()
	{
		boolean flag = false;
		if(!this.initnal && this.worldObj != null)
		{
			if(this.worldObj.isRemote)
			{
				NetworkHelper.requestInitialData(this);
			}
			else
			{
				MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
				this.initnal = true;
			}
		}
		
		if(this.storage > 0)
		{
			if(this.storage - 16 > 0)
			{
				this.production = sendEnergy(16);
				this.storage -= 16;
			}
			else
			{
				this.production = sendEnergy(this.storage);
				this.storage = 0;
			}
			PacketDispatcher.sendPacketToServer(ClientPacketHandler.sendPacket(this));
		}
		
		if(isWorking())
		{
			if(production > 0)
			{
				this.storage = this.storage + this.production;
				this.production = 0;
			}
			this.waitTime--;
			if(this.waitTime <= 0 && !this.worldObj.isRemote)
			{
				if(this.inv[0].getItemDamage() == Atoms.HYDROGEN.id && this.inv[1].getItemDamage() == Atoms.OXYGEN.id)
				{
					this.inv[0] = delSlot(this.inv[0]);
					this.inv[1] = delSlot(this.inv[1]);
					addSlot(new ItemStack(KagacraftItems.testTube, 1), 2);
					addSlot(new ItemStack(KagacraftItems.testTube, 1), 3);
					this.production = sendEnergy(this.hatuden);
					
					PacketDispatcher.sendPacketToServer(ClientPacketHandler.sendPacket(this));
					flag = true;
				}
				this.waitTime = 0;
			}
			if(this.inv[0] == null || this.inv[1] == null)
			{
				this.waitTime = 0;
			}
		}
		else if(this.inv[0] != null && this.inv[1] != null && this.inv[0].itemID == KagacraftItems.elements.itemID && this.inv[1].itemID == KagacraftItems.elements.itemID)
		{
			if(this.inv[0].getItemDamage() == Atoms.HYDROGEN.id && this.inv[1].getItemDamage() == Atoms.OXYGEN.id)
			{
				if(this.storage + this.hatuden < this.maxStorage)
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

	public boolean isWorking()
	{
		return this.waitTime > 0;
	}

	private ItemStack delSlot(ItemStack item)
	{
		if(item.stackSize == 1)
		{
			item = null;
		}
		else
		{
			item.stackSize--;
		}
		return item;
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

	public int sendEnergy(int send)
	{
		EnergyTileSourceEvent event = new EnergyTileSourceEvent(this, send);
		MinecraftForge.EVENT_BUS.post(event);
		return event.amount;
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
		this.storage = tagCompound.getInteger("storage");
		this.production = tagCompound.getInteger("production");
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
		tagCompound.setInteger("storage", this.storage);
		tagCompound.setInteger("production", this.production);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, Direction direction)
	{
		return true;
	}

	@Override
	public boolean isAddedToEnergyNet()
	{
		return this.initnal;
	}

	@Override
	public int getMaxEnergyOutput()
	{
		return 10;
	}

	@Override
	public void onNetworkUpdate(String field)
	{

	}

	@Override
	public List<String> getNetworkedFields()
	{
		return fields;
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
		this.inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "kagacraft.hydrogen";
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

	public int getStorage()
	{
		return this.storage;
	}

	public int getProduction()
	{
		return production;
	}

	public void setProduction(int production)
	{
		this.production = production;
	}

	public void setStorage(int storage)
	{
		this.storage = storage;
	}

}
