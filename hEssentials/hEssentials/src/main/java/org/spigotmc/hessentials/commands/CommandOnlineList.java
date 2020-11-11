package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandOnlineList extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandOnlineList() {
		super("who");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("online"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Onlinelist)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			sendMessage(p, api.u.sendOnlineList());
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
