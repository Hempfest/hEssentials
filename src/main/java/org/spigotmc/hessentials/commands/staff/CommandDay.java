package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

import java.util.Arrays;

public class CommandDay extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandDay() {
		super("day");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hday"));
		setPermission("hessentials.staff.day");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;
			if (length == 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
				}
				sendMessage(sender, api.lib.getPrefix() + "&a&oIt is now day.");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
			sendMessage(p, api.lib.getPrefix() + "&a&oIt is now day.");
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
