package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

public class Utils {
	
		//Reply hashmap	
		public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
		//Socialspy hashmap  
		public static HashMap<Player, String> socialspy = new HashMap<Player, String>();
	
	public static void msg(Player p, String s) {
		p.sendMessage(s);
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

	public static void reloadConfiguration() {
		Config messages = new Config("Messages");
		messages.reload();
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
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		Config.copy(in, messages.getFile());
		Config.copy(in2, help.getFile());
	}

	public static void createConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		if (!messages.exists()) {
			Config.copy(in, messages.getFile());
		}
		if (!help.exists()) {
			Config.copy(in2, help.getFile());
		}

	}

	public static int convertDouble(Double d) {
		return d.intValue();
	}

}
