package org.spigotmc.hessentials.commands;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Home;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandHome extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandHome() {
		super("home");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhome"));
		setPermission("hessentials.homes.use");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Home)");
			return true;
		}

		Player p = (Player) sender;
		Utils u = new Utils();
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			if (Bukkit.getServer().getVersion().contains("1.15")) {
				u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&ahomeName&7> ", "&f&oExample: &7/home &eSkybase"));
			} else if (Bukkit.getServer().getVersion().contains("1.16")) {
				u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&ahomeName&7> ", "&f&oExample: &7/home &eSkybase"));
			}
			return true;
		}

		if (length == 1) {
			Home home = new Home(p, args[0]);
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			home.teleport();
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
