package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandReply extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandReply() {
		super("reply");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hreply", "r"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Reply)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		 if (length == 0) {
			 sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel
					 + " <&cmessage&f> ");
			 return true;
		 }
		      if (length > 0) {
				  DataManager dm = new DataManager();
				  Config data = dm.getUser(p.getUniqueId());
				  String t = data.getConfig().getString("DM");
				  if (t == null) {
					  sendMessage(p, "&cThe player was not found!");
					  return true;
				  }
				  Player target = Bukkit.getPlayer(t);
				  if (target == null) {
					  sendMessage(p, "&cThe player was not found!");
					  return true;
				  }
				  StringBuilder reply = new StringBuilder();
				  for (int i = 0; i < args.length; i++) {
					  reply.append(args[i] + " ");
				  }
				  for (Player pl : Bukkit.getOnlinePlayers()) {
					  Config dat = dm.getUser(pl.getUniqueId());
					  String status = dat.getConfig().getString("SOCIAL-SPY");
					  if (status != null && status.equals("enabled"))
						  sendMessage(pl, api.lib.messageSpyMSG(p, target) + reply.toString());
				  }
				  sendMessage(p, api.lib.replySentMSG(p, target) + reply.toString());
				  target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0F, 60.0F);
				  sendMessage(target, api.lib.replyRecievedMSG(p, target) + reply.toString());
				  return true;
			  }

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
