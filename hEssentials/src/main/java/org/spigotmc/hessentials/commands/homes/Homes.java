package org.spigotmc.hessentials.commands.homes;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.variables.Strings;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Homes {

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	public static int countWarps(Player p) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		ConfigurationSection list = config.getConfigurationSection("Private-Homes");
		if (list == null)
			return 0;
		Set<?> warps = list.getKeys(false);
		return warps.size();
	}

	public static int countWarps(OfflinePlayer p) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		ConfigurationSection list = config.getConfigurationSection("Private-Homes");
		if (list == null)
			return 0;
		Set<?> warps = list.getKeys(false);
		return warps.size();
	}

	public static int maxWarps(Player p) {
		int returnv = 0;
		if (p == null)
			return 0;
		for (int i = 100; i >= 0; i--) {
			if (p.hasPermission("hessentials.homes.infinite")) {
				returnv = -1;
				break;
			}
			if (p.hasPermission("hessentials.homes." + i)) {
				returnv = i;
				break;
			}
		}
		if (returnv == -1)
			return 999;

		return returnv;
	}
	
	public static void warp(Player p, String name, String tag) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		final String playername = p.getName().toLowerCase();
		final ConfigurationSection list = config.getConfigurationSection("Private-Homes");
		if (list == null) {
			sendMessage(p, Strings.getInvalidUsage() + tag);
			return;
		}
		if (!list.contains(name)) {
			sendMessage(p, Strings.getPrefix() + "Home '" + name + "' doesn't exist");
			return;
		}

		World world = Bukkit.getWorld(config.getString("Private-Homes." + name + ".world"));
		double x = config.getDouble("warps." + playername + "." + name + ".x");
		double y = config.getDouble("warps." + playername + "." + name + ".y");
		double z = config.getDouble("warps." + playername + "." + name + ".z");
		float yaw = config.getInt("warps." + playername + "." + name + ".yaw");
		float pitch = config.getInt("warps." + playername + "." + name + ".pitch");
		p.teleport(new Location(world, x, y, z, yaw, pitch));

	}

	@SuppressWarnings("deprecation")
	public static void warp(Player p, Player online, String args, String args1, String tag) {

         if (online == null) {
           OfflinePlayer offline = Bukkit.getOfflinePlayer(args);
   		PlayerData homes = new PlayerData(offline.getUniqueId());
   		FileConfiguration config = homes.getConfig();
           ConfigurationSection list = config.getConfigurationSection("Private-Homes");
           if (list == null) {
        	   sendMessage(p, Strings.getInvalidUsage() + tag);
             return;
           } 
           if (!list.contains(args1)) {
             sendMessage(p, Strings.getPrefix() + offline.getName() + " doesn't have a home named: " + args1);
             return;
           } 
           ConfigurationSection warp = list.getConfigurationSection(args1);
           World world = Bukkit.getWorld(warp.getString("world"));
           int x = warp.getInt("x");
           int y = warp.getInt("y");
           int z = warp.getInt("z");
           Location loc = new Location(world, x, y, z);
           p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
           sendMessage(p, Strings.getPrefix() + "You have teleported to " + offline.getName() + "'s home: " + args1);
         } else {
     		PlayerData homes = new PlayerData(p.getUniqueId());
    		FileConfiguration config = homes.getConfig();
           ConfigurationSection list = config.getConfigurationSection("Private-Homes");
           if (list == null) {
        	   sendMessage(p, Strings.getInvalidUsage() + tag);
             return;
           } 
           if (!list.contains(args1)) {
             sendMessage(p, Strings.getPrefix() + online.getName() + " doesn't have a home named: " + args1);
             return;
           } 
           ConfigurationSection warp = list.getConfigurationSection(args1);
           World world = Bukkit.getWorld(warp.getString("world"));
           int x = warp.getInt("x");
           int y = warp.getInt("y");
           int z = warp.getInt("z");
           Location loc = new Location(world, x, y, z);
           p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
           sendMessage(p, Strings.getPrefix() + "You have teleported to " + online.getName() + "'s home: " + args1);
         } 
	}

	public static void setWarp(Player p, String name) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		if (countWarps(p) >= maxWarps(p)) {
			sendMessage(p, Strings.getPrefix()
					+ "You have exceeded your home limit please delete a warp before adding a new one.");
			return;
		}
		if (name.contains(":")) {
			sendMessage(p, Strings.getPrefix() + "Home names cannot contain a colon ':'.");
			return;
		}
		ConfigurationSection list = config.getConfigurationSection("Private-Homes");
		if (list.contains(name)) {
			ConfigurationSection warp = list.getConfigurationSection(name);
			Location loc = p.getLocation();
			warp.set("world", loc.getWorld().getName());
			warp.set("x", Double.valueOf(p.getLocation().getX()));
			warp.set("y", Double.valueOf(p.getLocation().getY()));
			warp.set("z", Double.valueOf(p.getLocation().getZ()));
			warp.set("yaw", Float.valueOf(loc.getYaw()));
			warp.set("pitch", Float.valueOf(loc.getPitch()));
			homes.saveConfig();
			sendMessage(p, Strings.getPrefix() + "Home '" + name + "' has been re-located.");
			return;
		}
		if (list.getString(name) == null)
			list.createSection(name);
		ConfigurationSection warp = list.getConfigurationSection(name);
		Location loc = p.getLocation();
		warp.set("world", loc.getWorld().getName());
		warp.set("x", Double.valueOf(p.getLocation().getX()));
		warp.set("y", Double.valueOf(p.getLocation().getY()));
		warp.set("z", Double.valueOf(p.getLocation().getZ()));
		warp.set("yaw", Float.valueOf(loc.getYaw()));
		warp.set("pitch", Float.valueOf(loc.getPitch()));
		homes.saveConfig();
		sendMessage(p, Strings.getPrefix() + "Home '" + name + "' has been set.");
	}
	
	public static void deleteWarp(Player p, String name) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		ConfigurationSection list = config.getConfigurationSection("Private-Homes");
		if (!list.contains(name)) {
			sendMessage(p, Strings.getPrefix() + "Home '" + name + "' doesn't exist");
			return;
		}
		list.set(name, null);
		homes.saveConfig();
		sendMessage(p, Strings.getPrefix() + "Home '" + name + "' has been deleted");
	}

	public static void listwarps(Player p) {
		PlayerData homes = new PlayerData(p.getUniqueId());
		FileConfiguration config = homes.getConfig();
		ConfigurationSection list = config.getConfigurationSection("Private-Homes");

		if (countWarps(p) == 0) {
			sendMessage(p, Strings.getPrefix() + "Home List &f[&3" + countWarps(p) + "&f/&3"
					+ String.valueOf(maxWarps(p)).replaceAll("999", Strings.infinity()) + "&f]");
			sendMessage(p, ChatColor.GRAY + "[" + ChatColor.RED + "You have no warps set." + ChatColor.GRAY + "]");
			return;
		}

		sendMessage(p, Strings.getPrefix() + "Home List &f[&3" + countWarps(p) + "&f/&3"
				+ String.valueOf(maxWarps(p)).replaceAll("999", Strings.infinity()) + "&f]");
		for (String warp : list.getKeys(false)) {
			TextComponent symbol = new TextComponent(Strings.color("&7- "));

			TextComponent warpname = new TextComponent(Strings.color("&a" + warp));

			warpname.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/home " + warp));
			warpname.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					(new ComponentBuilder("Click to teleport to '" + ChatColor.BOLD + warp + ChatColor.RESET + "'."))
							.create()));

			symbol.addExtra(warpname);

			p.spigot().sendMessage(symbol);
		}

		return;
	}

	@SuppressWarnings("deprecation")
	public static void listwarps(Player p, Player online, String args) {

		if (online == null) {
			OfflinePlayer offline = Bukkit.getOfflinePlayer(args);
			PlayerData homes = new PlayerData(offline.getUniqueId());
			FileConfiguration config = homes.getConfig();
			String playername = offline.getName();
			ConfigurationSection list = config.getConfigurationSection("Private-Homes");
			if (countWarps(offline) == 0) {
				sendMessage(p, Strings.getPrefix() + playername + "'s Home List §f[§3" + countWarps(offline) + "§f/§3"
						+ "?§f]");
				sendMessage(p, ChatColor.GRAY + "[&cThis player has no home set.&7]");
				return;
			}

			sendMessage(p,
					Strings.getPrefix() + playername + "'s Home List §f[§3" + countWarps(offline) + "§f/§3" + "?§f]");
			for (String warp : list.getKeys(false)) {
				TextComponent symbol = new TextComponent(Strings.color("&7- "));

				TextComponent warpname = new TextComponent(Strings.color("&a" + warp));

				warpname.setClickEvent(
						new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/playerhome " + playername + " " + warp));
				warpname.setHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								(new ComponentBuilder(
										"Click to teleport to '" + ChatColor.BOLD + warp + ChatColor.RESET + "'."))
												.create()));
				symbol.addExtra(warpname);

				p.spigot().sendMessage(symbol);
			}
		} else {
			PlayerData homes = new PlayerData(p.getUniqueId());
			FileConfiguration config = homes.getConfig();
			ConfigurationSection list = config.getConfigurationSection("Private-Homes");
			String playername = online.getName();
			if (countWarps(p) == 0) {
				sendMessage(p, Strings.getPrefix() + playername + "'s Home List §f[§3" + countWarps(online) + "§f/§3"
						+ String.valueOf(maxWarps(online)).replaceAll("999", Strings.infinity()) + "§f]");
				sendMessage(p, ChatColor.GRAY + "[&cThis player has no home set.&7]");
				return;
			}

			sendMessage(p, Strings.getPrefix() + playername + "'s Home List §f[§3" + countWarps(online) + "§f/§3"
					+ String.valueOf(maxWarps(online)).replaceAll("999", Strings.infinity()) + "§f]");
			for (String warp : list.getKeys(false)) {
				TextComponent symbol = new TextComponent(Strings.color("&7- "));

				TextComponent warpname = new TextComponent(Strings.color("&a" + warp));

				warpname.setClickEvent(
						new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/playerhome " + playername + " " + warp));
				warpname.setHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								(new ComponentBuilder(
										"Click to teleport to '" + ChatColor.BOLD + warp + ChatColor.RESET + "'."))
												.create()));
				symbol.addExtra(warpname);

				p.spigot().sendMessage(symbol);
			}
		}
		return;
	}

}
