package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandCFUpdate extends BukkitCommand {

	public CommandCFUpdate() {
		super("hload");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hc", "hessentialsconf", "hconf"));
		setPermission("hessentials.staff.updateconfig");
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
		if (!p.hasPermission("hessentials.staff.updateconfig")) {
			Strings.sendNoPermission(p);
			return true;
		}
		int length = args.length;
		if (length == 0) {
			Utils.defaultConfiguration();
			sendMessage(p, Strings.getPrefix() + "&fAll configs are now up to date.");
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
