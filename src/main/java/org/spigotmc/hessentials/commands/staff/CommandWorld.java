package org.spigotmc.hessentials.commands.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandWorld extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandWorld() {
		super("world");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("worlds", "hworld"));
		setPermission("hessentials.staff.worlds");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	public List<String> getWorlds() {
		List<String> array = new ArrayList<>();
		for (World w : Bukkit.getWorlds()) {
			array.add(w.getName());
		}
		return array;
	}

	private boolean hasSurface(Location location) {
		Block feet = location.getBlock();
		if (!feet.getType().isAir() && !feet.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().isAir())
			return false;
		Block head = feet.getRelative(BlockFace.UP);
		if (!head.getType().isAir())
			return false;
		Block ground = feet.getRelative(BlockFace.DOWN);
		return ground.getType().isSolid();
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;

			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			sendMessage(p, api.lib.getPrefix() + "&6&oWorlds: &7" + getWorlds().toString());
			return true;
		}
		if (length == 1) {
			String world = args[0];
			if (!getWorlds().contains(world)) {
				sendMessage(p, api.lib.getPrefix() + "&c&oWorld " + '"' + world + '"' + " wasn't found.");
				return true;
			}
			Location teleportLocation = (new Location(Bukkit.getWorld(world), 0, 80, 0));
			if (!hasSurface(teleportLocation))
				teleportLocation = (new Location(Bukkit.getWorld(world), 0, 80, 0)).add(7.0D, 10.0D, 7.0D);
			p.teleport(teleportLocation);
			sendMessage(p, api.lib.getPrefix() + "&a&oTeleporting to world " + world.toLowerCase() + "...");
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
