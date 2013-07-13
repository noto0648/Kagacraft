package kagacraft.client;

import kagacraft.block.container.ContainerHydrogen;
import kagacraft.block.tile.TileEntityHydrogen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiHydrogen extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/hydrogen.png");
	private TileEntityHydrogen tile;
	
	public GuiHydrogen(InventoryPlayer inventoryPlayer, TileEntityHydrogen tileEntity)
	{
		super(new ContainerHydrogen(inventoryPlayer, tileEntity));
		tile = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Hydrogen Generator", 8, 6, 4210752);
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
		
		int widthSize = tile.getStorage() * 24 / tile.maxStorage;
		this.drawTexturedModalRect(x + 134, y + 35, 176, 0, widthSize, 17);
		if(tile.isWorking())
		{
			this.drawTexturedModalRect(x + 50, y + 36, 176, 17, 24 - tile.waitTime * 24 / tile.maxWaitTime, 17);
		}
	}
}
