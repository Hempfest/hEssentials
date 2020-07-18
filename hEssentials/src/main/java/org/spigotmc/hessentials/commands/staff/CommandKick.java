package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandKick extends BukkitCommand {

	public CommandKick() {
		super("kick");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hkick"));
		setPermission("hessentials.staff.kick");
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
		 if (!p.hasPermission(this.getPermission())) {
   		 Strings.sendNoPermission(p, this.getPermission());
   		  return true;
   	  }
   	  if (length == 0) {
   		  
   		  sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + "kick <player> | <reason>");
   		  return true;
   	  }
   	  if (length == 1) {
   		  Player target = Bukkit.getPlayer(args[0]);
   		  if (target.getName().equals("Hempfest") || target.hasPermission(this.getPermission())) {
   			  sendMessage(p, Strings.getPrefix() + "You cannot kick this player!");
   			  return true;
   		  }
   		  target.kickPlayer(Strings.getPrefix() + "\n&c&lYou have been kicked.\n&fREASON:\n&lNONE");
   		  Bukkit.broadcastMessage(Strings.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&cNO REASON&7'.");
   		  return true;
   	  }
   	  if (length > 1) {
   		  StringBuilder rsn = new StringBuilder();
             for (int i = 1; i < args.length; i++)
               rsn.append(String.valueOf(args[i]) + " "); 
             Player target = Bukkit.getPlayer(args[0]);
             if (target.hasPermission(this.getPermission())) {
   			  sendMessage(p, Strings.getPrefix() + "You cannot kick this player!");
   			  return true;
   		  }
             target.kickPlayer(Strings.getPrefix() + "\n&c&lYou have been kicked.\n&c&lREASON:\n&f&l" + rsn);
             Bukkit.broadcastMessage(Strings.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&c" + rsn + "&7'.");
   		  return true;
   	  }

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
