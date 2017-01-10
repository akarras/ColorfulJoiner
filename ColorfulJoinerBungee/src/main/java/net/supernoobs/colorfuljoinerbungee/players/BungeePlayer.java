package net.supernoobs.colorfuljoinerbungee.players;

import java.util.UUID;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.datatypes.JoinType;

public class BungeePlayer {
	private UUID uuid;
	private String username;
	private JoinType phrase;
	
	
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
	
	public JoinType getPhrase(){
		return phrase;
	}
	public void setPhrase(JoinType phrase) {
		this.phrase = phrase;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void broadcastJoin() {
		ColorfulJoinerBungee.plugin.getLogger().info("Broadcasting join?");
		ColorfulJoinerBungee.plugin.getProxy().broadcast(new TextComponent(getPhrase().joinMessage));
	}
	
}
