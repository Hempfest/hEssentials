package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandGive extends BukkitCommand {

	public CommandGive() {
		super("give");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hgive"));
		setPermission("hessentials.give");
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
				sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <playerName> <itemName>");
				sendMessage(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel + " <playerName> <itemAmount> <itemName>");
					return true;
				}
				if (length == 1) {
					if (!p.hasPermission(this.getPermission() + ".others")) {
						Strings.sendNoPermission(p, this.getPermission() + ".others");
						return true;
					}
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
					if (!p.hasPermission(this.getPermission() + ".others")) {
						Strings.sendNoPermission(p, this.getPermission() + ".others");
						return true;
					}
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
					if (!p.hasPermission(this.getPermission() + ".others")) {
						Strings.sendNoPermission(p, this.getPermission() + ".others");
						return true;
					}
					String playerName = args[0];
					Player target = sender.getServer().getPlayerExact(playerName);
					Material itemType = Material.matchMaterial(args[2]);
					if (itemType == null) { //check whether the material exists
					    sender.sendMessage("Unknown material: " + args[2] + ".");
					    return true;
					}
					if (!Checks.isInt(args[1])) { //check whether the player is online
					    sendMessage(p, Strings.getPrefix() + args[1] + " is not a number.");
					    return true;
					}
					int amnt = Integer.valueOf(args[1]);
					for (int i = 0; i < amnt; i++) {
						ItemStack itemStack = new ItemStack(itemType);
						target.getInventory().addItem(itemStack);
					}
					;
					sendMessage(target, Strings.getPrefix() + "Here you go!");
					sendMessage(p, Strings.getPrefix() + "Gave " + '"' + target.getName() + '"' + " " + amnt + " " + itemType.toString());
					return true;
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
