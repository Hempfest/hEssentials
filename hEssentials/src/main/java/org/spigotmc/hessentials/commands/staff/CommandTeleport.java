package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;

public class CommandTeleport extends BukkitCommand {

	public CommandTeleport() {
		super("teleport");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hteleport", "htp", "tp"));
		setPermission("hessentials.teleport");
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
		
				// /tp - no args
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&l|", " &7<targetName&7>", "&f&oExample: &7/tp &eHempfest", "&f&oExample: &7/tp &eHempfest &7Steve");
					return true;
				}
				
				// /tp - playername only
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " - &c&oPlayer not found.");
						return true;
					}
					Location tloc = target.getLocation();
					p.teleport(tloc);
					sendMessage(p, Strings.getPrefix() + "Teleporting you to player " + '"' + target.getName() + '"' + ".");
					return true;
				}
				
				// /tp - tp player to player
				if (length == 2) {
					if (!p.hasPermission("hessentials.teleport.others")) {
						Strings.sendNoPermission(p, this.getPermission() + ".others");
						return true;
					}
					Player target1 = Bukkit.getPlayer(args[0]);
					if (target1 == null) {
						sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " - &c&oPlayer not found.");
						return true;
					}
					Player target2 = Bukkit.getPlayer(args[1]);
					if (target2 == null) {
						sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " - &c&oTarget not found.");
						return true;
					}
					Location t2loc = target2.getLocation();
					target1.teleport(t2loc);
					sendMessage(p, Strings.getPrefix() + "Teleporting player " + '"' + target1.getName() + '"' + " to player " + '"' + target2.getName() + '"' + ".");
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
