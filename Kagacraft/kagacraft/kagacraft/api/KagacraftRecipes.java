package kagacraft.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class KagacraftRecipes
{
	private static HashMap<List<Integer>, List<ItemStack>> electrolysersRecipe = new HashMap<List<Integer>, List<ItemStack>>();
	private static HashMap<List<Integer>, List<Integer>> electrolysersStacks = new HashMap<List<Integer>, List<Integer>>();
	private static HashMap<List<Integer>, ItemStack> lampRecipe = new HashMap<List<Integer>, ItemStack>();
	private static HashMap<List<Integer>, List<ItemStack>> decompserRecipe = new HashMap<List<Integer>, List<ItemStack>>();
	private static HashMap<List<Integer>, List<Integer>> decompserStacks= new HashMap<List<Integer>, List<Integer>>();
	
	private static KagacraftRecipes instance;
	
	public KagacraftRecipes()
	{
		instance = this;
	}
	
	public static KagacraftRecipes getInstance()
	{
		return instance;
	}

	public static void registerElectrolysersRecipe(ItemStack out1, ItemStack out2, int itemID, int metadata)
	{
		if(out1 != null && out2 != null)
		{
			electrolysersRecipe.put(Arrays.asList(itemID, metadata), Arrays.asList(out1, out2));
			electrolysersStacks.put(Arrays.asList(itemID, metadata), Arrays.asList(out1.stackSize, out2.stackSize));
		}
	}

	public static void registerLampRecipe(int itemID, int metadata, ItemStack out)
	{
		if(out != null)
		{
			lampRecipe.put(Arrays.asList(itemID, metadata), out);
		}
	}
	
	public static void registersDecompserRecipe(int itemID, int metadata, ItemStack[] stack)
	{
		if(stack != null)
		{
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < stack.length; i++)
			{
				list.add(stack[i].stackSize);
			}
			decompserRecipe.put(Arrays.asList(itemID, metadata), Arrays.asList(stack));
			decompserStacks.put(Arrays.asList(itemID, metadata), list);
		}
	}
	
	public static HashMap<List<Integer>, List<ItemStack>> getElectrolysersRecipe()
	{
		return (HashMap<List<Integer>, List<ItemStack>>)electrolysersRecipe.clone();
	}

	public static HashMap<List<Integer>, List<Integer>> getElectrolysersStacks()
	{
		return (HashMap<List<Integer>, List<Integer>>)electrolysersStacks.clone();
	}

	public static HashMap<List<Integer>, ItemStack> getLampRecipe()
	{
		return (HashMap<List<Integer>, ItemStack>)lampRecipe.clone();
	}

	public static HashMap<List<Integer>, List<ItemStack>> getDecompserRecipe()
	{
		return (HashMap<List<Integer>, List<ItemStack>>)decompserRecipe.clone();
	}
	
	public static HashMap<List<Integer>, List<Integer>> getDecompserStacks()
	{
		return (HashMap<List<Integer>, List<Integer>>)decompserStacks.clone();
	}
}
