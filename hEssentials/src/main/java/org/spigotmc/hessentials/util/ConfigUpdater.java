package org.spigotmc.hessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.commands.economy.Economy;

import addon.chat.hessentials.GroupAPI;




public class ConfigUpdater extends BukkitRunnable
{
	
	

	
    public void run() {
    	for (Player all : Bukkit.getOnlinePlayers()) {
    		Economy.updatePublicListing(all);
    		Economy.updatePlayerData(all);
    		Utils.updateConfiguration();
    		
    		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
    			
    			GroupAPI chat = new GroupAPI();
    			chat.updateGroup(all);
    		}
    	}
       
    }
}
