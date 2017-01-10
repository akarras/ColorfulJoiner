package net.supernoobs.colorfuljoinerbungee.players;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.datatypes.JoinType;

public class PlayerTracker {
	HashMap<UUID,BungeePlayer> playerMap;
	public PlayerTracker() {
		playerMap = new HashMap<UUID,BungeePlayer>();
	}
	
	
	public void playerJoined(ProxiedPlayer player) {
		BungeePlayer bungeePlayer = new BungeePlayer(player);
		playerMap.put(player.getUniqueId(), bungeePlayer);
	}
	
	public void playerLeft(ProxiedPlayer player) {
		playerMap.remove(player.getUniqueId());
	}
	
	public void playerSwitch(ProxiedPlayer player) {
		
	}
	
	public void setPlayerPhrase(UUID uuid, JoinType type) {
		BungeePlayer player = playerMap.get(uuid);
		player.setPhrase(type);
		player.broadcastJoin();
	}
}
