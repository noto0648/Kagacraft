package kagacraft;

import kagacraft.block.tile.TileEntitySpiritLamp;
import kagacraft.client.render.RenderKagacraftItems;
import kagacraft.client.render.RenderSpiritLamp;
import kagacraft.client.render.TileEntitySpiritLampRender;
import kagacraft.main.KagacraftItems;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpiritLamp.class, new TileEntitySpiritLampRender());
		MinecraftForgeClient.registerItemRenderer(KagacraftItems.elements.itemID, new RenderKagacraftItems());
		MinecraftForgeClient.registerItemRenderer(KagacraftItems.spiritLamp.blockID, new RenderSpiritLamp());
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
}
