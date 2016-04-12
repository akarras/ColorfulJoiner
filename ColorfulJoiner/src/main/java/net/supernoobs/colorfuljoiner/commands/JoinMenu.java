package net.supernoobs.colorfuljoiner.commands;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.supernoobs.colorfuljoiner.ColorfulJoiner;
import net.supernoobs.colorfuljoiner.datatypes.JoinType;

public class JoinMenu implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			//Check that a player ran the command
			//Load messages from storage and create an inventory
			HashMap<String,JoinType> map = ColorfulJoiner.plugin.storage.messages.getJoinMessages();
			Inventory plInventory = Bukkit.createInventory(null, getInventorySize(map.size()+1), "§aJoin Menu");
			//Iterate through the list of messages and add them to our inventory
			for(String Key:map.keySet()) {
				JoinType ty = map.get(Key);
				ItemStack joinMessageItem = joinStack(((Player) sender).getPlayer(),ty,Key);
				plInventory.addItem(joinMessageItem);
			}
			//Add our close item block to the last slot in the inventory
			ItemStack closeMenuItem = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = closeMenuItem.getItemMeta();
			meta.setDisplayName("§cClose Menu");
			closeMenuItem.setItemMeta(meta);
			plInventory.setItem(getInventorySize(map.size()+1)-1, closeMenuItem);
			//Show the inventory to the player
			((Player) sender).openInventory(plInventory);
			return true;
		}
		return false;
	}
	
	private int getInventorySize(int max) {
	    if (max <= 0) return 9;
	    int quotient = (int)Math.ceil(max / 9.0);
	    return quotient > 5 ? 54: quotient * 9;
	}
	
	private ItemStack joinStack(Player pl, JoinType type, String key){
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
