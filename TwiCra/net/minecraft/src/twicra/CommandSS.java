package net.minecraft.src.twicra;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

public class CommandSS implements ITweet
{

	@Override
	public String getName()
	{
		return "s";
	}

	@Override
	public void command(EntityPlayer par1, String[] par2)
	{
		if(par2.length == 1 || par2[1].equalsIgnoreCase("list"))
		{
			if(new File(Minecraft.getMinecraftDir(), "screenshots").exists())
			{
				String[] srs = new File(Minecraft.getMinecraftDir(), "screenshots").list();
				par1.addChatMessage("ID:FileName");
				for(int i = 0; i < srs.length; i++)
				{
					par1.addChatMessage(i + " : " + srs[i]);
				}
			}
		}
		else if(par2.length > 2)
		{
			try
			{
				String fileName = null;
				
				if(new File(Minecraft.getMinecraftDir(), "screenshots").exists())
				{
					String[] srs = new File(Minecraft.getMinecraftDir(), "screenshots").list();
					try
					{
						Integer.valueOf(par2[2]);
						fileName = srs[Integer.valueOf(par2[2])];
					}
					catch(NumberFormatException e)
					{
						if(new File(Minecraft.getMinecraftDir(), "screenshots/" + par2[2]).exists())
						{
							fileName = par2[2];
						}
					}
				}
				Twitter tw = TwiCra.getTwitter();
				StatusUpdate status = new StatusUpdate(par2[1].replace("\\n", "\r\n").replace("\\s", " "));
				if(fileName != null)
					status.media(new File(Minecraft.getMinecraftDir(), "screenshots/" + fileName));
				tw.updateStatus(status);
				par1.sendChatToPlayer("Tweet:" + par2[1].replace("\\n", "\r\n").replace("\\s", " ") + ", " + fileName);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			TwiCra.tweet(par2[1]);
			par1.sendChatToPlayer("Tweet:" + par2[1].replace("\\n", "\r\n").replace("\\s", " "));
		}
	}

}
