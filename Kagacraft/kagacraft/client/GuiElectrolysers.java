package kagacraft.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import kagacraft.block.container.ContainerElectrolysers;
import kagacraft.block.tile.TileEntityBase;
import kagacraft.block.tile.TileEntityElectrolysers;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiElectrolysers extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("kagacraft", "textures/gui/container/electrolysers.png");
	private TileEntityElectrolysers tile;

	public GuiElectrolysers(InventoryPlayer inventoryPlayer, TileEntityElectrolysers tileEntity)
	{
		super(new ContainerElectrolysers(inventoryPlayer, tileEntity));
		this.tile = tileEntity;
	}


	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Electrolysers", 8, 6, 4210752);
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

		if(tile.isWork())
		{
			int heiSize = tile.waitTime * 29 / tile.maxWaitTime;
			this.drawTexturedModalRect(x + 62, y + 35 + heiSize, 176, heiSize, 15, 29 - heiSize);
			this.drawTexturedModalRect(x + 98, y + 35 + heiSize, 191, heiSize, 15, 29 - heiSize);
		}
	}


	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}

	@Override
	public void onGuiClosed()
	{
		this.mc.thePlayer.sendQueue.addToSendQueue(createPacket(tile));
	}

	public static Packet createPacket(TileEntityBase tile)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		int x = tile.xCoord;
		int y = tile.yCoord;
		int z = tile.zCoord;
		int wait = tile.waitTime;
		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(wait);
			dos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "kagacraft";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		return  packet;
	}

}
