package net.supernoobs.colorfuljoiner.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.clip.placeholderapi.PlaceholderAPI;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.Settings;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;


public final class JoinListener implements Listener {
	ColorfulJoiner plugin;
	public JoinListener(ColorfulJoiner pl){
		plugin = pl;
	}
	
	@EventHandler
	public void playerLogin(PlayerJoinEvent event) {
		//If the player hasn't joined before, send the first join message. 
		if(!event.getPlayer().hasPlayedBefore()) {
			String firstJoinMessage = plugin.storage.messages.getFirstJoin();
			if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
				firstJoinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), firstJoinMessage);
			}
			Bukkit.getServer().broadcastMessage(firstJoinMessage);
			
			
		}
		//Load our player
		ColorPlayer player = plugin.storage.players.loadPlayer(event.getPlayer().getUniqueId());
		//Load our phrases up
		String joinPrefix = plugin.storage.messages.getPrefix().joinMessage;
		String joinPhrase = plugin.storage.messages.getPhrases().joinMessage;
		String playerJoinMessage = plugin.storage.messages.getJoinMessage(player).joinMessage;
		String joinMessage;
		//If we're using bungee, we need to send the message over the plugin channel and let Bungee send it
		if(ColorfulJoiner.plugin.settings.isUsingBungee()) {
			event.setJoinMessage(null);
		} else {
			if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
				joinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinPrefix + joinPhrase + playerJoinMessage);
			} else
				joinMessage = joinPhrase+playerJoinMessage;
			event.setJoinMessage(joinMessage);
		}
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		ColorPlayer player = plugin.storage.players.loadPlayer(event.getPlayer().getUniqueId());
		String quitPhrase = plugin.storage.messages.getPhrases().quitMessage;
		String playerPrefix = plugin.storage.messages.getPrefix().quitMessage;
		String playerQuitMessage = plugin.storage.messages.getJoinMessage(player).quitMessage;
		String quitMessage;
		//If we're using bungee, we need to send the message over the plugin channel and let Bungee send it
		if(ColorfulJoiner.plugin.settings.isUsingBungee()) {
			event.setQuitMessage(null);
		} else {
			if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
				quitMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), quitPhrase + playerQuitMessage);
			} else {
				quitMessage = quitPhrase+playerQuitMessage;
			}
			event.setQuitMessage(quitMessage);
		}
	}
}
