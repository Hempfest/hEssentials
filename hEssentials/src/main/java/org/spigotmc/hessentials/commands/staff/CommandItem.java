package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandItem extends BukkitCommand {

	public CommandItem() {
		super("item");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hitem", "i", "hi"));
		setPermission("hessentials.item");
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
		int length = args.length;
	
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
				sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <itemAmount> <itemName>");
					return true;
				}
				if (length == 1) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					if (!Checks.isInt(args[0])) { //check whether the player is online
					    sendMessage(p, Strings.getPrefix() + args[0] + " is not a number.");
					    return true;
					}
					sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <itemAmount> <itemName>");
					return true;
				}
				if (length == 2) {
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Material itemType = Material.matchMaterial(args[1]);
					if (itemType == null) { //check whether the material exists
					    sendMessage(p, Strings.getPrefix() + "Unknown material: " + args[1] + ".");
					    return true;
					}
					if (!Checks.isInt(args[0])) { //check whether the player is online
					    sendMessage(p, Strings.getPrefix() + args[0] + " is not a number.");
					    return true;
					}
						int amnt = Integer.valueOf(args[0]);
						for (int i = 0; i < amnt; i++) {
							ItemStack itemStack = new ItemStack(itemType);
							p.getInventory().addItem(itemStack);
						}
						;
						sendMessage(p, Strings.getPrefix() + "Here you go!");
						
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
