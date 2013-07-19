package kagacraft.item;

import kagacraft.Kagacraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCheckTool extends Item
{

	public ItemCheckTool(int par1)
	{
		super(par1);
		this.setCreativeTab(Kagacraft.tab);
		this.setUnlocalizedName("kagacraft.checkTool");
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		int blockID = w.getBlockId(x, y, z);
		int meta = w.getBlockMetadata(x, y, z);
		if(w.isRemote)
		{
			if(!is.hasTagCompound())
			{
				is.setTagCompound(new NBTTagCompound());
			}
			is.getTagCompound().setInteger("BID", blockID);
			is.getTagCompound().setInteger("BMETA", meta);
			p.openGui(Kagacraft.instance, 5, w, p.serverPosX, p.serverPosY, p.serverPosZ);
			return true;
		}
		return false;
	}

}
