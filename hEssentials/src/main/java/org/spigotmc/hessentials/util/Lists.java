package org.spigotmc.hessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

public class Lists {

	public static String sendOnlineList() {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(", ");
			string.append(onlinePlayers.getDisplayName() + Strings.color("&7"));
		}
		Config messages = new Config("Messages");
		String online = messages.getConfig().getString("Messages.Player-Responses.Online-List").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = online.replaceAll("%players%", string.toString());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return Strings.color(message);
	}
	public static String sendHelpMenu(Player player) {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(", ");
			string.append(onlinePlayers.getDisplayName() + Strings.color("&7"));
		}
		Config help = new Config("Help");
		Config messages = new Config("Messages");
		String online = help.getConfig().getString("Help-Menu").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = online.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return Strings.color(message);
	}
	public static String sendHelpMenu2(Player player) {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(", ");
			string.append(onlinePlayers.getDisplayName() + Strings.color("&7"));
		}
		Config help = new Config("Help");
		Config messages = new Config("Messages");
		String online = help.getConfig().getString("Help-Menu-PG2").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = online.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return Strings.color(message);
	}
	public static String sendHelpMenu3(Player player) {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(", ");
			string.append(onlinePlayers.getDisplayName() + Strings.color("&7"));
		}
		Config help = new Config("Help");
		Config messages = new Config("Messages");
		String online = help.getConfig().getString("Help-Menu-PG3").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = online.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return Strings.color(message);
	}
}
