package kagacraft.item;

import java.util.List;

import kagacraft.Kagacraft;
import kagacraft.api.KagacraftStr;
import kagacraft.main.Chemicals;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChemical extends Item
{

	public ItemChemical(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("kagacraft.chemical");
		this.setCreativeTab(Kagacraft.tab);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return Chemicals.chemicalList[par1].icon;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return "Kagacraft.chemical_" + Chemicals.chemicalList[par1ItemStack.getItemDamage()].name;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		for(int i = 0; i < Chemicals.chemicalList.length; i++)
		{
			if(Chemicals.chemicalList[i] != null)
			{
				Chemicals.chemicalList[i].icon = par1IconRegister.registerIcon("kagacraft:" + Chemicals.chemicalList[i].iconName);
			}
		}
	}
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int i = 0; i < Chemicals.chemicalList.length; i++)
		{
			if(Chemicals.chemicalList[i] != null)
			{
				par3List.add(new ItemStack(this.itemID, 1, i));
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(Chemicals.chemicalList[par1ItemStack.getItemDamage()] != null)
		{
			par3List.add(KagacraftStr.numberFormat(Chemicals.chemicalList[par1ItemStack.getItemDamage()].formula));
		}
	}
}
