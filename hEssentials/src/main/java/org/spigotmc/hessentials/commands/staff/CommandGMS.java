package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandGMS extends BukkitCommand {

	public CommandGMS() {
		super("gms");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hgms"));
		setPermission("hessentials.staff.gamemode.survival");
	}
	
	List<GameMode> Gm = Arrays.asList(new GameMode[] {GameMode.CREATIVE, GameMode.ADVENTURE});
	
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
			if (Gm.contains(p.getGameMode())) {
				p.setGameMode(GameMode.SURVIVAL);
				sendMessage(p, Strings.getPrefix() + "Now in game-mode survival.");
				return true;
			}
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
