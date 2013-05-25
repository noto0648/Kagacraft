package net.minecraft.src.twicra;

import net.minecraft.src.EntityPlayer;

public class CommandTweet implements ITweet
{

	@Override
	public String getName()
	{
		return "t";
	}

	@Override
	public void command(EntityPlayer par1, String[] par2)
	{
		if(par2.length > 1)
		{
			TwiCra.tweet(par2[1]);
			par1.sendChatToPlayer("Tweet:" + par2[1].replace("\\n", "\r\n").replace("\\s", " "));
		}
	}

}
