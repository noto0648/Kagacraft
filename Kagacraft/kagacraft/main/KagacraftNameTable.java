package kagacraft.main;

import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class KagacraftNameTable 
{

	public static void registers()
	{
		addName(Atoms.addStack(Atoms.HYDROGEN), "Hydrogen");
		addName(Atoms.addStack(Atoms.HELIUM), "Helium");
		addName(Atoms.addStack(Atoms.LITHIUM), "Lithium");
		addName(Atoms.addStack(Atoms.BERYLLIUM), "Beryllium");
		addName(Atoms.addStack(Atoms.BORON), "Boron");
		addName(Atoms.addStack(Atoms.CARBON), "Carbon");
		addName(Atoms.addStack(Atoms.NITROGEN), "Nitrogen");
		addName(Atoms.addStack(Atoms.OXYGEN), "Oxygen");
		addName(Atoms.addStack(Atoms.FLUORINE), "Fluorine");
		addName(Atoms.addStack(Atoms.NEON), "Neon");
		addName(Atoms.addStack(Atoms.SODIUM), "Sodium");
		addName(Atoms.addStack(Atoms.MAGNESIUM), "Magnesium");
		addName(Atoms.addStack(Atoms.ALMINIUM), "Alminium");
		addName(Atoms.addStack(Atoms.SILICON), "Silicon");
		addName(Atoms.addStack(Atoms.PHOSPHORUS), "Phosphorus");
		addName(Atoms.addStack(Atoms.SULFUR), "Sulfur");
		addName(Atoms.addStack(Atoms.CHLORINE), "Chlorine");
		addName(Atoms.addStack(Atoms.ARGON), "Argon");
		addName(Atoms.addStack(Atoms.POTASSIUM), "Potassium");
		addName(Atoms.addStack(Atoms.CALCIUM), "Calcium");
		addName(Atoms.addStack(Atoms.SCANDIUM), "Scandium");
		addName(Atoms.addStack(Atoms.TITANIUM), "Titanium");
		addName(Atoms.addStack(Atoms.VANADIUM), "Vanadium");
		addName(Atoms.addStack(Atoms.CHROMIUM), "Chromium");
		addName(Atoms.addStack(Atoms.MANGANESE), "Managanese");
		addName(Atoms.addStack(Atoms.IRON), "Iron");
		addName(Atoms.addStack(Atoms.COBALT), "Cobalt");
		addName(Atoms.addStack(Atoms.NICKEL), "Nickel");
		addName(Atoms.addStack(Atoms.COPPER), "Copper");
		addName(Atoms.addStack(Atoms.ZINC), "Zinc");
		addName(Atoms.addStack(Atoms.GALLIUM), "Gallium");
		addName(Atoms.addStack(Atoms.GERMANIUM), "Garmanium");
		addName(Atoms.addStack(Atoms.ARSENIC), "Arsenic");
		addName(Atoms.addStack(Atoms.SELENIUM), "Selemium");
		addName(Atoms.addStack(Atoms.BROMINE), "Bromine");
		addName(Atoms.addStack(Atoms.KRYPTON), "Krypton");
		addName(Atoms.addStack(Atoms.RUBIDIUM), "Rubidium");
		addName(Atoms.addStack(Atoms.STRONTIUM), "Strontium");
		addName(Atoms.addStack(Atoms.YTTORIUM), "Yttorium");
		addName(Atoms.addStack(Atoms.ZIRCONIUM), "Zirconium");
		addName(Atoms.addStack(Atoms.NIOBIUM), "Niobium");
		addName(Atoms.addStack(Atoms.MOLYBDENUM), "Morybdenum");
		addName(Atoms.addStack(Atoms.TECHNETIUM), "Technetium");
		addName(Atoms.addStack(Atoms.RUTHENIUM), "Ruthenium");
		addName(Atoms.addStack(Atoms.RHODIUM), "Rhodium");
		addName(Atoms.addStack(Atoms.PALLADIUM), "Palladium");
		addName(Atoms.addStack(Atoms.SILVER), "Silver");
		addName(Atoms.addStack(Atoms.CADMIUM), "Cadmium");
		addName(Atoms.addStack(Atoms.INDIUM), "Indium");
		addName(Atoms.addStack(Atoms.TIN), "Tin");
		addName(Atoms.addStack(Atoms.ANTIMONY), "Antimony");
		addName(Atoms.addStack(Atoms.TELLURIUM), "Tellurium");
		addName(Atoms.addStack(Atoms.IODINE), "Iodine");
		addName(Atoms.addStack(Atoms.XENON), "Xenon");
		addName(Atoms.addStack(Atoms.CAESIUM), "Caesium");
		addName(Atoms.addStack(Atoms.BARIUM), "Barium");
		addName(Atoms.addStack(Atoms.LANTHANUM), "Lanthanum");
		addName(Atoms.addStack(Atoms.CERIUM), "Cerium");
		addName(Atoms.addStack(Atoms.PRASEODYMIUM), "Praseodymium");
		addName(Atoms.addStack(Atoms.NEODYMIUM), "Neodymium");
		addName(Atoms.addStack(Atoms.PROMETHIUM), "Promethium");
		addName(Atoms.addStack(Atoms.SAMARIUM), "Samarium");
		addName(Atoms.addStack(Atoms.EUROPIUM), "Europium");
		addName(Atoms.addStack(Atoms.GADOLINIUM), "Gadorinium");
		addName(Atoms.addStack(Atoms.TERBIUM), "Terbium");
		addName(Atoms.addStack(Atoms.DYSPROSIUM), "Dysprosium");
		addName(Atoms.addStack(Atoms.HOLMIUM), "Holimium");
		addName(Atoms.addStack(Atoms.ERBIUM), "Erbium");
		addName(Atoms.addStack(Atoms.THULIUM), "Thulium");
		addName(Atoms.addStack(Atoms.YTTERBIUM), "Ytterbium");
		addName(Atoms.addStack(Atoms.LUTETIUM), "Lutetium");
		addName(Atoms.addStack(Atoms.HAFNIUM), "Hafnium");
		addName(Atoms.addStack(Atoms.TANTALUM), "Tantalum");
		addName(Atoms.addStack(Atoms.TUNGSTEN), "Tungsten");
		addName(Atoms.addStack(Atoms.RHENIUM), "Rhenium");
		addName(Atoms.addStack(Atoms.OSMIUM), "Osmium");
		addName(Atoms.addStack(Atoms.IRIDIUM), "Iridium");
		addName(Atoms.addStack(Atoms.PLATINUM), "Platinum");
		addName(Atoms.addStack(Atoms.GOLD), "Gold");
		addName(Atoms.addStack(Atoms.MERCURY), "Mercury");
		addName(Atoms.addStack(Atoms.THALLIUM), "Thallium");
		addName(Atoms.addStack(Atoms.LEAD), "Lead");
		addName(Atoms.addStack(Atoms.BISMUTH), "Bismuth");
		addName(Atoms.addStack(Atoms.POLONIUM), "Polonium");
		addName(Atoms.addStack(Atoms.ASTATINE), "Astatine");
		addName(Atoms.addStack(Atoms.RADON), "Radon");
		addName(Atoms.addStack(Atoms.FRANCIUM), "Francium");
		addName(Atoms.addStack(Atoms.RADIUM), "Radium");
		addName(Atoms.addStack(Atoms.ACTINIUM), "Actinium");
		addName(Atoms.addStack(Atoms.THORIUM), "Thorium");
		addName(Atoms.addStack(Atoms.PROTACTINIUM), "Protactinium");
		addName(Atoms.addStack(Atoms.URANIUM), "Uranium");
		addName(Atoms.addStack(Atoms.NEPTUNIUM), "Neptunium");
		addName(Atoms.addStack(Atoms.PLUTONIUM), "Plutonium");
		addName(Atoms.addStack(Atoms.AMERICIUM), "Americium");
		addName(Atoms.addStack(Atoms.CURIUM), "Curium");
		addName(Atoms.addStack(Atoms.BERKELIUM), "Berkelium");
		addName(Atoms.addStack(Atoms.CALIFORNIUM), "Californium");
		addName(Atoms.addStack(Atoms.EINSTEINIUM), "Einsteinium");
		addName(Atoms.addStack(Atoms.FERMIUM), "Fermium");
		addName(Atoms.addStack(Atoms.MENDILEVIUM), "Mendilevium");
		addName(Atoms.addStack(Atoms.NOBELIUM), "Nobelium");
		addName(Atoms.addStack(Atoms.LAWRENCIUM), "Lawrencium");
		addName(Atoms.addStack(Atoms.RUTHERFORDIUM), "Rutherfordium");
		addName(Atoms.addStack(Atoms.DUBNIUM), "Dubnium");
		addName(Atoms.addStack(Atoms.SEABORGIUM), "Seaborgium");
		addName(Atoms.addStack(Atoms.BOHRIUM), "Bohrium");
		addName(Atoms.addStack(Atoms.HASSIUM), "Hassium");
		addName(Atoms.addStack(Atoms.MEITNERIUM), "Meitnerium");
		addName(Atoms.addStack(Atoms.DARMSTADTIUM), "Darmstadtium");
		addName(Atoms.addStack(Atoms.ROENTGENIUM), "Roentgenium");
		addName(Atoms.addStack(Atoms.COPERNICIUM), "Copernicium");
		addName(Atoms.addStack(Atoms.UNUNTRIUM), "UnunTrium");
		addName(Atoms.addStack(Atoms.FLEROVIUM), "Flerovium");
		addName(Atoms.addStack(Atoms.UNUNPENTIUM), "UnunPentium");
		addName(Atoms.addStack(Atoms.LIVERMORIUM), "Livermorium");
		addName(Atoms.addStack(Atoms.UNUNSEPTIUM), "UnunSeptium");
		addName(Atoms.addStack(Atoms.UNUNOCTIUM), "UnunOctium");
		
		addName(Atoms.addStack(Atoms.HYDROGEN), "ja_JP", "水素");
		addName(Atoms.addStack(Atoms.HELIUM), "ja_JP", "ヘリウム");
		addName(Atoms.addStack(Atoms.LITHIUM), "ja_JP", "リチウム");
		addName(Atoms.addStack(Atoms.BERYLLIUM), "ja_JP", "ベリリウム");
		addName(Atoms.addStack(Atoms.BORON), "ja_JP", "ホウ素");
		addName(Atoms.addStack(Atoms.CARBON), "ja_JP", "炭素");
		addName(Atoms.addStack(Atoms.NITROGEN), "ja_JP", "窒素");
		addName(Atoms.addStack(Atoms.OXYGEN), "ja_JP", "酸素");
		addName(Atoms.addStack(Atoms.FLUORINE), "ja_JP", "フッ素");
		addName(Atoms.addStack(Atoms.NEON), "ja_JP", "ネオン");
		addName(Atoms.addStack(Atoms.SODIUM), "ja_JP", "ナトリウム");
		addName(Atoms.addStack(Atoms.MAGNESIUM), "ja_JP", "マグネシウム");
		addName(Atoms.addStack(Atoms.ALMINIUM), "ja_JP", "アルミニウム");
		addName(Atoms.addStack(Atoms.SILICON), "ja_JP", "ケイ素");
		addName(Atoms.addStack(Atoms.PHOSPHORUS), "ja_JP", "リン");
		addName(Atoms.addStack(Atoms.SULFUR), "ja_JP", "硫黄");
		
		addName(KagacraftItems.testTube, "Test Tube");
		addName(KagacraftItems.spiritLamp, "Spirit Lamp");
		addName(new ItemStack(KagacraftItems.electrolysers, 1, 0), "Electrolysers");
		addName(new ItemStack(KagacraftItems.electrolysers, 1, 1), "Decomposer");
		addName(new ItemStack(KagacraftItems.electrolysers, 1, 2), "Synthesis");
		addName(new ItemStack(KagacraftItems.electrolysers, 1, 3), "Hydrogen Generator");
		
		ModLoader.addLocalization("itemGroup.Kagacraft", "Kagacraft Items");
	}
	
	public static void addName(Object objectToName, String lang, String name)
	{
		LanguageRegistry.instance().addNameForObject(objectToName, lang, name);
	}
	
	public static void addName(Object objectToName, String name)
	{
		LanguageRegistry.instance().addName(objectToName, name);
	}
}
