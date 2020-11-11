package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.gui.homes.InventoryHomesOther;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandHomelist extends BukkitCommand {

	heHook api = heHook.getHook();
	Utils u = new Utils();

	public CommandHomelist() {
		super("listhomes");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hlisthomes", "homelist", "hhomelist", "hl", "lh"));
		setPermission("hessentials.staff.homes.use.others");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Homelist)");
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
						u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&oExample: &7/homelist &eHempfest"));
					} else if (Bukkit.getServer().getVersion().contains("1.16")) {
						u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cplayerName&7> ", "&f&oExample: &7/homelist &eHempfest"));
					}
					return true;
				}
				
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					if (api.u.usernameToUUID(args[0]) == null) {
						sendMessage(p, api.lib.getPrefix() + "The player " + '"' + args[0] + '"' + " was not found.");
						return true;
					}
					GuiLibrary gui = HempCore.guiManager(p);
					gui.setData2(args[0]);
					new InventoryHomesOther(gui).open();
					return true;
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
