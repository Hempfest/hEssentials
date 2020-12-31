package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;

public class CommandBack extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandBack() {
		super("back");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hback"));
		setPermission("hessentials.staff");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;
			if (length == 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
				}
				sendMessage(sender, api.lib.getPrefix() + "&a&oIt is now day.");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		DataManager dm = new DataManager();
		Config user = dm.getUser(p.getUniqueId());
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			Location back = user.getConfig().getLocation("Past-Location");
			if (back != null) {
				sendMessage(p, api.lib.getPrefix() + "&a&oTeleporting back to previous location.");
				p.teleport(back);
			} else {
				sendMessage(p, api.lib.getPrefix() + "&c&oPrevious location not found!");
				return true;
			}
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
