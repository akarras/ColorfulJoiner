package net.supernoobs.colorfuljoiner.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.clip.placeholderapi.PlaceholderAPI;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.Settings;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;
import net.supernoobs.colorfuljoiner.datatypes.JoinType;

public class BungeeListener implements PluginMessageListener {
	private ColorfulJoiner plugin;
	public BungeeListener() {
		plugin = ColorfulJoiner.plugin;
	}
	
	public void onPluginMessageReceived(String channel, Player messagePlayer, byte[] message) {
		if (!channel.equals("ColorfulJoiner")) {
			return;
		}		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		
		String queryPart = in.readUTF();
		if(queryPart.equals("test")) {
			
		}
		if(queryPart.equals("phrasesrequest")) {
			String playerUUID = in.readUTF();
			ColorPlayer cPlayer = ColorfulJoiner.plugin.storage.players.loadPlayer(UUID.fromString(playerUUID));
			JoinType type = ColorfulJoiner.plugin.storage.messages.getJoinMessage(cPlayer);
			
			//ColorfulJoiner.plugin.getLogger().info(joinPart+" Join message recieved");
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("phrases");
			out.writeUTF(playerUUID);
			out.writeUTF(type.joinMessage);
			out.writeUTF(type.quitMessage);
			ColorfulJoiner.plugin.getServer().sendPluginMessage(ColorfulJoiner.plugin, "ColorfulJoiner", out.toByteArray());
			return;
		}
		if(queryPart.equals("joinrequest")) {
			
			String playerUUID = in.readUTF();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("joinresponse");
			ColorPlayer player = plugin.storage.players.loadPlayer(UUID.fromString(playerUUID));
			Bukkit.getServer().getPlayer(player.uuid);
			//Load our phrases up
			String joinPrefix = plugin.storage.messages.getPrefix().joinMessage;
			String joinPhrase = plugin.storage.messages.getPhrases().joinMessage;
			String playerJoinMessage = plugin.storage.messages.getJoinMessage(player).joinMessage;
			String joinMessage;
			//If we're using bungee, we need to send the message over the plugin channel and let Bungee send it

			if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
				joinMessage = PlaceholderAPI.setPlaceholders(Bukkit.getServer().getPlayer(player.uuid), 
						joinPrefix + joinPhrase + ColorfulJoiner.plugin.settings.getServerName() + ", &c" + playerJoinMessage);
			} else
				joinMessage = joinPhrase+playerJoinMessage;
			out.writeUTF(joinMessage);
			ColorfulJoiner.plugin.getServer().sendPluginMessage(ColorfulJoiner.plugin, "ColorfulJoiner", out.toByteArray());
		}
		
		ColorfulJoiner.plugin.getLogger().info(queryPart+" Plugin message recieved?");
	}

}
