package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;


public class CommandHelp extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandHelp() {
		super("help");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhelp"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	public boolean isInt(String e) {
		try {
			Integer.parseInt(e);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Help)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		Utils u = new Utils();
		DataManager dm = new DataManager();
		Config mess = dm.getMisc("Help");
		List<String> help = mess.getConfig().getStringList("Help-Menu");
		List<String> staffhelp = mess.getConfig().getStringList("Staff-Help-Menu");
		int pgAmnt = mess.getConfig().getInt("Amount-per-page");
		if (length == 0) {
			u.paginateHelp(p, help, 1, pgAmnt);
			return true;
		}

		if (length == 1) {
			try {
				if (args[0].equalsIgnoreCase("staff")) {
				if (p.hasPermission("hessentials.staff")) {
				u.paginateStaffHelp(p, staffhelp, 1, pgAmnt);
				return true;
				} else {
					sendMessage(p, api.lib.getPrefix() + "Invalid page number.");
					return true;
				}
			}
			int amnt = Integer.valueOf(args[0]);
			if (!isInt(args[0])) {
				sendMessage(p, api.lib.getPrefix() + "Invalid page number.");
				return true;
			}
			u.paginateHelp(p, help, amnt, pgAmnt);
			return true;
			} catch (NumberFormatException ex) {
				sendMessage(p, api.lib.getPrefix() + "Invalid page number.");
			}
		}
		
		if (length == 2) {
			int amnt = Integer.valueOf(args[1]);
			if (!isInt(args[1])) {
				sendMessage(p, api.lib.getPrefix() + "Invalid page number.");
				return true;
			}
			if (args[0].equalsIgnoreCase("staff")) {
				if (p.hasPermission("hessentials.staff")) {
					u.paginateStaffHelp(p, staffhelp, amnt, pgAmnt);
					return true;
				}
			}
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
