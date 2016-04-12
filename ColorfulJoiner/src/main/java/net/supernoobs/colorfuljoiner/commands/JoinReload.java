package net.supernoobs.colorfuljoiner.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.storage.ColorfulStorageLoader;

public class JoinReload implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ColorfulJoiner.plugin.storage = new ColorfulStorageLoader();
		sender.sendMessage("§aColorfulJoiner reloaded");
		return true;
	}
	
}
