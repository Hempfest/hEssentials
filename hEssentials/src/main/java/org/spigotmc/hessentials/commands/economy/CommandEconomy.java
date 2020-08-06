package org.spigotmc.hessentials.commands.economy;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.gui.economy.EcoSHOP;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;


public class CommandEconomy extends BukkitCommand {
	
	EconomyData eco;
	UUID uuid;
	Player p;
	int length;
	
	public CommandEconomy() {
		super("economy");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("heconomy", "heco"));
		setPermission("hessentials.economy");
	}
	
	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		p = (Player) sender;
		uuid = p.getUniqueId();
		eco = new EconomyData(uuid);
		length = args.length;
		
		if (length == 0) {
			
		}
		
		if (length == 1) {
			if (args[0].equalsIgnoreCase("shop")) {
				new EcoSHOP(Utils.guiManager(p)).open();
				return true;
			}
			if (args[0].equalsIgnoreCase("balance")) {
				sendMessage(p, Strings.getPrefix() + "Balance: " + Eco.format(Eco.getBalance(p)) + " &6&ogold");
				return true;
			}
			if (args[0].equalsIgnoreCase("toplist")) {
				Eco.getLeaderboard(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " set <&c" + "playerName" + "&r> <&cAmount&r>");
				return true;
			}
			return true;
		}
		
		if (length == 2) {
			if (args[0].equalsIgnoreCase("deposit")) {
				int amount = Integer.valueOf(args[1]);
				if (!Checks.isInt(args[1])) {
					p.sendMessage(Strings.getPrefix() + '"' + args[1] + '"' + " is not a number.");
					return true;
				}
				Eco.depositAmount(p, amount);
				return true;
			}
			if (args[0].equalsIgnoreCase("withdraw")) {
				int amount = Integer.valueOf(args[1]);
				if (!Checks.isInt(args[1])) {
					p.sendMessage(Strings.getPrefix() + '"' + args[1] + '"' + " is not a number.");
					return true;
				}
				Eco.withdrawAmount(p, amount);
				return true;
			}
			if (args[0].equalsIgnoreCase("reset")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sendMessage(p, Strings.getPrefix() + "Player not found");
					return true;
				}
				sendMessage(p, Strings.getPrefix() + "Reset the balance of player: " + target.getName());
				Eco.resetBalance(p, eco);
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " set " + args[1] + " <&cAmount&r>");
				return true;
			}
			return true;
		}
		
		if (length == 3) {
			if (args[0].equalsIgnoreCase("set")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sendMessage(p, Strings.getPrefix() + "Player not found");
					return true;
				}
				if (!args[2].contains(".") || !Checks.isDouble(args[2])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: ###.##");
					return true;
				}
				sendMessage(p, Strings.getPrefix() + "Set the balance of player: " + target.getName() + " to: " + Eco.format(Double.valueOf(args[2])));
				Eco.setBalance(p, eco, Double.valueOf(args[2]));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("buy")) {
				int amount = Integer.valueOf(args[1]); 
				Material itemType = Material.matchMaterial(args[2]);
				if (itemType == null) { //check whether the material exists
				    sender.sendMessage("Unknown material: " + args[2] + ".");
				    return true;
				}
				ItemStack itemStack = new ItemStack(itemType);
				Eco.buyItem(p, itemStack, amount, eco);
				return true;
			}
			if (args[0].equalsIgnoreCase("sell")) {
				int amount = Integer.valueOf(args[1]); 
				Material itemType = Material.matchMaterial(args[2]);
				if (itemType == null) { //check whether the material exists
				    sender.sendMessage("Unknown material: " + args[2] + ".");
				    return true;
				}
				ItemStack itemStack = new ItemStack(itemType);
				Eco.sellItem(p, itemStack, amount, eco);
				return true;
			}
		}
		
		
		
		
		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
	}
	
}
