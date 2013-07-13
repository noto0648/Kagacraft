package kagacraft.item;

import kagacraft.main.KagacraftItems;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElectrolysers extends ItemBlock
{

	public ItemElectrolysers(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int meta = par1ItemStack.getItemDamage();
		if(meta == 0)
		{
			return "Kagacraft.electrolysers";
		}
		if(meta == 1)
		{
			return "Kagacraft.decomposers";
		}
		if(meta == 2)
		{
			return "Kagacraft.synthesis";
		}
		if(meta == 2)
		{
			return "Kagacraft.hydrogenGenerator";
		}
		return "Kagacraft.block";
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int par1)
	{
		return KagacraftItems.electrolysers.getIcon(2, par1);
	}
}
