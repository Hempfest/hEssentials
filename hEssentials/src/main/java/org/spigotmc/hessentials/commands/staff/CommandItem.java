package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandItem extends BukkitCommand {

	public CommandItem() {
		super("item");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("i"));
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
				sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <playerName> <itemName>");
					return true;
				}
				if (length == 1) {
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					if (target == null) { //check whether the player is online
					    sender.sendMessage("Player " + playerName + " is not online.");
					    return true;
					}
					sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <playerName> <itemName>");
					return true;
				}
				if (length == 2) {
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					Material itemType = Material.matchMaterial(args[1]);
					if (itemType == null) { //check whether the material exists
					    sender.sendMessage("Unknown material: " + args[1] + ".");
					    return true;
					}
					ItemStack itemStack = new ItemStack(itemType);
					target.getInventory().addItem(itemStack);
					target.sendMessage("Here you go!");
					return true;
				}
				if (length == 3) {
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					Material itemType = Material.matchMaterial(args[2]);
					if (itemType == null) { //check whether the material exists
					    sender.sendMessage("Unknown material: " + args[2] + ".");
					    return true;
					}
					try {
					int amnt = Integer.valueOf(args[1]);
					for (int i = 0; i < amnt; i++) {
						ItemStack itemStack = new ItemStack(itemType);
						target.getInventory().addItem(itemStack);
					}
					;
					target.sendMessage("Here you go!");
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						target.sendMessage(""
								+ "Not a number!");
					}
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
