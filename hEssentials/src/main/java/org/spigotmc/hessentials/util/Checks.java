package org.spigotmc.hessentials.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;

public class Checks {

	public static boolean canUseScoreboard() {
		Config score = new Config("Scoreboard");
		FileConfiguration s = score.getConfig();
		if (s.getBoolean("Use-Scoreboard?") == true) {
			return true;
		}
		return false;
	}

	public static boolean didRecieve(Player player) {
		return Utils.recieved.containsKey(player.getName())
				? ((Boolean) Utils.recieved.get(player.getName())).booleanValue()
				: false;
	}

	// HUD check
	public static boolean hasScore(Player player) {
		return Utils.hud.containsKey(player.getName()) ? ((Boolean) Utils.hud.get(player.getName())).booleanValue()
				: false;
	}

	// HUD check
	public static boolean hasScore_muted(Player player) {
		return Utils.hud_muted.containsKey(player.getName())
				? ((Boolean) Utils.hud_muted.get(player.getName())).booleanValue()
				: false;
	}


	// HUD check
	public static boolean hasScore_tracking(Player player) {
		return Utils.hud_tracking.containsKey(player.getName())
				? ((Boolean) Utils.hud_tracking.get(player.getName())).booleanValue()
				: false;
	}

	public static boolean allPagesActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")
				&& help.getConfig().getString("Three-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}

	public static boolean onePageActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("no")
				&& help.getConfig().getString("Three-Pages?").equals("no")) {
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

}
