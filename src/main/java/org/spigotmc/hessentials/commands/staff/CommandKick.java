package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandKick extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandKick() {
		super("kick");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hkick"));
		setPermission("hessentials.staff.kick");
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

				sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + "kick <player> | <reason>");
				return true;
			}
			if (length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target.hasPermission(this.getPermission())) {
					sendMessage(sender, api.lib.getPrefix() + "You cannot kick this player!");
					return true;
				}
				target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&fREASON:\n&lNONE"));
				Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&f' kicked for '&cNO REASON&f'."));
				return true;
			}
			if (length > 1) {
				StringBuilder rsn = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					rsn.append(args[i] + " ");
				Player target = Bukkit.getPlayer(args[0]);
				if (target.hasPermission(this.getPermission())) {
					sendMessage(sender, api.lib.getPrefix() + "You cannot kick this player!");
					return true;
				}
				target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&c&lREASON:\n&f&l" + rsn));
				Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&f' kicked for '&c" + rsn + "&f'."));
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		 if (!p.hasPermission(this.getPermission())) {
			 api.lib.sendNoPermission(p, this.getPermission());
			 return true;
		 }
   	  if (length == 0) {

		  sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + "kick <player> | <reason>");
		  return true;
	  }
   	  if (length == 1) {
	      Player target = Bukkit.getPlayer(args[0]);
	      if (target.hasPermission(this.getPermission())) {
		      sendMessage(p, api.lib.getPrefix() + "You cannot kick this player!");
		      return true;
	      }
	      target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&fREASON:\n&lNONE"));
	      Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&f' kicked for '&cNO REASON&f'."));
	      return true;
      }
   	  if (length > 1) {
	      StringBuilder rsn = new StringBuilder();
	      for (int i = 1; i < args.length; i++)
		      rsn.append(args[i] + " ");
	      Player target = Bukkit.getPlayer(args[0]);
	      if (target.hasPermission(this.getPermission())) {
		      sendMessage(p, api.lib.getPrefix() + "You cannot kick this player!");
		      return true;
	      }
	      target.kickPlayer(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&c&lREASON:\n&f&l" + rsn);
	      Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&f' kicked for '&c" + rsn + "&f'."));
	      return true;
      }

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
