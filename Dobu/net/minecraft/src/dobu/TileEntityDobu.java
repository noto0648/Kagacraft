package net.minecraft.src.dobu;

import net.minecraft.src.TileEntity;

public class TileEntityDobu extends TileEntity
{
	
	public int getBlockMetadata()
	{
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}
}
