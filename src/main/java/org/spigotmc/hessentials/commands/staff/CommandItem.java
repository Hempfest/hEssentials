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
					sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <itemName> <itemAmount>");
					return true;
				}
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					Material itemType = Items.getMaterial(args[0]);
					if (itemType == null) { //check whether the material exists
						sendMessage(p, api.lib.getPrefix() + "Item not found: " + args[0] + ".");
						return true;
					}
					ItemStack itemStack = new ItemStack(itemType, 64);
					p.getInventory().addItem(itemStack);
					sendMessage(p, api.lib.getPrefix() + "Here you go!");
					return true;
				}
				if (length == 2) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					Material itemType = Items.getMaterial(args[0]);
					if (itemType == null) { //check whether the material exists
						sendMessage(p, api.lib.getPrefix() + "Item not found: " + args[0] + ".");
						return true;
					}
					if (!api.u.isInt(args[1])) { //check weather the 2nd argument is a number or not
						sendMessage(p, api.lib.getPrefix() + args[1] + " is not a number.");
						return true;
					}
					int amnt = Integer.parseInt(args[1]);
						ItemStack itemStack = new ItemStack(itemType, amnt);
						p.getInventory().addItem(itemStack);
					sendMessage(p, api.lib.getPrefix() + "Here you go!");

					return true;
				}
		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
