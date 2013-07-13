package kagacraft.block.tile;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.network.INetworkTileEntityEventListener;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityElectricMachine extends TileEntityBase implements IEnergySink, INetworkTileEntityEventListener
{
	protected int maxEnergy = 200;
	protected int energy = 0;
	protected boolean initnal = false;
	
	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction)
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		if(!initnal)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.initnal = true;
		}
		this.onUpdate();
	}
	
	public void onUpdate() {}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
		if(this.initnal)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.initnal = false;
		}
	}
	
	@Override
	public boolean isAddedToEnergyNet()
	{
		return this.initnal;
	}

	@Override
	public void onNetworkEvent(int event)
	{
		
	}

	@Override
	public int injectEnergy(Direction directionFrom, int amount)
	{
		if (this.energy >= this.maxEnergy)
		{
			return amount;
		}
		this.energy += amount;
		return 0;
	}

	@Override
	public int demandsEnergy()
	{
		return this.maxEnergy - this.energy;
	}
	
	@Override
	public int getMaxSafeInput()
	{
		return 32;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("energy", this.energy);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.energy = tagCompound.getInteger("energy");
	}
	
	public boolean isEnergy()
	{
		return this.energy > 0;
	}
}
