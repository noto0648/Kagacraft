package kagacraft.main;

import kagacraft.block.container.ContainerDecomposer;
import kagacraft.block.container.ContainerElectrolysers;
import kagacraft.block.container.ContainerHydrogen;
import kagacraft.block.container.ContainerSpiritLamp;
import kagacraft.block.container.ContainerSynthesis;
import kagacraft.block.tile.TileEntityDecomposer;
import kagacraft.block.tile.TileEntityElectrolysers;
import kagacraft.block.tile.TileEntityHydrogen;
import kagacraft.block.tile.TileEntitySpiritLamp;
import kagacraft.block.tile.TileEntitySynthesis;
import kagacraft.client.GuiCheckTool;
import kagacraft.client.GuiDecomposer;
import kagacraft.client.GuiElectrolysers;
import kagacraft.client.GuiHydrogen;
import kagacraft.client.GuiSpiritLamp;
import kagacraft.client.GuiSynthesis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == 0 && tileEntity instanceof TileEntityElectrolysers)
		{
			return new ContainerElectrolysers(player.inventory, (TileEntityElectrolysers) tileEntity);
		}
		if(ID == 1 && tileEntity instanceof TileEntitySpiritLamp)
		{
			return new ContainerSpiritLamp(player.inventory, (TileEntitySpiritLamp) tileEntity);
		}
		if(ID == 2 && tileEntity instanceof TileEntityDecomposer)
		{
			return new ContainerDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
		}
		if(ID == 3 && tileEntity instanceof TileEntitySynthesis)
		{
			return new ContainerSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
		}
		if(ID == 4 && tileEntity instanceof TileEntityHydrogen)
		{
			return new ContainerHydrogen(player.inventory, (TileEntityHydrogen) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == 0 && tileEntity instanceof TileEntityElectrolysers)
		{
			return new GuiElectrolysers(player.inventory, (TileEntityElectrolysers) tileEntity);
		}
		if(ID == 1 && tileEntity instanceof TileEntitySpiritLamp)
		{
			return new GuiSpiritLamp(player.inventory, (TileEntitySpiritLamp) tileEntity);
		}
		if(ID == 2 && tileEntity instanceof TileEntityDecomposer)
		{
			return new GuiDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
		}
		if(ID == 3 && tileEntity instanceof TileEntitySynthesis)
		{
			return new GuiSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
		}
		if(ID == 4 && tileEntity instanceof TileEntityHydrogen)
		{
			return new GuiHydrogen(player.inventory, (TileEntityHydrogen) tileEntity);
		}
		if(ID == 5 )
		{
			return new GuiCheckTool(player.getCurrentEquippedItem());
		}
		return null;
	}

}
