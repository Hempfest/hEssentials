package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandMuteChat extends BukkitCommand {

	public CommandMuteChat() {
		super("mutechat");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("mc", "hmc"));
		setPermission("hessentials.staff.mutechat");
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				Strings.sendNoPermission(p, this.getPermission());
				return true;
			}
			if (Utils.Chat_MUTED) {
				Utils.Chat_MUTED = false;
				Utils.sendChat_Unmuted();
				
				return true;
			} else if (!Utils.Chat_MUTED) {
				Utils.Chat_MUTED = true;
				Utils.sendChat_Muted();
				
				return true;
			}
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
