package net.minecraft.src.dobu;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public class ItemDobu extends ItemBlock
{
	private String[] names = new String[]{"normal", "normal", "white", "white", "mesh", "mesh"};
	private final int blockID;
	public ItemDobu(int par1)
	{
		super(par1);
		this.blockID = par1 + 256;
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return super.getUnlocalizedName() +  names[par1ItemStack.getItemDamage()];
	}

	public int getMetadata(int par1)
	{
		return par1;
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		return false;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		float var4 = 1.0F;
		double var5 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)var4;
		double var7 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)var4 + 1.62D - (double)par3EntityPlayer.yOffset;
		double var9 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)var4;

		MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
		if (var12 == null)
		{
			return par1ItemStack;
		}
		else
		{
			int var13 = var12.blockX;
			int var14 = var12.blockY + 1;
			int var15 = var12.blockZ;
			if(par2World.getBlockId(var13, var14, var15) == 0)
			{
				Block var20 = Block.blocksList[this.blockID];
				int var21 = this.getMetadata(par1ItemStack.getItemDamage());

				if (var12.sideHit == 2)
				{
					--var15;
				}

				if (var12.sideHit == 3)
				{
					++var15;
				}

				if (var12.sideHit == 4)
				{
					--var13;
				}

				if (var12.sideHit == 5)
				{
					++var13;
				}

				if (par2World.setBlock(var13, var14, var15, this.blockID, var21, 3))
				{
					if (par2World.getBlockId(var13, var14, var15) == this.blockID)
					{
						Block.blocksList[this.blockID].onBlockPlacedBy(par2World, var13, var14, var15, par3EntityPlayer, par1ItemStack);
						Block.blocksList[this.blockID].onPostBlockPlaced(par2World, var13, var14, var15, var21);
					}

					par2World.playSoundEffect((double)((float)par3EntityPlayer.posX + 0.5F), (double)((float)par3EntityPlayer.posY + 0.5F), (double)((float)par3EntityPlayer.posZ + 0.5F), var20.stepSound.getPlaceSound(), (var20.stepSound.getVolume() + 1.0F) / 2.0F, var20.stepSound.getPitch() * 0.8F);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}
				}
			}
		}
		return par1ItemStack;
	}

}
