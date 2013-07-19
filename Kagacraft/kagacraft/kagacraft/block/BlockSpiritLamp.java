package kagacraft.block;

import java.util.Random;

import kagacraft.Kagacraft;
import kagacraft.block.tile.TileEntityBase;
import kagacraft.block.tile.TileEntitySpiritLamp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpiritLamp extends BlockContainer
{

	public BlockSpiritLamp(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(Kagacraft.tab);
		this.setUnlocalizedName("kagacraft.spiritLamp");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		player.openGui(Kagacraft.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}


	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory))
		{
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0)
			{
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound())
				{
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? 0 : 15;
	}

	public static void blockUpdate(Boolean flag, World world, int par1, int par2, int par3)
	{
		if(flag)
		{
			world.setBlockMetadataWithNotify(par1, par2, par3, 1, 2);
		}
		else if(!flag)
		{
			world.setBlockMetadataWithNotify(par1, par2, par3, 0, 2);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if(par1World.getBlockMetadata(par2, par3, par4) == 1)
		{
			TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
			if(tile instanceof TileEntityBase)
			{
				byte dir = ((TileEntityBase)tile).getDirection();
				if(dir == 2)
				{
					par1World.spawnParticle("smoke", par2 +0.55D, par3 + 0.6D, par4 + 0.6D, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", par2 + 0.55D, par3 + 0.6D, par4 + 0.6D, 0.0D, 0.0D, 0.0D);
				}
				if(dir == 3)
				{
					par1World.spawnParticle("smoke", par2 +0.45D, par3 + 0.6D, par4 + 0.45D, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", par2 + 0.45D, par3 + 0.6D, par4 + 0.45D, 0.0D, 0.0D, 0.0D);
				}
				if(dir == 4)
				{
					par1World.spawnParticle("smoke", par2 +0.6D, par3 + 0.6D, par4 + 0.45D, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", par2 + 0.6D, par3 + 0.6D, par4 + 0.45D, 0.0D, 0.0D, 0.0D);
				}
				if(dir == 5)
				{
					par1World.spawnParticle("smoke", par2 +0.4D, par3 + 0.6D, par4 + 0.55D, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("flame", par2 + 0.4D, par3 + 0.6D, par4 + 0.55D, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}


	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		if (!par1World.isRemote && tile instanceof TileEntityBase)
		{
			TileEntityBase te = (TileEntityBase)tile;

			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}
			te.setDirection((byte)b0);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (tile instanceof TileEntityBase)
		{
			TileEntityBase te = (TileEntityBase)tile;
			if (l == 0)
			{
				te.setDirection((byte)2);
			}

			if (l == 1)
			{
				te.setDirection((byte)5);
			}

			if (l == 2)
			{
				te.setDirection((byte)3);
			}

			if (l == 3)
			{
				te.setDirection((byte)4);
			}
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySpiritLamp();
	}

}
