package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;

public class Utils {
	
		//Reply hashmap	
		public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
		//Socialspy hashmap  
		public static HashMap<Player, String> socialspy = new HashMap<Player, String>();
	
	public static void msg(Player player, String s) {
		sendMessage(player, s);
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
		messages.reload();
	}
	
	public static void createPlayerConfig(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
			f.set("USERNAME", (Object)player.getName());
			f.set("IP-ADDRESS", (Object)player.getAddress().toString().replace("/", ""));
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
