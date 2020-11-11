package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandGMC extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandGMC() {
		super("gmc");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("hgmc"));
		setPermission("hessentials.staff.gamemode.creative");
	}

	List<GameMode> Gm = Arrays.asList(GameMode.SURVIVAL, GameMode.ADVENTURE);

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (GMC)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			if (Gm.contains(p.getGameMode())) {
				p.setGameMode(GameMode.CREATIVE);
				sendMessage(p, api.lib.getPrefix() + "Now in game-mode creative.");
				return true;
			}
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
