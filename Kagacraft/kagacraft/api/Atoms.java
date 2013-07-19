package kagacraft.api;

import kagacraft.main.KagacraftItems;
import net.minecraft.item.ItemStack;

public enum Atoms
{
	HYDROGEN(0, "H", 1, 1, EnumStates.Gas, EnumTypes.nonmetal, 1),
	HELIUM(1, "He", 18, 1, EnumStates.Gas, EnumTypes.rareGas, 4),
	LITHIUM(2, "Li", 1, 2, EnumStates.Solid, EnumTypes.alkaliMetals, 7),
	BERYLLIUM(3, "Be", 2, 2, EnumStates.Solid, EnumTypes.alkalineEarthMetal, 9),
	BORON(4, "B", 13, 2, EnumStates.Solid, EnumTypes.metalloid, 11),
	CARBON(5, "C", 14, 2, EnumStates.Solid, EnumTypes.nonmetal, 12),
	NITROGEN(6, "N", 15, 2, EnumStates.Gas, EnumTypes.nonmetal, 14),
	OXYGEN(7, "O", 16, 2, EnumStates.Gas, EnumTypes.nonmetal, 16),
	FLUORINE(8, "F", 17, 2, EnumStates.Gas, EnumTypes.halogen, 19),
	NEON(9, "Ne", 18, 2, EnumStates.Gas, EnumTypes.rareGas, 20),
	SODIUM(10, "Na", 1, 3, EnumStates.Solid, EnumTypes.alkaliMetals, 23),
	MAGNESIUM(11, "Mg", 2, 3, EnumStates.Solid, EnumTypes.alkalineEarthMetal, 24),
	ALMINIUM(12, "Al", 13, 3, EnumStates.Solid, EnumTypes.baseMetal, 27),
	SILICON(13, "Si", 14, 3, EnumStates.Solid, EnumTypes.metalloid, 28),
	PHOSPHORUS(14, "P", 15, 3, EnumStates.Solid, EnumTypes.nonmetal, 31),
	SULFUR(15, "S", 16, 3, EnumStates.Solid, EnumTypes.nonmetal, 32),
	CHLORINE(16, "Cl", 17, 3, EnumStates.Gas, EnumTypes.halogen, 35),
	ARGON(17, "Ar", 18, 3, EnumStates.Gas, EnumTypes.rareGas, 40),
	POTASSIUM(18, "K", 1, 4, EnumStates.Solid, EnumTypes.alkaliMetals, 39),
	CALCIUM(19, "Ca", 2, 4, EnumStates.Solid, EnumTypes.alkalineEarthMetal, 40),
	SCANDIUM(20, "Sc", 3, 4, EnumStates.Solid, EnumTypes.transitionElements, 45),
	TITANIUM(21, "Ti", 4, 4, EnumStates.Solid, EnumTypes.transitionElements,48),
	VANADIUM(22, "V", 5, 4, EnumStates.Solid, EnumTypes.transitionElements, 51),
	CHROMIUM(23, "Cr", 6, 4, EnumStates.Solid, EnumTypes.transitionElements, 52),
	MANGANESE(24, "Mn", 7, 4, EnumStates.Solid, EnumTypes.transitionElements, 55),
	IRON(25, "Fe", 8, 4, EnumStates.Solid, EnumTypes.transitionElements, 56),
	COBALT(26, "Co", 9, 4, EnumStates.Solid, EnumTypes.transitionElements, 59),
	NICKEL(27, "Ni", 10, 4, EnumStates.Solid, EnumTypes.transitionElements, 59),
	COPPER(28, "Cu", 11, 4, EnumStates.Solid, EnumTypes.transitionElements, 64),
	ZINC(29, "Zn", 12, 4, EnumStates.Solid, EnumTypes.transitionElements, 65),
	GALLIUM(30, "Ga", 13, 4, EnumStates.Solid, EnumTypes.baseMetal, 70),
	GERMANIUM(31, "Ge", 14, 4, EnumStates.Solid, EnumTypes.metalloid, 73),
	ARSENIC(32, "As", 15, 4, EnumStates.Solid, EnumTypes.metalloid, 75),
	SELENIUM(33, "Se", 16, 4, EnumStates.Solid, EnumTypes.nonmetal, 79),
	BROMINE(34, "Br", 17, 4, EnumStates.Solid, EnumTypes.halogen, 80),
	KRYPTON(35, "Kr", 18, 4, EnumStates.Gas, EnumTypes.rareGas, 84),
	RUBIDIUM(36, "Rb", 1, 5, EnumStates.Solid, EnumTypes.alkaliMetals, 85),
	STRONTIUM(37, "Sr", 2, 5, EnumStates.Solid, EnumTypes.alkalineEarthMetal, 88),
	YTTORIUM(38, "Y", 3, 5, EnumStates.Solid, EnumTypes.transitionElements, 89),
	ZIRCONIUM(39, "Zr", 4, 5, EnumStates.Solid, EnumTypes.transitionElements, 91),
	NIOBIUM(40, "Nb", 5, 5, EnumStates.Solid, EnumTypes.transitionElements, 93),
	MOLYBDENUM(41, "Mo", 6, 5, EnumStates.Solid, EnumTypes.transitionElements, 96),
	TECHNETIUM(42, "Tc", 7, 5, EnumStates.Solid, EnumTypes.transitionElements, 99),
	RUTHENIUM(43, "Ru", 8, 5, EnumStates.Solid, EnumTypes.transitionElements, 101),
	RHODIUM(44, "Rh", 9, 5, EnumStates.Solid, EnumTypes.transitionElements, 103),
	PALLADIUM(45, "Pd", 10, 5, EnumStates.Solid, EnumTypes.transitionElements, 106),
	SILVER(46, "Ag", 11, 5, EnumStates.Solid, EnumTypes.transitionElements, 108),
	CADMIUM(47, "Cd", 12, 5, EnumStates.Solid, EnumTypes.transitionElements, 112),
	INDIUM(48, "In", 13, 5, EnumStates.Solid, EnumTypes.baseMetal, 115),
	TIN(49, "スズ", 14, 5, EnumStates.Solid, EnumTypes.baseMetal, 119),
	ANTIMONY(50, "Sb", 15, 5, EnumStates.Solid, EnumTypes.metalloid, 122),
	TELLURIUM(51, "Te", 16, 5, EnumStates.Solid, EnumTypes.metalloid, 128),
	IODINE(52, "I", 17, 5, EnumStates.Solid, EnumTypes.halogen, 127),
	XENON(53, "Xe", 18, 5, EnumStates.Gas, EnumTypes.rareGas, 131),
	CAESIUM(54, "Cs", 1, 6, EnumStates.Gas, EnumTypes.alkaliMetals, 133),
	BARIUM(55, "Ba", 2, 6, EnumStates.Liquid, EnumTypes.alkalineEarthMetal, 137),
	
	
	LANTHANUM(56, "La", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 139),
	CERIUM(57, "Ce", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 140),
	PRASEODYMIUM(58, "Pr", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 141),
	NEODYMIUM(59, "Nd", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 144),
	PROMETHIUM(60, "Pm", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 145),
	SAMARIUM(61, "Sm", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 150),
	EUROPIUM(62, "Eu", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 152),
	GADOLINIUM(63, "Gd", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 157),
	TERBIUM(64, "Tb", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 159),
	DYSPROSIUM(65, "Dy", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 163),
	HOLMIUM(66, "Ho", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 165),
	ERBIUM(67, "Er", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 167),
	THULIUM(68, "Tm", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 169),
	YTTERBIUM(69, "Tb", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 173),
	LUTETIUM(70, "Lu", 3, 6, EnumStates.Gas, EnumTypes.lanthanoid, 175),
	
