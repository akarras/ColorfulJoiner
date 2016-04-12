package net.supernoobs.colorfuljoiner.storage;

import java.io.File;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;

public class ColorfulPlayerStorage {
	private File playersFile;
	public ColorfulPlayerStorage(){
		playersFile = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"players.yml");
		if(!playersFile.exists()) {
			try {playersFile.createNewFile();
			
			} catch (Exception e) {
				
			}
		}
	}
	
	public void savePlayer(ColorPlayer player){
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playersFile);
		yaml.set(player.uuid.toString(), player.joinMessage);
		try{
			yaml.save(playersFile);
		}
		catch (Exception e) {
			ColorfulJoiner.plugin.getLogger().info("Failed saving "+player.uuid);
		}
	}
	
	public ColorPlayer loadPlayer(UUID playerUUID){
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playersFile);

		ColorPlayer player = new ColorPlayer(playerUUID);
		
		if(yaml.contains(playerUUID.toString())) {
			String message = (String) yaml.get(playerUUID.toString());
			player.joinMessage = message;
			return player;
		}
		player.joinMessage = "Default";
		return player;
	}
}
