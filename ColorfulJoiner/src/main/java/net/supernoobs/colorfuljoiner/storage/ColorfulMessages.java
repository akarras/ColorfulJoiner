package net.supernoobs.colorfuljoiner.storage;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;
import net.supernoobs.colorfuljoiner.datatypes.JoinType;

public class ColorfulMessages {
	private HashMap<String,JoinType> joinMessages;
	private JoinType defaultJoin;
	private JoinType phrases;
	private JoinType prefix;
	private String firstJoin;
	
	public ColorfulMessages() {
		File messagesFile = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"joinMessages.yml");
		if(!messagesFile.exists()) {
			ColorfulJoiner.plugin.saveResource("joinMessages.yml", false);
		}
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(messagesFile);
		phrases = new JoinType();
		phrases.joinMessage = (String) yaml.get("phrases.join");
		phrases.quitMessage = (String) yaml.get("phrases.quit");
		
		prefix = new JoinType();
		prefix.joinMessage = (String) yaml.get("players.join");
		prefix.quitMessage = (String) yaml.get("players.leave");
		
		firstJoin = (String) yaml.get("firstJoin");
		
		defaultJoin = new JoinType();
		defaultJoin.joinMessage = (String) yaml.get("default.join");
		defaultJoin.quitMessage = (String) yaml.get("default.quit");
		
		ConfigurationSection section = yaml.getConfigurationSection("messages");
		loadCustomMessages(section);
	}
	
	public HashMap<String,JoinType> getJoinMessages(){
		return joinMessages;
	}
	
	public JoinType getPhrases(){
		return phrases;
	}
	
	public JoinType getPrefix(){
		return prefix;
	}
	
	public String getFirstJoin(){
		return firstJoin;
	}
	
	public void AddJoinMessage(String message, JoinType type) {
		joinMessages.put(message, type);
		saveMessages();
	}
	public JoinType getJoinMessage(ColorPlayer player) {
		if(player == null) return defaultJoin;
		if(player.joinMessage == null) return defaultJoin;
		//If the player's join message is in our hashmap we should return that
		if(joinMessages.containsKey(player.joinMessage)){
			return joinMessages.get(player.joinMessage);
		}
		return defaultJoin;
	}
	private void saveMessages() {
		File messagesFile = new File(ColorfulJoiner.plugin.getDataFolder()+File.separator+"joinMessages.yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(messagesFile);
		yaml.set("messages", joinMessages);
		try{
			yaml.save(messagesFile);
		} catch (Exception e) {
			ColorfulJoiner.plugin.getLogger().info("Failed to save messages.yml");
		}
	}
	private void loadCustomMessages(ConfigurationSection section) {
		joinMessages = new HashMap<String,JoinType>();
		for(String key:section.getKeys(false)) {
			JoinType type = new JoinType();
			type.joinMessage = (String) section.get(key+".join");
			type.quitMessage = (String) section.get(key+".quit");
			joinMessages.put(key, type);
		}
	}
}
