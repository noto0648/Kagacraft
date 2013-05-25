package net.minecraft.src.twicra;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;

public class TweetCommand extends CommandBase
{
	private static  List<ITweet> list = new ArrayList<ITweet>();

	@Override
	public String getCommandName()
	{
		return "twc";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2)
	{
		if(var2 != null)
		{
			boolean f = false;
			for(int i = 0; i < list.size(); i++)
			{
				if(var2[0].equalsIgnoreCase(list.get(i).getName()))
				{
					list.get(i).command(getCommandSenderAsPlayer(var1), var2);
					f = true;
					break;
				}
			}
			if(!f)
			{
				var1.sendChatToPlayer("Command Not Found");
			}
		}
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	

	public static void registerCmd(ITweet par1)
	{
		list.add(par1);
	}

}
