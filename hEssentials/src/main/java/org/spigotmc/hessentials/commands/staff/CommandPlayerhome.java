package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.commands.homes.Homes;
import org.spigotmc.hessentials.util.variables.Message;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandPlayerhome extends BukkitCommand {

	public CommandPlayerhome() {
		super("playerhome");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hplayerhome", "hph", "ph"));
		setPermission("hessentials.homes.use.others");
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
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase");
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase");
					return true;
				}
				
				if (length == 2) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Player online = Bukkit.getPlayer(args[0]);
					Homes.warp(p, online, args[0], args[1], commandLabel);
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
