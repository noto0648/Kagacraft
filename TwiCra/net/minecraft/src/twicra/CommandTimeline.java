package net.minecraft.src.twicra;

import java.util.List;

import twitter4j.Status;
import net.minecraft.src.EntityPlayer;

public class CommandTimeline implements ITweet
{

	@Override
	public String getName()
	{
		return "l";
	}

	@Override
	public void command(EntityPlayer par1, String[] par2)
	{
		List<Status> list = TwiCra.getUserTimeLine();
		for(int i = 0; i < list.size(); i++)
		{
			par1.addChatMessage("§6【" + list.get(list.size() - 1 - i).getUser().getName() + "】§r" + list.get(list.size() - 1 - i).getText());
		}
	}

}
