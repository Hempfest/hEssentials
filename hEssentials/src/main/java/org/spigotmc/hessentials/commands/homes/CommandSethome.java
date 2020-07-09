package org.spigotmc.hessentials.commands.homes;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;

public class CommandSethome extends BukkitCommand {

	public CommandSethome() {
		super("sethome");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hsethome"));
		setPermission("hessentials.homes.set");
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
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&ahomeName&7> ", "&f&oExample: &7/sethome &eSkybase");
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Homes.setWarp(p, args[0]);
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
