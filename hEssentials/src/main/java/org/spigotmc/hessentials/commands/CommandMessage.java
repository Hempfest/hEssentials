package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandMessage extends BukkitCommand {

	public CommandMessage() {
		super("message");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hmessage", "msg", "hmsg"));
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
			sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel
					+ " <&cplayerName&f> <&7message&f>");
			return true;
		}
		if (length == 1) {
			sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel
					+ " <&cplayerName&f> <&7message&f>");
			return true;
		}
		if (length > 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(ChatColor.AQUA + "That player is not online!");
				return true;
			} else {
				StringBuilder msg = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					msg.append(String.valueOf(args[i]) + " ");
				Utils.reply.put(target, p);
				Utils.reply.put(p, target);
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (Utils.socialspy.get(pl) == "enabled")
						sendMessage(pl, Strings.messageRecievedMSG(p, target) + msg.toString());
				}
				sendMessage(p, Strings.messageSentMSG(p, target) + msg.toString());
				target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0F, 60.0F);
				target.sendMessage(Strings.messageRecievedMSG(p, target) + msg.toString());
				return true;
			}
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
