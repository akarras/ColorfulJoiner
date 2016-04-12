package net.supernoobs.colorfuljoiner.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;

public final class JoinListener implements Listener {
	@EventHandler
	public void playerLogin(PlayerJoinEvent event) {
		if(!event.getPlayer().hasPlayedBefore()) {
			String firstJoinMessage = ColorfulJoiner.plugin.storage.messages.getFirstJoin();
			if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
				firstJoinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), firstJoinMessage);
			}
			Bukkit.getServer().broadcastMessage(firstJoinMessage);
		}
		ColorPlayer player = ColorfulJoiner.plugin.storage.players.loadPlayer(event.getPlayer().getUniqueId());
		String joinPhrase = ColorfulJoiner.plugin.storage.messages.getPhrases().joinMessage;
		String joinMessage = joinPhrase + ColorfulJoiner.plugin.storage.messages.getJoinMessage(player).joinMessage;
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			joinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinMessage);
		}
		
		event.setJoinMessage(joinMessage);
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		ColorPlayer player = ColorfulJoiner.plugin.storage.players.loadPlayer(event.getPlayer().getUniqueId());
		String quitMessage = ColorfulJoiner.plugin.storage.messages.getPhrases().quitMessage + 
				ColorfulJoiner.plugin.storage.messages.getJoinMessage(player).quitMessage;
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			quitMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), quitMessage);
		}
		event.setQuitMessage(quitMessage);
	}
}
