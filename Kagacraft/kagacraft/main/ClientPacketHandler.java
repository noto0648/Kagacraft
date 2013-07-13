package kagacraft.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import kagacraft.Kagacraft;
import kagacraft.block.tile.TileEntityHydrogen;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));


		if(packet.channel.equals("kagacraft"))
		{
			System.out.println("jyusin:Client");
			ByteArrayDataInput var1 = ByteStreams.newDataInput(packet.data);
			int x, y, z, production, storage;
			x = var1.readInt();
			y = var1.readInt();
			z = var1.readInt();
			production = var1.readInt();
			storage = var1.readInt();
			World world = Kagacraft.proxy.getClientWorld();
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntityHydrogen)
			{
				TileEntityHydrogen var2 = (TileEntityHydrogen) tileEntity;
				var2.setProduction(production);
				var2.setStorage(storage);
			}
		}
	}


	public static Packet sendPacket(TileEntityHydrogen tile)
	{
		System.out.println("sousin");
		ByteArrayOutputStream var1 = new ByteArrayOutputStream(128);
		DataOutputStream var2 = new DataOutputStream(var1);
		int x, y, z, production, storage;

		x = tile.xCoord;
		y = tile.yCoord;
		z = tile.zCoord;
		production = tile.getProduction();
		storage = tile.getStorage();
		
		try
		{
			var2.writeInt(x);
			var2.writeInt(y);
			var2.writeInt(z);
			var2.writeInt(production);
			var2.writeInt(storage);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "kagacraft";
		packet.data = var1.toByteArray();
		packet.length = var1.size();
		packet.isChunkDataPacket = true;
		return packet;
	}
}

