package org.spigotmc.hessentials.commands.staff;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

import java.util.Arrays;

public class CommandMuteChat extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandMuteChat() {
		super("mutechat");
		setDescription("Primary staff command for hEssentials.");
		setAliases(Arrays.asList("mc", "hmc"));
		setPermission("hessentials.staff.mutechat");
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
				if (api.u.isMuted() == true) {
					api.u.setMuted(false);
					api.u.sendChat_Unmuted();
				} else {
					api.u.setMuted(true);
					api.u.sendChat_Muted();

				}
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			if (api.u.isMuted() == true) {
				api.u.setMuted(false);
				api.u.sendChat_Unmuted();
			} else {
				api.u.setMuted(true);
				api.u.sendChat_Muted();

			}
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
