package kagacraft.main;

import kagacraft.api.Atoms;
import kagacraft.api.KagacraftRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChemicalFormuras
{
	public ChemicalFormuras()
	{
		registers();
	}
	
	public void registers()
	{
		
		KagacraftRecipes.registerElectrolysersRecipe(new ItemStack(KagacraftItems.elements, 1, Atoms.OXYGEN.id), new ItemStack(KagacraftItems.elements, 2, Atoms.HYDROGEN.id), KagacraftItems.chemicals.itemID, Chemicals.water.id);
		KagacraftRecipes.registerLampRecipe(Block.dirt.blockID, 0, new ItemStack(Item.goldenCarrot));
		
		KagacraftRecipes.registersDecompserRecipe(Item.ingotGold.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.GOLD, 8)});
		KagacraftRecipes.registersDecompserRecipe(Item.ingotIron.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.IRON, 8)});
		KagacraftRecipes.registersDecompserRecipe(Item.coal.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 8)});
		KagacraftRecipes.registersDecompserRecipe(Item.coal.itemID, 1, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 3), Atoms.addStack(Atoms.HYDROGEN, 5)});
		KagacraftRecipes.registersDecompserRecipe(Item.diamond.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 128), Atoms.addStack(Atoms.CARBON, 128), Atoms.addStack(Atoms.CARBON, 64), Atoms.addStack(Atoms.CARBON, 64)});
		KagacraftRecipes.registersDecompserRecipe(Item.emerald.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.BERYLLIUM), Atoms.addStack(Atoms.ALMINIUM, 2)});
		KagacraftRecipes.registersDecompserRecipe(Item.bone.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.CALCIUM, 8), Atoms.addStack(Atoms.IRON)});
		KagacraftRecipes.registersDecompserRecipe(Item.redstone.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.copperOxide, 2)});
		KagacraftRecipes.registersDecompserRecipe(Block.wood.blockID, 0, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 2), Chemicals.createStack(Chemicals.water, 5), Atoms.addStack(Atoms.OXYGEN)});
		KagacraftRecipes.registersDecompserRecipe(Block.wood.blockID, 1, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 1), Chemicals.createStack(Chemicals.water, 6), Atoms.addStack(Atoms.OXYGEN)});
		KagacraftRecipes.registersDecompserRecipe(Block.wood.blockID, 2, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 1), Chemicals.createStack(Chemicals.water, 4), Atoms.addStack(Atoms.OXYGEN, 3)});
		KagacraftRecipes.registersDecompserRecipe(Block.wood.blockID, 3, new ItemStack[] {Atoms.addStack(Atoms.CARBON, 2), Chemicals.createStack(Chemicals.water, 4), Atoms.addStack(Atoms.OXYGEN, 2)});
		KagacraftRecipes.registersDecompserRecipe(Item.wheat.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.starch, 5), Chemicals.createStack(Chemicals.water, 10)});
		KagacraftRecipes.registersDecompserRecipe(Item.potato.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.starch, 12)});
		KagacraftRecipes.registersDecompserRecipe(Item.dyePowder.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.water, 2), Atoms.addStack(Atoms.CARBON, 3)});
		KagacraftRecipes.registersDecompserRecipe(Item.snowball.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.water)});
		KagacraftRecipes.registersDecompserRecipe(Block.dirt.blockID, 0, new ItemStack[] {Atoms.addStack(Atoms.SILICON, 2), Atoms.addStack(Atoms.OXYGEN, 2)});
		KagacraftRecipes.registersDecompserRecipe(Block.cobblestone.blockID, 0, new ItemStack[] {Atoms.addStack(Atoms.SILICON, 4), Atoms.addStack(Atoms.OXYGEN, 2)});
		KagacraftRecipes.registersDecompserRecipe(Block.stone.blockID, 0, new ItemStack[] {Atoms.addStack(Atoms.SILICON, 4), Atoms.addStack(Atoms.OXYGEN)});
		KagacraftRecipes.registersDecompserRecipe(Block.sand.blockID, 0, new ItemStack[] {Atoms.addStack(Atoms.SILICON, 5), Atoms.addStack(Atoms.OXYGEN, 2)});
		KagacraftRecipes.registersDecompserRecipe(Item.clay.itemID, 0, new ItemStack[] {Atoms.addStack(Atoms.ALMINIUM), Atoms.addStack(Atoms.SILICON, 2), Atoms.addStack(Atoms.OXYGEN, 2), Atoms.addStack(Atoms.CALCIUM)});
		KagacraftRecipes.registersDecompserRecipe(Item.melon.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.water, 2), Atoms.addStack(Atoms.POTASSIUM)});
		KagacraftRecipes.registersDecompserRecipe(Item.feather.itemID, 0, new ItemStack[] {Chemicals.createStack(Chemicals.water, 8), Atoms.addStack(Atoms.NITROGEN, 3)});
		
	}
}
