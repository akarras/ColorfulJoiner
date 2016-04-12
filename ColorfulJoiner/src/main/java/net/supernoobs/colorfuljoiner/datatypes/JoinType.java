package net.supernoobs.colorfuljoiner.datatypes;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class JoinType implements ConfigurationSerializable {
	public String joinMessage;
	public String quitMessage;
	
	
	
	public Map<String, Object> serialize() {
		Map<String, Object> serialize = new HashMap<String, Object>();
		serialize.put("join", joinMessage);
		serialize.put("quit", quitMessage);
		return serialize;
	}
}
