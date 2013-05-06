package net.minecraft.src.globe;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

public class TileEntityGlobeRender extends TileEntitySpecialRenderer
{
	private ModelGlobe model = new ModelGlobe();
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		this.render((TileEntityGlobe)var1, var2, var4, var6, var8);
	}
	
	public void render(TileEntityGlobe var1, double var2, double var4, double var6, float var8)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5f, (float)var4 + 1.5f, (float)var6 + 0.5f);
		GL11.glRotatef(180, 0f, 0f, 1f);
		this.bindTextureByName("/globe/texture/GlobeTexture.png");
		GL11.glPushMatrix();
		model.renderAll(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
