package net.supernoobs.colorfuljoiner.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.ColorPlayer;

public class InventoryListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		if (inventory.getName().equals("§aJoin Menu")) {
			ItemStack item = event.getCurrentItem();
			if(item == null) return;
			if(item.getData().getItemType() == Material.BREAD) {
				if(item.hasItemMeta()) {
					//Check what join values we're looking for
					ItemMeta meta = item.getItemMeta();
					String selectedJoin = meta.getDisplayName();
					selectedJoin = ChatColor.stripColor(selectedJoin);
					//Check if the player has permission
					Player player = (Player) event.getWhoClicked();
					if(!player.hasPermission("colorfuljoiner.message."+selectedJoin.replace(" ", "_"))&&
							!player.hasPermission("colorfuljoiner.message.*")){
						player.sendMessage(ColorfulJoiner.plugin.storage.pluginMessages.noPermission);
					}
					
					//save the message to the player
					
					ColorPlayer colorPlayer = ColorfulJoiner.plugin.storage.players.loadPlayer(player.getUniqueId());
					colorPlayer.joinMessage = selectedJoin;
					ColorfulJoiner.plugin.storage.players.savePlayer(colorPlayer);
					
					//Message the player about their new message
					player.sendMessage("§aYou have set your join message to "+selectedJoin);
					
					player.closeInventory();
				}
			}
			if(item.getData().getItemType() == Material.REDSTONE_BLOCK) {
				Player pl = (Player) event.getWhoClicked();
				pl.closeInventory();
			}
			event.setCancelled(true);
		}
	}
}
