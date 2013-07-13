package kagacraft.main;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Chemicals
{

	public static Chemicals[] chemicalList = new Chemicals[256];
	public static final Chemicals methanol = new Chemicals("Methanol", "ChemicalA", "CH4O").register();
	public static final Chemicals ethanol = new Chemicals("Ethanol", "ChemicalA", "C2H6O").register();
	public static final Chemicals water = new Chemicals("Water", "ChemicalW", "H2O").register();
	public static final Chemicals hydrochloricAcid = new Chemicals("Hydrochloric Acid", "ChemicalB", "HCl").register();
	public static final Chemicals carbonDioxide = new Chemicals("Carbon Dioxide", "ChemicalB", "CO2").register();
	public static final Chemicals copperOxide = new Chemicals("Copper Oxide", "ChemicalC", "Cu2O").register();
	public static final Chemicals ironOxide = new Chemicals("Iron Oxide", "ChemicalC", "Fe3O4").register();
	public static final Chemicals silverOxide = new Chemicals("Silver Oxide", "ChemicalC", "Ag2O").register();
	public static final Chemicals goldOxide = new Chemicals("Gold Oxide", "ChemicalC", "Au2O3").register();
	public static final Chemicals magnesiumOxide = new Chemicals("Magnesium Oxide", "ChemicalC", "Mg2O").register();
	public static final Chemicals starch = new Chemicals("Starch", "ChemicalB", "C6H10O5").register();
	public static final Chemicals acetylene = new Chemicals("Acetylene", "ChemicalB", "C2H2").register();
	public static final Chemicals ammonia = new Chemicals("Ammonia", "ChemicalB", "NH3").register();
	public static final Chemicals carbonMonoxide = new Chemicals("Carbon Monoxide", "ChemicalB", "CO").register();
	public static final Chemicals ethane = new Chemicals("Ethane", "ChemicalB", "C2H5OH").register();
	public static final Chemicals ethylene = new Chemicals("Ethylene", "ChemicalB", "C2H4").register();
	public static final Chemicals ammoniumChloride = new Chemicals("Ammonium Chloride", "ChemicalB", "NH4Cl").register();
	public static final Chemicals calciumChloride = new Chemicals("Calcium Chloride", "ChemicalB", "CaCl2").register();
	public static final Chemicals cobaltChloride = new Chemicals("Cobalt chloride", "ChemicalB", "CoCl2").register();
	public static final Chemicals sodiumChloride = new Chemicals("Sodium chloride", "ChemicalB", "NaCl").register();
	public static final Chemicals hydrogenPeroxide = new Chemicals("Hydrogen Peroxide", "ChemicalB", "H2O2").register();
	public static final Chemicals citricAcid = new Chemicals("Citric Acid", "ChemicalA", "C6H8O7").register();
	public static final Chemicals glucose = new Chemicals("Glucose", "ChemicalC", "C6H12O6").register();
	public static final Chemicals aceticAcid = new Chemicals("Acetic Acid", "ChemicalA", "CH3COOH").register();
	public static final Chemicals nitricAcid = new Chemicals("Nitric Acid", "ChemicalB", "HNO3").register();
	public static final Chemicals saltpetre = new Chemicals("Saltpetre", "ChemicalB", "KNO3").register();
	public static final Chemicals potassiumHydroxide = new Chemicals("Potassium Hydroxide", "ChemicalB", "KOH").register();
	public static final Chemicals sodiumHydroxide = new Chemicals("Sodium Hydroxide", "ChemicalB", "NaOH").register();
	public static final Chemicals fructose = new Chemicals("Fructose", "ChemicalB", "C6H12O6").register();
	public static final Chemicals propane = new Chemicals("Propane", "ChemicalB", "CH3CH2CH3").register();
	
	
	public String name;
	public String iconName;
	public Icon icon = null;
	public int id;
	public String formula;
	
	public Chemicals(String name, String icon, String format)
	{
		this.name = name;
		this.iconName = icon;
		this.formula = format;
	}
	
	public Chemicals register()
	{
		for(int i = 0; i < chemicalList.length; i++)
		{
			if(chemicalList[i] == null)
			{
				chemicalList[i] = this;
				this.id = i;
				break;
			}
		}
		LanguageRegistry.addName(createStack(chemicalList[id]), name);
		return this;
	}
	
	public static ItemStack createStack(Chemicals c)
	{
		return createStack(c, 1);
	}
	
	public static ItemStack createStack(Chemicals c, int stackSize)
	{
		return new ItemStack(KagacraftItems.chemicals, stackSize, c.id);
	}
}
