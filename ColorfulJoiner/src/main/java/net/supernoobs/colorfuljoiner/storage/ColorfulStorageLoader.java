package net.supernoobs.colorfuljoiner.storage;

import java.io.File;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.Messages;
import net.supernoobs.colorfuljoiner.Settings;

public class ColorfulStorageLoader {
	
	File pluginDirectory;
	public ColorfulMessages messages;
	public ColorfulPlayerStorage players;
	public Messages pluginMessages;
	public Settings settings;
	
	public ColorfulStorageLoader() {
		pluginDirectory = ColorfulJoiner.plugin.getDataFolder();
		pluginDirectory.mkdirs();
		messages = new ColorfulMessages();
		players = new ColorfulPlayerStorage();
		pluginMessages = new Messages();
		settings = new Settings();
		
		//Load our default config file
		ColorfulJoiner plugin = ColorfulJoiner.plugin;
		File config = new File(plugin.getDataFolder()+File.separator+"config.yml");
		if(!config.exists()) {
			plugin.saveResource("config.yml", false);
		}
	}
	
}
