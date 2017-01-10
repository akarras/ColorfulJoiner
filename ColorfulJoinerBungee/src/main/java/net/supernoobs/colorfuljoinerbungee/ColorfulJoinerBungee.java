package net.supernoobs.colorfuljoinerbungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.supernoobs.colorfuljoinerbungee.commands.SendTest;
import net.supernoobs.colorfuljoinerbungee.listeners.ServerListener;
import net.supernoobs.colorfuljoinerbungee.listeners.PluginMessageListener;
import net.supernoobs.colorfuljoinerbungee.players.PlayerTracker;

public class ColorfulJoinerBungee extends Plugin {
	public PlayerTracker tracker;
	public static ColorfulJoinerBungee plugin;
	public ColorfulMessages messages;
	
    @Override
    public void onEnable() {
    	tracker = new PlayerTracker();
        plugin = this;
        
        messages = new ColorfulMessages();
        
        getProxy().registerChannel("ColorfulJoiner");
        getProxy().getPluginManager().registerListener(this, new ServerListener());
        getProxy().getPluginManager().registerListener(this, new PluginMessageListener());
        getProxy().getPluginManager().registerCommand(this, new SendTest());
    }
    
    @Override
    public void onDisable() {
    	getProxy().getPluginManager().unregisterListeners(this);
    	getProxy().getPluginManager().unregisterCommands(this);
    }

}