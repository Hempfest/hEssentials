package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandWhois extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandWhois() {
		super("whois");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hwhois", "playerinfo"));
		setPermission("hessentials.staff.playerinfo");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Whois)");
			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			sendMessage(p, api.lib.getPrefix() + "Invalid Usage: /" + commandLabel + " <&cplayerName&f>");
			return true;
		}
		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
				DataManager dm = new DataManager();
				Config data2 = dm.getUser(otarget.getUniqueId());
				if (data2.exists()) {

					u.sendOfflinePlayerInfo(p, otarget);
					return true;
				}
			} else {
				u.sendPlayerInfo(p, target);
				return true;
			}
			sendMessage(p, api.lib.getPrefix() + "This player has never joined this server.");
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
