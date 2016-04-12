package net.supernoobs.colorfuljoinerbungee.players;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerTracker {
	HashMap<UUID,BungeePlayer> playerMap;
	public PlayerTracker() {
		playerMap = new HashMap<UUID,BungeePlayer>();
	}
	
	
	public void playerJoined(ProxiedPlayer player) {
		
	}
	
	public void playerLeft(UUID player) {
		playerMap.remove(player);
	}
	
	public void playerSwitch() {
		
	}
}
