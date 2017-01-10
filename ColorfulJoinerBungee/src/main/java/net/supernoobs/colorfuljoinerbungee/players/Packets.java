package net.supernoobs.colorfuljoinerbungee.players;

import java.util.UUID;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Packets {
	public static byte[] RequestPhrase(UUID uuid) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("phrasesrequest");
		out.writeUTF(uuid.toString());
		return out.toByteArray();
	}
}
