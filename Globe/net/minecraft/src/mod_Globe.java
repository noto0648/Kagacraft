package net.minecraft.src;

import net.minecraft.src.globe.BlockGlobe;
import net.minecraft.src.globe.ModelGlobe;
import net.minecraft.src.globe.TileEntityGlobe;
import net.minecraft.src.globe.TileEntityGlobeRender;

import org.lwjgl.opengl.GL11;

public class mod_Globe extends BaseMod
{

	public static Block globeBlock;
	@MLProp
	public static int globeBlockID = 251;
	public static int globeRenderID;

	private ModelGlobe model = new ModelGlobe();

	@Override
	public String getVersion()
	{
		return "1.5.1";
	}

	@Override
	public void load()
	{
		globeRenderID = ModLoader.getUniqueBlockModelID(this, true);
		globeBlock = new BlockGlobe(globeBlockID).getIndirectPowerOutput("NotoMod.blockGlobe");
		ModLoader.registerBlock(globeBlock);
		ModLoader.registerTileEntity(TileEntityGlobe.class, "tile.globe", new TileEntityGlobeRender());
		ModLoader.addName(globeBlock, "Globe");
	}

	public void renderInvBlock(RenderBlocks var1, Block var2, int var3, int var4)
	{
		if(var4 != globeRenderID)
		{
			return;
		}
		else
		{
			RenderEngine re = ModLoader.getMinecraftInstance().renderEngine;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.625f, 1.4f, 0.5f);
			GL11.glRotatef(180, 0f, 0f, 1f);

			if(var2 instanceof BlockGlobe)
			{
				re.bindTexture("/globe/texture/GlobeTexture.png");
				model.renderAll(0.065f);
			}
			GL11.glPopMatrix();
		}
	}

}
