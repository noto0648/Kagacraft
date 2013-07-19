package kagacraft.item;

import kagacraft.Kagacraft;
import kagacraft.main.Chemicals;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTestTube extends Item
{
	@SideOnly(Side.CLIENT)
	private Icon field_111220_a;

	public ItemTestTube(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setCreativeTab(Kagacraft.tab);
		this.func_111206_d("kagacraft:TestTube");
		this.setUnlocalizedName("Kagacraft.testTube");
	}
	


	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{

			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return par1ItemStack;
				}

				if (par1ItemStack.getItemDamage() == 0)
				{
					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
					{
						return par1ItemStack;
					}

					if (par2World.getBlockMaterial(i, j, k) == Material.water && par2World.getBlockMetadata(i, j, k) == 0)
					{
						par2World.setBlockToAir(i, j, k);

						if (par3EntityPlayer.capabilities.isCreativeMode)
						{
							if (!par3EntityPlayer.inventory.addItemStackToInventory(Chemicals.createStack(Chemicals.water)))
							{
								par3EntityPlayer.dropPlayerItem(Chemicals.createStack(Chemicals.water));
							}
							return par1ItemStack;
						}

						if (--par1ItemStack.stackSize <= 0)
						{
							return Chemicals.createStack(Chemicals.water);
						}

						if (!par3EntityPlayer.inventory.addItemStackToInventory(Chemicals.createStack(Chemicals.water)))
						{
							par3EntityPlayer.dropPlayerItem(Chemicals.createStack(Chemicals.water));
						}
						return par1ItemStack;
					}
				}
			}
			return par1ItemStack;
		}
	}
}
