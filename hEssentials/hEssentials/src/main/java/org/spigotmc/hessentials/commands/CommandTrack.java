package org.spigotmc.hessentials.commands;

import java.util.Arrays;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandTrack extends BukkitCommand {

	heHook api = heHook.getHook();
	static Utils u = new Utils();

	public CommandTrack() {
		super("track");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("htrack"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	static int stop = 0;

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
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(u.trackPlayers(p)));
			}
        }, 20L, 0L);
		
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Track)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			sendActionBar(p);
			StopTimer();
			sendMessage(sender, api.lib.getPrefix() + "&a&oNow tracking players within 1000m.");
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
