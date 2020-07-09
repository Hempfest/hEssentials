package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandInvsee extends BukkitCommand {

	public CommandInvsee() {
		super("inventorysee");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("invsee"));
		setPermission("hessentials.staff.invsee");
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
		
		if (!p.hasPermission(this.getPermission())) {
			Strings.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			sendMessage(p, Strings.getPrefix() + "This is a command.");
			return true;
		}
		
		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				//player no exist
				return true;
			}
			Utils.openPlayerInventory(p, target);
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
