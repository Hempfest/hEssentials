package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandSocialSpy extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandSocialSpy() {
		super("socialspy");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("sc", "hspy", "hsc"));
		setPermission("hessentials.staff.socialspy");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Socialspy)");
			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		DataManager dm = new DataManager();
		Config data = dm.getUser(p.getUniqueId());
		if (data.getConfig().getString("SOCIAL-SPY") == null) {
			data.getConfig().set("SOCIAL-SPY", "enabled");
			data.saveConfig();
			sendMessage(p, api.lib.getSocialSpyOn(p));
			return true;
		}
		if (data.getConfig().getString("SOCIAL-SPY").equals("disabled")) {
			data.getConfig().set("SOCIAL-SPY", "enabled");
			data.saveConfig();
			sendMessage(p, api.lib.getSocialSpyOn(p));
			return true;
		} else if (data.getConfig().getString("SOCIAL-SPY").equals("enabled")) {
			data.getConfig().set("SOCIAL-SPY", "disabled");
			data.saveConfig();
			sendMessage(p, api.lib.getSocialSpyOff(p));
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
