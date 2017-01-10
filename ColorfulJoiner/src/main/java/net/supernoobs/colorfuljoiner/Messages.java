package net.supernoobs.colorfuljoiner;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	public String noPermission;
	
	
	
	public Messages() {
		File plStrings = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"pluginMessages.yml");
		if(!plStrings.exists())
			ColorfulJoiner.plugin.saveResource("pluginMessages.yml", false);
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(plStrings);
		
		noPermission = (String) yaml.getString("NoPermission");
		
	}
}
