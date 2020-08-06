package org.spigotmc.hessentials.util.variables;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Utils;

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
	
	public static boolean isInt(String e) {
		try {
			Integer.parseInt(e);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean economyEnabled() {
		Config me = new Config("hEconomy");
		if (me.getConfig().getBoolean("Use-GoldEco?") == true) {
		return true;
		} else if (me.getConfig().getBoolean("Use-GoldEco?") == false) {
			return false;
		}
	return false;
	}
	
	//Check if is double
	public static boolean isDouble(String e) {
		try {
			Double.parseDouble(e);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean checkforPH() {
	      if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
	    	  
	            return true;
	        } else {
	        	return false;
	            
	            
	        }
	}
	
	// HUD check
	public static boolean hasScore(Player player) {
		return Utils.hud.containsKey(player.getName()) ? ((Boolean) Utils.hud.get(player.getName())).booleanValue()
				: false;
	}
	
	public static boolean titleSent(Player player) {
		return Utils.title_claim.containsKey(player.getName()) ? ((Boolean) Utils.title_claim.get(player.getName())).booleanValue()
				: false;
	}
	
	public static boolean titleSent2(Player player) {
		return Utils.title_claim2.containsKey(player.getName()) ? ((Boolean) Utils.title_claim2.get(player.getName())).booleanValue()
				: false;
	}
	
	public static boolean titleSent3(Player player) {
		return Utils.title_claim3.containsKey(player.getName()) ? ((Boolean) Utils.title_claim3.get(player.getName())).booleanValue()
				: false;
	}
	
	public static boolean titleSent4(Player player) {
		return Utils.title_claim4.containsKey(player.getName()) ? ((Boolean) Utils.title_claim4.get(player.getName())).booleanValue()
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

}
