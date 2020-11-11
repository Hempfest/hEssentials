package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandInvsee extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandInvsee() {
		super("inventorysee");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("invsee"));
		setPermission("hessentials.staff.invsee");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Invsee)");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			sendMessage(p, api.lib.getPrefix() + "This is a command.");
			return true;
		}

		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				//player no exist
				return true;
			}
			u.openPlayerInventory(p, target);
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
