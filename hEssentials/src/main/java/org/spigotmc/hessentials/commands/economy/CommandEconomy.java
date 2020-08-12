package org.spigotmc.hessentials.commands.economy;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.gui.economy.EcoSHOP;
import org.spigotmc.hessentials.util.MaterialUtils;
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
		if (Checks.economyEnabled()) {
		if (length == 0) {
			
		}
		
		if (length == 1) {
			if (p.hasPermission("hessentials.economy")) {
			if (args[0].equalsIgnoreCase("shop")) {
				new EcoSHOP(Utils.guiManager(p)).open();
				return true;
			}
			if (args[0].equalsIgnoreCase("buylist")) {
				Economy.paginateItemList(p, 1);
				return true;
			}
			if (args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("bal")) {
				sendMessage(p, Strings.getPrefix() + "Balance: " + Economy.format(Economy.getBalance(p)) + " &6&ogold");
				return true;
			}
			if (args[0].equalsIgnoreCase("toplist")) {
				Economy.getLeaderboard(p, 1);
				return true;
			}
			if (args[0].equalsIgnoreCase("pay")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " pay <&c" + "playerName" + "&r> <&cAmount&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("buy")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " buy <&c" + "amount" + "&r> <&citem_Name&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("sell")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " sell <&c" + "amount" + "&r> <&citem_Name&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("withdraw")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " withdraw <&c" + "amount" + "&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("deposit")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " deposit <&c" + "amount" + "&r>");
				return true;
			}
			} else {
				Strings.sendNoPermission(p, "hessentials.economy");
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (!p.hasPermission("hessentials.economy.set")) {
					Strings.sendNoPermission(p, "hessentials.economy.set");
					return true;
				}
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " set <&c" + "playerName" + "&r> <&cAmount&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("reset")) {
				if (!p.hasPermission("hessentials.economy.reset")) {
					Strings.sendNoPermission(p, "hessentials.economy.reset");
					return true;
				}
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " reset <&cplayerName&f>");
				return true;
			}
			return true;
		}
		
		if (length == 2) {
			if (args[0].equalsIgnoreCase("buylist")) {
				if (!Checks.isInt(args[1])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: #?");
					return true;
				}
				int page = Integer.valueOf(args[1]);
				Economy.paginateItemList(p, page);
				return true;
			}
			if (args[0].equalsIgnoreCase("toplist")) {
				if (!Checks.isInt(args[1])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: #?");
					return true;
				}
				int page = Integer.valueOf(args[1]);
				Economy.getLeaderboard(p, page);
				return true;
			}
			if (args[0].equalsIgnoreCase("buy")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " buy <&c" + "amount" + "&r> <&citem_Name&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("pay")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " pay <&c" + "playerName" + "&r> <&cAmount&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("sell")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " sell <&c" + "amount" + "&r> <&citem_Name&r>");
				return true;
			}
			if (args[0].equalsIgnoreCase("deposit")) {
				if (!Checks.isInt(args[1])) {
					p.sendMessage(Strings.getPrefix() + '"' + args[1] + '"' + " is not a number.");
					return true;
				}
				int amount = Integer.valueOf(args[1]);
				Economy.depositAmount(p, amount);
				return true;
			}
			if (args[0].equalsIgnoreCase("withdraw")) {
				if (!Checks.isInt(args[1])) {
					p.sendMessage(Strings.getPrefix() + '"' + args[1] + '"' + " is not a number.");
					return true;
				}
				int amount = Integer.valueOf(args[1]);
				Economy.withdrawAmount(p, amount);
				return true;
			}
			if (args[0].equalsIgnoreCase("reset")) {
				if (!p.hasPermission("hessentials.economy.reset")) {
					Strings.sendNoPermission(p, "hessentials.economy.reset");
					return true;
				}
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sendMessage(p, Strings.getPrefix() + "Player not found");
					return true;
				}
				EconomyData eco2 = new EconomyData(target.getUniqueId());
				sendMessage(p, Strings.getPrefix() + "Reset the balance of player: " + target.getName());
				Economy.resetBalance(p, eco2);
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				sendMessage(p, Strings.getPrefix() + "Not enough arguments! ~ \nExpected /" + commandLabel + " set " + args[1] + " <&cAmount&r>");
				return true;
			}
			return true;
		}
		
		if (length == 3) {
			if (args[0].equalsIgnoreCase("pay")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sendMessage(p, Strings.getPrefix() + "Player not found");
					return true;
				}
				if (!Checks.isInt(args[2])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: #?");
					return true;
				}
				if (!Economy.hasEnough(target, Integer.valueOf(args[2]))) {
					sendMessage(p, Strings.getPrefix() + "You don't have enough gold!");
					return true;
				}
				EconomyData eco2 = new EconomyData(target.getUniqueId());
				int amount = Integer.valueOf(args[2]);
				Economy.takeAmount(p, eco, amount);
				Economy.giveAmount(target, eco2, amount);
				
				sendMessage(p, Strings.getPrefix() + "Sent " + amount +  "&6g&f to player: " + target.getName());
				target.sendTitle(Strings.color(Strings.getPrefix()), Strings.color("&2&lYOU &agot &6&lGOLD&a."), 20, 60, 20);
				target.spawnParticle(Particle.FIREWORKS_SPARK, target.getEyeLocation(), 5);
				target.playSound(target.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 6, 1);
				target.playEffect(EntityEffect.FIREWORK_EXPLODE);
				target.playSound(target.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 10, 1);
				target.playSound(target.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
				sendMessage(target, Strings.getPrefix() + "Player " + p.getName() +  " sent you: " + amount + "&6g");
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (!p.hasPermission("hessentials.economy.set")) {
					Strings.sendNoPermission(p, "hessentials.economy.set");
					return true;
				}
				Player target = Bukkit.getPlayer(args[1]);
				
				if (target == null) {
					sendMessage(p, Strings.getPrefix() + "Player not found");
					return true;
				}
				EconomyData eco2 = new EconomyData(target.getUniqueId());
				if (!args[2].contains(".") || !Checks.isDouble(args[2])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: ###.##");
					return true;
				}
				sendMessage(p, Strings.getPrefix() + "Set the balance of player: " + target.getName() + " to: " + Economy.format(Double.valueOf(args[2])));
				Economy.setBalance(p, eco2, Double.valueOf(args[2]));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("buy")) {
				 
				Material itemType = MaterialUtils.getMaterial(args[2]);
				if (itemType == null) { //check whether the material exists
					sendMessage(p, Strings.getPrefix() + "Item not found: " + args[2] + ".");
				    return true;
				}
				if (!Checks.isInt(args[1])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: #?");
					return true;
				}
				int amount = Integer.valueOf(args[1]);
				ItemStack itemStack = new ItemStack(itemType);
				Economy.buyItem(p, itemStack, amount, eco);
				return true;
			}
			if (args[0].equalsIgnoreCase("sell")) {
				
				Material itemType = MaterialUtils.getMaterial(args[2]);
				if (itemType == null) { //check whether the material exists
					sendMessage(p, Strings.getPrefix() + "Item not found: " + args[2] + ".");
				    return true;
				}
				if (!Checks.isInt(args[1])) {
					sendMessage(p, Strings.getPrefix() + "Wrong format!\nExpected format: #?");
					return true;
				}
				int amount = Integer.valueOf(args[1]); 
				ItemStack itemStack = new ItemStack(itemType);
				Economy.sellItem(p, itemStack, amount, eco);
				return true;
			}
		}
		sendMessage(p, Strings.getPrefix() + "You entered the command wrong.");
		return true;
		} else {
			sendMessage(p, Strings.getPrefix() + "hEssentials &6Gold Economy &ris disabled on this server.");
			return true;
			
		}
		
		

	}
	
}
