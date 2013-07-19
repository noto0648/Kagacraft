package kagacraft.client;

import kagacraft.api.Atoms;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiPeriodicTable extends GuiScreen
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/periodictable.png");
	private int xSize = 304;
	private int ySize = 176;
	
	private final String periodicTable = "1000000000000000011100000000001111111100000000001111111111111111111111111111111111111111111111";
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		int x = (this.width - xSize) / 2;
		int y = (this.height - ySize) / 2;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		for(int i = 0; i < Atoms.ATOMS.length; i++)
		{
			
		}
		GL11.glPopMatrix();
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
