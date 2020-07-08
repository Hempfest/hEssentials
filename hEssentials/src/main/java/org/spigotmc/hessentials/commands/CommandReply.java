package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandReply extends BukkitCommand {

	public CommandReply() {
		super("reply");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hreply", "r"));
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
						+ " <&cmessage&f> ");
		        return true;
		      } 
		      if (length > 0) {
		    	  Player target = (Player)Utils.reply.get(p);
			      StringBuilder reply = new StringBuilder();
			      for (int i = 0; i < args.length; i++) {
			        reply.append(String.valueOf(args[i]) + " ");
			      }
			      if (target == null) {
			        sendMessage(p, ChatColor.RED + "You have nobody to reply to!");
			        return true;
			      }
		        Utils.reply.put(target, p);
		        Utils.reply.put(p, target);
		        	for (Player pl : Bukkit.getOnlinePlayers()) {
		                if (Utils.socialspy.get(pl) == "enabled")
		                  sendMessage(pl, Strings.replyRecievedMSG(p, target) + reply.toString());  
		            } 
		       sendMessage(p, Strings.replySentMSG(p, target) + reply.toString());
		        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0F, 60.0F);
		        sendMessage(target, Strings.replyRecievedMSG(p, target) + reply.toString());
		        return true;
		      }

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
