package kagacraft.main;

import kagacraft.block.BlockElectrolysers;
import kagacraft.block.BlockSpiritLamp;
import kagacraft.item.ItemCheckTool;
import kagacraft.item.ItemChemical;
import kagacraft.item.ItemElectrolysers;
import kagacraft.item.ItemElements;
import kagacraft.item.ItemTestTube;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class KagacraftItems
{
	public static Block electrolysers;
	public static Block spiritLamp;

	public static Item testTube;
	public static Item elements;
	public static Item chemicals;
	public static Item checkTool;

	public static void registers()
	{
		electrolysers = new BlockElectrolysers(KagacraftConfig.electrolysersID, Material.rock);
		spiritLamp = new BlockSpiritLamp(KagacraftConfig.spiritLampID, Material.rock);

		testTube = new ItemTestTube(KagacraftConfig.testTubeID - 256);
		elements = new ItemElements(KagacraftConfig.elementsID - 256);
		chemicals = new ItemChemical(KagacraftConfig.chemicalsID - 256);
		checkTool = new ItemCheckTool(KagacraftConfig.checkToolID - 256);

		GameRegistry.registerBlock(electrolysers, ItemElectrolysers.class,"kagacraft.electrolysers");
		GameRegistry.registerBlock(spiritLamp, "kagacraft.spiritLamp");

		GameRegistry.registerItem(testTube, "kagacraft.testtube");
		GameRegistry.registerItem(elements, "kagacraft.elements");
		GameRegistry.registerItem(chemicals, "kagacraft.chemical");
		GameRegistry.registerItem(checkTool, "kagacraft.checktool");

	}
}
