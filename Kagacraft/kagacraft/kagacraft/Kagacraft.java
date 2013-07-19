package kagacraft;

import kagacraft.main.ClientPacketHandler;
import kagacraft.main.CreativeTabKagacraft;
import kagacraft.main.KagacraftConfig;
import kagacraft.main.KagacraftItems;
import kagacraft.main.KagacraftRegisters;
import kagacraft.main.ServerPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = "kagacraft", name = "KagaCraft", version = "1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels = "kagacraft", packetHandler = ClientPacketHandler.class ,clientPacketHandlerSpec = @SidedPacketHandler(channels = {"kagacraft" }, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = {"kagacraft" }, packetHandler = ServerPacketHandler.class))
public class Kagacraft
{
	@Mod.Instance("kagacraft")
	public static Kagacraft instance;
	
	@SidedProxy(clientSide = "kagacraft.ClientProxy", serverSide = "kagacraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static final CreativeTabKagacraft tab = new CreativeTabKagacraft();
	
	public static final boolean isDebug = true;
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent evt)
	{
		KagacraftRegisters.registers(instance);
		proxy.registers();
	}
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent evt)
	{
		KagacraftConfig.load(evt);
		KagacraftItems.registers();
	}
}
