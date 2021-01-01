package org.spigotmc.hessentials.commands.staff;

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

public class CommandPlayerhome extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandPlayerhome() {
		super("playerhome");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hplayerhome", "hph", "ph"));
		setPermission("hessentials.staff.homes.use.others");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Playerhome)");
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
						u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase"));
					} else if (Bukkit.getServer().getVersion().contains("1.16")) {
						u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase"));
					}
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					if (Bukkit.getServer().getVersion().contains("1.15")) {
						u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase"));
					} else if (Bukkit.getServer().getVersion().contains("1.16")) {
						u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> &7<&ahomeName&7> ", "&f&oExample: &7/playerhome &eHempfest &aSkybase"));
					}
					return true;
				}
				
				if (length == 2) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					Home home = new Home(p, args[0], args[1]);
					home.teleportOther();
					return true;
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
