package kagacraft.client;

import kagacraft.block.container.ContainerSpiritLamp;
import kagacraft.block.tile.TileEntitySpiritLamp;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiSpiritLamp extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/spiritlamp.png");
	private TileEntitySpiritLamp tile;
	
	public GuiSpiritLamp(InventoryPlayer inventoryPlayer, TileEntitySpiritLamp tileEntity)
	{
		super(new ContainerSpiritLamp(inventoryPlayer, tileEntity));
		this.tile = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("SpiritLamp", 8, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		//draw your Gui here, only thing you need to change is the path
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int heiSize = tile.fuel * 62 / tile.maxFuel;
		this.drawTexturedModalRect(x + 152, y + 12 + (62 - heiSize), 200, 0, 16, heiSize);
		this.drawTexturedModalRect(x + 152, y + 12, 216, 0, 16, 62);
		if(tile.isWorking())
		{
			int waitSize = tile.waitTime * 24 / tile.maxWaitTime;
			this.drawTexturedModalRect(x + 79, y + 34, 176, 0, 24 -waitSize, 17);
		}
	}

}
