package org.spigotmc.hessentials.commands.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;

public class CommandSuffix extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandSuffix() {
		super("suffix");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hsuffix"));
		setPermission("hessentials.chat.suffix");
	}


	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Suffix)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}

			sendMessage(p, api.lib.getPrefix() + "Not enough arguements.");
			sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <playerName> <suffix>");
			return true;
		}
		
		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			if (target == null) {
				String suffix = args[0];
				DataManager dm = new DataManager();
				Config users = dm.getMisc("Suffix");
				FileConfiguration fc = users.getConfig();
				fc.set(p.getUniqueId().toString() + ".suffix", suffix);
				users.saveConfig();
				sendMessage(p, api.lib.getPrefix() + "Your suffix was changed to: " + suffix);
				return true;
			}
			sendMessage(p, api.lib.getPrefix() + "Not enough arguements.");
			sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " " + target.getName() + " <suffix>");
			return true;
		}
		
		if (length == 2) {
			if (!p.hasPermission(this.getPermission() + ".others")) {
				api.lib.sendNoPermission(p, this.getPermission() + ".others");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				for (String id : api.u.getAllUserIDs()) {
					UUID u = UUID.fromString(id);
					List<String> names = new ArrayList<>();
					names.add(Bukkit.getOfflinePlayer(u).getName());
					if (!names.contains(args[0])) {
						sendMessage(p, api.lib.getPrefix() + "The player was not found!");
						return true;
					}
				}
				OfflinePlayer offlineT = Bukkit.getOfflinePlayer(api.u.usernameToUUID(args[0]));
				String suffix = args[1];
				DataManager dm = new DataManager();
				Config users = dm.getMisc("Suffix");
				FileConfiguration fc = users.getConfig();
				fc.set(offlineT.getUniqueId().toString() + ".suffix", suffix);
				users.saveConfig();
				sendMessage(p, api.lib.getPrefix() + offlineT.getName() + " suffix changed to: " + suffix);
				return true;
			}
			String suffix = args[1];
			DataManager dm = new DataManager();
			Config users = dm.getMisc("Suffix");
			FileConfiguration fc = users.getConfig();
			fc.set(target.getUniqueId().toString() + ".suffix", suffix);
			users.saveConfig();
			sendMessage(p, api.lib.getPrefix() + target.getName() + " suffix changed to: " + suffix);
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
