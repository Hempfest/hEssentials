package org.spigotmc.hessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

import m.h.clans.util.ClanAPI;

public class Lists {

	public static String sendOnlineList() {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(Strings.color("&7, "));
			string.append(onlinePlayers.getDisplayName());
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

	public static String sendSB_Line1(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-One").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line2(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Two").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line3(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Three").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line4(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Four").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line5(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Five").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line6(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Six").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line7(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Seven").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line8(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Eight").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line9(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Nine").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}

	public static String sendSB_Line10(Player p) {
		Config sb = new Config("Scoreboard");
		FileConfiguration s = sb.getConfig();
		String pname = s.getString("Line-Ten").replace("%player%", p.getName());
		String online = pname.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
		String max = online.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String prefix = max.replaceAll("%prefix%", Strings.getPrefix());
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			String clan = prefix.replaceAll("%clan%", ClanAPI.getClanName(p));
			String mobkills = clan.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
			String playerkills = mobkills.replaceAll("%playerkills%",
					String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
			return Strings.color(
					playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
		}
		String mobkills = prefix.replaceAll("%mobkills%", String.valueOf(p.getStatistic(Statistic.MOB_KILLS)));
		String playerkills = mobkills.replaceAll("%playerkills%",
				String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS)));
		return Strings
				.color(playerkills.replaceAll("%ip%", String.valueOf(p.getAddress().toString().replaceAll("/", ""))));
	}
}
