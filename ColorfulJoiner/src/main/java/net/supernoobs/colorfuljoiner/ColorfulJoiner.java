package net.supernoobs.colorfuljoiner;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.permission.Permission;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlaceholders;
import net.supernoobs.colorfuljoiner.listeners.BungeeListener;
import net.supernoobs.colorfuljoiner.listeners.InventoryListener;
import net.supernoobs.colorfuljoiner.listeners.JoinListener;
import net.supernoobs.colorfuljoiner.storage.ColorfulStorageLoader;
import net.supernoobs.colorfuljoiner.util.CommandRegistrationManager;

public class ColorfulJoiner extends JavaPlugin {
	public static ColorfulJoiner plugin;
	public ColorfulStorageLoader storage;
	public Permission permission;
	public Settings settings;
	
	@Override
	public void onEnable(){
		plugin = this;
		storage = new ColorfulStorageLoader();
		CommandRegistrationManager.RegisterCommands();
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		settings = new Settings();
		//If we're using bungee, we want to register our bungee channels
		if(settings.isUsingBungee()) {
			getServer().getMessenger().registerOutgoingPluginChannel(this, "ColorfulJoiner");
			getServer().getMessenger().registerIncomingPluginChannel(this, "ColorfulJoiner", new BungeeListener());
		}
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		PlaceholderAPI.registerPlaceholderHook(this, new ColorPlaceholders());
		//getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
	
	
	@Override
	public void onDisable(){
		PlaceholderAPI.unregisterPlaceholderHook(this);
		getServer().getMessenger().unregisterOutgoingPluginChannel(this);
		getServer().getMessenger().unregisterIncomingPluginChannel(this);
	}
}
