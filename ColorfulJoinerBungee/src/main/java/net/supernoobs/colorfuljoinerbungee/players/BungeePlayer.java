package net.supernoobs.colorfuljoinerbungee.players;

import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeePlayer {
	private UUID uuid;
	private String username;
	private String phrase;
	
	public BungeePlayer(ProxiedPlayer player) {
		uuid = player.getUniqueId();
		username = player.getName();
	}
	
	public BungeePlayer(UUID playerUUID) {
		uuid = playerUUID;
	}
	
	public UUID getUUID(){ 
		return uuid;
	}
	public void setUUID(UUID uuid){
		this.uuid = uuid;
	}
	
	public String getPhrase(){
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String getUsername() {
		return username;
	}
	
}