	HAFNIUM(71, "Hf", 4, 6, EnumStates.Solid, EnumTypes.transitionElements, 178),
	TANTALUM(72, "Ta", 5, 6, EnumStates.Solid, EnumTypes.transitionElements, 181),
	TUNGSTEN(73, "W", 6, 6, EnumStates.Solid, EnumTypes.transitionElements, 184),
	RHENIUM(74, "Re", 7, 6, EnumStates.Solid, EnumTypes.transitionElements, 186),
	OSMIUM(75, "Os", 8, 6, EnumStates.Solid, EnumTypes.transitionElements, 190),
	IRIDIUM(76, "Ir", 9, 6, EnumStates.Solid, EnumTypes.transitionElements, 192),
	PLATINUM(77, "Pt", 10, 6, EnumStates.Solid, EnumTypes.transitionElements, 195),
	GOLD(78, "Au", 11, 6, EnumStates.Solid, EnumTypes.transitionElements, 197),
	MERCURY(79, "Hg", 12, 6, EnumStates.Liquid, EnumTypes.transitionElements, 201),
	THALLIUM(80, "Ti", 13, 6, EnumStates.Solid, EnumTypes.baseMetal, 204),
	LEAD(81, "Pb", 14, 6, EnumStates.Solid, EnumTypes.baseMetal, 207),
	BISMUTH(82, "Bi", 15, 6, EnumStates.Solid, EnumTypes.baseMetal, 209),
	POLONIUM(83, "Po", 16, 6, EnumStates.Solid, EnumTypes.metalloid, 209),
	ASTATINE(84, "At", 17, 6, EnumStates.Solid, EnumTypes.halogen, 210),
	RADON(85, "Rn", 18, 6, EnumStates.Gas, EnumTypes.rareGas, 222),
	FRANCIUM(86, "Fr", 1, 7, EnumStates.Solid, EnumTypes.alkaliMetals, 223),
	RADIUM(87, "Ra", 2, 7, EnumStates.Solid, EnumTypes.alkalineEarthMetal, 226),
	
