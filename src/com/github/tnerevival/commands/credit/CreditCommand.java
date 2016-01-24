package com.github.tnerevival.commands.credit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.tnerevival.TNE;
import com.github.tnerevival.commands.TNECommand;
import com.github.tnerevival.core.Message;

public class CreditCommand extends TNECommand {

	public CreditCommand(TNE plugin) {
		super(plugin);
		subCommands.add(new CreditCommandsCommand(plugin));
		subCommands.add(new CreditInventoryCommand(plugin));
	}

	@Override
	public String getName() {
		return "credit";
	}

	@Override
	public String[] getAliases() {
		return new String[0];
	}

	@Override
	public String getNode() {
		return "tne.credit";
	}

	@Override
	public boolean console() {
		return false;
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] arguments) {
		if(arguments.length == 0) {
			help(sender);
			return false;
		}
		
		if(arguments[0].equalsIgnoreCase("help")) {
			help(sender);
			return false;
		}
		
		TNECommand sub = FindSub(arguments[0]);
		if(sub == null) {
			Message noCommand = new Message("Messages.Command.None");
			noCommand.addVariable("$command", "/" + getName());
			noCommand.addVariable("$arguments", arguments[0]);
			sender.sendMessage(noCommand.translate());
			return false;
		}
		if(!sub.canExecute(sender)) {
			Message unable = new Message("Messages.Command.Unable");
			unable.addVariable("$command", "/" + getName());
			sender.sendMessage(unable.translate());
			return false;
		}
		return sub.execute(sender, removeSub(arguments));
	}

	@Override
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "~~~~~Credit Commands~~~~~");
		sender.sendMessage(ChatColor.GOLD + "/credit help - View general credit command help.");
		sender.sendMessage(ChatColor.GOLD + "/credit inventory <inventory> - View time credits for <inventory> in every world.");
		sender.sendMessage(ChatColor.GOLD + "/credit commands - View all command credits you have accumulated.");
	}
	
}