package kagacraft.client;

import java.util.Arrays;
import java.util.List;

import kagacraft.api.KagacraftAPI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCheckTool extends GuiScreen
{
	private static final ResourceLocation tex = new ResourceLocation("kagacraft", "textures/gui/container/checktool.png");
	private int blockID = 0;
	private int meta = 0;
	private RenderItem renderer = new RenderItem();
	
	public GuiCheckTool(ItemStack par1)
	{
		if(par1 != null && par1.hasTagCompound())
		{
			this.blockID = par1.getTagCompound().getInteger("BID");
			this.meta = par1.getTagCompound().getInteger("BMETA");
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		this.mc.renderEngine.func_110577_a(tex);
		int x = (width - 176) / 2;
		int y = (height - 166) / 2;
		this.drawTexturedModalRect(x, y, 0, 0,176, 166);

		this.fontRenderer.drawString("Kagacraft Tool", x + 8, y + 6, 4210752);
		this.fontRenderer.drawString("BlockName : " + new ItemStack(blockID, 1, meta).getDisplayName(), x + 8, y + 30, 4210752);

		this.fontRenderer.drawString("Constituent element ", x + 8, y + 54, 4210752);

		if(KagacraftAPI.getDecompserRecipe().containsKey(Arrays.asList(blockID,meta)))
		{
			List<ItemStack> stack = KagacraftAPI.getDecompserRecipe().get(Arrays.asList(blockID,meta));
			List<Integer> _stack = KagacraftAPI.getDecompserStacks().get(Arrays.asList(blockID,meta));
			this.renderer.zLevel = 200.0F;
			for(int i = 0; i < stack.size(); i++)
			{
				ItemStack item = new ItemStack(stack.get(i).itemID, _stack.get(i), stack.get(i).getItemDamage());
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				this.fontRenderer.drawStringWithShadow(item.getDisplayName() + "x" + _stack.get(i), x + 26 + (i % 2 * 75), y + 67 + (i / 2 * 18), 0xFFFFFF);
				GL11.glEnable(GL11.GL_LIGHTING);
				this.renderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.func_110434_K(), item, x + 8 + (i % 2 * 75), y + 64 + (i / 2 * 18));
				GL11.glPopMatrix();
			}
			this.renderer.zLevel = 0.0F;
		}
		else
		{
			this.fontRenderer.drawStringWithShadow("Decomposition can not.", x + 26 , y + 67 , 0xFFFFFF);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		if (par2 == this.mc.gameSettings.keyBindInventory.keyCode || par2 == 1)
		{
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		}
	}
}
