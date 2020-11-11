package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

import java.util.Arrays;

public class CommandKickAll extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandKickAll() {
		super("kickall");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hkickall"));
		setPermission("hessentials.staff.kickall");
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
				for (Player target : Bukkit.getOnlinePlayers()) {
					if (target != sender) {
						target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&fREASON:\n&lNONE"));
						Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&cNO REASON&7'."));
					}
				}
			}
			if (length > 1) {
				StringBuilder rsn = new StringBuilder();
				for (int i = 0; i < args.length; i++)
					rsn.append(args[i] + " ");
				for (Player target : Bukkit.getOnlinePlayers()) {
					if (target != sender) {
						target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&c&lREASON:\n&f&l" + rsn));
						Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&c" + rsn + "&7'."));
					}
				}
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
   		for (Player target : Bukkit.getOnlinePlayers()) {
   			if (!target.getName().equals(p.getName())) {
				target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&fREASON:\n&lNONE"));
				Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&cNO REASON&7'."));
				return true;
			}
     		  }
   	  }
   	  if (length > 1) {
   		  StringBuilder rsn = new StringBuilder();
             for (int i = 0; i < args.length; i++)
				 rsn.append(args[i] + " ");
             for (Player target : Bukkit.getOnlinePlayers()) {
				 if (!target.getName().equals(p.getName())) {
					 target.kickPlayer(api.lib.color(api.lib.getPrefix() + "\n&c&lYou have been kicked.\n&c&lREASON:\n&f&l" + rsn));
					 Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "Player '&c" + target.getName() + "&7' kicked for '&c" + rsn + "&7'."));

				 }

			 }
		  return true;
	  }

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
