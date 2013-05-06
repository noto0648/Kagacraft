package net.minecraft.src;

import net.minecraft.src.dobu.BlockDobu;
import net.minecraft.src.dobu.ItemDobu;
import net.minecraft.src.dobu.ModelDobu;
import net.minecraft.src.dobu.TileEntityDobu;
import net.minecraft.src.dobu.TileEntityDobuRender;

import org.lwjgl.opengl.GL11;

public class mod_DobuBlock extends BaseMod
{

	public static Block dobu;
	public static ItemBlock itemDobu;

	@MLProp
	public static int dobuID = 247;


	public static int renderType;
	private ModelDobu model = new ModelDobu();

	public String getVersion()
	{
		return "1.5.1";
	}

	public String toString()
	{
		return "Dobu";
	}
	
	public void load()
	{
		renderType = ModLoader.getUniqueBlockModelID(this, true);
		dobu = new BlockDobu(dobuID, Material.rock).getIndirectPowerOutput("NotoMod.dobu").setHardness(0.8f);
		ModLoader.registerBlock(dobu, ItemDobu.class);
		ModLoader.registerTileEntity(TileEntityDobu.class, "tile.dobu", new TileEntityDobuRender());
		ModLoader.addName(new ItemStack(dobu, 1, 0), "Dobu Normal");
		ModLoader.addName(new ItemStack(dobu, 1, 2), "Dobu White");
		ModLoader.addName(new ItemStack(dobu, 1, 4), "Dobu Mesh");
		ModLoader.addName(new ItemStack(dobu, 1, 0), "ja_JP", "石のどぶ");
		ModLoader.addName(new ItemStack(dobu, 1, 2), "ja_JP", "白めのどぶ");
		ModLoader.addName(new ItemStack(dobu, 1, 4), "ja_JP", "金網のどぶ");
		
		ModLoader.addRecipe(new ItemStack(dobu, 12, 0), new Object[]{"XXX", "C C", Character.valueOf('X'), Block.stone, Character.valueOf('C'), new ItemStack(Block.stoneSingleSlab, 1, 0)});
		ModLoader.addRecipe(new ItemStack(dobu, 12, 2), new Object[]{"XXX", "C C", Character.valueOf('X'), new ItemStack(Block.stoneSingleSlab, 1, 0), Character.valueOf('C'), new ItemStack(Block.stoneSingleSlab, 1, 0)});
		ModLoader.addRecipe(new ItemStack(dobu, 12, 4), new Object[]{"XXX", "C C", Character.valueOf('X'), Block.fenceIron, Character.valueOf('C'), new ItemStack(Block.stoneSingleSlab, 1, 0)});
	}

	public void renderInvBlock(RenderBlocks var1, Block var2, int var3, int var4) 
	{
		if(var4 != renderType)
		{
			return;
		}
		else
		{
			RenderEngine re = ModLoader.getMinecraftInstance().renderEngine;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.625f, 1.2f, 0.5f);
			GL11.glRotatef(180, 0f, 0f, 1f);

			if(var2 instanceof BlockDobu)
			{
					if(var3 == 2 || var3 == 3)
					{
						re.bindTexture("/noto/Dobu_Whiting.png");
					}
					else if(var3 == 4 || var3 == 5)
					{
						re.bindTexture("/noto/Dobu_Mesh.png");
					}
					else
					{
						re.bindTexture("/noto/Dobu.png");
					}
					model.modelRender(0.065f);
			}
			GL11.glPopMatrix();
		}
	}
}
