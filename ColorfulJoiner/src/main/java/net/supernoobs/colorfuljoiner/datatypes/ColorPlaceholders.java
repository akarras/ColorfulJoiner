package net.supernoobs.colorfuljoiner.datatypes;

import org.bukkit.entity.Player;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;

public class ColorPlaceholders extends EZPlaceholderHook {
	
	public ColorPlaceholders() {
		super(ColorfulJoiner.plugin, "colorfuljoiner");
		
	}

	@Override
	public String onPlaceholderRequest(Player pl, String variable) {
		ColorPlayer player = ColorfulJoiner.plugin.storage.players.loadPlayer(pl.getUniqueId());
		
		if(variable.equals("join")) {
			return ColorfulJoiner.plugin.storage.messages.getJoinMessage(player).joinMessage;
		} else if (variable.equals("quit")) {
			return ColorfulJoiner.plugin.storage.messages.getJoinMessage(player).quitMessage;
		}
		return null;
	}

}
