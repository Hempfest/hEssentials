package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandPay extends BukkitCommand {

	public CommandPay() {
		super("pay");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hpay"));
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
					sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " <&c" + "playerName" + "&r> <&cAmount&r>");
					return true;
				}

				if (length == 1) {
					sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " <&c" + "playerName" + "&r> <&cAmount&r>");
					return true;
				}
				
				if (length == 2) {
					Bukkit.dispatchCommand(p, "heco pay " + args[0] + " " + args[1]);
					return true;
				}

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
