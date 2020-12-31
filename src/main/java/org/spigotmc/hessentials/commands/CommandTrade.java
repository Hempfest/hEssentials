package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandTrade extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandTrade() {
		super("trade");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("htrade"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Trade)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {

			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
