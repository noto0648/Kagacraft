package kagacraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;

public class KagacraftAPI
{

	public static boolean loadKagacraft()
	{
		return Loader.isModLoaded("kagacraft");
	}
	
	public static int hasElement(EntityPlayer par1)
	{
		return 0;
	}
	
	public static Item getKagacraftItem(String name)
	{
		for(int i = 0; i < Item.itemsList.length; i++)
		{
			Item item = Item.itemsList[i];
			if(item.getUnlocalizedName().equalsIgnoreCase(name))
			{
				return item;
			}
		}
		return null;
	}
	
	public static Item getElementItem()
	{
		return getKagacraftItem("kagacraft.elements");
	}
	
	public static Atoms getAtoms(ItemStack par1)
	{
		return Atoms.getAtoms(par1.getItemDamage());
	}
}
