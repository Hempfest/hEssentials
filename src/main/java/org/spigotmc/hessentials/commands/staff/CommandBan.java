package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import java.util.Arrays;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;


public class CommandBan extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandBan() {
		super("ban");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hban"));
		setPermission("hessentials.staff.ban");
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
				sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + " /ban <playerName> <reason>");
				return true;
			}
			if (length == 1) {
				DataManager dm = new DataManager();
				Player target = Bukkit.getPlayer(args[0]);
				Config users = dm.requestData("Banlist");
				if (target == null) {
					OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
					if (Bukkit.getBanList(Type.NAME).getBanEntries().contains(otarget.getName())) {
						sendMessage(sender, api.lib.getPrefix() + "&cThis player is already banned.");
						return true;
					}
					users.getConfig().set("Users." + otarget.getName() + ".reason", "No reason.");
					users.saveConfig();
					Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + otarget.getName() + "&f' banned for '&c" + "NO REASON" + "&f'."));
					Bukkit.getBanList(Type.NAME).addBan(otarget.getName(), api.lib.color("&4&l" + "No reason."), null, "CONSOLE");
					return true;
				}
				users.getConfig().set("Users." + target.getName() + ".reason", "No reason.");
				users.saveConfig();
				target.kickPlayer(api.lib.getPrefix() + api.lib.color("\n&c&lYou have been banned.\n&fREASON:\n&lNONE"));
				Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + target.getName() + "&f' banned for '&cNO REASON&f'."));
				Bukkit.getBanList(Type.NAME).addBan(target.getName(), api.lib.color("&4&lNO REASON"), null, "CONSOLE");
				return true;
			}
			if (length > 1) {
				StringBuilder rsn = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					rsn.append(args[i] + " ");
				Player target = Bukkit.getPlayer(args[0]);
				int stop = rsn.length() - 1;
				DataManager dm = new DataManager();
				Config users = dm.requestData("Banlist");
				if (target == null) {
					OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
					users.getConfig().set("Users." + op.getName() + ".reason", rsn.substring(0, stop));
					users.saveConfig();
					Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + op.getName() + "&f' banned for '&c" + rsn.substring(0, stop) + "&f'."));
					Bukkit.getBanList(Type.NAME).addBan(op.getName(), api.lib.color("&4&l" + rsn), null, "CONSOLE");
					return true;
				}
				users.getConfig().set("Users." + target.getName() + ".reason", rsn.substring(0, stop));
				users.saveConfig();
				target.kickPlayer(api.lib.color(api.lib.getPrefix() + api.lib.color("\n&c&lYou have been banned.\n&c&lREASON:\n&f&l" + rsn)));
				Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + target.getName() + "&f' banned for '&c" + rsn.substring(0, stop) + "&f'."));
				Bukkit.getBanList(Type.NAME).addBan(target.getName(), api.lib.color("&4&l" + rsn), null, "CONSOLE");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		Utils u = new Utils();
		int length = args.length;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
  	  if (length == 0) {
		  if (Bukkit.getServer().getVersion().contains("1.15")) {
			  u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&l|", " &7<banReason&7>", "&f&oExample: &7/ban &eHempfest", "&f&oExample: &7/ban &eHempfest &cCheating"));
		  } else if (Bukkit.getServer().getVersion().contains("1.16")) {
			  u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&l|", " &7<banReason&7>", "&f&oExample: &7/ban &eHempfest", "&f&oExample: &7/ban &eHempfest &cCheating"));
		  }
		  return true;
	  }
  	  if (length == 1) {
		  DataManager dm = new DataManager();
		  Player target = Bukkit.getPlayer(args[0]);
		  Config users = dm.requestData("Banlist");
		  if (target == null) {
			  OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
			  if (Bukkit.getBanList(Type.NAME).getBanEntries().contains(otarget.getName())) {
				  sendMessage(p, api.lib.getPrefix() + "&cThis player is already banned.");
				  return true;
			  }
			  users.getConfig().set("Users." + otarget.getName() + ".reason", "No reason.");
			  users.saveConfig();
			  Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + otarget.getName() + "&f' banned for '&c" + "NO REASON" + "&f'."));
			  Bukkit.getBanList(Type.NAME).addBan(otarget.getName(), api.lib.color("&4&l" + "No reason."), null, p.getName());
			  return true;
		  }
		  if (target.hasPermission(this.getPermission())) {
			  p.sendMessage(api.lib.getPrefix() + "You cannot ban this player!");
			  return true;
		  }
		  users.getConfig().set("Users." + target.getName() + ".reason", "No reason.");
		  users.saveConfig();
		  target.kickPlayer(api.lib.getPrefix() + api.lib.color("\n&c&lYou have been banned.\n&fREASON:\n&lNONE"));
		  Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + target.getName() + "&f' banned for '&cNO REASON&f'."));
		  Bukkit.getBanList(Type.NAME).addBan(target.getName(), api.lib.color("&4&lNO REASON"), null, p.getName());
		  return true;
	  }
  	  if (length > 1) {
		  StringBuilder rsn = new StringBuilder();
		  for (int i = 1; i < args.length; i++)
			  rsn.append(args[i] + " ");
		  Player target = Bukkit.getPlayer(args[0]);
		  int stop = rsn.length() - 1;
		  DataManager dm = new DataManager();
		  Config users = dm.requestData("Banlist");
		  if (target == null) {
			  OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
			  users.getConfig().set("Users." + op.getName() + ".reason", rsn.substring(0, stop));
			  users.saveConfig();
			  Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + op.getName() + "&f' banned for '&c" + rsn.substring(0, stop) + "&f'."));
			  Bukkit.getBanList(Type.NAME).addBan(op.getName(), api.lib.color("&4&l" + rsn), null, p.getName());
			  return true;
		  }
		  if (target.hasPermission(this.getPermission())) {
			  p.sendMessage(api.lib.getPrefix() + "You cannot ban this player!");
			  return true;
		  }
		  users.getConfig().set("Users." + target.getName() + ".reason", rsn.substring(0, stop));
		  users.saveConfig();
		  target.kickPlayer(api.lib.color(api.lib.getPrefix() + api.lib.color("\n&c&lYou have been banned.\n&c&lREASON:\n&f&l" + rsn)));
		  Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(" Player '&c" + target.getName() + "&f' banned for '&c" + rsn.substring(0, stop) + "&f'."));
		  Bukkit.getBanList(Type.NAME).addBan(target.getName(), api.lib.color("&4&l" + rsn), null, p.getName());
		  return true;
	  }

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
