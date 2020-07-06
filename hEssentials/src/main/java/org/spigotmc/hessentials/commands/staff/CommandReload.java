package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class CommandReload extends BukkitCommand {

	public CommandReload() {
		super("hessentials");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hess", "he", "hesse", "hessversion"));
		setPermission("hessentials.staff.reload");
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
		if (!p.hasPermission("hessentials.staff.reload")) {
			Strings.sendNoPermission(p);
			return true;
		}
		int length = args.length;
		PluginDescriptionFile pdf = HempfestEssentials.instance.getDescription();
		if (length == 0) {
			Utils.reloadConfiguration();
			Message.textHoverable(p, Strings.getPrefix() + "&2&oCurrently running ", "&av" + pdf.getVersion() , "&3&oPlugin by &b&oHempfest");
			sendMessage(p, "&fAll configs are reloaded.");
			return true;
		}

		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
