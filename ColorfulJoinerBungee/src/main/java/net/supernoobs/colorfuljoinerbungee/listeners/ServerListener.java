package net.supernoobs.colorfuljoinerbungee.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.supernoobs.colorfuljoinerbungee.ColorfulJoinerBungee;
import net.supernoobs.colorfuljoinerbungee.runnables.RequestJoin;
import net.supernoobs.colorfuljoinerbungee.runnables.RequestPlayerData;

public class ServerListener implements Listener {
	HashMap<UUID,String> playerMap;
	public ServerListener() {
		playerMap = new HashMap<UUID,String>();
	}
	
	
    @EventHandler
    public void onServerConnected(final ServerConnectedEvent event) {
        ColorfulJoinerBungee.plugin.tracker.playerJoined(event.getPlayer());
        //new RequestPlayerData(event.getPlayer(), 1, TimeUnit.SECONDS)
        ColorfulJoinerBungee.plugin.getProxy().getScheduler().schedule(ColorfulJoinerBungee.plugin, new RequestJoin(event.getPlayer()), 10, TimeUnit.SECONDS);
        /*Server server = event.getServer();
        server.sendData("ColorfulJoiner", Packets.RequestPhrase(event.getPlayer().getUniqueId()));
        ColorfulJoinerBungee.plugin.getLogger().info("Connected thing "+
        event.getPlayer().getName()+" /"+event.getServer().getInfo().getName());*/
    }
    
    @EventHandler
    public void onServerSwitch(final ServerSwitchEvent event) {
    	ColorfulJoinerBungee.plugin.tracker.playerSwitch(event.getPlayer());
    }
    
    @EventHandler
    public void playerDisconnectEvent(final ServerDisconnectEvent event) {
    	ColorfulJoinerBungee.plugin.tracker.playerLeft(event.getPlayer());
    }
}