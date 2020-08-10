package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandNight extends BukkitCommand {

	public CommandNight() {
		super("night");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hnight"));
		setPermission("hessentials.staff.night");
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
			Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(15000L);
			sendMessage(p, Strings.getPrefix() + "&5&oIt is now night.");
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
