package net.supernoobs.colorfuljoinerbungee.commands;

import java.util.concurrent.TimeUnit;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.players.Packets;
import net.supernoobs.colorfuljoinerbungee.runnables.RequestJoin;

public class SendTest extends Command {

	public SendTest() {
		super("sendtest");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		/*for(ServerInfo server:ColorfulJoinerBungee.plugin.getProxy().getServers().values()) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("test");
			server.sendData("ColorfulJoiner", out.toByteArray());
			out = ByteStreams.newDataOutput();
			out.writeUTF("join");
			out.writeUTF("colorfulchew");
			server.sendData("ColorfulJoiner", out.toByteArray());
		}*/
		if(args.length > 0) {
			ProxiedPlayer player = ColorfulJoinerBungee.plugin.getProxy().getPlayer(args[0]);
			
			ColorfulJoinerBungee.plugin.getProxy().getScheduler().schedule(ColorfulJoinerBungee.plugin, 
					new RequestJoin(player), 1, TimeUnit.SECONDS);
			
			//ByteArrayDataOutput out = ByteStreams.newDataOutput();
			//out.writeUTF("phrasesrequest");
			//out.writeUTF(player.getUniqueId().toString());
			//player.getServer().sendData("ColorfulJoiner", Packets.RequestPhrase(player.getUniqueId()));
			//player.sendData("ColorfulJoiner", out.toByteArray());
		}
	}

}
