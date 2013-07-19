package kagacraft.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity
{
	public int waitTime;
	private byte direction;
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.waitTime = tagCompound.getInteger("waitTime");
		this.direction = tagCompound.getByte("dir");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("waitTime", this.waitTime);
		tagCompound.setByte("dir", this.direction);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.customParam1);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, var1);
	}
	
	public byte getDirection()
	{
		return this.direction;
	}
	
	public void setDirection(byte par1)
	{
		this.direction = par1;
	}
	
}
