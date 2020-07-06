package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandWhois extends BukkitCommand {

	public CommandWhois() {
		super("whois");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hwhois", "playerinfo"));
		setPermission("hessentials.staff.playerinfo");
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}

		Player p = (Player) sender;
		
		int length = args.length;
		if (length == 0) {
			sendMessage(p, Strings.getPrefix() + "Invalid Usage: /" + commandLabel + " <&cplayerName&f>");
			return true;
		}
		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
				PlayerData data2 = new PlayerData(otarget.getUniqueId());
			if (data2.exists()) {
				
				Utils.sendOfflinePlayerInfo(p, otarget);
				return true;
			}
			} else {
				Utils.sendPlayerInfo(p, target);
				return true;
			}
				sendMessage(p, Strings.getPrefix() + "This player has never joined this server.");
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
