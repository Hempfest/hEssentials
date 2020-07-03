package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Lists;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class HelpCommand extends BukkitCommand {

	public HelpCommand() {
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
		
			if (Utils.allPagesActive()) {
				int pgcount = 3;
				sendMessage(p, Lists.sendHelpMenu(p));
				Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "help 2");
				return true;
			} else if (Utils.twoPagesActive()) {
				int pgcount = 2;
				sendMessage(p, Lists.sendHelpMenu(p));
				Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "help 2");
				return true;
			} else if (Utils.onePageActive()) {
				int pgcount = 1;
			sendMessage(p, Lists.sendHelpMenu(p));
			Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
			return true;
			}
			return true;
		}
		
		if (length == 1) {
			if (args[0].equalsIgnoreCase("1")) {
				if (Utils.allPagesActive()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "help 2");
				} else if (Utils.twoPagesActive()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "help 2");
				} else if (Utils.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("2")) {
				if (Utils.allPagesActive()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textRunnable(p, "&fCurrently on page [2/" + pgcount + "]", " | &lPAGE 3 >>", "Click to goto the next page.", "help 3");
				} else if (Utils.twoPagesActive()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textHoverable(p, "&fCurrently on page [2/" + pgcount + "]", " | [O]", "There is only two help pages." );
				} else if (Utils.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("3")) {
				if (Utils.allPagesActive()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelpMenu3(p));
					Message.textHoverable(p, "&fCurrently on page [3/" + pgcount + "]", " | [O]", "You are on the last help page." );
				} else if (Utils.twoPagesActive()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelpMenu2(p));
					Message.textHoverable(p, "&fCurrently on page [2/" + pgcount + "]", " | [O]", "There is only two help pages." );
				} else if (Utils.onePageActive()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelpMenu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
				return true;
			}
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
