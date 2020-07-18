package org.spigotmc.hessentials.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandTrack extends BukkitCommand {

	public CommandTrack() {
		super("track");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("htrack"));
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	static int stop = 0 ;
	
	public static void StopTimer() {
		 Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable() {
             public void run() {
                     Bukkit.getScheduler().cancelTask(stop);
             }
         }, 20L * 60);
	}
	
	public static void sendActionBar(final Player p) {
		stop = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.getInstance(), new Runnable() {
            public void run() {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Utils.trackPlayers(p)));
            }
        }, 20L, 0L);
		
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
				sendActionBar(p);
	            StopTimer();
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
