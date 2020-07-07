package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

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
			if (Utils.Chat_MUTED) {
				Utils.Chat_MUTED = false;
				Utils.sendChat_Unmuted();
				for (Player a : Bukkit.getOnlinePlayers()) {
				Utils.removeScoreboard(a);
				Utils.createScoreboard(a);
				Utils.animateScoreTitle(a);
				}
			} else {
				Utils.Chat_MUTED = true;
				Utils.sendChat_Muted();
				for (Player a : Bukkit.getOnlinePlayers()) {
					Utils.removeScoreboard(a);
					Utils.createMutedScoreboard(a);
					Utils.animateMutedTitle(a);
					}
				return true;
			}
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
