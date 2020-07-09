package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;


public class CommandUnban extends BukkitCommand {

	public CommandUnban() {
		super("unban");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hunban"));
		setPermission("hessentials.staff.unban");
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
		int length = args.length;
		 if (!p.hasPermission(this.getPermission())) {
   		  sendMessage(p, Strings.getPrefix() + " You are not authorized!");
   		  return true;
   	  }
   	  if (length == 0) {
   		  sendMessage(p, Strings.getPrefix() + " /unban <player>");
   		  sendMessage(p, "&cBan-List: &7" + Bukkit.getBanList(Type.NAME).getBanEntries().toString());
   		  return true;
   	  }
   	  if (length == 1) {
   		  OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
   		  if (!Bukkit.getBanList(Type.NAME).isBanned(target.getName())) {
   			  sendMessage(p, Strings.getPrefix() + " Player ban entry not found!");
   			  return true;
   		  }
   		  sendMessage(p, Strings.getPrefix() + " Player '§c" + target.getName() + "§7' unbanned.");
   		  Bukkit.getBanList(Type.NAME).pardon(target.getName());
   		  return true;
   	  }

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
