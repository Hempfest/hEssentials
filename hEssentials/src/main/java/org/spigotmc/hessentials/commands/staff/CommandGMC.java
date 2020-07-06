package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Strings;

public class CommandGMC extends BukkitCommand {

	public CommandGMC() {
		super("gmc");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hgmc"));
		setPermission("hessentials.staff.gamemode");
	}
	
	List<GameMode> Gm = Arrays.asList(new GameMode[] {GameMode.SURVIVAL, GameMode.ADVENTURE});
	
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
			if (!p.hasPermission("hessentials.staff.gamemode")) {
				Strings.sendNoPermission(p);
				return true;
			}
			if (Gm.contains(p.getGameMode())) {
				p.setGameMode(GameMode.CREATIVE);
				sendMessage(p, Strings.getPrefix() + "Now in game-mode creative.");
				return true;
			}
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
