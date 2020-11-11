package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.gui.staff.InventoryStaff;
import org.spigotmc.hessentials.util.heHook;

public class CommandStaff extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandStaff() {
		super("staff");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hstaff"));
		setPermission("hessentials.staff");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Staff)");
			return true;
		}

		Player p = (Player) sender;
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			GuiLibrary gui = HempCore.guiManager(p);
			new InventoryStaff(gui).open();
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
