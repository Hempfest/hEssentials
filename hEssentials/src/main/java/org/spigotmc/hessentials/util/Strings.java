package org.spigotmc.hessentials.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;

import net.md_5.bungee.api.ChatColor;

public class Strings {
	
	
	public static void sendReceivedMenu(Player p) {
		p.sendMessage(getPrefix() + color("&a&lSuccessfully received the Menu Item's."));
	}
	
	public static void sendNoPermission(Player p) {
		p.sendMessage(getPrefix() + color("&4You do not have permission."));
	}
	
	public static String getPrefix() { 
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		return color(mess.getString("Messages.Prefix") + " ");
	}
	
	public static String messageSentMSG(Player player, Player target) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Player-Message-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String messageRecievedMSG(Player player, Player target) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Message-Revieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String replySentMSG(Player player, Player target) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String replyRecievedMSG(Player player, Player target) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Revieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String socialSpyActivateMSG(Player player) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Activated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String socialSpyDeactivateMSG(Player player) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Deactivated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
