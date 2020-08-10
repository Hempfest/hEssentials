package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandWithdraw extends BukkitCommand {

	public CommandWithdraw() {
		super("withdraw");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hwithdraw"));
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
					sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " <&c" + "amount" + "&r>");
					return true;
				}

				if (length == 1) {
					Bukkit.dispatchCommand(p, "heco withdraw " + args[0]);
					return true;
				}
				

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
