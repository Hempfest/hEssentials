package org.spigotmc.hessentials.commands;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.gui.homes.InventoryHomes;
import org.spigotmc.hessentials.util.Home;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandHomes extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandHomes() {
		super("homes");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hhomes"));
		setPermission("hessentials.homes");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Homes)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			GuiLibrary gui = HempCore.guiManager(p);
			try {
				new InventoryHomes(HempCore.guiManager(p)).open();
			} catch (IllegalArgumentException e) {
				sendMessage(p, "&c&oThere was an issue opening the GUI. Trying now to send as list format.");
				try {
					Home home = new Home(p, "A");
					home.list();

				} catch (Exception ex) {
					sendMessage(p, "&c&oCould not retrieve home list. Contact a staff member.");
				}
			}
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
