package kagacraft.block;

import java.util.List;
import java.util.Random;

import kagacraft.Kagacraft;
import kagacraft.block.tile.TileEntityBase;
import kagacraft.block.tile.TileEntityDecomposer;
import kagacraft.block.tile.TileEntityElectrolysers;
import kagacraft.block.tile.TileEntityHydrogen;
import kagacraft.block.tile.TileEntitySynthesis;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElectrolysers extends Block
{
	private Icon[] icons = new Icon[8];
	public BlockElectrolysers(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(Kagacraft.tab);
		this.setUnlocalizedName("kagacraft.electrolysers");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		TileEntity tile = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		if(tile instanceof TileEntityBase && metadata == 0)
		{
			TileEntityBase t = (TileEntityBase)tile;
			if(par5 == 0 || par5 == 1)
			{
				return icons[2];
			}
			if(par5 != t.getDirection())
			{
				return icons[1];
			}
			else
			{
				return icons[0];
			}
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons[0] = par1IconRegister.registerIcon("kagacraft:machine_top");
		icons[1] = par1IconRegister.registerIcon("kagacraft:machine_side");
		icons[2] = par1IconRegister.registerIcon("kagacraft:machine_above");
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0)
		{
			player.openGui(Kagacraft.instance, 0, world, x, y, z);
		}
		else if(meta == 1)
		{
			player.openGui(Kagacraft.instance, 2, world, x, y, z);
		}
		else if(meta == 2)
		{
			player.openGui(Kagacraft.instance, 3, world, x, y, z);
		}
		else if(meta == 3)
		{
			player.openGui(Kagacraft.instance, 4, world, x, y, z);
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
		world.removeBlockTileEntity(x, y, z);
	}


	@Override
	public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
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

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
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
	public TileEntity createTileEntity(World world, int metadata)
	{
		if(metadata == 0)
		{
			return new TileEntityElectrolysers();
		}
		if(metadata == 1)
		{
			return new TileEntityDecomposer();
		}
		if(metadata == 2)
		{
			return new TileEntitySynthesis();
		}
		if(metadata == 3)
		{
			return new TileEntityHydrogen();
		}
		return null;
	}

}
