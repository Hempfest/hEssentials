package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Checks;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandTpr extends BukkitCommand {

	public CommandTpr() {
		super("tpr");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("teleportrequest", "htpr", "tpre"));
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}

		final Player p = (Player) sender;
		int length = args.length;
		final Config data = new Config("Teleports");
				if (length == 0) {
					sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " &7<&aaccept&7,&cdeny&7> &f&l| &7<&cplayerName&7>");
					return true;
				}
				
				if (length == 1) {
					
					if (args[0].equalsIgnoreCase("accept")) {
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
				    	    		return true;
				    	    	}
				    		    	 
				        		destination.sendMessage(" ");
				    		         sendMessage(p, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
						    			sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
						    			destination.sendMessage(ChatColor.GRAY + "                      " + Strings.getPrefix());
						    			destination.sendMessage(ChatColor.GRAY + "  You are being targeted by a " + ChatColor.RED + "Player");
						    			destination.sendMessage(ChatColor.GRAY + "        Warp delayed " + ChatColor.RED + "10s" + ChatColor.GRAY + ", please stand still.");
						    			destination.sendMessage(" ");
				    		         int taskid = Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable()
				    		         {
				    		           public void run()
				    		           {
				    		        	   
				    		        	   if (Utils.warping.containsKey(destination.getName())) {
				    		        		   Utils.warping.remove(destination.getName());
				    		               
				    		              
				    		               destination.teleport(new Location(w, x, y, z, yaw, pitch));
				    		               Utils.recieved.put(p.getName(), Boolean.valueOf(false));
				    		    			
				    		    			data.getConfig().set("Request-List." + p.getName(), "");
				    		        		data.saveConfig();
				    		        	   }
				    		           }
				    		         }, 200L);
				    		         Utils.warping.put(destination.getName(), Integer.valueOf(taskid));
				      	    	return true;
				        	}
				        	if (Utils.canWarp(destination)) {
				        		destination.teleport(new Location(w, x, y, z, yaw, pitch));
				    			Utils.recieved.put(p.getName(), Boolean.valueOf(false));
				    			sendMessage(p, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
				    			sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
				    			data.getConfig().set("Request-List." + p.getName(), "");
				        		data.saveConfig();
				        		return true;
				        	} 		
				    			
				    		} else
				    		sendMessage(p, ChatColor.RED + "Seems the player whom sent the request has logged off.");
				    		Utils.recieved.put(p.getName(), Boolean.valueOf(false));
				    		data.getConfig().set("Request-List." + p.getName(), "");
				    		data.saveConfig();
				    		return true;
				    	} else
				    	sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
				    	return true;
					}
					
					if (args[0].equalsIgnoreCase("deny")) {
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
				    		return true;
				    	} else
				    	sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
				    	return true;
					}
					
					final Player other = Bukkit.getPlayer(args[0]);
	          		if (other != null) {
	          			if (other.getName().equals(p.getName())) {
	          				sendMessage(p, ChatColor.RED + "You cannot request to teleport to yourself!");
	          				return true;
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
	          		return true;
	          		} else
	          	    sendMessage(p, ChatColor.RED + "No online player by the name of '" + args[0] + "' was found.");
	          		return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
