package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandDay extends BukkitCommand {

	public CommandDay() {
		super("day");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hday"));
		setPermission("hessentials.staff.day");
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
			Config voting = new Config("bed_voting");
			ConfigurationSection section = voting.getConfig().getConfigurationSection("Bed-Voting");
			 section.set("Day-Voting", 0);
			 voting.saveConfig();
			Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
			sendMessage(p, Strings.getPrefix() + "&a&oIt is now day.");
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
