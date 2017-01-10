package net.supernoobs.colorfuljoinerbungee.runnables;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.players.Packets;

public class RequestPlayerData implements Runnable {
	private String _player;
	public RequestPlayerData(ProxiedPlayer player) {
		_player = player.getName();
	}
	
	@Override
	public void run() {
		ProxiedPlayer player = ColorfulJoinerBungee.plugin.getProxy().getPlayer(_player);
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("phrasesrequest");
		out.writeUTF(player.getUniqueId().toString());
        player.getServer().sendData("ColorfulJoiner", Packets.RequestPhrase(player.getUniqueId()));
		
	}

}
