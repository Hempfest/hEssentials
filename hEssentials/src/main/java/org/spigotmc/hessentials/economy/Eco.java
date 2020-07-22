package org.spigotmc.hessentials.economy;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.variables.Checks;

import m.h.clans.util.Strings;

public abstract class Eco {
	
	String something;
	
	
	public void createPlayerData() {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void giveAmount(Player p, double amount, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void takeAmount(Player p, double amount, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void resetBalance(Player p, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void setBalance(Player p, double amount, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public List<String> getLeadboard(EconomyData eco) {
		List<String> test = eco.getConfig().getStringList("Test");
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
		return test;
	}
	
	public void transferMoney(Player p, Player target, EconomyData pEco, EconomyData tEco) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void buyItem(Player p, ItemStack item, double amount) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void sellItem(Player p, ItemStack item, double amount) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public void openGUI(Player p) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else
	
		something = "Economy not found";
	}
	
	public Inventory MAIN_shopPage() {
		Inventory main = Bukkit.createInventory(null, 54, Strings.colorize("&6&lEconomy&7: &a&oStore Front"));
		return main;
	}
	
	public Inventory BUY_shopPage_1() {
		Inventory page1 = Bukkit.createInventory(null, 54, Strings.colorize("&6&lEconomy &7Buy: &a&oPage 1"));
		
		return page1;
		
	}
	
	public Inventory BUY_shopPage_2() {
		Inventory page2 = Bukkit.createInventory(null, 54, Strings.colorize("&6&lEconomy &7Buy: &a&oPage 2"));
		return page2;
		
	}
	
	public Inventory SELL_shopPage_1() {
		Inventory page1 = Bukkit.createInventory(null, 54, Strings.colorize("&6&lEconomy &7Sell: &a&oPage 1"));
		
		return page1;
		
	}
	
	public Inventory SELL_shopPage_2() {
		Inventory page2 = Bukkit.createInventory(null, 54, Strings.colorize("&6&lEconomy &7Sell: &a&oPage 2"));
		return page2;
		
	}
	
}
