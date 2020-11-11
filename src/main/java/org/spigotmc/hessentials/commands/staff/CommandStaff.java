package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.gui.staff.InventoryConfiguration;
import org.spigotmc.hessentials.listener.events.Events;
import org.spigotmc.hessentials.util.Utils;
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

	private static boolean isInventoryFull(Player p) { return (p.getInventory().firstEmpty() == -1); }

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
			//GuiLibrary gui = HempCore.guiManager(p);
			//new InventoryConfiguration(gui).open();
			if (Events.staffGui.containsKey(p.getUniqueId())) {
				// give their stuff back
				if (Events.staffGui.get(p.getUniqueId())) {
					Events.staffGui.put(p.getUniqueId(), false);
					p.getInventory().clear();
					ItemStack[] contents = Utils.invStorage.get(p.getUniqueId());
					for (ItemStack item : contents) {
						if (item != null) {
							if (isInventoryFull(p)) {
								p.getWorld().dropItemNaturally(p.getLocation(), item);
							} else
								p.getInventory().addItem(item);
						}
					}
					sendMessage(p, api.lib.getPrefix() + "&c&oDisabling staff mode, here's your stuff back.");
				} else {
					Events.staffGui.put(p.getUniqueId(), true);
					api.u.sendStaffMenu(p);
					sendMessage(p, api.lib.getPrefix() + "&a&oEnabling staff mode.");
				}
			} else {
				Events.staffGui.put(p.getUniqueId(), true);
				api.u.sendStaffMenu(p);
				sendMessage(p, api.lib.getPrefix() + "&a&oEnabling staff mode.");
			}
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

}
