package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;

import m.h.clans.util.ClanAPI;

public class Utils {
		
		public static ClanAPI api;
		
		public Utils (ClanAPI api) {
			Utils.api = api;
		}
		
		//Reply hashmap	
		public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
		//Socialspy hashmap  
		public static HashMap<Player, String> socialspy = new HashMap<Player, String>();
	
	public static void msg(Player player, String s) {
		sendMessage(player, s);
	}
	
	public static void registerTabCommand(String cmdName, TabCompleter completer, CommandExecutor executor) {
		try {
		HempfestEssentials.instance.getCommand(cmdName).setExecutor(executor);
		HempfestEssentials.instance.getCommand(cmdName).setTabCompleter(completer);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void registerCommand(BukkitCommand command) {
		try {

			final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			commandMapField.setAccessible(true);

			final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
			commandMap.register(command.getLabel(), command);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void MOTD(Player player) {
		Config motd = new Config("MOTD");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		if (motd.exists()) {
			sendMessage(player, Strings.getMOTD(player));
		} else {
			Config.copy(in3, motd.getFile());
			sendMessage(player, Strings.getPrefix() + "The plugin hasn't yet gotten a chance to create " + '"' + "MOTD.yml" + '"' + "\nCreating it now..");
		}
	}
	
	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	public static void sendPlayerInfo(Player player, Player target) {
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, Strings.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		sendMessage(player, "&f&oIP &7&l| &2&o" + target.getAddress().toString().replace("/", ""));
		sendMessage(player, "&f&oUUID &7&l| &2&o" + target.getUniqueId().toString());
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		sendMessage(player, "&f&oGamemode &7&l| &2&o" + target.getGameMode());
		return;
	}
	
	public static void createScoreboard(Player p) {
		Map<Integer, List<String>> scoreboardData = new HashMap<Integer, List<String>>();
		List<String> title = new ArrayList<String>();
		title.add("&4Hello :)");
		title.add("&3Hello :)");
		title.add("&5Hello :)");
		title.add("&bHello :)");
		List<String> lineOne = new ArrayList<String>();
		lineOne.add("&f<");
		lineOne.add("&f<3");
		lineOne.add("&d<3");
		lineOne.add("&d&l<3");

		scoreboardData.put(1, lineOne);

		new ScoreboardTask(HempfestEssentials.instance, title, scoreboardData, lineOne.size()).runTaskTimer(HempfestEssentials.instance, 0, 8);
	}
	
	public static void sendOfflinePlayerInfo(Player player, OfflinePlayer target) {
		PlayerData data = new PlayerData(target.getUniqueId());
		String IP = data.getConfig().getString("IP-ADDRESS");
		String UUID = target.getUniqueId().toString();
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, Strings.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		sendMessage(player, "&f&oIP &7&l| &2&o" + IP);
		sendMessage(player, "&f&oUUID &7&l| &2&o" + UUID);
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		return;
	}
	
	public static void npbMOTD(Player player) {
		Config motd = new Config("MOTD");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		if (motd.exists()) {
			sendMessage(player, Strings.getNPB_MOTD(player));
		} else {
			Config.copy(in3, motd.getFile());
			sendMessage(player, Strings.getPrefix() + "The plugin hasn't yet gotten a chance to create " + '"' + "MOTD.yml" + '"' + "\nCreating it now..");
		}
	}
	
	public static void reloadConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config motd = new Config("MOTD");
		help.reload();
		motd.reload();
		messages.reload();
	}
	
	public static void createPlayerConfig(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
			f.set("USERNAME", (Object)player.getName());
			f.set("IP-ADDRESS", (Object)player.getAddress().toString().replace("/", ""));
			f.set("Last-Time-Played", 0);
			pd.saveConfig();
		
	}
	
	public static void matchUsername(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.getString("USERNAME").matches(player.getName())) {
			f.set("USERNAME", (Object)player.getName());
			pd.saveConfig();
		}
	}
	
	public static void matchLTP(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.get("Last-Time-Played").equals(player.getLastPlayed())) {
			f.set("USERNAME", (Object)player.getLastPlayed());
			pd.saveConfig();
		}
	}
	
	public static void matchIP(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.getString("IP-ADDRESS").matches(player.getAddress().toString().replace("/", ""))) {
			f.set("IP-ADDRESS", (Object)player.getAddress().toString().replace("/", ""));
			pd.saveConfig();
		}
	}
	
	public static boolean allPagesActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes") && help.getConfig().getString("Three-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}
	
	public static boolean onePageActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("no") && help.getConfig().getString("Three-Pages?").equals("no")) {
			return true;
		}
		return false;
	}
	
	public static boolean twoPagesActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}
	
	public static void defaultConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config motd = new Config("MOTD");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		Config.copy(in, messages.getFile());
		Config.copy(in2, help.getFile());
		Config.copy(in3, motd.getFile());
	}

	public static void createConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config motd = new Config("MOTD");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		if (!messages.exists()) {
			Config.copy(in, messages.getFile());
		}
		if (!help.exists()) {
			Config.copy(in2, help.getFile());
		}
		if (!motd.exists()) {
			Config.copy(in3, motd.getFile());
		}

	}

	public static int convertDouble(Double d) {
		return d.intValue();
	}

}
