package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Lists;
import org.spigotmc.hessentials.util.variables.Message;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandStaffHelp extends BukkitCommand {

	public CommandStaffHelp() {
		super("staffhelp");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hstaffhelp"));
		setPermission("hessentials.staff");
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
		
			if (Checks.allPages_STAFF_Active()) {
				int pgcount = 3;
				sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
				Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "staffhelp 2");
			} else if (Checks.twoPages_STAFF_Active()) {
				int pgcount = 2;
				sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
				Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "staffhelp 2");
			} else if (Checks.onePage_STAFF_Active()) {
				int pgcount = 1;
			sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
			Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
			return true;
			}
			return true;
		}
		
		if (length == 1) {
			if (args[0].equalsIgnoreCase("1")) {
				if (Checks.allPages_STAFF_Active()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
					Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "staffhelp 2");
				} else if (Checks.twoPages_STAFF_Active()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
					Message.textRunnable(p, "&fCurrently on page [1/" + pgcount + "]", " | &lPAGE 2 >>", "Click to goto the next page.", "staffhelp 2");
				} else if (Checks.onePage_STAFF_Active()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("2")) {
				if (Checks.allPages_STAFF_Active()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelp_STAFF_Menu2(p));
					Message.textRunnable(p, "&fCurrently on page [2/" + pgcount + "]", " | &lPAGE 3 >>", "Click to goto the next page.", "staffhelp 3");
				} else if (Checks.twoPages_STAFF_Active()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelp_STAFF_Menu2(p));
					Message.textHoverable(p, "&fCurrently on page [2/" + pgcount + "]", " | [O]", "There is only two help pages." );
				} else if (Checks.onePage_STAFF_Active()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
			} else if (args[0].equalsIgnoreCase("3")) {
				if (Checks.allPages_STAFF_Active()) {
					int pgcount = 3;
					sendMessage(p, Lists.sendHelp_STAFF_Menu3(p));
					Message.textHoverable(p, "&fCurrently on page [3/" + pgcount + "]", " | [O]", "You are on the last help page." );
				} else if (Checks.twoPages_STAFF_Active()) {
					int pgcount = 2;
					sendMessage(p, Lists.sendHelp_STAFF_Menu2(p));
					Message.textHoverable(p, "&fCurrently on page [2/" + pgcount + "]", " | [O]", "There is only two help pages." );
				} else if (Checks.onePage_STAFF_Active()) {
					int pgcount = 1;
					sendMessage(p, Lists.sendHelp_STAFF_Menu(p));
					Message.textHoverable(p, "&fCurrently on page [1/" + pgcount + "]", " | [O]", "There is only one help page." );
					return true;
				}
			}
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
