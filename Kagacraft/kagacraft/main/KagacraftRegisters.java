package kagacraft.main;

import kagacraft.Kagacraft;
import kagacraft.block.tile.TileEntityDecomposer;
import kagacraft.block.tile.TileEntityElectrolysers;
import kagacraft.block.tile.TileEntityHydrogen;
import kagacraft.block.tile.TileEntitySpiritLamp;
import kagacraft.block.tile.TileEntitySynthesis;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class KagacraftRegisters
{
	public static void registers(Kagacraft instance)
	{
		new ChemicalFormuras().registers();
		KagacraftNameTable.registers();
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
		
		GameRegistry.registerTileEntity(TileEntityElectrolysers.class, "kagacraft.electrolysers");
		GameRegistry.registerTileEntity(TileEntitySpiritLamp.class, "kagacraft.spiritLamp");
		GameRegistry.registerTileEntity(TileEntityDecomposer.class, "kagacraft.decomposer");
		GameRegistry.registerTileEntity(TileEntitySynthesis.class, "kagacraft.synthesis");
		GameRegistry.registerTileEntity(TileEntityHydrogen.class, "kagacraft.hydrogenGenerator");
		
	}
}
