package kagacraft.client.render;

import kagacraft.item.ItemElements;
import kagacraft.main.Atoms;
import kagacraft.main.KagacraftItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderKagacraftItems implements IItemRenderer
{
	private Minecraft mc;
	private static RenderItem renderItem = new RenderItem();
	
	public RenderKagacraftItems()
	{
		this.mc = Minecraft.getMinecraft();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if(item.itemID == KagacraftItems.elements.itemID && type == ItemRenderType.INVENTORY)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if(item.getItem() instanceof ItemElements)
		{
			Icon icon = item.getIconIndex();
			renderItem.renderIcon(0, 0, icon, 16, 16);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			FontRenderer fr = this.mc.fontRenderer;
			fr.drawStringWithShadow(Atoms.ATOMS[item.getItemDamage()], 1, 1, 15658734);
		}
	}

}
