package net.supernoobs.colorfuljoiner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.JoinType;

public class Inventories {
	public static void ShowJoinMenu(int page, Player player) {
		LinkedHashMap<String,JoinType> map = ColorfulJoiner.plugin.storage.messages.getJoinMessages();
		Inventory plInventory = Bukkit.createInventory(null, getInventorySize(map.size()+1), "§aJoin Menu");
		int inventorySize = getInventorySize(map.size()+1);
		//Iterate through the list of messages and add them to our inventory
		ArrayList<String> keys = new ArrayList<String>(map.keySet());
		for(int x = 0; x<inventorySize-1; x++) {
			if(x>=map.size()) {
				break;
			}
			String tehKey = keys.get(x+page*inventorySize);
			JoinType outputType = map.get(tehKey);
			ItemStack elStacko = joinStack(player,outputType,tehKey);
			plInventory.setItem(x, elStacko);
		}
		ItemStack nextPage = new ItemStack(Material.ARROW);
		ItemMeta meta = nextPage.getItemMeta();
		meta.setDisplayName("§7Next Page");
		nextPage.setItemMeta(meta);
		plInventory.setItem(inventorySize-2, nextPage);
		meta.setDisplayName("§7Previous Page");
		nextPage.setItemMeta(meta);
		if(page == 0) {
			plInventory.setItem(inventorySize-3, nextPage);
		}
		//Add our close item block to the last slot in the inventory
		ItemStack closeMenuItem = new ItemStack(Material.REDSTONE_BLOCK);
		meta = closeMenuItem.getItemMeta();
		meta.setDisplayName("§cClose Menu");
		closeMenuItem.setItemMeta(meta);
		plInventory.setItem(inventorySize-1, closeMenuItem);
		player.openInventory(plInventory);
	}
	
	private static int getInventorySize(int max) {
	    if (max <= 0) return 9;
	    int quotient = (int)Math.ceil(max / 9.0);
	    return quotient > 5 ? 54: quotient * 9;
	}
	
	private static ItemStack joinStack(Player pl, JoinType type, String key){
		if(pl.hasPermission("colorfuljoiner.message."+key.replace(" ", "_"))|pl.hasPermission("colorfuljoiner.message.*")){
		ItemStack stack = new ItemStack(Material.BREAD, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("§a"+key);
		meta.setLore(Arrays.asList(
				"§2Join: "+type.joinMessage,
				"§2Left: §c"+type.quitMessage,
				"§dClick to set"));
		stack.setItemMeta(meta);
		return stack;
		}
		ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("§4No Permission - "+key);
		meta.setLore(Arrays.asList("§2Join: "+type.joinMessage,
				"§2Left: §c"+type.quitMessage,
				"§cYou lack permission for this message"));
		stack.setItemMeta(meta);
		return stack;
		
	}
}
