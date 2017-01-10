package net.supernoobs.colorfuljoiner.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.supernoobs.colorfuljoiner.util.Inventories;

public class JoinMenu implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			//Check that a player ran the command
			//Load messages from storage and create an inventory
			Inventories.ShowJoinMenu(0, (Player) sender);
			
			return true;
		}
		return false;
	}
	
	
}
