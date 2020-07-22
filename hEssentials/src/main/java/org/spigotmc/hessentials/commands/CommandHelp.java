package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Lists;
import org.spigotmc.hessentials.util.variables.Message;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandHelp extends BukkitCommand {

	public CommandHelp() {
		super("help");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhelp"));
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
		
			if (Checks.allPagesActive()) {
				//int pgcount = 3;
				sendMessage(p, Lists.sendHelpMenu(p));
				Message.textRunnable(p, "&fPage [&b&l1&f]", " | Page [&72&f]", " | Page [&73&f]", "Click to goto page 1.", "Click to goto page 2.", "Click to goto page 3.", "help 1", "help 2", "help 3");
			} else if (Checks.twoPagesActive()) {
				//int pgcount = 2;
				sendMessage(p, Lists.sendHelpMenu(p));
				Message.textRunnable(p, "&fPage [" + "&b&l1" + "&f]", " | Page [&72&f]", "Click to goto the next page.", "help 2");
			} else if (Checks.onePageActive()) {
				int pgcount = 1;
			sendMessage(p, Lists.sendHelpMenu(p));
			Message.textHoverable(p, "&fPage [&71&f/&b" + pgcount + "&f]", " | [&b&o~&f]", "There is only one help page." );
			return true;
			}
			return true;
		}
		
		if (length == 1) {
			if (args[0].equalsIgnoreCase("1")) {
				if (Checks.allPagesActive()) {
					//int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textRunnable(p, "&fPage [" + "&b&l1" + "&f]", " | Page [&72&f]", " | Page [&73&f]", "Click to goto page 1.", "Click to goto page 2.", "Click to goto page 3.", "help 1", "help 2", "help 3");
				} else if (Checks.twoPagesActive()) {
					//int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textRunnable(p, "&fPage [" + "&b&l1" + "&f]", " | Page [&72&f]", "Click to goto the next page.", "help 2");
				} else if (Checks.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fPage [&71&f/&b" + pgcount + "&f]", " | [&b&o~&f]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("2")) {
				if (Checks.allPagesActive()) {
				//	int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textRunnable(p, "&fPage [" + "&71" + "&f]", " | Page [&b&l2&f]", " | Page [&73&f]", "Click to goto page 1.", "Click to goto page 2.", "Click to goto page 3.", "help 1", "help 2", "help 3");
				} else if (Checks.twoPagesActive()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textHoverable(p, "&fPage [&72&f/&b" + pgcount + "&f]", " | [&b&o~&f]", "There is only two help pages." );
				} else if (Checks.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fPage [&71&f/&b" + pgcount + "&f]", " | [&b&o~&f]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("3")) {
				if (Checks.allPagesActive()) {
					//int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu3(p));
					Message.textRunnable(p, "&fPage [" + "&71" + "&f]", " | Page [&72&f]", " | Page [&b&l3&f]", "Click to goto page 1.", "Click to goto page 2.", "Click to goto page 3.", "help 1", "help 2", "help 3");
				} else if (Checks.twoPagesActive()) {
					//int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textHoverable(p, "&fPage [" + "&71" + "&f]", " | Page [&b&l2&f]", "You are on the final page." );
				} else if (Checks.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fPage [&71&f/&b" + pgcount + "&f]", " | [&b&o~&f]", "There is only one help page." );
					return true;
				}
			}
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
