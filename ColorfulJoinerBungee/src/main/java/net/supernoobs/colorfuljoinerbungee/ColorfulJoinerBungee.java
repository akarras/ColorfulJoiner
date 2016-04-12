package net.supernoobs.colorfuljoinerbungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.supernoobs.colorfuljoinerbungee.listener.BungeeListener;
import net.supernoobs.colorfuljoinerbungee.players.PlayerTracker;

public class ColorfulJoinerBungee extends Plugin {
	public static PlayerTracker tracker;
	
    @Override
    public void onEnable() {
    	tracker = new PlayerTracker();
        getProxy().getPluginManager().registerListener(this, new BungeeListener());
    }

}