package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandTeleport extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandTeleport() {
		super("teleport");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hteleport", "htp", "tp"));
		setPermission("hessentials.staff.teleport");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			// /tp - tp player to player
			int length = args.length;
			if (length == 0) {
				sendMessage(sender, api.lib.getPrefix() + "/" + commandLabel + " <player1> <player2>");
				return true;
			}
			if (length == 1) {
				sendMessage(sender, api.lib.getPrefix() + "/" + commandLabel + " <player1> <player2>");
				return true;
			}
			if (length == 2) {
				Player target1 = Bukkit.getPlayer(args[0]);
				if (target1 == null) {
					sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " - &c&oPlayer not found.");
					return true;
				}
				Player target2 = Bukkit.getPlayer(args[1]);
				if (target2 == null) {
					sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " - &c&oTarget not found.");
					return true;
				}
				Location t2loc = target2.getLocation();
				target1.teleport(t2loc);
				sendMessage(sender, api.lib.getPrefix() + "Teleporting player " + '"' + target1.getName() + '"' + " to player " + '"' + target2.getName() + '"' + ".");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		Utils u = new Utils();
		int length = args.length;
		
				// /tp - no args
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					if (Bukkit.getServer().getVersion().contains("1.15")) {

					} else if (Bukkit.getServer().getVersion().contains("1.16")) {

					}
					u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&l|", " &7<targetName&7>", "&f&oExample: &7/tp &eHempfest", "&f&oExample: &7/tp &eHempfest &7Steve"));
					return true;
				}
				
				// /tp - playername only
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						api.u.lastLocation(p, args[0]);
						return true;
					}
					Location tloc = target.getLocation();
					p.teleport(tloc);
					sendMessage(p, api.lib.getPrefix() + "Teleporting you to player " + '"' + target.getName() + '"' + ".");
					return true;
				}
				
				// /tp - tp player to player
				if (length == 2) {
					if (!p.hasPermission(this.getPermission() + ".others")) {
						api.lib.sendNoPermission(p, this.getPermission() + ".others");
						return true;
					}
					Player target1 = Bukkit.getPlayer(args[0]);
					if (target1 == null) {
						sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " - &c&oPlayer not found.");
						return true;
					}
					Player target2 = Bukkit.getPlayer(args[1]);
					if (target2 == null) {
						sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " - &c&oTarget not found.");
						return true;
					}
					Location t2loc = target2.getLocation();
					target1.teleport(t2loc);
					sendMessage(p, api.lib.getPrefix() + "Teleporting player " + '"' + target1.getName() + '"' + " to player " + '"' + target2.getName() + '"' + ".");
					return true;
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
