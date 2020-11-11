package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.library.Items;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.heHook;

public class CommandGive extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandGive() {
		super("give");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hgive"));
		setPermission("hessentials.staff.give");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}


	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Give)");
			return true;
		}

		Player p = (Player) sender;
		int length = args.length;
	
				if (length == 0) {
					if (!p.hasPermission(this.getPermission())) {
						api.lib.sendNoPermission(p, this.getPermission());
						return true;
					}
					sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <playerName> <itemAmount> <itemName>");
					return true;
				}
				if (length == 1) {
					if (!p.hasPermission(this.getPermission() + ".other")) {
						api.lib.sendNoPermission(p, this.getPermission() + ".other");
						return true;
					}
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					if (target == null) { //check whether the player is online
						sender.sendMessage("Player " + playerName + " is not online.");
						return true;
					}
					sendMessage(p, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <playerName> <itemName>");
					return true;
				}
				if (length == 2) {
					if (!p.hasPermission(this.getPermission() + ".other")) {
						api.lib.sendNoPermission(p, this.getPermission() + ".other");
						return true;
					}
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					Material itemType = Items.getMaterial(args[1]);
					if (itemType == null) { //check whether the material exists
						sendMessage(p, api.lib.getPrefix() + "Item not found: " + args[1] + ".");
						return true;
					}
					ItemStack itemStack = new ItemStack(itemType);
					target.getInventory().addItem(itemStack);
					target.sendMessage("Here you go!");
					return true;
				}
				if (length == 3) {
					if (!p.hasPermission(this.getPermission() + ".other")) {
						api.lib.sendNoPermission(p, this.getPermission() + ".other");
						return true;
					}
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					Material itemType = Items.getMaterial(args[2]);
					if (itemType == null) { //check whether the material exists
						sendMessage(p, api.lib.getPrefix() + "Item not found: " + args[2] + ".");
						return true;
					}
					if (!api.u.isInt(args[1])) { //check whether the player is online
						sendMessage(p, api.lib.getPrefix() + args[1] + " is not a number.");
						return true;
					}
					int amnt = Integer.valueOf(args[1]);
					for (int i = 0; i < amnt; i++) {
						ItemStack itemStack = new ItemStack(itemType);
						target.getInventory().addItem(itemStack);
					}
					sendMessage(target, api.lib.getPrefix() + "Here you go!");
					sendMessage(p, api.lib.getPrefix() + "Gave " + '"' + target.getName() + '"' + " " + amnt + " " + itemType.toString());
					return true;
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
