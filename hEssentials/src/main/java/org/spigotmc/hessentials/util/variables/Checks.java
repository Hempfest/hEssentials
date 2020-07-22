package org.spigotmc.hessentials.util.variables;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Utils;

import m.h.clans.Clans;
import net.milkbowl.vault.economy.Economy;

public class Checks {
	
	private static Economy econ = null;
	
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
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vault")) {
			if (HempfestEssentials.getInstance().eco.isEnabled()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean setupEconomy() {
        if (Clans.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Clans.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
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
	
	public static boolean allPages_STAFF_Active() {
		Config help = new Config("Staff_Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")
				&& help.getConfig().getString("Three-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}

	public static boolean onePage_STAFF_Active() {
		Config help = new Config("Staff_Help");
		if (help.getConfig().getString("Two-Pages?").equals("no")
				&& help.getConfig().getString("Three-Pages?").equals("no")) {
			return true;
		}
		return false;
	}

	public static boolean twoPages_STAFF_Active() {
		Config help = new Config("Staff_Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}

}
