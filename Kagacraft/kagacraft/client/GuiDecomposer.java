package kagacraft.client;

import kagacraft.block.container.ContainerDecomposer;
import kagacraft.block.tile.TileEntityDecomposer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiDecomposer extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/decomposer.png");
	private TileEntityDecomposer tile;
	
	public GuiDecomposer(InventoryPlayer inventoryPlayer, TileEntityDecomposer tileEntity)
	{
		super(new ContainerDecomposer(inventoryPlayer, tileEntity));
		this.tile = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Decomposer", 8, 6, 4210752);
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
			this.drawTexturedModalRect(x + 67, y + 35, 176, 0, 24 - tile.waitTime *  24 / tile.maxWaitTime, 17);
		}
	}
}
