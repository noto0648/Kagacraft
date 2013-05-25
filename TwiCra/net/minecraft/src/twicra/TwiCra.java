package net.minecraft.src.twicra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwiCra
{
	private static String Token;
	private static String TokenSelect;
	public static List<String> userList;
	public static HashMap<String, AccessToken> accessList;
	
	public static void load()
	{
		acountLoad(true);
	}

	public static void acountLoad(boolean par1)
	{
		if(userList != null)
		{
			userList.clear();
		}
		if(accessList != null)
		{
			accessList.clear();
		}
		userList = new ArrayList();
		accessList = new HashMap<String, AccessToken>();
		try
		{
			File f = new File(Minecraft.getMinecraftDir(), "config/TwiCra_Twitter.cfg");
			if(f.exists())
			{
				BufferedReader fr = new BufferedReader(new FileReader(f));
				String p;
				while((p = fr.readLine()) != null)
				{
					String[] ps = p.split(",");
					accessList.put(ps[0], new AccessToken(ps[1], ps[2]));
					userList.add(ps[0]);
				}
				fr.close();
				
				if(par1)
				{
					Token = accessList.get(userList.get(0)).getToken();
					TokenSelect = accessList.get(userList.get(0)).getTokenSecret();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void registerCmd(ITweet par1)
	{
		TweetCommand.registerCmd(par1);
	}

	public static String getToken()
	{
		return Token;
	}

	public static String getTokenSelect()
	{
		return TokenSelect;
	}

	public static void setToken(String par1)
	{
		Token = par1;
	}

	public static void setTokenSelect(String par1)
	{
		TokenSelect = par1;
	}

	public static Twitter getTwitter()
	{
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = loadAccessToken();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(Const.consumerKey, Const.consumerSelect);
		twitter.setOAuthAccessToken(accessToken);
		return twitter;
	}

	public static Status tweet(String par1)
	{
		if(par1.length() > 0 && par1.length() < 140)
		{
			try
			{
				Twitter twitter = getTwitter();
				Status status = twitter.updateStatus(par1.replace("\\n", "\r\n").replace("\\s", " "));
				return status;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static List<Status> getUserTimeLine()
	{
		try
		{
			return getTwitter().getHomeTimeline();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void userChange(String par1)
	{
		Token = accessList.get(par1).getToken();
		TokenSelect = accessList.get(par1).getTokenSecret();
	}
	
	private static AccessToken loadAccessToken()
	{
		if(Token != null && TokenSelect != null)
			return new AccessToken(Token, TokenSelect);
		return null;
	}
}
