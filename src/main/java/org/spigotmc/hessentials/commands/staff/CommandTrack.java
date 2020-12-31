package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;

public class CommandTrack extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandTrack() {
		super("track");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("htrack"));
		setPermission("hessentials.staff");
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
		DataManager dm = new DataManager();
		Config user = dm.getUser(p.getUniqueId());
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			Player oTar = null;
			for (Entity e : p.getNearbyEntities(1000, 200, 1000)) {
				if (e instanceof Player) {
					oTar = (Player) e;
				}
			}
			if (oTar == null) {
				api.u.sendMessage(p, api.u.getPrefix() + "&c&oThere are no players nearby to track.");
				return true;
			}
			Location tarLoc = oTar.getLocation();
			double distance = p.getLocation().distance(tarLoc);
			long amount = Math.round(distance);
			api.u.sendMessage(p, api.u.getPrefix() + "&c&o" + oTar.getName() + " &fis &a&o" + amount + " &fblocks away.");
			return true;
		}

		if (length == 1) {
			String targetName = args[0];
			if (api.u.usernameToUUID(targetName) == null) {
				return true;
			}

			OfflinePlayer target = Bukkit.getOfflinePlayer(api.u.usernameToUUID(targetName));
			if (target.isOnline()) {
				Player oTar = target.getPlayer();
				if (oTar == null) {
					api.u.sendMessage(p, api.u.getPrefix() + "&c&oThere are no players nearby to track.");
					return true;
				}
				Location tarLoc = oTar.getLocation();
				double distance = p.getLocation().distance(tarLoc);
				long amount = Math.round(distance);
				api.u.sendMessage(p, api.u.getPrefix() + "&c&o" + oTar.getName() + " &fis &a&o" + amount + " &fblocks away.");
			} else {
				api.u.sendMessage(p, api.u.getPrefix() + "&c&oThis player is not online.");
			}
			return true;

		}


		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
