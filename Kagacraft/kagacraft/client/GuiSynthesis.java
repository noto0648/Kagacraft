package kagacraft.client;

import kagacraft.block.container.ContainerSynthesis;
import kagacraft.block.tile.TileEntitySynthesis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiSynthesis extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/synthesis.png");
	private TileEntitySynthesis tile;

	public GuiSynthesis(InventoryPlayer inventoryPlayer, TileEntitySynthesis tileEntity)
	{
		super(new ContainerSynthesis(inventoryPlayer, tileEntity));
		this.tile = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Synthesis", 8, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if(tile.isWorking())
		{
			this.drawTexturedModalRect(x + 39, y + 47, 176, 0, 24 - tile.waitTime *  24 / tile.maxWaitTime, 17);
		}
	}
}
