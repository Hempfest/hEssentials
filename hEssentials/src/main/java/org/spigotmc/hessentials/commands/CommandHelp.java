package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

import m.h.clans.checks.Checks;


public class CommandHelp extends BukkitCommand {

	public CommandHelp() {
		super("help");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhelp"));
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
		Utils u = new Utils();
		Config mess = new Config("Help");
		List<String> help = mess.getConfig().getStringList("Help-Menu"); 
		List<String> staffhelp = mess.getConfig().getStringList("Staff-Help-Menu");
		int pgAmnt = mess.getConfig().getInt("Amount-per-page");
		if (length == 0) {
			u.paginateHelp(sender, help, 1, pgAmnt);
			return true;
		}
		
		if (length == 1) {
			if (args[0].equalsIgnoreCase("staff")) {
				if (p.hasPermission(this.getPermission())) {
				u.paginateHelp(p, staffhelp, 1, pgAmnt);
				return true;
				}
			}
			int amnt = Integer.valueOf(args[0]);
			if (!Checks.isInt(args[0])) {
				sendMessage(p, Strings.getPrefix() + "Invalid page number.");
				return true;
			}
			u.paginateHelp(p, help, amnt, pgAmnt);
			return true;
		}
		
		if (length == 2) {
			int amnt = Integer.valueOf(args[1]);
			if (!Checks.isInt(args[1])) {
				sendMessage(p, Strings.getPrefix() + "Invalid page number.");
				return true;
			}
			if (args[0].equalsIgnoreCase("staff")) {
			if (p.hasPermission(this.getPermission())) {
			u.paginateHelp(p, staffhelp, amnt, pgAmnt);
			return true;
				}
			}
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
