package org.spigotmc.hessentials.commands.tprequest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;

public class CommandTpRequest implements CommandExecutor, TabCompleter {
	
	List<String> arguments = new ArrayList<String>();

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}

		final Player p = (Player) sender;
		int length = args.length;
		final Config data = new Config("Teleports");
				if (length == 0) {
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aaccept&7,&cdeny&7> ", "&f&l|", " &7<&cplayerName&7>", "&f&oExample: &7/tpr &aAccept\n&f&oExample: &7/tpr &cDeny", "&f&oExample: &7/tpr &eHempfest");
					return true;
				}
				
				if (length == 1) {
					
					if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a")) {
				    	TpRequest.acceptRequest(p, data);
				    	return true;
					}
					
					if (args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("deny")) {
						TpRequest.denyRequest(p, data);
				    	return true;
					}
					
					final Player other = Bukkit.getPlayer(args[0]);
	          		TpRequest.sendRequest(p, other, data, args[0]);
	          		return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String [] args) {
		
		if (arguments.isEmpty()) {
			arguments.add("accept");;
			arguments.add("deny");
		}
		
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
		
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
	return null;
	}

}
