package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandReload extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandReload() {
		super("hessentials");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hess", "he", "hesse", "hessversion"));
		setPermission("hessentials.staff.reload");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;
			PluginDescriptionFile pdf = HempfestEssentials.instance.getDescription();
			if (length == 0) {
				u.reloadConfiguration();
				u.sendMessage(sender, api.lib.getPrefix() + "&2&oCurrently running " + "&av" + pdf.getVersion());
				sendMessage(sender, "&fAll configs are reloaded.");
				return true;
			}
			return true;
		}

		Player p = (Player) sender;
		Utils u = new Utils();
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		PluginDescriptionFile pdf = HempfestEssentials.instance.getDescription();
		if (length == 0) {
			u.reloadConfiguration();
			if (Bukkit.getServer().getVersion().contains("1.15")) {
				u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + "&2&oCurrently running ", "&av" + pdf.getVersion(), "&3&oPlugin by &b&oHempfest"));
			} else if (Bukkit.getServer().getVersion().contains("1.16")) {
				u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + "&2&oCurrently running ", "&av" + pdf.getVersion(), "&3&oPlugin by &b&oHempfest"));
			}
			sendMessage(p, "&fAll configs are reloaded.");
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
