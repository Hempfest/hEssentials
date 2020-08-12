package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandSuffix extends BukkitCommand {

	public CommandSuffix() {
		super("suffix");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hsuffix"));
		setPermission("hessentials.staff.chat.suffix");
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
			
			sendMessage(p, Strings.getPrefix() + "Not enough arguements.");
			sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <playerName> <suffix>");
			return true;
		}
		
		if (length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				String suffix = args[0];
				PlayerData pd = new PlayerData(p.getUniqueId());
				pd.getConfig().set("SUFFIX", suffix);
				pd.saveConfig();
				sendMessage(p, Strings.getPrefix() + "Your suffix was changed to: " + suffix);
				return true;
			}
			sendMessage(p, Strings.getPrefix() + "Not enough arguements.");
			sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " " + target.getName() + " <suffix>");
			return true;
		}
		
		if (length == 2) {
			if (!p.hasPermission(this.getPermission() + ".others")) {
				Strings.sendNoPermission(p, this.getPermission() + ".others");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				Player offlineT = Bukkit.getPlayer(args[0]); 
				if (offlineT == null) {
					sendMessage(p, Strings.getPrefix() + "The player was not found!");
					return true;
				}
				String suffix = args[1];
				PlayerData pd = new PlayerData(offlineT.getUniqueId());
				pd.getConfig().set("SUFFIX", suffix);
				pd.saveConfig();
				sendMessage(p, Strings.getPrefix() + offlineT.getName() + " suffix changed to: " + suffix);
				return true;
			}
			String suffix = args[1];
			PlayerData pd = new PlayerData(target.getUniqueId());
			pd.getConfig().set("SUFFIX", suffix);
			pd.saveConfig();
			sendMessage(p, Strings.getPrefix() + target.getName() + " suffix changed to: " + suffix);
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
