package kagacraft.item;

import kagacraft.Kagacraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPeriodicTable extends Item
{

	public ItemPeriodicTable(int par1)
	{
		super(par1);
		this.setCreativeTab(Kagacraft.tab);
		this.setUnlocalizedName("kagacraft.periodictable");
		this.func_111206_d("kagacraft:periodicTable");
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par2World.isRemote)
		{
			par3EntityPlayer.openGui(Kagacraft.instance, 6, par2World, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ);
		}
		return par1ItemStack;
	}
	
}
