package org.spigotmc.hessentials.commands.economy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.gui.economy.EcoBUY;
import org.spigotmc.hessentials.gui.economy.EcoBUY2;
import org.spigotmc.hessentials.gui.economy.EcoSHOP;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Checks;
import org.spigotmc.hessentials.util.variables.Strings;

public class Eco implements Listener	 {

    static EconomyData pl;
	static Config me;
	static Logger log = Logger.getLogger("Minecraft");
	static boolean transactionSuccess = true;
	
	public static String format(double cost) {
		String number = String.valueOf(cost);
		Double numParsed = Double.parseDouble(number);
		String numString = String.format("%,.2f", numParsed);
		return numString;
	}

	public static void createPlayerData(Player p) {
		if (Checks.economyEnabled()) {
			// Do stuff
			pl = new EconomyData(p.getUniqueId());
			me = new Config("hEconomy");
			FileConfiguration e = me.getConfig();
			FileConfiguration data = pl.getConfig();
			double startingBal = e.getDouble("Economy.Starting-Balance");
			
			if (!hasBalance(p)) {
				data.set("BALANCE", Double.valueOf(format(startingBal).replaceAll(",", "")));
				pl.saveConfig();
			}
			
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}
	
	public static void updatePlayerData(Player p) {
		pl = new EconomyData(p.getUniqueId());
		me = new Config("hEconomy");
		FileConfiguration e = me.getConfig();
		FileConfiguration data = pl.getConfig();
		double startingBal = e.getDouble("Economy.Starting-Balance");
		
		if (Checks.economyEnabled()) {
			if (!hasBalance(p)) {
				data.set("BALANCE", Double.valueOf(format(startingBal).replaceAll(",", "")));
				pl.saveConfig();
			}
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}
	
	public static void depositAmount(Player p, int amount) {
		pl = new EconomyData(p.getUniqueId());
		FileConfiguration data = pl.getConfig();
		double current = data.getDouble("BALANCE");
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		int item = mainHand.getAmount();
		if (amount > item) {
			p.sendMessage(Strings.getPrefix() +"You don't have enough in hand.");
			return;
		}
		if (mainHand.getType() == Material.GOLD_BLOCK) {
			int blok = 9 * amount;

			data.set("BALANCE", current + blok);
			pl.saveConfig();

			mainHand.setAmount(item - amount);
			p.updateInventory();
			p.sendMessage(Strings.getPrefix() +"Succesfully deposited " + '"' + format(blok) + '"' + " gold.");
			return;
		}
		if (mainHand.getType() == Material.GOLD_INGOT) {

			data.set("BALANCE", current + amount);
			pl.saveConfig();

			mainHand.setAmount(item - amount);
			p.updateInventory();
			p.sendMessage(Strings.getPrefix() +"Succesfully deposited " + '"' + format(amount) + '"' + " gold.");
			return;
		}
		if (mainHand.getType() == Material.GOLD_NUGGET) {
			double nug = 0.11 * amount;

			data.set("BALANCE", current + nug);
			pl.saveConfig();

			mainHand.setAmount(item - amount);
			p.updateInventory();
			p.sendMessage(Strings.getPrefix() +"Succesfully deposited " + '"' + format(nug) + '"' + " gold.");
			return;
		}
		p.sendMessage(Strings.getPrefix() +"Invalid usage. Cannot deposit " + '"' + mainHand.getType() + '"' + ".");
		return;
	}
	
	public static void withdrawAmount(Player p, int amount) {
		pl = new EconomyData(p.getUniqueId());
		FileConfiguration data = pl.getConfig();
		double current = data.getDouble("BALANCE");
		if (amount > current) {
  	    	p.sendMessage(Strings.getPrefix() + "You don't have enough gold in the bank.");
  	    	return;
  	    }
		  if (amount > 640) {
			  p.sendMessage(Strings.getPrefix() + "You can not withdraw more than 10 stacks at a time.");
			  return;
		  }
		
		
    	ItemStack i = new ItemStack(Material.GOLD_INGOT, amount);
    	 data.set("BALANCE", current - amount);
    	    pl.saveConfig();
    	    p.getLocation().getWorld().dropItem(p.getLocation(), i);
    	    p.updateInventory();
	}
	
	public static Double getBalance(Player p) {
		EconomyData eco = new EconomyData(p.getUniqueId());
		FileConfiguration d = eco.getConfig();
		return d.getDouble("BALANCE");
	}
	
	public static boolean hasBalance(Player p) {
		EconomyData eco = new EconomyData(p.getUniqueId());
		if (eco.getConfig().getDouble("BALANCE") == 0) {
			return false;
		}
		if (!eco.exists()) {
			p.kickPlayer(Strings.color(Strings.getPrefix() + "\nKicked for:\n&c&oInvalid user data. &aRe-log."));
			return false;
		}
		return true;
	}

	public static void giveAmount(Player p, EconomyData eco, double amount) {
		FileConfiguration e = eco.getConfig();
		if (Checks.economyEnabled()) {
			// Do stuff
			
			double now = 0;
			if (hasBalance(p)) {
				double current = e.getDouble("BALANCE");
				now = current + amount;	
			} else {
				now = amount;
			}
				log.info(Strings.getPrefix() + "Player " + p.getName() + " was given " + amount + " gold.");
			e.set("BALANCE", Double.valueOf(format(now).replaceAll(",", "")));
			eco.saveConfig();
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static void takeAmount(Player p, EconomyData eco, double amount) {
		FileConfiguration e = eco.getConfig();
		if (Checks.economyEnabled()) {
			// Do stuff
			if (hasBalance(p)) {
			double current = e.getDouble("BALANCE");
			if (amount > current) {
				log.info(Strings.getPrefix() + "Player " + p.getName() + " doesn't have enough gold to take.");
				return;
			}
			e.set("BALANCE", Double.valueOf(format(current - amount).replaceAll(",", "")));
			eco.saveConfig();
            log.info(Strings.getPrefix() + amount + " was removed from player " + p.getName() + ".");
		}
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static void resetBalance(Player p, EconomyData eco) {
		FileConfiguration e = eco.getConfig();
		me = new Config("hEconomy");
		FileConfiguration e2 = me.getConfig();
		double startingBal = e2.getDouble("Economy.Starting-Balance");
		if (Checks.economyEnabled()) {
			// Do stuff
			e.set("BALANCE", Double.valueOf(format(startingBal).replaceAll(",", "")));
			eco.saveConfig();
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static void setBalance(Player p, EconomyData eco, double amount) {
		FileConfiguration e = eco.getConfig();
		if (Checks.economyEnabled()) {
			// Do stuff
			e.set("BALANCE", Double.valueOf(format(amount).replaceAll(",", "")));
			eco.saveConfig();
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static void getLeaderboard(Player p) {
		if (Checks.economyEnabled()) {
			// Do stuff
			YamlConfiguration config = new YamlConfiguration();

			File[] files =new File(HempfestEssentials.getInstance().getDataFolder() + "/economy/players").listFiles();
			for (File file : files) {
				try {
					config.load(file);

					HashMap<String, Double> players = new HashMap<String, Double>();

					// Filling the hashMap
					for (String playerName : config.getKeys(false)) {
						
						players.put(playerName, config.getDouble("BALANCE"));
					}
					p.sendMessage(Strings.color("&7&m------------&7&l[&6&oTop&7&l]&7&m------------"));

					String nextTop = "";
					Double nextTopBal = 0.0;

					for (int i = 1; i < 11; i++) {
						for (String playerName : players.keySet()) {
							if (players.get(playerName) > nextTopBal) {
								nextTop = playerName;
								nextTopBal = players.get(playerName);
							}
						}
						EconomyData data = new EconomyData(UUID.fromString(config.toString()));
						FileConfiguration player = data.getConfig();
						p.sendMessage(Strings.color(" &7# &6&l" + i + " &e&o" + player.getDouble("BALANCE")
								+ " &7: &6&l" + format(nextTopBal)));
						players.remove(nextTop);
						nextTop = "";
						nextTopBal = 0.0;
					}
					p.sendMessage(Strings.color("&7&m------------&7&l[&6&oTop&7&l]&7&m------------"));

				} catch (FileNotFoundException e) {
					log.info(Strings.getPrefix() + " Tried accessing a file that doesnt exist.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InvalidConfigurationException e) {
					log.info(Strings.getPrefix() + " Configuration is null; maybe it's missing?");
				}

			}
		} else
			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static void transferMoney(Player p, Player target, EconomyData pEco, EconomyData tEco) {
		//FileConfiguration e = pEco.getConfig();
		//FileConfiguration e2 = tEco.getConfig();
		if (Checks.economyEnabled()) {
			// Do stuff
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}
	
	public static boolean hasEnough(Player p, double amount) {
		pl = new EconomyData(p.getUniqueId());
		FileConfiguration e = pl.getConfig();
		if (e.getDouble("BALANCE") < amount) {
			return false;
		} else
			return true;
	}
	
	public static void buyItem(Player p, ItemStack item, int amount, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
			me = new Config("hEconomy");
			FileConfiguration m = me.getConfig();
			double itemPrice = m.getDouble(item.getType().name() + ".Buy-Price");
			double itPrice = itemPrice * amount;
			if (m.getDouble(item.getType().name() + ".Buy-Price") == 0) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "Item not found."));
				if (p.hasPermission("hessentials.staff")) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "Configure item: " + item.getType().name().toString()));
				}
				return;
			}
			if (hasEnough(p, itPrice)) {
				for (int i = 0; i < amount; i++) {
					if (isInventoryFull(p) == Boolean.valueOf(false)) {
					p.getInventory().addItem(item);
					transactionSuccess = true;
					} else
						transactionSuccess = false;
				}
				if (transactionSuccess) {
				takeAmount(p, eco, itPrice);
				 p.sendMessage(Strings.color(Strings.getPrefix() + amount + " " + item.getType().name().toString().toLowerCase() + " was bought for " + itPrice + " &6gold"));
				} else {
					p.sendMessage(Strings.color(Strings.getPrefix() + "You don't have enough inventory space."));
				}
			} else {
				p.sendMessage(Strings.color(Strings.getPrefix() + "You don't have enough gold."));
			}
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}
	
	public static int getAmount(Player arg0, ItemStack arg1) {
        if (arg1 == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = arg0.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(arg1))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }
	
	public static void sellItem(Player p, ItemStack item, int amount, EconomyData eco) {
		if (Checks.economyEnabled()) {
			// Do stuff
			me = new Config("hEconomy");
			FileConfiguration m = me.getConfig();
			double itemPrice = m.getDouble(item.getType().name() + ".Sell-Price");
			
			if (m.getDouble(item.getType().name() + ".Sell-Price") == 0) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "Item not found."));
				if (p.hasPermission("hessentials.staff")) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "Configure item: " + item.getType().name().toString()));
				}
				return;
			}
			if (!p.getInventory().contains(item.getType())) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "You don't have any " + '"' + item.getType().name().toString().toLowerCase() + '"' + " in your inventory."));
				return;
			}
			if (getAmount(p, item) < amount) {
				p.sendMessage(Strings.color(Strings.getPrefix() + "You don't have enough " + '"' + item.getType().name().toString().toLowerCase() + '"' + " in your inventory."));
				return;
			}
			for(int i = 0; i < p.getInventory().getSize(); i++){
				  ItemStack itm = p.getInventory().getItem(i);
				 
				  if(itm != null && itm.getType().equals(item.getType())){
				    int amt = itm.getAmount() - amount;
				    itm.setAmount(amt);
				    p.getInventory().setItem(i, amt > 0 ? itm : null);
				    p.updateInventory();
				   
				    break;
				  }
					
			}
			double itPrice = itemPrice * amount;
			 giveAmount(p, eco, itPrice);
			 p.sendMessage(Strings.color(Strings.getPrefix() + amount + " " + item.getType().name().toString().toLowerCase() + " was sold for " + itPrice + " &6gold"));
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}
	
	public static boolean isInventoryFull(Player p)
	{
	    return p.getInventory().firstEmpty() == -1;
	}
	
	public static void openGUI(Player p) {
		if (Checks.economyEnabled()) {
			// Do stuff
		} else

			log.info(Strings.getPrefix() + "Economy not enabled!~");
		return;
	}

	public static Inventory SELL_shopPage_1() {
		Inventory page1 = Bukkit.createInventory(null, 54, Strings.color("&6&lEconomy &7Sell: &a&oPage 1"));
		ItemStack b5 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm5 = b5.getItemMeta();
		bm5.setDisplayName(Strings.color("&0*"));
		b5.setItemMeta(bm5);
		ItemStack b4 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta bm4 = b4.getItemMeta();
		bm4.setDisplayName(Strings.color("&7&oNext page &f&o>> &7&o(&b&oClick&7&o)"));
		b4.setItemMeta(bm4);
		ItemStack b3 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta bm3 = b3.getItemMeta();
		bm3.setDisplayName(Strings.color("&7&oMain Menu &f&o<< &7&o(&b&oClick&7&o)"));
		b3.setItemMeta(bm3);
		
		// Add items to list
		me = new Config("hEconomy");
		FileConfiguration m = me.getConfig();
		if (m.contains("STONE")) {
		ItemStack b2 = new ItemStack(Material.STONE, 1);
		ItemMeta bm2 = b2.getItemMeta();
		double amount = m.getDouble("STONE.Sell-Price");
		bm2.setDisplayName(Strings.color("&7&oStone"));
		bm2.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount + "&6&lGOLD")));
		b2.setItemMeta(bm2);
		
		page1.setItem(10, b2);
		}
		if (m.contains("COBBLESTONE")) {
		ItemStack b1 = new ItemStack(Material.COBBLESTONE, 1);
		ItemMeta bm1 = b1.getItemMeta();
		double amount2 = m.getDouble("COBBLESTONE.Sell-Price");
		bm1.setDisplayName(Strings.color("&7&oCobblestone"));
		bm1.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount2 + "&6&lGOLD")));
		b1.setItemMeta(bm1);
		
		page1.setItem(11, b1);
		}
		if (m.contains("DIRT")) {
		ItemStack b0 = new ItemStack(Material.DIRT, 1);
		ItemMeta bm0 = b0.getItemMeta();
		double amount3 = m.getDouble("DIRT.Sell-Price");
		bm0.setDisplayName(Strings.color("&7&oDirt"));
		bm0.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount3 + "&6&lGOLD")));
		b0.setItemMeta(bm0);
		
		page1.setItem(12, b0);
		}
		if (m.contains("GRASS_BLOCK")) {
		ItemStack b = new ItemStack(Material.GRASS_BLOCK, 1);
		ItemMeta bm = b.getItemMeta();
		double amount4 = m.getDouble("GRASS_BLOCK.Sell-Price");
		bm.setDisplayName(Strings.color("&7&oGrass_block"));
		bm.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount4 + "&6&lGOLD")));
		b.setItemMeta(bm);
		
		page1.setItem(13, b);
		}
		if (m.contains("OAK_LOG")) {
		ItemStack b6 = new ItemStack(Material.OAK_LOG, 1);
		ItemMeta bm6 = b6.getItemMeta();
		double amount0 = m.getDouble("OAK_LOG.Sell-Price");
		bm6.setDisplayName(Strings.color("&7&oOak_log"));
		bm6.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount0 + "&6&lGOLD")));
		b6.setItemMeta(bm6);
		
		page1.setItem(14, b6);
		}
		if (m.contains("SPRUCE_LOG")) {
			ItemStack b7 = new ItemStack(Material.SPRUCE_LOG, 1);
			ItemMeta bm7 = b7.getItemMeta();
			double amount5 = m.getDouble("SPRUCE_LOG.Sell-Price");
			bm7.setDisplayName(Strings.color("&7&oSpruce_log"));
			bm7.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount5 + "&6&lGOLD")));
			b7.setItemMeta(bm7);
			
			page1.setItem(15, b7);
		}
		if (m.contains("BIRCH_LOG")) {
			ItemStack b8 = new ItemStack(Material.BIRCH_LOG, 1);
			ItemMeta bm8 = b8.getItemMeta();
			double amount6 = m.getDouble("BIRCH_LOG.Sell-Price");
			bm8.setDisplayName(Strings.color("&7&oBirch_log"));
			bm8.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount6 + "&6&lGOLD")));
			b8.setItemMeta(bm8);
			
			page1.setItem(16, b8);
		}
		if (m.contains("SPRUCE_LOG")) {
			ItemStack b9 = new ItemStack(Material.SPRUCE_LOG, 1);
			ItemMeta bm9 = b9.getItemMeta();
			double amount7 = m.getDouble("SPRUCE_LOG.Sell-Price");
			bm9.setDisplayName(Strings.color("&7&oSpruce_log"));
			bm9.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount7 + "&6&lGOLD")));
			b9.setItemMeta(bm9);
			
			page1.setItem(19, b9);
		}
		if (m.contains("JUNGLE_LOG")) {
			ItemStack b10 = new ItemStack(Material.JUNGLE_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("JUNGLE_LOG.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oJungle_log"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(20, b10);
		}
		if (m.contains("ACACIA_LOG")) {
			ItemStack b10 = new ItemStack(Material.ACACIA_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("ACACIA_LOG.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oAcacia_log"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(21, b10);
		}
		if (m.contains("DARK_OAK_LOG")) {
			ItemStack b10 = new ItemStack(Material.DARK_OAK_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("DARK_OAK_LOG.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oDark_oak_log"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(22, b10);
		}
		if (m.contains("GRANITE")) {
			ItemStack b10 = new ItemStack(Material.GRANITE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GRANITE.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oGranite"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(23, b10);
		}
		if (m.contains("POLISHED_GRANITE")) {
			ItemStack b10 = new ItemStack(Material.POLISHED_GRANITE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("POLISHED_GRANITE.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oPolished_granite"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(24, b10);
		}
		if (m.contains("STONE_BRICKS")) {
			ItemStack b10 = new ItemStack(Material.STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("STONE_BRICKS.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oStone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(25, b10);
		}
		if (m.contains("CHISELED_STONE_BRICKS")) {
			ItemStack b10 = new ItemStack(Material.CHISELED_STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("CHISELED_STONE_BRICKS.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oChiseled_stone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(28, b10);
		}
        if (m.contains("CRACKED_STONE_BRICKS")) {
        	ItemStack b10 = new ItemStack(Material.CRACKED_STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("CRACKED_STONE_BRICKS.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oCracked_stone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(29, b10);
		}
        if (m.contains("GLASS")) {
        	ItemStack b10 = new ItemStack(Material.GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GLASS.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oGlass"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(30, b10);
		}
        if (m.contains("GLOWSTONE")) {
        	ItemStack b10 = new ItemStack(Material.GLOWSTONE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GLOWSTONE.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oGlowstone"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(31, b10);
		}
        if (m.contains("TORCH")) {
        	ItemStack b10 = new ItemStack(Material.TORCH, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("TORCH.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oTorch"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(32, b10);
		}
        if (m.contains("BOOKSHELF")) {
        	ItemStack b10 = new ItemStack(Material.BOOKSHELF, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("BOOKSHELF.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oBookshelf"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(33, b10);
        }
        if (m.contains("ENCHANTING_TABLE")) {
        	ItemStack b10 = new ItemStack(Material.ENCHANTING_TABLE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("ENCHANTING_TABLE.Sell-Price");
			bm10.setDisplayName(Strings.color("&7&oEnchanting_table"));
			bm10.setLore(Arrays.asList(Strings.color("Sell for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(34, b10);
        }
		
		//
		
		
		page1.setItem(37, b3);
		page1.setItem(43, b4);
		page1.setItem(0, b5);
		page1.setItem(1, b5);
		page1.setItem(2, b5);
		page1.setItem(3, b5);
		page1.setItem(4, b5);
		page1.setItem(5, b5);
		page1.setItem(6, b5);
		page1.setItem(7, b5);
		page1.setItem(8, b5);
		page1.setItem(9, b5);
		page1.setItem(17, b5);
		page1.setItem(18, b5);
		page1.setItem(26, b5);
		page1.setItem(27, b5);
		page1.setItem(35, b5);
		page1.setItem(36, b5);
		page1.setItem(44, b5);
		page1.setItem(45, b5);
		page1.setItem(46, b5);
		page1.setItem(47, b5);
		page1.setItem(48, b5);
		page1.setItem(49, b5);
		page1.setItem(50, b5);
		page1.setItem(51, b5);
		page1.setItem(52, b5);
		page1.setItem(53, b5);
		return page1;

	}

	public static Inventory SELL_shopPage_2() {
		Inventory page2 = Bukkit.createInventory(null, 54, Strings.color("&6&lEconomy &7Sell: &a&oPage 2"));
		return page2;

	}
	
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String menu = e.getView().getTitle();
	if (menu.equals(Strings.color("&6&lEconomy &7Buy: &a&oPage 1"))) {
		if (e.getCurrentItem() != null) {
			ItemStack item = e.getCurrentItem();
		if(e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
			if (item.hasItemMeta()) {
				if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oMain Menu &f&o<< &7&o(&b&oClick&7&o)"))) {
					e.setCancelled(true);
					new EcoSHOP(Utils.guiManager(p)).open();
				} 
				if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oNext page &f&o>> &7&o(&b&oClick&7&o)"))) {
					e.setCancelled(true);
					new EcoBUY2(Utils.guiManager(p)).open();
				} 
				
				
			
			}
		}
		EcoItems.iterateItems(e);
	}
		
	}
	if (menu.equals(Strings.color("&6&lEconomy &7Buy: &a&oPage 2"))) {
		if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
			ItemStack item = e.getCurrentItem();
			if (item.hasItemMeta()) {
				if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oBack page &f&o<< &7&o(&b&oClick&7&o)"))) {
					e.setCancelled(true);
					new EcoBUY(Utils.guiManager(p)).open();
				} 
				if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oNext page &f&o>> &7&o(&b&oClick&7&o)"))) {
					e.setCancelled(true);

				} 
			}
		}
		EcoItems.iterateItems(e);
	}
	}
	
}
