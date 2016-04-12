package net.supernoobs.colorfuljoiner;

import org.bukkit.plugin.java.JavaPlugin;

import net.supernoobs.colorfuljoiner.listeners.BungeeListener;
import net.supernoobs.colorfuljoiner.listeners.InventoryListener;
import net.supernoobs.colorfuljoiner.listeners.JoinListener;
import net.supernoobs.colorfuljoiner.storage.ColorfulStorageLoader;
import net.supernoobs.colorfuljoiner.util.CommandRegistrationManager;

public class ColorfulJoiner extends JavaPlugin {
	public static ColorfulJoiner plugin;
	public ColorfulStorageLoader storage;
	
	public boolean usingBungee;
	
	@Override
	public void onEnable(){
		plugin = this;
		storage = new ColorfulStorageLoader();
		CommandRegistrationManager.RegisterCommands();
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().unregisterIncomingPluginChannel(this, "BungeeCord", new BungeeListener());
		
		usingBungee = getConfig().getBoolean("using-bungee", false);
	}
	
	
	@Override
	public void onDisable(){
		
	}
}
