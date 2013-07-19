package kagacraft.api;

public class KagacraftStr
{

	public static String numberFormat(String par1)
	{
		par1 = par1.replace("0", "₀");
		par1 = par1.replace("1", "₁");
		par1 = par1.replace("2", "₂");
		par1 = par1.replace("3", "₃");
		par1 = par1.replace("4", "₄");
		par1 = par1.replace("5", "₅");
		par1 = par1.replace("6", "₆");
		par1 = par1.replace("7", "₇");
		par1 = par1.replace("8", "₈");
		par1 = par1.replace("9", "₉");
		return par1;
	}
	
	public static String firstToUpper(String par)
	{
		par = par.toLowerCase();
		String first = par.substring(0, 1);
		first = first.toUpperCase();
		par = first + par.substring(1);
		return par;
	}
}
