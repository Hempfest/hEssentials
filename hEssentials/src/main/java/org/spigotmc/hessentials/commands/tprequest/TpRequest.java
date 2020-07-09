package org.spigotmc.hessentials.commands.tprequest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Checks;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class TpRequest {
	
	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	public static void acceptRequest(final Player p, final Config data) {
		if (Checks.didRecieve(p)) {
			String dest = data.getConfig().getString("Request-List." + p.getName());
			final Player destination = Bukkit.getPlayer(dest);
    		if (destination != null) {
    			Location desty = p.getLocation();
    			final double x = desty.getX();
    			final double y = desty.getY();
    			final double z = desty.getZ();
    			final float yaw = desty.getYaw();
    			final float pitch = desty.getPitch();
    			final World w = desty.getWorld();
    			
        	if(!Utils.canWarp(destination)) {
        		if (destination.hasPermission("hessentials.teleport.fast")) {
        			destination.sendMessage(Strings.getPrefix() + ChatColor.GREEN + " Acting swifly (With permisson)");
    	    		destination.teleport(new Location(w, x, y, z, yaw, pitch));
    	    		data.getConfig().set("Request-List." + p.getName(), "");
    	    		Utils.recieved.put(p.getName(), Boolean.valueOf(false));
            		data.saveConfig();
    	    		return;
    	    	}
    		    	 
        		destination.sendMessage(" ");
    		         sendMessage(destination, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
		    			sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
		    			destination.sendMessage(ChatColor.GRAY + "                         " + Strings.getPrefix());
		    			destination.sendMessage(ChatColor.GRAY + "         You are being targeted by a " + ChatColor.RED + "Player");
		    			destination.sendMessage(ChatColor.GRAY + "                   Warp delayed " + ChatColor.RED + "10s" + ChatColor.GRAY + "            ");
		    			destination.sendMessage(" ");
		    		Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable() {
    		           public void run()
    		           {
    		        	   
    		               
    		              
    		               destination.teleport(new Location(w, x, y, z, yaw, pitch));
    		               Utils.recieved.put(p.getName(), Boolean.valueOf(false));
    		    			
    		    			data.getConfig().set("Request-List." + p.getName(), "");
    		        		data.saveConfig();
    		        	   
    		           }
    		         }, 200L);
      	    	return;
        	}
        	if (Utils.canWarp(destination)) {
        		destination.teleport(new Location(w, x, y, z, yaw, pitch));
    			Utils.recieved.put(p.getName(), Boolean.valueOf(false));
    			sendMessage(destination, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
    			sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
    			data.getConfig().set("Request-List." + p.getName(), "");
        		data.saveConfig();
        		return;
        	} 		
    			
    		} else
    		sendMessage(p, ChatColor.RED + "Seems the player whom sent the request has logged off.");
    		Utils.recieved.put(p.getName(), Boolean.valueOf(false));
    		data.getConfig().set("Request-List." + p.getName(), "");
    		data.saveConfig();
    		return;
    	} else
    	sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
    	return;
	}
	
	public static void denyRequest(final Player p, Config data) {
		if (Checks.didRecieve(p)) {
    		String dest = data.getConfig().getString("Request-List." + p.getName());
    		Player destination = Bukkit.getPlayer(dest);
    		if (destination != null) {
    			Utils.recieved.put(p.getName(), Boolean.valueOf(false));
    			sendMessage(destination, ChatColor.RED + "'" + p.getName() + "' has denied your request!");
    			sendMessage(p, ChatColor.RED + "Request to teleport from '" + destination.getName() + "' has been denied.");
    			data.getConfig().set("Request-List." + p.getName(), "");
    			data.saveConfig();
    		} else
    		sendMessage(p, ChatColor.RED + "Seems the player whom sent the request has logged off.");
    		data.getConfig().set("Request-List." + p.getName(), "");
    		data.saveConfig();
    		return;
    	} else
    	sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
		return;
	}
	
	public static void sendRequest(final Player p, final Player other, final Config data, String args) {
		if (other != null) {
  			if (other.getName().equals(p.getName())) {
  				sendMessage(p, ChatColor.RED + "You cannot request to teleport to yourself!");
  				return;
  			}
  		if (!Checks.didRecieve(other)) {
  			Utils.recieved.put(other.getName(), Boolean.valueOf(true));
  			data.getConfig().set("Request-List." + other.getName(), p.getName());
  			data.saveConfig();
  			p.sendMessage(" ");
  			sendMessage(p, ChatColor.GREEN + "Sent a teleport request to: " + ChatColor.GRAY + other.getName() + "\nRequest will expire in 1m 30s.");
  			p.sendMessage(" ");
  			other.sendMessage(" ");
  			sendMessage(other, ChatColor.GREEN + "'" + p.getName() + "' wishes to teleport to you.");
  			Strings.goRequest("/tpr accept", "/tpr deny", p, other);
  			sendMessage(other, "Request expires in:" + ChatColor.GRAY + " 1m 30s.");
  			other.sendMessage(" ");
  			Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable() {

				public void run() {
					if (Checks.didRecieve(other)) {
					sendMessage(other, ChatColor.GRAY + "Teleport request has expired.");
				sendMessage(p, ChatColor.GREEN + "You may now send another teleport request.");
					Utils.recieved.put(other.getName(), Boolean.valueOf(false));
					data.getConfig().set("Request-List." + other.getName(), "");
					data.saveConfig();
					}
				}
  				
  			}, 1800L);
  		} else
  		sendMessage(p, ChatColor.RED + "'" + other.getName() + '"' + " already has a teleport request pending.");
  		return;
  		} else
  	    sendMessage(p, ChatColor.RED + "No online player by the name of '" + args + "' was found.");
	}
	
	
}
