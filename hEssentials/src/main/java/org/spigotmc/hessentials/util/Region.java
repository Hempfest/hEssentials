package org.spigotmc.hessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.commands.claim.ClaimUtil;
import org.spigotmc.hessentials.commands.economy.Economy;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;

import addon.chat.hessentials.GroupAPI;
import m.h.clans.listener.ClanAPI;




public class Region extends BukkitRunnable
{
	
	
    private boolean clansEnabled() {
    	if (Bukkit.getPluginManager().isPluginEnabled("Clans")) {
    		return true;
    	}
    	return false;
    }
	
    public void run() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
    		//Config data = new Config("Claims");
    		//FileConfiguration d = data.getConfig();
   
    		Location loc = p.getLocation();
    		
    		Economy.updatePlayerData(p);
    		Utils.updateConfiguration();
    		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
    			
    			GroupAPI chat = new GroupAPI();
    			chat.updateGroup(p);
    		}
    		if (ClaimUtil.isInClaim(loc)) {
    			ClaimUtil.updateClaimUser(p);
    			if (ClaimUtil.isClaimOwner(p)) {
    				if (!Checks.titleSent(p) || !Checks.titleSent4(p) && !Checks.titleSent2(p)) {
    					p.sendTitle(Strings.getOwnerTitle(p), Strings.getOwnerSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
            			Utils.title_claim4.put(p.getName(), Boolean.valueOf(true));
            			p.sendMessage(Strings.color(Strings.getOwnerTitle(p)  + " " + Strings.getOwnerSubTitle(p)));
            			return;
    				}
    				if (Checks.titleSent2(p) && Checks.titleSent(p)) {
    					p.sendTitle(Strings.getOwnerTitle(p), Strings.getOwnerSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
            			p.sendMessage(Strings.color(Strings.getOwnerTitle(p)  + " " + Strings.getOwnerSubTitle(p)));
            			return;
    				}
    			} else
    			if (ClaimUtil.isClaimUser(p)) {
    				if (!Checks.titleSent(p)) {
    					p.sendTitle(Strings.getUserTitle(p), Strings.getUserSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
            			p.sendMessage(Strings.color(Strings.getUserTitle(p)  + " " + Strings.getUserSubTitle(p)));
            			return;
    				}
    				if (Checks.titleSent2(p)) {
    					p.sendTitle(Strings.getUserTitle(p), Strings.getUserSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
            			p.sendMessage(Strings.color(Strings.getUserTitle(p)  + " " + Strings.getUserSubTitle(p)));
            			return;
    				}
    				if (Checks.titleSent(p) && Checks.titleSent4(p)) {
    					p.sendTitle(Strings.getUserTitle(p), Strings.getUserSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim4.put(p.getName(), Boolean.valueOf(false));
            			p.sendMessage(Strings.color(Strings.getUserTitle(p)  + " " + Strings.getUserSubTitle(p)));
            			return;
    				}
    			} else
    				if (!ClaimUtil.isClaimOwner(p) || !ClaimUtil.isClaimUser(p)) {
    					if (!Checks.titleSent(p)) {
    					p.sendTitle(Strings.getRandomTitle(p), Strings.getRandomsubTitle(p), 20 * 1, 20 * 2, 20 * 1);
            			Utils.title_claim.put(p.getName(), Boolean.valueOf(true));
            			Utils.title_claim2.put(p.getName(), Boolean.valueOf(true));
            			p.sendMessage(Strings.color(Strings.getRandomTitle(p)  + " " + Strings.getRandomsubTitle(p)));
            			return;
    					}
    					if (Checks.titleSent(p) && !Checks.titleSent2(p)) {
    						p.sendTitle(Strings.getRandomTitle(p), Strings.getRandomsubTitle(p), 20 * 1, 20 * 2, 20 * 1);
                			Utils.title_claim2.put(p.getName(), Boolean.valueOf(true));
                			p.sendMessage(Strings.color(Strings.getRandomTitle(p)  + " " + Strings.getRandomsubTitle(p)));
                			return;
    					}
    				}
    			
    		} else if (!ClaimUtil.isInClaim(loc)) {
    		
    			if (Checks.titleSent(p)) {
    				p.sendTitle(Strings.getWildTitle(p), Strings.getWildSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
        			Utils.title_claim.put(p.getName(), Boolean.valueOf(false));
        			Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
        			Utils.title_claim4.put(p.getName(), Boolean.valueOf(false));
        			if (clansEnabled()) {
        				if (!ClanAPI.isInClaim(loc)) {
        			p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
        			
        				} else
        					return;
        			} else if (!clansEnabled())
        				p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
        			return;
				}
    			if (Checks.titleSent2(p)) {
    				p.sendTitle(Strings.getWildTitle(p), Strings.getWildSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
    				Utils.title_claim.put(p.getName(), Boolean.valueOf(false));
        			Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
        			Utils.title_claim4.put(p.getName(), Boolean.valueOf(false));
        			if (clansEnabled()) {
        				if (!ClanAPI.isInClaim(loc)) {
        			p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
        			
        				} else
        					return;
        			} else if (!clansEnabled())
        				p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
        			return;
				}
    			if (!Checks.titleSent3(p) && Checks.titleSent2(p) && Checks.titleSent(p)) {
					p.sendTitle(Strings.getWildTitle(p), Strings.getWildSubTitle(p), 20 * 1, 20 * 2, 20 * 1);
					Utils.title_claim.put(p.getName(), Boolean.valueOf(false));
					Utils.title_claim2.put(p.getName(), Boolean.valueOf(false));
    			Utils.title_claim3.put(p.getName(), Boolean.valueOf(true));
    			Utils.title_claim4.put(p.getName(), Boolean.valueOf(false));
    			if (clansEnabled()) {
    				if (!ClanAPI.isInClaim(loc)) {
    			p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
    			
    				} else
    					return;
    			} else if (!clansEnabled())
    				p.sendMessage(Strings.color(Strings.getWildTitle(p) + " " + Strings.getWildSubTitle(p)));
    			return;
				}
    			return;
    		}
    	
    }
    }
}
