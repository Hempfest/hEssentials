package org.spigotmc.hessentials.commands.staff;

import addon.hessentials.util.GroupAPI;
import java.util.Arrays;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandDisableSuffix extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandDisableSuffix() {
		super("disable");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hdisable"));
		setPermission("hessentials.chat.suffix.others");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
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
			sendMessage(p, api.lib.getPrefix() + "&c&oInvalid usage: &7/disable &4*&6<playerName>");
			return true;
		}
		if (length == 1) {
			try {
				String name = args[0];
				OfflinePlayer op = Bukkit.getOfflinePlayer(api.u.usernameToUUID(name));
				if (api.u.getAllUserIDs().contains(op.getUniqueId().toString())) {
					UUID id = op.getUniqueId();
					GroupAPI groupAPI = new GroupAPI();
					groupAPI.setUsingSuffix(id, "no");
					api.u.sendMessage(p, "&c&oPlayer suffix now disabled.");
					return true;
				}
			} catch (IllegalArgumentException e) {
				sendMessage(p, api.lib.getPrefix() + "Player not found.");
				return true;
			}
		}


		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
