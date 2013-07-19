package kagacraft.client.render;

import kagacraft.block.tile.TileEntitySpiritLamp;
import kagacraft.client.model.ModelSpiritLamp;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileEntitySpiritLampRender extends TileEntitySpecialRenderer
{
	private ModelSpiritLamp model = new ModelSpiritLamp();
	private static final ResourceLocation field_110629_a = new ResourceLocation("kagacraft", "textures/entity/spirit_lamp.png");
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
	{
		render((TileEntitySpiritLamp)tileentity, d0, d1, d2, f);
	}


	public void render(TileEntitySpiritLamp tile, double d0, double d1, double d2, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(d0 + 0.5D, d1 + 1.5D, d2 + 0.5D);
		GL11.glRotatef(180F, 0F, 0F, 1F);
		if(tile.getDirection() == 2)
		{
			GL11.glRotatef(180F, 0F, 1F, 0F);
		}
		if(tile.getDirection() == 4)
		{
			GL11.glRotatef(90F, 0F, 1F, 0F);
		}
		if(tile.getDirection() == 5)
		{
			GL11.glRotatef(270F, 0F, 1F, 0F);
		}
		this.func_110628_a(field_110629_a);
		model.allRender(0.0625F, tile.isTestTube());
		GL11.glPopMatrix();
	}
}
