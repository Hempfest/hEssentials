package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandFly extends BukkitCommand {

	public CommandFly() {
		super("fly");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hfly"));
		setPermission("hessentials.staff.fly");
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

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			   if (!p.hasPermission("hessentials.staff.fly")) {
   	            Strings.sendNoPermission(p, this.getPermission());
   	            return true;
   	          } 
   	          if (p.getAllowFlight()) {
   	        	 if (p.getGameMode().equals(GameMode.CREATIVE)) {
   	        		p.setGameMode(GameMode.SURVIVAL);
   					sendMessage(p, Strings.getPrefix() + "Now in game-mode survival.");
  	        		  return true;
  	        	  }
   	        	  p.setAllowFlight(false);
   	        	  p.setFlying(false);
   	        	 
   	        	  p.sendMessage(Strings.getPrefix() + "No longer in flight mode.");
   	        	  return true;
   	          } if (!p.getAllowFlight()) {
   	        	  if (p.getGameMode().equals(GameMode.CREATIVE)) {
   	        		  p.sendMessage(Strings.getPrefix() + "You are in creative, simply double-tap your jump key-bind.");
   	        		  return true;
   	        	  }
   	         	  Vector vec = new Vector(0, 2, 0);
   	        	  p.setVelocity(vec);
   	        	  
   	        	  p.setAllowFlight(true);
   	        	  p.setFlying(true);
   	        	  
   	        	  p.sendMessage(Strings.getPrefix() + "Now in flight mode.");
   	        	  return true;
   	          }
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
