package kagacraft.main;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class KagacraftConfig
{
	public static int electrolysersID = 3520;
	public static int spiritLampID = 3521;

	public static int testTubeID = 29799;
	public static int elementsID = 29800;
	public static int chemicalsID = 29801;
	public static int checkToolID = 29802;

	public static void load(FMLPreInitializationEvent evt)
	{
		Configuration cfg = new Configuration(new File(evt.getModConfigurationDirectory(), "Kagacraft.cfg"));
		electrolysersID = cfg.getBlock("ElectrolysersID", electrolysersID).getInt();
		spiritLampID = cfg.getBlock("SpiritLampID", spiritLampID).getInt();

		testTubeID = cfg.getItem("TestTubeID", testTubeID).getInt();
		elementsID = cfg.getItem("ElementsID", elementsID).getInt();
		chemicalsID = cfg.getItem("ChemicalsID", chemicalsID).getInt();
		checkToolID = cfg.getItem("CheckToolID", checkToolID).getInt();
		cfg.save();
	}
}
