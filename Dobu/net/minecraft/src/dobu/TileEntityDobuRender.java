package net.minecraft.src.dobu;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

public class TileEntityDobuRender extends TileEntitySpecialRenderer
{
	private ModelDobu model = new ModelDobu();
	
	private void render(TileEntityDobu var1, double var2, double var4, double var6, float var8)
	{
		float var10 = ((var1.getBlockMetadata() % 2) == 1)? 0.5f : 0.625f;
		float var11 = ((var1.getBlockMetadata() % 2) == 0)? 0.5f : 0.625f;
		GL11.glPushMatrix();
		//GL11.glTranslatef((float)var2 + 0.625f, (float)var4 + 1.0f, (float)var6 + 0.5f);
		GL11.glTranslatef((float)var2 + var10, (float)var4 + 1.0f, (float)var6 + var11);
		GL11.glRotatef(180, 0f, 0f, 1f);
		
		if(var1.getBlockMetadata() % 2 == 1)
		{
			GL11.glRotatef(90, 0f, 1f, 0f);
		}
		
		if(var1.getBlockMetadata() == 0||var1.getBlockMetadata() == 1)
		{
			this.bindTextureByName("/noto/Dobu.png");
		}
		
		if(var1.getBlockMetadata() == 2||var1.getBlockMetadata() == 3)
		{
			this.bindTextureByName("/noto/Dobu_Whiting.png");
		}
		
		if(var1.getBlockMetadata() == 4||var1.getBlockMetadata() == 5)
		{
			this.bindTextureByName("/noto/Dobu_Mesh.png");
		}
		
		GL11.glPushMatrix();
		this.model.modelRender(0.0625F);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		this.render((TileEntityDobu)var1, var2, var4, var6, var8);
	}

}
