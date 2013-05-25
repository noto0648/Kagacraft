package net.minecraft.src;

import net.minecraft.src.twicra.CommandChange;
import net.minecraft.src.twicra.CommandOauth;
import net.minecraft.src.twicra.CommandSS;
import net.minecraft.src.twicra.CommandTimeline;
import net.minecraft.src.twicra.CommandTweet;
import net.minecraft.src.twicra.TweetCommand;
import net.minecraft.src.twicra.TwiCra;

public class mod_TwiCra extends BaseMod
{
	
	public KeyBinding key = new KeyBinding("TweetKey", 49);
	
	@Override
	public String getVersion()
	{
		return "1.5.2";
	}

	@Override
	public String getName()
	{
		return "Twi Cra";
	}

	@Override
	public void load()
	{
		ModLoader.addCommand(new TweetCommand());
		TwiCra.load();
		TwiCra.registerCmd(new CommandTweet());
		TwiCra.registerCmd(new CommandOauth());
		TwiCra.registerCmd(new CommandSS());
		TwiCra.registerCmd(new CommandChange());
		TwiCra.registerCmd(new CommandTimeline());
		ModLoader.registerKey(this, key, true);
	}

	@Override
	public void keyboardEvent(KeyBinding var1)
	{
		if(var1 == key)
		{
			
		}
	}
}
