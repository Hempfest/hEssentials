package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Component;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandBan extends BukkitCommand {

	public CommandBan() {
		super("ban");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hban"));
		setPermission("hessentials.staff.ban");
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}

		Player p = (Player) sender;
		Utils u = new Utils();
		int length = args.length;
		if (!p.hasPermission(this.getPermission())) {
  		  Strings.sendNoPermission(p, this.getPermission());
  		  return true;
  	  }
  	  if (length == 0) {
  		u.sendComponent(p, Component.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&l|", " &7<banReason&7>", "&f&oExample: &7/ban &eHempfest", "&f&oExample: &7/ban &eHempfest &cCheating"));
  		  return true;
  	  }
  	  if (length == 1) {
  		  Player target = Bukkit.getPlayer(args[0]);
  		  if (target == null) {
  			  OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
  			  if (Bukkit.getBanList(Type.NAME).getBanEntries().contains((Object)otarget.getName())) {
  				  sendMessage(p, Strings.getPrefix() + "&cThis player is already banned.");
  				  return true;
  			  }
  			  return true;
  		  }
  		  if (target.hasPermission(this.getPermission())) {
  			  p.sendMessage(Strings.getPrefix() + "You cannot ban this player!");
  			  return true;
  		  }
  		  target.kickPlayer(Strings.getPrefix() + "\n&c&lYou have been banned.\n&fREASON:\n&lNONE");
  		  Bukkit.broadcastMessage(Strings.getPrefix() + " Player '&c" + target.getName() + "&7' banned for '&cNO REASON&7'.");
  		  Bukkit.getBanList(Type.NAME).addBan(target.getName(), Strings.color("&4&lNO REASON"), null, p.getName());
  		  return true;
  	  }
  	  if (length > 1) {
  		  StringBuilder rsn = new StringBuilder();
            for (int i = 1; i < args.length; i++)
              rsn.append(String.valueOf(args[i]) + " "); 
            Player target = Bukkit.getPlayer(args[0]);
            if (target.hasPermission(this.getPermission())) {
  			  p.sendMessage(Strings.getPrefix() + "You cannot ban this player!");
  			  return true;
  		  }
            target.kickPlayer(Strings.getPrefix() + "\n&c&lYou have been banned.\n&c&lREASON:\n&f&l" + rsn);
            Bukkit.broadcastMessage(Strings.getPrefix() + " Player '&c" + target.getName() + "&7' banned for '&c" + rsn + "&7'.");
            Bukkit.getBanList(Type.NAME).addBan(target.getName(), Strings.color("&4&l" + rsn), null, p.getName());
  		  return true;
  	  }

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
