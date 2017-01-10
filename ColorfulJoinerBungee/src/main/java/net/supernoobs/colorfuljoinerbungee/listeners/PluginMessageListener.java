package net.supernoobs.colorfuljoinerbungee.listeners;

import java.util.UUID;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.datatypes.JoinType;

public class PluginMessageListener implements Listener {
	public PluginMessageListener() {
		ColorfulJoinerBungee.plugin.getLogger().info("Registering plugin event");
	}
	@EventHandler
	public void pluginMessage(PluginMessageEvent event){
		if (event.getTag().equals("ColorfulJoiner")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
			String packetType = in.readUTF();
			if(packetType.equals("test")) {
				ColorfulJoinerBungee.plugin.getLogger().info("Test recieved");
				return;
			}
			if(packetType.equals("phrases")) {
				//Update player join message packet
				String uuid = in.readUTF();
				String join = in.readUTF();
				String quit = in.readUTF();
				JoinType type = new JoinType();
				type.joinMessage = join;
				type.quitMessage = quit;
				ColorfulJoinerBungee.plugin.tracker.setPlayerPhrase(UUID.fromString(uuid), type);
				ColorfulJoinerBungee.plugin.getLogger().info("uuid: "+uuid+" Join: "+join+" quit: "+quit);
				return;
			}
			if(packetType.equals("joinresponse")) {
				String joinResponse = in.readUTF();
				ColorfulJoinerBungee.plugin.getProxy().broadcast(new TextComponent(joinResponse));
				ColorfulJoinerBungee.plugin.getLogger().info("This is amazing");
			}
			
			//String playerPrefix = in.readUTF();
			//String playerMessage = in.readUTF();
			//TextComponent text = new TextComponent(messageType+playerPrefix+playerMessage);
			
			//ColorfulJoinerBungee.plugin.getProxy().broadcast(text);
		}
	}
}
