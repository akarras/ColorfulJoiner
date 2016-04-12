package net.supernoobs.colorfuljoiner.storage;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;

public class ColorfulPluginStrings {
	public String noPermission;
	
	
	
	public ColorfulPluginStrings() {
		File plStrings = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"pluginMessages.yml");
		if(!plStrings.exists())
			ColorfulJoiner.plugin.saveResource("pluginMessages.yml", false);
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(plStrings);
		
		noPermission = (String) yaml.getString("NoPermission");
		
	}
}
