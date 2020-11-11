package org.spigotmc.hessentials.commands.staff;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

import java.util.Arrays;

public class CommandUpdate extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandUpdate() {
		super("hload");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hessentialsconf", "hconf"));
		setPermission("hessentials.staff.updateconfig");
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
				u.defaultConfiguration();
				sendMessage(sender, api.lib.getPrefix() + "&fAll configs are now up to date.");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			u.defaultConfiguration();
			sendMessage(p, api.lib.getPrefix() + "&fAll configs are now up to date.");
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
