package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandMessage extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandMessage() {
		super("message");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hmessage", "msg", "hmsg"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Msg)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel
					+ " <&cplayerName&f> <&7message&f>");
			return true;
		}
		if (length == 1) {
			sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel
					+ " <&cplayerName&f> <&7message&f>");
			return true;
		}
		if (length > 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(ChatColor.AQUA + "That player is not online!");
				return true;
			} else {
				DataManager dm = new DataManager();
				Config data = dm.getUser(target.getUniqueId());
				Config dat = dm.getUser(p.getUniqueId());
				data.getConfig().set("DM", p.getName());
				dat.getConfig().set("DM", target.getName());
				data.saveConfig();
				dat.saveConfig();
				StringBuilder msg = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					msg.append(args[i] + " ");
				u.reply.put(target, p);
				u.reply.put(p, target);
				for (Player pl : Bukkit.getOnlinePlayers()) {
					Config d = dm.getUser(pl.getUniqueId());
					String status = d.getConfig().getString("SOCIAL-SPY");
					if (status != null && status.equals("enabled"))
						sendMessage(pl, api.lib.messageSpyMSG(p, target) + msg.toString());
				}
				sendMessage(p, api.lib.messageSentMSG(p, target) + msg.toString());
				target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0F, 60.0F);
				target.sendMessage(api.lib.messageRecievedMSG(p, target) + msg.toString());
				return true;
			}
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
