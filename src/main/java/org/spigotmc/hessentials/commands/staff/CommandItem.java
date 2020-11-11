package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.library.Items;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.heHook;

public class CommandItem extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandItem() {
		super("item");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hitem", "i", "hi"));
		setPermission("hessentials.staff.item");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}


	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Item)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
	
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <itemAmount> <itemName>");
					return true;
				}
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					if (!api.u.isInt(args[0])) { //check whether the player is online
						sendMessage(p, api.lib.getPrefix() + args[0] + " is not a number.");
						return true;
					}
					sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <itemAmount> <itemName>");
					return true;
				}
				if (length == 2) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					Material itemType = Items.getMaterial(args[1]);
					if (itemType == null) { //check whether the material exists
						sendMessage(p, api.lib.getPrefix() + "Item not found: " + args[1] + ".");
						return true;
					}
					if (!api.u.isInt(args[0])) { //check whether the player is online
						sendMessage(p, api.lib.getPrefix() + args[0] + " is not a number.");
						return true;
					}
					int amnt = Integer.valueOf(args[0]);
					for (int i = 0; i < amnt; i++) {
						ItemStack itemStack = new ItemStack(itemType);
						p.getInventory().addItem(itemStack);
					}
					sendMessage(p, api.lib.getPrefix() + "Here you go!");

					return true;
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
