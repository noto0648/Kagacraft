package net.minecraft.src.globe;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_Globe;

public class BlockGlobe extends BlockContainer
{

	public BlockGlobe(int par1)
	{
		super(par1, Material.clay);
		setCreativeTab(CreativeTabs.tabDecorations);
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
		return mod_Globe.globeRenderID;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityGlobe();
	}
	
	

}
