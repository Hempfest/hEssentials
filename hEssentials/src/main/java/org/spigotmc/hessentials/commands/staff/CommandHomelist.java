package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.commands.homes.Homes;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.gui.homes.HomeListOther;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Component;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandHomelist extends BukkitCommand {

	public CommandHomelist() {
		super("listhomes");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hlisthomes", "homelist", "hhomelist", "hl", "lh"));
		setPermission("hessentials.staff.homes.use.others");
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
		Utils u = new Utils();
		int length = args.length;
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					u.sendComponent(p, Component.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&oExample: &7/homelist &eHempfest"));
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					try {
					Gui gui = Utils.guiManager(p);
					gui.setViewer2(args[0]);
					new HomeListOther(Utils.guiManager(p)).open();
			        Homes.listwarps(p, args[0]);
					} catch (NullPointerException e) {
						
						sendMessage(p, Strings.getPrefix() + "The player " + '"' + args[0] + '"' + " was not found.");
					}
			        return true;
	    	}
				

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
