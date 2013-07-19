package kagacraft.block.container;

import kagacraft.block.tile.TileEntitySynthesis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSynthesis extends Container
{
	private TileEntitySynthesis tileEntity;
	
	public ContainerSynthesis(InventoryPlayer inventoryPlayer, TileEntitySynthesis te)
	{
		this.tileEntity = te;
		
		for(int i = 0; i < 7; i++)
		{
			this.addSlotToContainer(new Slot(tileEntity, i, 26 + i * 18, 17));
		}
		this.addSlotToContainer(new Slot(tileEntity, 7, 80, 49));
		this.addSlotToContainer(new Slot(tileEntity, 8, 134, 48));
		bindPlayerInventory(inventoryPlayer);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return tileEntity.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slot_object = (Slot) inventorySlots.get(slot);

		if(slot_object != null && slot_object.getHasStack())
		{
			ItemStack stack_in_slot = slot_object.getStack();
			stack = stack_in_slot.copy();

			if(slot == 0)
			{
				if(!mergeItemStack(stack_in_slot, 1, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if(!mergeItemStack(stack_in_slot, 0, 1, false))
			{
				return null;
			}

			if(stack_in_slot.stackSize == 0)
			{
				slot_object.putStack(null);
			}
			else
			{
				slot_object.onSlotChanged();
			}
		}

		return stack;

	}
}