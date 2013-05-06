package net.minecraft.src.dobu;

import java.util.List;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_DobuBlock;

public class BlockDobu extends BlockContainer
{

	public BlockDobu(int par1, Material par2Material)
	{
		super(par1, par2Material);
		setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.07F, 1.0F);
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
	{
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = par1World.getBlockMetadata(par2, par3, par4);
		if((var6 == 3)||(var6 == 1))
		{
			par1World.setBlock(par2, par3, par4, this.blockID, 0 + var7, 3);
		}
		else
		{
			par1World.setBlock(par2, par3, par4, this.blockID, 1 + var7, 3);
		}
	}

	public int damageDropped(int par1)
	{
		return (par1 % 2) == 0 ? par1 : par1 - 1;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public int getRenderType()
	{
		return mod_DobuBlock.renderType;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityDobu();
	}
	
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 4));
	}
	
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("stone");
	}

}
