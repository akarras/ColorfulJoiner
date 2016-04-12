package net.supernoobs.colorfuljoiner.util;

import org.bukkit.command.PluginCommand;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.commands.JoinMenu;
import net.supernoobs.colorfuljoiner.commands.JoinReload;

public class CommandRegistrationManager {
	public static void RegisterCommands(){
		registerReloadCommand();
		registerJoinMenuCommand();
	}
	private static void registerReloadCommand() {
		PluginCommand cmd = ColorfulJoiner.plugin.getCommand("joinreload");
		cmd.setName("joinreload");
		cmd.setDescription("Reload join messages");
		cmd.setUsage("/reloadjoin");
		cmd.setPermission("colorfuljoiner.admin");
		cmd.setExecutor(new JoinReload());
	}
	private static void registerJoinMenuCommand() {
		PluginCommand cmd = ColorfulJoiner.plugin.getCommand("joinmenu");
		cmd.setName("joinmenu");
		cmd.setDescription("Show join menu");
		cmd.setUsage("/joinmenu");
		cmd.setPermission("colorfuljoiner.usemenu");
		cmd.setExecutor(new JoinMenu());
	}
}
