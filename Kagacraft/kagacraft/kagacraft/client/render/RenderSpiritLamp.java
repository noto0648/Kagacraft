package kagacraft.client.render;

import kagacraft.client.model.ModelSpiritLamp;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderSpiritLamp implements IItemRenderer
{
	private ModelSpiritLamp model = new ModelSpiritLamp();
	private static final ResourceLocation field_110629_a = new ResourceLocation("kagacraft", "textures/entity/spirit_lamp.png");
	private Minecraft mc = Minecraft.getMinecraft();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		this.mc.renderEngine.func_110577_a(field_110629_a);
		GL11.glRotatef(180F, 0F, 0F, 1F);
		GL11.glTranslatef(0.0F, -1.3F, 0.0F);
		model.allRender(0.06F, false);
		GL11.glPopMatrix();

	}

}
