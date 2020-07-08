package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Checks;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandHud extends BukkitCommand {

	public CommandHud() {
		super("hud");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhud"));
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
					if (Checks.hasScore(p)) {
						
				Utils.removeScoreboard(p);
				Utils.remScore(p);
					
					} else if (!Checks.hasScore(p)){
					Utils.createScoreboard(p);	
					Utils.animateScoreTitle(p);
						return true;
					}
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
