package org.spigotmc.hessentials.commands.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;

public class GamemodeCommand extends BukkitCommand implements TabCompleter {

	List<String> arguments = new ArrayList<String>();
	
	public GamemodeCommand() {
		super("gamemode");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("gm", "hgm", "hgamemode"));
		setPermission("hessentials.staff.gamemode");
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
		if (!p.hasPermission("hessentials.staff.gamemode")) {
			Strings.sendNoPermission(p);
			return true;
		}
		int length = args.length;
		if (length == 0) {
			sendMessage(p, Strings.getPrefix() + "Invalid usage: /" + commandLabel
					+ " survival/creative/adventure <playerName>");
			return true;
		}

		if (length == 1) {
			if (args[0].equalsIgnoreCase("creative")) {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Strings.getPrefix() + "Now in game-mode creative.");
				return true;
			}
			if (args[0].equalsIgnoreCase("survival")) {
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Strings.getPrefix() + "Now in game-mode survival.");
				return true;
			}
			if (args[0].equalsIgnoreCase("adventure")) {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Strings.getPrefix() + "Now in game-mode creative.");
				return true;
			}
			sendMessage(p, Strings.getPrefix() + "&4Unknown gamemode type");
			sendMessage(p, Strings.getPrefix() + "Invalid usage: /" + commandLabel
					+ " survival/creative/adventure <playerName>");
			return true;
		}

		if (length == 2) {
			Player target = Bukkit.getPlayer(args[1]);

			if (target != null) {
				if (args[0].equalsIgnoreCase("creative") && p.hasPermission("hessentials.staff.gamemode.other")) {
					target.setGameMode(GameMode.CREATIVE);
					target.sendMessage(Strings.getPrefix() + "Now in game-mode creative.");
					p.sendMessage(Strings.getPrefix() + target.getName() + "'s now in game-mode creative.");
					return true;
				}else
				if (args[0].equalsIgnoreCase("survival") && p.hasPermission("hessentials.staff.gamemode.other")) {
					target.setGameMode(GameMode.SURVIVAL);
					target.sendMessage(Strings.getPrefix() + "Now in game-mode survival.");
					p.sendMessage(Strings.getPrefix() + target.getName() + "'s now in game-mode survival.");
					return true;
				}else
				if (args[0].equalsIgnoreCase("adventure") && p.hasPermission("hessentials.staff.gamemode.other")) {
					target.setGameMode(GameMode.ADVENTURE);
					target.sendMessage(Strings.getPrefix() + "Now in game-mode adventure.");
					p.sendMessage(Strings.getPrefix() + target.getName() + "'s now in game-mode adventure.");
					return true;
				}else if (!p.hasPermission("hessentials.staff.gamemode.other")) {
					Strings.sendNoPermission(p);
					return true;
				}
			} else {
				// player doesnt exist
				return true;
			}
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String [] args) {
		
		if (arguments.isEmpty()) {
			arguments.add("creative"); arguments.add("adventure");
			arguments.add("survival");
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
