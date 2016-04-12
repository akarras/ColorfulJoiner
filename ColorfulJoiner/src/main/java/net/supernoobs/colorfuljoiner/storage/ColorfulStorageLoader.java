package net.supernoobs.colorfuljoiner.storage;

import java.io.File;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;

public class ColorfulStorageLoader {
	
	File pluginDirectory;
	public ColorfulMessages messages;
	public ColorfulPlayerStorage players;
	public ColorfulPluginStrings pluginMessages;
	
	public ColorfulStorageLoader() {
		pluginDirectory = ColorfulJoiner.plugin.getDataFolder();
		pluginDirectory.mkdirs();
		messages = new ColorfulMessages();
		players = new ColorfulPlayerStorage();
		pluginMessages = new ColorfulPluginStrings();
	}
	
}
