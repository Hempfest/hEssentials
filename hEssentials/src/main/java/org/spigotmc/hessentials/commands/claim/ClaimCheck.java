package org.spigotmc.hessentials.commands.claim;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;




public class ClaimCheck extends BukkitRunnable
{
	
    
    public void run() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
    		//Config data = new Config("Claims");
    		//FileConfiguration d = data.getConfig();
    		Location loc = p.getLocation();
    		ClaimUtil.updateClaimUser(p);
    		if (ClaimUtil.isInClaim(loc) && ClaimUtil.isClaimOwner(p)) {
    			p.sendTitle("You are owner", ClaimUtil.getClaimOwner(loc), 20 * 1, 20 * 2, 20 * 1);
    			
    		}
    		if (ClaimUtil.isInClaim(loc) && ClaimUtil.isClaimUser(p)) {
    			p.sendTitle("You are a User", ClaimUtil.getClaimOwner(loc), 20 * 1, 20 * 2, 20 * 1);
    		}
    		if (ClaimUtil.isInClaim(loc) && !ClaimUtil.isClaimUser(p) && !ClaimUtil.isClaimOwner(p)) {
    			p.sendTitle("You are a random", ClaimUtil.getClaimOwner(loc), 20 * 1, 20 * 2, 20 * 1);
    		}
    		if (!ClaimUtil.isInClaim(loc)) {
    			p.sendTitle("WILD", ClaimUtil.getClaimOwner(loc), 20 * 1, 20 * 2, 20 * 1);
    			
        }
    	
    }
    }
}