	ACTINIUM(88, "Ac", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 227),
	THORIUM(89, "Th", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 232),
	PROTACTINIUM(90, "Pa", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 231),
	URANIUM(91, "U", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 238),
	NEPTUNIUM(92, "Np", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 237),
	PLUTONIUM(93, "Pu", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 244),
	AMERICIUM(94, "Am", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 243),
	CURIUM(95, "Cu", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 247),
	BERKELIUM(96, "Bk", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 247),
	CALIFORNIUM(97, "Cf", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 251),
	EINSTEINIUM(98, "Es", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 252),
	FERMIUM(99, "Fm", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 257),
	MENDILEVIUM(100, "Md", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 258),
	NOBELIUM(101, "No", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 259),
	LAWRENCIUM(102, "Lr", 3, 7, EnumStates.Solid, EnumTypes.Actinoid, 262),
	
	RUTHERFORDIUM(103, "Rf", 4, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 267),
	DUBNIUM(104, "Db", 5, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 268),
	SEABORGIUM(105, "Sg", 6, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 271),
	BOHRIUM(106, "Bh", 7, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 272),
	HASSIUM(107, "Hs", 8, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 270),
	MEITNERIUM(108, "Mt", 9, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 276),
	DARMSTADTIUM(109, "Ds", 10, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 281),
	ROENTGENIUM(110, "Rg", 11, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 280),
	COPERNICIUM(111, "Cn", 12, 7, EnumStates.UnKnown, EnumTypes.transitionElements, 285),
	UNUNTRIUM(112, "Uut", 13, 7, EnumStates.UnKnown, EnumTypes.baseMetal, 284),
	FLEROVIUM(113, "Fl", 14, 7, EnumStates.UnKnown, EnumTypes.baseMetal, 289),
	UNUNPENTIUM(114, "Uup", 15, 7, EnumStates.UnKnown, EnumTypes.baseMetal, 288),
	LIVERMORIUM(115, "Lv", 16, 7, EnumStates.UnKnown, EnumTypes.baseMetal, 293),
	UNUNSEPTIUM(116, "Uus", 17, 17, EnumStates.UnKnown, EnumTypes.halogen, 294),
	UNUNOCTIUM(117, "Uuo", 18, 17, EnumStates.UnKnown, EnumTypes.rareGas, 294);
	
	public static Atoms[] elementsList = new Atoms[200];
	public static Atoms[] ATOMS = new Atoms[180];
	public int id;
	public String sign;
	public int x;
	public int y;
	public EnumStates states;
	public EnumTypes type;
	public int mass;

	private Atoms(int _id, String _sign, int _x, int _y, EnumStates _states, EnumTypes _type, int _mass)
	{
		this.id = _id;
		this.sign = _sign;
		this.x = _x;
		this.y = _y;
		this.states = _states;
		this.type = _type;
		this.mass = _mass;
	}

	public int getElementNumber(Atoms atoms)
	{
		return atoms.id + 1;
	}

	public static String getSign(Atoms atoms)
	{
		return elementsList[atoms.id].sign;
	}
	
	public static String getSign(int num)
	{
		return elementsList[num].sign;
	}
	
	public static ItemStack addStack(String num)
	{
		return addStack(num, 1);
	}
	
	public static ItemStack addStack(String num, int size)
	{
		for(int i = 0;  i < elementsList.length; i++)
		{
			Atoms element = elementsList[i];
			if(element.sign.equalsIgnoreCase(num))
			{
				return addStack(element, size);
			}
		}
		return null;
	}
	
	public static ItemStack addStack(int num)
	{
		return addStack(num, 1);
	}
	
	public static ItemStack addStack(int num, int size)
	{
		return new ItemStack(KagacraftItems.elements, size, num);
	}
	
	public static ItemStack addStack(Atoms num)
	{
		return addStack(num, 1);
	}

	public static ItemStack addStack(Atoms num, int size)
	{
		return addStack(num.id, size);
	}
	
	public static String getElementName(Atoms num)
	{
		return KagacraftStr.firstToUpper(num.name());
	}
	
	public static Atoms getAtoms(int num)
	{
		return elementsList[num];
	}
	
	static
	{
		elementsList = values();
		ATOMS = values();
	}
}
