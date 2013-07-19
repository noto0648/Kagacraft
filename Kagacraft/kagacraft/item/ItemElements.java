package kagacraft.item;

import java.util.List;

import kagacraft.Kagacraft;
import kagacraft.api.Atoms;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElements extends Item
{
	public static Icon[] icons = new Icon[4];
	
	public ItemElements(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setCreativeTab(Kagacraft.tab);
		this.setUnlocalizedName("kagacraft.elements");
		this.func_111206_d("kagacraft:TestTubeE");
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int i = 0; i < Atoms.ATOMS.length; i++)
			par3List.add(new ItemStack(itemID, 1, i));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return "Kagacraft.elements_" + Atoms.getSign(par1ItemStack.getItemDamage());
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons[0] = par1IconRegister.registerIcon("kagacraft:element_solid");
		icons[1] = par1IconRegister.registerIcon("kagacraft:element_liquid");
		icons[2] = par1IconRegister.registerIcon("kagacraft:element_gas1");
		icons[3] = par1IconRegister.registerIcon("kagacraft:element_gas2");
	}
}
