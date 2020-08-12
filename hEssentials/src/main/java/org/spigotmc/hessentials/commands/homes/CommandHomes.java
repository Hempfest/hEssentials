package org.spigotmc.hessentials.commands.homes;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.gui.homes.HomeList;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandHomes extends BukkitCommand {

	public CommandHomes() {
		super("homes");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhomes"));
		setPermission("hessentials.homes");
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
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Gui gui = new Gui(p);
					gui.setUUID(p.getUniqueId());
					new HomeList(Utils.guiManager(p)).open();
					Homes.listwarps(p);
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
