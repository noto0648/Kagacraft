package net.minecraft.src.twicra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;

public class CommandChange implements ITweet
{

	@Override
	public String getName()
	{
		return "c";
	}

	@Override
	public void command(EntityPlayer par1, String[] par2)
	{
		if(par2.length <= 1 || par2[1].equalsIgnoreCase("list"))
		{
			par1.addChatMessage("== Account list ==");
			for(int i = 0; i < TwiCra.userList.size(); i++)
			{
				par1.addChatMessage(TwiCra.userList.get(i));
			}
		}
		else if(par2.length > 1)
		{
			for(int i = 0; i < TwiCra.userList.size(); i++)
			{
				if(par2[1].equalsIgnoreCase(TwiCra.userList.get(i)))
				{
					TwiCra.userChange(TwiCra.userList.get(i));
					par1.addChatMessage("[TwiCra]" + TwiCra.userList.get(i) + "にアカウントを変更しました");
					Sort(TwiCra.userList.get(i) + "," + TwiCra.accessList.get(TwiCra.userList.get(i)).getToken() + "," + TwiCra.accessList.get(TwiCra.userList.get(i)).getTokenSecret());
					return;
				}
			}
			par1.addChatMessage("[TwiCra]" + par2[1] + "は存在しません");
		}
	}

	private void Sort(String par1)
	{
		try
		{
			File f = new File(Minecraft.getMinecraftDir(), "config/TwiCra_Twitter.cfg");
			if(f.exists())
			{
				List<String> list = new ArrayList<String>();
				BufferedReader fr = new BufferedReader(new FileReader(f));
				String p = null;
				while((p = fr.readLine()) != null)
				{
					list.add(p);
				}
				fr.close();
				
				FileWriter fw = new FileWriter(f, false);
				int skip = list.indexOf(par1);
				fw.write(list.get(skip) + "\r\n");
				for(int i = 0; i < list.size(); i++)
				{
					if(skip != i)
					{
						fw.write(list.get(i) + "\r\n");
					}
				}
				fw.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
