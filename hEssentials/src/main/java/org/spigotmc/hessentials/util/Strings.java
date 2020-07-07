package org.spigotmc.hessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

import net.md_5.bungee.api.ChatColor;

public class Strings {
	
	
	public static void sendReceivedMenu(Player p) {
		p.sendMessage(getPrefix() + color("&a&lSuccessfully received the Menu Item's."));
	}
	
	public static void sendNoPermission(Player p) {
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		p.sendMessage(getPrefix() + color(mess.getString("Messages.Player-Responses.No-Permission")));
	}
	
	public static String getPrefix() { 
		Config messages = new Config("Messages");
		FileConfiguration mess = messages.getConfig();	
		return color(mess.getString("Messages.Prefix") + " ");
	}
	
	public static String getScorePrefix() { 
		Config score = new Config("Scoreboard");
		FileConfiguration sc = score.getConfig();	
		return sc.getString("Prefix");
	}
	
	public static String getFirstJoinMSG(Player player) {
		Config messages = new Config("Messages");
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.First-Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getJoinMSG(Player player) {
		Config messages = new Config("Messages");
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getLeaveMSG(Player player) {
		Config messages = new Config("Messages");
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Leave").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getMOTD(Player player) {
		Config motd = new Config("MOTD");
		Config messages = new Config("Messages");
		FileConfiguration m = motd.getConfig();
		String section = m.getString("Message-of-the-day").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = section.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return message;
	}
	
	public static String getNPB_MOTD(Player player) {
		Config motd = new Config("MOTD");
		Config messages = new Config("Messages");
		FileConfiguration m = motd.getConfig();
		String section = m.getString("MOTD-First-Join").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = section.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return message;
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
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Recieve").replaceAll("%target%", target.getName());
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
