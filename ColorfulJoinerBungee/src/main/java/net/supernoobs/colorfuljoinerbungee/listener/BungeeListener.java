package net.supernoobs.colorfuljoinerbungee.listener;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeListener implements Listener {
	HashMap<UUID,String> playerMap;
	public BungeeListener() {
		playerMap = new HashMap<UUID,String>();
	}
	
	
    @EventHandler
    public void onServerConnected(final ServerConnectedEvent event) {
        event.getPlayer().sendMessage(new ComponentBuilder("Welcome to " + event.getServer().getInfo().getName() + "!").color(ChatColor.GREEN).create());
    }
    
    @EventHandler
    public void onServerSwitch(final ServerSwitchEvent event) {
    	event.getPlayer().getServer().getInfo().getName();
    	
    }
}