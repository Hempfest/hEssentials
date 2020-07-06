package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandSocialSpy extends BukkitCommand {

	public CommandSocialSpy() {
		super("socialspy");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("sc", "hspy", "hsc"));
		setPermission("hessentials.staff.socialspy");
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
		if (!p.hasPermission("hessentials.staff.socialspy")) {
			Strings.sendNoPermission(p);
			return true;
		}
		        if (Utils.socialspy.get(p) == null) {
		          Utils.socialspy.put(p, "enabled");
		          p.sendMessage(ChatColor.GRAY + "SocialSpy Enabled!");
		          return true;
		        }
		          if (Utils.socialspy.get(p) == "disabled") {
		            Utils.socialspy.put(p, "enabled");
		            p.sendMessage(ChatColor.GRAY + "SocialSpy Enabled!");
		            return true;
		          } else  if (Utils.socialspy.get(p) == "enabled") {
		            Utils.socialspy.put(p, "disabled");
		            p.sendMessage(ChatColor.GRAY + "SocialSpy Disabled!");
		            return true;
		          } 
		          
		         

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
