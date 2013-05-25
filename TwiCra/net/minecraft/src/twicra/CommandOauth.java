package net.minecraft.src.twicra;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class CommandOauth implements ITweet
{
	
	private Twitter twitter;
	private RequestToken requestToken;
	
	public CommandOauth()
	{
		try
		{
			twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer(Const.consumerKey, Const.consumerSelect);
			requestToken = twitter.getOAuthRequestToken();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName()
	{
		return "o";
	}

	@Override
	public void command(EntityPlayer par1, String[] par2)
	{
		if(par2.length > 1 && par2[1] != null)
		{
			try
			{
				AccessToken accessToken = null;
				accessToken = twitter.getOAuthAccessToken(requestToken, par2[1]);
				File f = new File(Minecraft.getMinecraftDir(), "config/TwiCra_Twitter.cfg");
				
				if(f.exists())
				{
					BufferedReader fr = new BufferedReader(new FileReader(f));
					String p;
					while((p = fr.readLine()) != null)
					{
						if(accessToken.getScreenName().equalsIgnoreCase(p.split(",")[0]))
						{
							par1.addChatMessage("[TwiCra] 既にそのアカウントは登録されています");
							fr.close();
							twitter = TwitterFactory.getSingleton();
							twitter.setOAuthConsumer(Const.consumerKey, Const.consumerSelect);
							requestToken = twitter.getOAuthRequestToken();
							return;
						}
					}
					fr.close();
				}
				FileWriter fw = new FileWriter(f, true);
				fw.write(accessToken.getScreenName() + "," + accessToken.getToken() + "," + accessToken.getTokenSecret() + "\r\n");
				fw.close();
				par1.addChatMessage("[TwiCra] 認証成功");
				TwiCra.acountLoad(false);
				TwiCra.userChange(accessToken.getScreenName());
				par1.addChatMessage("[TwiCra]" + accessToken.getScreenName() + "にアカウントを変更しました");
				twitter = TwitterFactory.getSingleton();
				twitter.setOAuthConsumer(Const.consumerKey, Const.consumerSelect);
				requestToken = twitter.getOAuthRequestToken();
				return;
			}
			catch(Exception e)
			{
				par1.addChatMessage("[TwiCra] Error <Oauth>");
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				Minecraft.getMinecraft().displayInGameMenu();
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(requestToken.getAuthorizationURL()));
				par1.addChatMessage("[TwiCra] PINコードを \"/twc o [PIN]\" とコマンドで送信してください");
			}
			catch (Exception exc)
			{
				par1.addChatMessage("[TwiCra] Error <Oauth>");
				exc.printStackTrace();
			}
		}
	}

}
