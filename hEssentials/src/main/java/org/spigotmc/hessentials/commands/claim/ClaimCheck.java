package org.spigotmc.hessentials.commands.claim;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;




public class ClaimCheck extends BukkitRunnable
{
	
    
    public void run() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
    		//Config data = new Config("Claims");
    		//FileConfiguration d = data.getConfig();
    		Location loc = p.getLocation();
    		ClaimUtil.updateClaimUser(p);
    			if (ClaimUtil.isInClaim(loc) && ClaimUtil.isClaimOwner(p) && !Checks.titleSent(p)) {
        			p.sendTitle(Strings.getOwnerTitle(p), Strings.getOwnerSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
        			p.sendMessage(Strings.color(Strings.getOwnerTitle(p)  + " " + Strings.getOwnerSubTitle(p)));
        		}
        		if (ClaimUtil.isInClaim(loc) && ClaimUtil.isClaimUser(p) && !Checks.titleSent(p)) {
        			p.sendTitle(Strings.getUserTitle(p), Strings.getUserSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
        			p.sendMessage(Strings.color(Strings.getUserTitle(p)  + " " + Strings.getUserSubTitle(p)));
        		}
        		if (ClaimUtil.isInClaim(loc) && !ClaimUtil.isClaimUser(p) && !ClaimUtil.isClaimOwner(p) && Checks.titleSent(p) && !Checks.titleSent2(p)) {
        			p.sendTitle(Strings.getRandomTitle(p), Strings.getRandomsubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim2.put(p.getName(), Boolean.valueOf(true));
        			p.sendMessage(Strings.color(Strings.getRandomTitle(p)  + " " + Strings.getRandomsubTitle(p)));
        		}
        		if (ClaimUtil.isInClaim(loc) && !ClaimUtil.isClaimUser(p) && !ClaimUtil.isClaimOwner(p) && !Checks.titleSent(p)) {
        			p.sendTitle(Strings.getRandomTitle(p), Strings.getRandomsubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
        			p.sendMessage(Strings.color(Strings.getRandomTitle(p)  + " " + Strings.getRandomsubTitle(p)));
        		}
        		
        	
        		if (!ClaimUtil.isInClaim(loc) && Checks.titleSent(p)) {
        			p.sendTitle(Strings.getWildTitle(p), Strings.getWildSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim.put(p.getName(), Boolean.valueOf(false));
        			Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
        			p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
            }
    	
    }
    }
}
