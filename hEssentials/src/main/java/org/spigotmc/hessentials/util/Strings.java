package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Strings {
	
	
	public static void sendReceivedMenu(Player p) {
		p.sendMessage(getPrefix() + color("&a&lSuccessfully received the Menu Item's."));
	}
	
	public static void sendNoPermission(Player p, String permission) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();	
		p.sendMessage(getPrefix() + color(mess.getString("Messages.Player-Responses.No-Permission").replaceAll("%permnode%", permission)));
	}
	
	public static String getPrefix() { 
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();	
		return color(mess.getString("Messages.Prefix") + " ");
	}
	
	public static String getScorePrefix() { 
		Config score = new Config("Scoreboard");
		FileConfiguration sc = score.getConfig();	
		return sc.getString("Prefix");
	}
	
	public static String getFirstJoinMSG(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.First-Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getSocialSpyOn(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Social-Spy-Activated").replaceAll("%player%", player.getName());
		return color(MSG.replaceAll("%prefix%", getPrefix()));
	}
	public static String getSocialSpyOff(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Social-Spy-Deactivated").replaceAll("%player%", player.getName());
		return color(MSG.replaceAll("%prefix%", getPrefix()));
	}
	
	public static String getJoinMSG(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getLeaveMSG(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Leave").replaceAll("%player%", player.getName());
		return color(MSG);
	}
	
	public static String getMessagesUsed() {
		Config messages = new Config("Messages");
		Config messages_es = new Config("Messages_espanola");
		FileConfiguration config = messages.getConfig();
		if (config.getString("Config.Use").equals("es")) {
			InputStream in = HempfestEssentials.instance.getResource("Messages_espanola.yml");
			if (!messages_es.exists()) {
				Config.copy(in, messages_es.getFile());
			}
			return "Messages_espanola";
			}
		if (config.getString("Config.Use").equals("en")) {
			return "Messages";
		}
			return "Messages";
	}
	
	public static String getInvalidUsage() {
		return "Invalid usage: /";
	}
	
	public static String getChatMuted() {
		return color("&f&oChat: &4Muted");
	}
	
	  public static String infinity() {
		  String inf = null;
		    try {
		    	inf = new String(String.valueOf(Character.toString('\u221e')).getBytes("UTF-8"), "UTF-8");
		    } catch (UnsupportedEncodingException ex) {
		    	inf = "?";
		    	ex.printStackTrace();
		    }
		    return inf;
	  }
	
	public static String getMOTD(Player player) {
		Config motd = new Config("MOTD");
		Config messages = new Config(getMessagesUsed());
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
		Config messages = new Config(getMessagesUsed());
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
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Player-Message-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String messageRecievedMSG(Player player, Player target) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Message-Revieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String replySentMSG(Player player, Player target) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String replyRecievedMSG(Player player, Player target) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Recieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String socialSpyActivateMSG(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();	
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Activated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
	public static String socialSpyDeactivateMSG(Player player) {
		Config messages = new Config(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Deactivated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}
	
public static void goSet(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §3/" + tag + " §7set §3[warpName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Set a warp where you stand.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  public static void goRequest(String Command, String Command2, Player s, Player p) {
	    
		TextComponent text = new TextComponent("§2|§7> §2Click a button to respond. ");
		TextComponent click = new TextComponent("§a[§nACCEPT§a]");
		TextComponent clickb = new TextComponent(" §7| ");
		TextComponent click2 = new TextComponent("§4[§nDENY§4]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Click to accept the request from '" + p.getName() + "'.")).create()));
		click2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Click to deny the request from '" + p.getName() + "'.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command));
		click2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command2));
		text.addExtra(click);
		text.addExtra(clickb);
		text.addExtra(click2);
		p.spigot().sendMessage((BaseComponent)text); 

}
  public static void goDel(CommandSender s, String Command, String tag) {
	    
	  TextComponent click = new TextComponent("§3|§7> §3/" + tag + " §7delete §3[warpName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Remove a set warp.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  public static void goWarp(CommandSender s, String Command, String tag) {
	    
	  TextComponent click = new TextComponent("§3|§7> §3/" + tag + " §7[warpName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Warp to a specified location.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  public static void goList(CommandSender s, String Command, String tag) {
	    
	  TextComponent click = new TextComponent("§3|§7> §3/" + tag + " §7list");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Show your list of set warps.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  
  public static void tpRequest(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §b/" + tag + " §7to §b[playerName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§6Request to teleport to another online player.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  
  public static void tpAccept(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §b/" + tag + " §7deny");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§6Deny a players request to teleport to you.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  
  public static void tpDeny(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §b/" + tag + " §7accept");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§6Accept a players request to teleport to you.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
  
  public static void goTo(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §b/" + tag + " §7to §b[playerName] §b[warpName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§6Goto someone elses private warp of specification.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}

public static void gotoList(CommandSender s, String Command, String tag) {
	    
		TextComponent click = new TextComponent("§3|§7> §b/" + tag + " §7list §b[playerName]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§6Show someone elses list of private warps.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Command));
		s.spigot().sendMessage((BaseComponent)click); 

}
	
	public static String color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
