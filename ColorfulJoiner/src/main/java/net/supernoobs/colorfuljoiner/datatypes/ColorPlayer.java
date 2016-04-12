package net.supernoobs.colorfuljoiner.datatypes;

import java.util.UUID;

import org.bukkit.entity.Player;

public class ColorPlayer {
	public ColorPlayer() {
		
	}
	public ColorPlayer(UUID uuid) {
		this.uuid = uuid;
	}
	
	public ColorPlayer(Player player) {
		uuid = player.getUniqueId();
	}
	
	public UUID uuid;
	public String joinMessage;
}
