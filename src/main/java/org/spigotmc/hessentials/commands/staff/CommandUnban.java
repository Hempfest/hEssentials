package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;


public class CommandUnban extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandUnban() {
		super("unban");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hunban"));
		setPermission("hessentials.staff.unban");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;
			if (length == 0) {
				sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + " /unban <player>");
				sendMessage(sender, "&cBan-List: &7" + api.u.getBannedUsers().toString());
				return true;
			}
			if (length == 1) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if (!Bukkit.getBanList(Type.NAME).isBanned(target.getName())) {
					sendMessage(sender, api.lib.getPrefix() + " Player ban entry not found!");
					return true;
				}
				DataManager dm = new DataManager();
				Config users = dm.requestData("Banlist");
				FileConfiguration fc = users.getConfig();
				fc.set("Users." + target.getName(), null);
				users.saveConfig();
				sendMessage(sender, api.lib.getPrefix() + " Player '&c" + target.getName() + "&f' unbanned.");
				Bukkit.getBanList(Type.NAME).pardon(target.getName());
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		 if (!p.hasPermission(this.getPermission())) {
			 sendMessage(p, api.lib.getPrefix() + " You are not authorized!");
			 return true;
		 }
   	  if (length == 0) {
		  sendMessage(p, api.lib.getPrefix() + " /unban <player>");
		  sendMessage(p, "&cBan-List: &7" + api.u.getBannedUsers().toString());
		  return true;
	  }
   	  if (length == 1) {
		  OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		  if (!Bukkit.getBanList(Type.NAME).isBanned(target.getName())) {
			  sendMessage(p, api.lib.getPrefix() + " Player ban entry not found!");
			  return true;
		  }
		  DataManager dm = new DataManager();
		  Config users = dm.requestData("Banlist");
		  FileConfiguration fc = users.getConfig();
		  fc.set("Users." + target.getName(), null);
		  users.saveConfig();
		  sendMessage(p, api.lib.getPrefix() + " Player '&c" + target.getName() + "&f' unbanned.");
		  Bukkit.getBanList(Type.NAME).pardon(target.getName());
		  return true;
	  }

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
