package net.supernoobs.colorfuljoiner;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Settings {
	private boolean usingBungee;
	private String serverName;
	public Settings() {
		File settingsFile = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"settings.yml");
		if(!settingsFile.exists()) {
			ColorfulJoiner.plugin.saveResource("settings.yml", false);
		}
		
		YamlConfiguration settingsConf = YamlConfiguration.loadConfiguration(settingsFile);
		boolean confChanged = false;
		if(!settingsConf.contains("settings.bungee.enabled")) {
			settingsConf.set("settings.bungee.enabled", true);
			confChanged = true;
		}
		if(!settingsConf.contains("settings.bungee.server-name")) {
			settingsConf.set("settings.bungee.server-name", "&4Server");
			confChanged = true;
		}
		
		if(confChanged) {
			try {
				settingsConf.save(settingsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		setUsingBungee(settingsConf.getBoolean("settings.bungee.enabled",false));
		setServerName(settingsConf.getString("settings.bungee.server-name", "&4Server"));
	}
	public boolean isUsingBungee() {
		return usingBungee;
	}
	public void setUsingBungee(boolean usingBungee) {
		this.usingBungee = usingBungee;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
