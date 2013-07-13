package kagacraft.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabKagacraft extends CreativeTabs
{

	public CreativeTabKagacraft()
	{
		super("Kagacraft");
	}

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return Item.itemsList[KagacraftItems.testTube.itemID];
    }
}
