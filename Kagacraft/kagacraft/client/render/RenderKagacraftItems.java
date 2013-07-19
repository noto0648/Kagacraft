package kagacraft.client.render;

import kagacraft.api.Atoms;
import kagacraft.api.EnumStates;
import kagacraft.api.KagacraftAPI;
import kagacraft.item.ItemElements;
import kagacraft.main.KagacraftItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
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
			ItemElements ie = (ItemElements)item.getItem();
			if(KagacraftAPI.getAtoms(item).states == EnumStates.Liquid || KagacraftAPI.getAtoms(item).states == EnumStates.UnKnown)
			{
				renderItem.renderIcon(0, 0, ie.icons[1], 16, 16);
			}
			else if(KagacraftAPI.getAtoms(item).states == EnumStates.Solid)
			{
				renderItem.renderIcon(0, 0, ie.icons[0], 16, 16);
			}
			else if(KagacraftAPI.getAtoms(item).states == EnumStates.Gas)
			{
				renderItem.renderIcon(0, 0, ie.icons[(int) (((int)Minecraft.getSystemTime() / 800L % 2L) + 2)], 16, 16);
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			FontRenderer fr = this.mc.fontRenderer;
			fr.drawStringWithShadow(Atoms.getSign(item.getItemDamage()), 1, 1, 15658734);
		}
	}

}
