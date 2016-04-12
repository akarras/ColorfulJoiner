package net.supernoobs.colorfuljoinerbungee.listener;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessageListener implements Listener {
	@EventHandler
	public void plLogin(PluginMessageEvent event){
		if (event.getTag() == "") {
			
		}
	}
}
