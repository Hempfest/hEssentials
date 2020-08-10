package org.spigotmc.hessentials.commands.homes;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Component;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandDelhome extends BukkitCommand {

	public CommandDelhome() {
		super("delhome");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hdelhome", "deletehome"));
		setPermission("hessentials.homes.delete");
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
		Utils u = new Utils();
		int length = args.length;
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					u.sendComponent(p, Component.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&ahomeName&7> ", "&f&oExample: &7/delhome &eSkybase"));
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Homes.deleteWarp(p, args[0]);
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
