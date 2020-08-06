package org.spigotmc.hessentials.commands.economy;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.gui.economy.EcoBUY;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Strings;

public class EcoItems{
	
	protected static ItemStack FILLER_GLASS = makeItem(Material.BLACK_STAINED_GLASS_PANE, " ");
	
	public static ItemStack makeItem(Material material, String displayName, String... lore) {

		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);

		itemMeta.setLore(Arrays.asList(lore));
		item.setItemMeta(itemMeta);

		return item;
	}
	
	public static void setFillerGlass(Inventory inventory) {
		for (int i = 0; i < 36; i++) {
			if (inventory.getItem(i) == null) {
				inventory.setItem(i, FILLER_GLASS);
			}
		}
	}
	
	
	
	public static void fillerGlassMain(Inventory main) {
		ItemStack b4 = makeItem(Material.ENCHANTED_BOOK, Strings.color("&7&oBuy items (&b&oCLICK&7&o)"));
		ItemStack b3 = makeItem(Material.ENCHANTED_BOOK, Strings.color("&7&oSell items (&b&oCLICK&7&o)"));
		
		main.setItem(21, b3);
		main.setItem(23, b4);
		setFillerGlass(main);
	}
	
	public static void fillerGlassMenu(Inventory page1) {
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
	}
	
	public static void fillerGlassMenu2(Inventory page2) {
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
		bm3.setDisplayName(Strings.color("&7&oBack page &f&o<< &7&o(&b&oClick&7&o)"));
		b3.setItemMeta(bm3);
		page2.setItem(37, b3);
		page2.setItem(43, b4);
		page2.setItem(0, b5);
		page2.setItem(1, b5);
		page2.setItem(2, b5);
		page2.setItem(3, b5);
		page2.setItem(4, b5);
		page2.setItem(5, b5);
		page2.setItem(6, b5);
		page2.setItem(7, b5);
		page2.setItem(8, b5);
		page2.setItem(9, b5);
		page2.setItem(17, b5);
		page2.setItem(18, b5);
		page2.setItem(26, b5);
		page2.setItem(27, b5);
		page2.setItem(35, b5);
		page2.setItem(36, b5);
		page2.setItem(44, b5);
		page2.setItem(45, b5);
		page2.setItem(46, b5);
		page2.setItem(47, b5);
		page2.setItem(48, b5);
		page2.setItem(49, b5);
		page2.setItem(50, b5);
		page2.setItem(51, b5);
		page2.setItem(52, b5);
		page2.setItem(53, b5);
	}
	
	
	public static void iterateConfigItemsBuying(Config me, FileConfiguration m, Inventory page1) {
		if (m.contains("STONE")) {
		ItemStack b2 = new ItemStack(Material.STONE, 1);
		ItemMeta bm2 = b2.getItemMeta();
		double amount = m.getDouble("STONE.Buy-Price");
		bm2.setDisplayName(Strings.color("&7&oStone"));
		bm2.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount + "&6&lGOLD")));
		b2.setItemMeta(bm2);
		
		page1.setItem(10, b2);
		}
		if (m.contains("COBBLESTONE")) {
		ItemStack b1 = new ItemStack(Material.COBBLESTONE, 1);
		ItemMeta bm1 = b1.getItemMeta();
		double amount2 = m.getDouble("COBBLESTONE.Buy-Price");
		bm1.setDisplayName(Strings.color("&7&oCobblestone"));
		bm1.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount2 + "&6&lGOLD")));
		b1.setItemMeta(bm1);
		
		page1.setItem(11, b1);
		}
		if (m.contains("DIRT")) {
		ItemStack b0 = new ItemStack(Material.DIRT, 1);
		ItemMeta bm0 = b0.getItemMeta();
		double amount3 = m.getDouble("DIRT.Buy-Price");
		bm0.setDisplayName(Strings.color("&7&oDirt"));
		bm0.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount3 + "&6&lGOLD")));
		b0.setItemMeta(bm0);
		
		page1.setItem(12, b0);
		}
		if (m.contains("GRASS_BLOCK")) {
		ItemStack b = new ItemStack(Material.GRASS_BLOCK, 1);
		ItemMeta bm = b.getItemMeta();
		double amount4 = m.getDouble("GRASS_BLOCK.Buy-Price");
		bm.setDisplayName(Strings.color("&7&oGrass_block"));
		bm.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount4 + "&6&lGOLD")));
		b.setItemMeta(bm);
		
		page1.setItem(13, b);
		}
		if (m.contains("OAK_LOG")) {
		ItemStack b6 = new ItemStack(Material.OAK_LOG, 1);
		ItemMeta bm6 = b6.getItemMeta();
		double amount0 = m.getDouble("OAK_LOG.Buy-Price");
		bm6.setDisplayName(Strings.color("&7&oOak_log"));
		bm6.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount0 + "&6&lGOLD")));
		b6.setItemMeta(bm6);
		
		page1.setItem(14, b6);
		}
		if (m.contains("SPRUCE_LOG")) {
			ItemStack b7 = new ItemStack(Material.SPRUCE_LOG, 1);
			ItemMeta bm7 = b7.getItemMeta();
			double amount5 = m.getDouble("SPRUCE_LOG.Buy-Price");
			bm7.setDisplayName(Strings.color("&7&oSpruce_log"));
			bm7.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount5 + "&6&lGOLD")));
			b7.setItemMeta(bm7);
			
			page1.setItem(15, b7);
		}
		if (m.contains("BIRCH_LOG")) {
			ItemStack b8 = new ItemStack(Material.BIRCH_LOG, 1);
			ItemMeta bm8 = b8.getItemMeta();
			double amount6 = m.getDouble("BIRCH_LOG.Buy-Price");
			bm8.setDisplayName(Strings.color("&7&oBirch_log"));
			bm8.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount6 + "&6&lGOLD")));
			b8.setItemMeta(bm8);
			
			page1.setItem(16, b8);
		}
		if (m.contains("SPRUCE_LOG")) {
			ItemStack b9 = new ItemStack(Material.SPRUCE_LOG, 1);
			ItemMeta bm9 = b9.getItemMeta();
			double amount7 = m.getDouble("SPRUCE_LOG.Buy-Price");
			bm9.setDisplayName(Strings.color("&7&oSpruce_log"));
			bm9.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount7 + "&6&lGOLD")));
			b9.setItemMeta(bm9);
			
			page1.setItem(19, b9);
		}
		if (m.contains("JUNGLE_LOG")) {
			ItemStack b10 = new ItemStack(Material.JUNGLE_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("JUNGLE_LOG.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oJungle_log"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(20, b10);
		}
		if (m.contains("ACACIA_LOG")) {
			ItemStack b10 = new ItemStack(Material.ACACIA_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("ACACIA_LOG.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oAcacia_log"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(21, b10);
		}
		if (m.contains("DARK_OAK_LOG")) {
			ItemStack b10 = new ItemStack(Material.DARK_OAK_LOG, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("DARK_OAK_LOG.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oDark_oak_log"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(22, b10);
		}
		if (m.contains("GRANITE")) {
			ItemStack b10 = new ItemStack(Material.GRANITE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GRANITE.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oGranite"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(23, b10);
		}
		if (m.contains("POLISHED_GRANITE")) {
			ItemStack b10 = new ItemStack(Material.POLISHED_GRANITE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("POLISHED_GRANITE.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oPolished_granite"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(24, b10);
		}
		if (m.contains("STONE_BRICKS")) {
			ItemStack b10 = new ItemStack(Material.STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("STONE_BRICKS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oStone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(25, b10);
		}
		if (m.contains("CHISELED_STONE_BRICKS")) {
			ItemStack b10 = new ItemStack(Material.CHISELED_STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("CHISELED_STONE_BRICKS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oChiseled_stone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(28, b10);
		}
        if (m.contains("CRACKED_STONE_BRICKS")) {
        	ItemStack b10 = new ItemStack(Material.CRACKED_STONE_BRICKS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("CRACKED_STONE_BRICKS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oCracked_stone_bricks"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(29, b10);
		}
        if (m.contains("GLASS")) {
        	ItemStack b10 = new ItemStack(Material.GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oGlass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(30, b10);
		}
        if (m.contains("GLOWSTONE")) {
        	ItemStack b10 = new ItemStack(Material.GLOWSTONE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GLOWSTONE.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oGlowstone"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(31, b10);
		}
        if (m.contains("TORCH")) {
        	ItemStack b10 = new ItemStack(Material.TORCH, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("TORCH.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oTorch"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(32, b10);
		}
        if (m.contains("BOOKSHELF")) {
        	ItemStack b10 = new ItemStack(Material.BOOKSHELF, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("BOOKSHELF.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oBookshelf"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(33, b10);
        }
        if (m.contains("ENCHANTING_TABLE")) {
        	ItemStack b10 = new ItemStack(Material.ENCHANTING_TABLE, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("ENCHANTING_TABLE.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oEnchanting_table"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page1.setItem(34, b10);
        }
	}
	
	public static void iterateConfigItemsBuying2(Config me, FileConfiguration m, Inventory page2) {
		if (m.contains("IRON_BARS")) {
		ItemStack b2 = new ItemStack(Material.IRON_BARS, 1);
		ItemMeta bm2 = b2.getItemMeta();
		double amount = m.getDouble("IRON_BARS.Buy-Price");
		bm2.setDisplayName(Strings.color("&7&oIron_bars"));
		bm2.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount + "&6&lGOLD")));
		b2.setItemMeta(bm2);
		
		page2.setItem(10, b2);
		}
		if (m.contains("WHITE_STAINED_GLASS")) {
		ItemStack b1 = new ItemStack(Material.WHITE_STAINED_GLASS, 1);
		ItemMeta bm1 = b1.getItemMeta();
		double amount2 = m.getDouble("WHITE_STAINED_GLASS.Buy-Price");
		bm1.setDisplayName(Strings.color("&7&oWhite_stained_glass"));
		bm1.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount2 + "&6&lGOLD")));
		b1.setItemMeta(bm1);
		
		page2.setItem(11, b1);
		}
		if (m.contains("ORANGE_STAINED_GLASS")) {
		ItemStack b0 = new ItemStack(Material.ORANGE_STAINED_GLASS, 1);
		ItemMeta bm0 = b0.getItemMeta();
		double amount3 = m.getDouble("ORANGE_STAINED_GLASS.Buy-Price");
		bm0.setDisplayName(Strings.color("&7&oOrange_stained_glass"));
		bm0.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount3 + "&6&lGOLD")));
		b0.setItemMeta(bm0);
		
		page2.setItem(12, b0);
		}
		if (m.contains("MAGENTA_STAINED_GLASS")) {
		ItemStack b = new ItemStack(Material.MAGENTA_STAINED_GLASS, 1);
		ItemMeta bm = b.getItemMeta();
		double amount4 = m.getDouble("MAGENTA_STAINED_GLASS.Buy-Price");
		bm.setDisplayName(Strings.color("&7&oMagenta_stained_glass"));
		bm.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount4 + "&6&lGOLD")));
		b.setItemMeta(bm);
		
		page2.setItem(13, b);
		}
		if (m.contains("LIGHT_BLUE_STAINED_GLASS")) {
		ItemStack b6 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 1);
		ItemMeta bm6 = b6.getItemMeta();
		double amount0 = m.getDouble("LIGHT_BLUE_STAINED_GLASS.Buy-Price");
		bm6.setDisplayName(Strings.color("&7&oLight_blue_stained_glass"));
		bm6.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount0 + "&6&lGOLD")));
		b6.setItemMeta(bm6);
		
		page2.setItem(14, b6);
		}
		if (m.contains("YELLOW_STAINED_GLASS")) {
			ItemStack b7 = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
			ItemMeta bm7 = b7.getItemMeta();
			double amount5 = m.getDouble("YELLOW_STAINED_GLASS.Buy-Price");
			bm7.setDisplayName(Strings.color("&7&oYellow_stained_glass"));
			bm7.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount5 + "&6&lGOLD")));
			b7.setItemMeta(bm7);
			
			page2.setItem(15, b7);
		}
		if (m.contains("LIME_STAINED_GLASS")) {
			ItemStack b8 = new ItemStack(Material.LIME_STAINED_GLASS, 1);
			ItemMeta bm8 = b8.getItemMeta();
			double amount6 = m.getDouble("LIME_STAINED_GLASS.Buy-Price");
			bm8.setDisplayName(Strings.color("&7&oLime_stained_glass"));
			bm8.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount6 + "&6&lGOLD")));
			b8.setItemMeta(bm8);
			
			page2.setItem(16, b8);
		}
		if (m.contains("PINK_STAINED_GLASS")) {
			ItemStack b9 = new ItemStack(Material.PINK_STAINED_GLASS, 1);
			ItemMeta bm9 = b9.getItemMeta();
			double amount7 = m.getDouble("PINK_STAINED_GLASS.Buy-Price");
			bm9.setDisplayName(Strings.color("&7&oPink_stained_glass"));
			bm9.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount7 + "&6&lGOLD")));
			b9.setItemMeta(bm9);
			
			page2.setItem(19, b9);
		}
		if (m.contains("GRAY_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.GRAY_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GRAY_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oGray_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(20, b10);
		}
		if (m.contains("LIGHT_GRAY_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("LIGHT_GRAY_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oLight_gray_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(21, b10);
		}
		if (m.contains("CYAN_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.CYAN_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("CYAN_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oCyan_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(22, b10);
		}
		if (m.contains("PURPLE_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.PURPLE_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("PURPLE_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oPurple_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(23, b10);
		}
		if (m.contains("BLUE_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.BLUE_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("BLUE_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oBlue_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(24, b10);
		}
		if (m.contains("BROWN_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.BROWN_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("BROWN_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oBrown_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(25, b10);
		}
		if (m.contains("GREEN_STAINED_GLASS")) {
			ItemStack b10 = new ItemStack(Material.GREEN_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("GREEN_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oGreen_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(28, b10);
		}
        if (m.contains("RED_STAINED_GLASS")) {
        	ItemStack b10 = new ItemStack(Material.RED_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("RED_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oRed_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(29, b10);
		}
        if (m.contains("BLACK_STAINED_GLASS")) {
        	ItemStack b10 = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("BLACK_STAINED_GLASS.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oBlack_stained_glass"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(30, b10);
		}
        if (m.contains("STICKY_PISTON")) {
        	ItemStack b10 = new ItemStack(Material.STICKY_PISTON, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("STICKY_PISTON.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oSticky_piston"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(31, b10);
		}
        if (m.contains("PISTON")) {
        	ItemStack b10 = new ItemStack(Material.PISTON, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("PISTON.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oPiston"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(32, b10);
		}
        if (m.contains("REDSTONE_TORCH")) {
        	ItemStack b10 = new ItemStack(Material.REDSTONE_TORCH, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("REDSTONE_TORCH.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oRedstone_torch"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(33, b10);
        }
        if (m.contains("REDSTONE_LAMP")) {
        	ItemStack b10 = new ItemStack(Material.REDSTONE_LAMP, 1);
			ItemMeta bm10 = b10.getItemMeta();
			double amount8 = m.getDouble("REDSTONE_LAMP.Buy-Price");
			bm10.setDisplayName(Strings.color("&7&oRedstone_lamp"));
			bm10.setLore(Arrays.asList(Strings.color("Buy for this item for"), Strings.color(amount8 + "&6&lGOLD")));
			b10.setItemMeta(bm10);
			
			page2.setItem(34, b10);
        }
	}
	
	public static void iterateMenuItems(Player p, InventoryClickEvent e) {
		String menu = e.getView().getTitle();
		if (menu.equals(Strings.color("&6&lEconomy&7: &a&oStore Front"))) {
			if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
				ItemStack item = e.getCurrentItem();
			
				if (item.hasItemMeta()) {
					if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oBuy items (&b&oCLICK&7&o)"))) {
						e.setCancelled(true);
						new EcoBUY(Utils.guiManager(p)).open();
					} else if (item.getItemMeta().getDisplayName().equals(Strings.color("&7&oSell items (&b&oCLICK&7&o)"))) {
						e.setCancelled(true);
						//p.openInventory(SELL_shopPage_1());
						p.updateInventory();
					}
				}
			}
		}
	}
	
	public static void iterateItems(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		switch (e.getCurrentItem().getType()) {
		case ACACIA_BOAT:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_BUTTON:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_DOOR:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_FENCE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_FENCE_GATE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_LEAVES:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 acacia_log");
			break;
		case ACACIA_PLANKS:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_PRESSURE_PLATE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_SAPLING:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_SIGN:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_SLAB:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_STAIRS:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_TRAPDOOR:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_WALL_SIGN:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACACIA_WOOD:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ACTIVATOR_RAIL:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case AIR:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ALLIUM:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANCIENT_DEBRIS:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANDESITE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANDESITE_SLAB:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANDESITE_STAIRS:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANDESITE_WALL:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ANVIL:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case APPLE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ARMOR_STAND:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ARROW:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ATTACHED_MELON_STEM:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ATTACHED_PUMPKIN_STEM:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case AZURE_BLUET:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BAKED_POTATO:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BAMBOO:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BAMBOO_SAPLING:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BARREL:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BARRIER:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BASALT:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BAT_SPAWN_EGG:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEACON:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEDROCK:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEEF:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEEHIVE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEETROOT:
			
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEETROOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEETROOT_SEEDS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEETROOT_SOUP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEE_NEST:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BEE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BELL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_BOAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_LEAVES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 birch_log");
			break;
		case BIRCH_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BIRCH_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACKSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACKSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACKSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACKSTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLACK_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLAST_FURNACE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLAZE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLAZE_ROD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLAZE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_ICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_ORCHID:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_STAINED_GLASS_PANE:
			e.setCancelled(true);
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BLUE_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BONE_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BONE_MEAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BOOKSHELF:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BOW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BOWL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRAIN_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRAIN_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRAIN_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRAIN_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BREAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BREWING_STAND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_MUSHROOM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_MUSHROOM_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BROWN_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUBBLE_COLUMN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUBBLE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUBBLE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUBBLE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUBBLE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CACTUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAKE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAMPFIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CARROT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CARROTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CARROT_ON_A_STICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CARTOGRAPHY_TABLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CARVED_PUMPKIN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAT_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAULDRON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAVE_AIR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CAVE_SPIDER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAIN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAINMAIL_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAINMAIL_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAINMAIL_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAINMAIL_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHAIN_COMMAND_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHARCOAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHEST:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHEST_MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHICKEN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHICKEN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHIPPED_ANVIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_NETHER_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_POLISHED_BLACKSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_QUARTZ_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_RED_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHISELED_STONE_BRICKS:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 chiseled_stone_bricks");
			break;
		case CHORUS_FLOWER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHORUS_FRUIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CHORUS_PLANT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CLAY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CLAY_BALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COAL_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COARSE_DIRT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COBBLESTONE:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 cobblestone");
			break;
		case COBBLESTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COBBLESTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COBBLESTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COBWEB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COCOA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COCOA_BEANS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COD_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COD_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COMMAND_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COMMAND_BLOCK_MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COMPARATOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COMPASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COMPOSTER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CONDUIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_BEEF:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_CHICKEN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_COD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_MUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_PORKCHOP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_RABBIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKED_SALMON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COOKIE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CORNFLOWER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case COW_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRACKED_NETHER_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRACKED_POLISHED_BLACKSTONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRACKED_STONE_BRICKS:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 cracked_stone_bricks");
			break;
		case CRAFTING_TABLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CREEPER_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CREEPER_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CREEPER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CREEPER_WALL_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_FUNGUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_HYPHAE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_NYLIUM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_ROOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRIMSON_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CROSSBOW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CRYING_OBSIDIAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CUT_RED_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CUT_RED_SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CUT_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CUT_SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case CYAN_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DAMAGED_ANVIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DANDELION:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_BOAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_LEAVES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 dark_oak_log");
			break;
		case DARK_OAK_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_OAK_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_PRISMARINE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_PRISMARINE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DARK_PRISMARINE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DAYLIGHT_DETECTOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BRAIN_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BRAIN_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BRAIN_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BRAIN_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BUBBLE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BUBBLE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BUBBLE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BUBBLE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_BUSH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_FIRE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_FIRE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_FIRE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_FIRE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_HORN_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_HORN_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_HORN_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_HORN_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_TUBE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_TUBE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_TUBE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEAD_TUBE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DEBUG_STICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DETECTOR_RAIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_HORSE_ARMOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIAMOND_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIORITE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIORITE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIORITE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIORITE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DIRT:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 dirt");
			break;
		case DISPENSER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DOLPHIN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DONKEY_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRAGON_BREATH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRAGON_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRAGON_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRAGON_WALL_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRIED_KELP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DRIED_KELP_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DROPPER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case DROWNED_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ELDER_GUARDIAN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ELYTRA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EMERALD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EMERALD_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EMERALD_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENCHANTED_BOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENCHANTED_GOLDEN_APPLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENCHANTING_TABLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENDERMAN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENDERMITE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENDER_CHEST:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENDER_EYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ENDER_PEARL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_CRYSTAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_GATEWAY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_PORTAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_PORTAL_FRAME:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_ROD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_STONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_STONE_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_STONE_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case END_STONE_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EVOKER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case EXPERIENCE_BOTTLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FARMLAND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FEATHER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FERMENTED_SPIDER_EYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FILLED_MAP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIREWORK_ROCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIREWORK_STAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE_CHARGE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FIRE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FISHING_ROD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FLETCHING_TABLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FLINT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FLINT_AND_STEEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FLOWER_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FLOWER_POT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FOX_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FROSTED_ICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FURNACE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case FURNACE_MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GHAST_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GHAST_TEAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GILDED_BLACKSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GLASS:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 glass");
			break;
		case GLASS_BOTTLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GLISTERING_MELON_SLICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GLOBE_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GLOWSTONE:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 glowstone");
			break;
		case GLOWSTONE_DUST:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_APPLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_CARROT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_HORSE_ARMOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLDEN_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLD_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLD_INGOT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLD_NUGGET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GOLD_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRANITE:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 granite");
			break;
		case GRANITE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRANITE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRANITE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRASS_BLOCK:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 grass_block");
		break;
		case GRASS_PATH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRAY_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GREEN_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GRINDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GUARDIAN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case GUNPOWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HAY_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HEART_OF_THE_SEA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HEAVY_WEIGHTED_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HOGLIN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HONEYCOMB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HONEYCOMB_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HONEY_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HONEY_BOTTLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HOPPER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HOPPER_MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HORN_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HORN_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HORN_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HORN_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HORSE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case HUSK_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_CHISELED_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_COBBLESTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_CRACKED_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_MOSSY_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_STONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INFESTED_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case INK_SAC:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_BARS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_HORSE_ARMOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_INGOT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_NUGGET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case IRON_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ITEM_FRAME:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JACK_O_LANTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JIGSAW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUKEBOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_BOAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_LEAVES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 jungle_log");
			break;
		case JUNGLE_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case JUNGLE_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case KELP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case KELP_PLANT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case KNOWLEDGE_BOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LADDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LANTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LAPIS_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LAPIS_LAZULI:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LAPIS_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LARGE_FERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LAVA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LAVA_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER_HORSE_ARMOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEATHER_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LECTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LEVER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_BLUE_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_GRAY_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIGHT_WEIGHTED_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LILAC:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LILY_OF_THE_VALLEY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LILY_PAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LIME_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LINGERING_POTION:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LLAMA_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LODESTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case LOOM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGENTA_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGMA_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGMA_CREAM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAGMA_CUBE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MAP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MELON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MELON_SEEDS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MELON_SLICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MELON_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MILK_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOJANG_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOOSHROOM_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_COBBLESTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_COBBLESTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_COBBLESTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_COBBLESTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_STONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_STONE_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_STONE_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOSSY_STONE_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MOVING_PISTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MULE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSHROOM_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSHROOM_STEW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_11:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_13:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_BLOCKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_CAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_CHIRP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_FAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_MALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_MELLOHI:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_PIGSTEP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_STAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_STRAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_WAIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUSIC_DISC_WARD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case MYCELIUM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NAME_TAG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NAUTILUS_SHELL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_BOOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_CHESTPLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_INGOT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_LEGGINGS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_SCRAP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERITE_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHERRACK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICK_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_GOLD_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_PORTAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_QUARTZ_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_SPROUTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_STAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_WART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NETHER_WART_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case NOTE_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_BOAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_LEAVES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 oak_log");
			break;
		case OAK_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OAK_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OBSERVER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OBSIDIAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OCELOT_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ORANGE_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case OXEYE_DAISY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PACKED_ICE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PAINTING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PANDA_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PAPER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PARROT_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PEONY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PETRIFIED_OAK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PHANTOM_MEMBRANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PHANTOM_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PIGLIN_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PIGLIN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PIG_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PILLAGER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PINK_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PISTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PISTON_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PLAYER_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PLAYER_WALL_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PODZOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POISONOUS_POTATO:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLAR_BEAR_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_ANDESITE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_ANDESITE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_ANDESITE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BASALT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_BLACKSTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_DIORITE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_DIORITE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_DIORITE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_GRANITE:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 Polished_granite");
			break;
		case POLISHED_GRANITE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POLISHED_GRANITE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POPPED_CHORUS_FRUIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POPPY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PORKCHOP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTATO:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTATOES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTION:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_ACACIA_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_ALLIUM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_AZURE_BLUET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_BAMBOO:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_BIRCH_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_BLUE_ORCHID:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_BROWN_MUSHROOM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_CACTUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_CORNFLOWER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_CRIMSON_FUNGUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_CRIMSON_ROOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_DANDELION:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_DARK_OAK_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_DEAD_BUSH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_FERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_JUNGLE_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_LILY_OF_THE_VALLEY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_OAK_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_ORANGE_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_OXEYE_DAISY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_PINK_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_POPPY:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_RED_MUSHROOM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_RED_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_SPRUCE_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_WARPED_FUNGUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_WARPED_ROOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_WHITE_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POTTED_WITHER_ROSE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case POWERED_RAIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_CRYSTALS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_SHARD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PRISMARINE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUFFERFISH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUFFERFISH_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUFFERFISH_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUMPKIN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUMPKIN_PIE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUMPKIN_SEEDS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PUMPKIN_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPLE_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPUR_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPUR_PILLAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPUR_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case PURPUR_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ_PILLAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case QUARTZ_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RABBIT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RABBIT_FOOT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RABBIT_HIDE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RABBIT_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RABBIT_STEW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RAIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RAVAGER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_LAMP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_ORE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_TORCH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_WALL_TORCH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REDSTONE_WIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_MUSHROOM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_MUSHROOM_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_NETHER_BRICKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_NETHER_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_NETHER_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_NETHER_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SAND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SANDSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SANDSTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RED_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REPEATER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case REPEATING_COMMAND_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case RESPAWN_ANCHOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ROSE_BUSH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ROTTEN_FLESH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SADDLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SALMON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SALMON_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SALMON_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SAND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SANDSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SANDSTONE_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SCAFFOLDING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SCUTE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SEAGRASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SEA_LANTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SEA_PICKLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHEARS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHEEP_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHIELD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHROOMLIGHT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHULKER_SHELL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SHULKER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SILVERFISH_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SKELETON_HORSE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SKELETON_SKULL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SKELETON_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SKELETON_WALL_SKULL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SKULL_BANNER_PATTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SLIME_BALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SLIME_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SLIME_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMITHING_TABLE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOKER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_QUARTZ:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_QUARTZ_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_QUARTZ_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_RED_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_RED_SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_RED_SANDSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_SANDSTONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_SANDSTONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_SANDSTONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_STONE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SMOOTH_STONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SNOW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SNOWBALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SNOW_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_CAMPFIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_FIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_LANTERN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_SAND:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_SOIL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_TORCH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SOUL_WALL_TORCH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPAWNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPECTRAL_ARROW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPIDER_EYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPIDER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPLASH_POTION:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPONGE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_BOAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_LEAVES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_LOG:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 spruce_log");
			break;
		case SPRUCE_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_SAPLING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SPRUCE_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SQUID_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STICKY_PISTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 stone");
			break;
		case STONECUTTER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_BRICKS:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 stone_bricks");
			break;
		case STONE_BRICK_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_BRICK_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_BRICK_WALL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STONE_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRAY_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIDER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_ACACIA_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_ACACIA_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_BIRCH_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_BIRCH_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_CRIMSON_HYPHAE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_CRIMSON_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_DARK_OAK_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_DARK_OAK_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_JUNGLE_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_JUNGLE_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_OAK_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_OAK_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_SPRUCE_LOG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_SPRUCE_WOOD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_WARPED_HYPHAE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRIPPED_WARPED_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRUCTURE_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case STRUCTURE_VOID:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SUGAR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SUGAR_CANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SUNFLOWER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SUSPICIOUS_STEW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SWEET_BERRIES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case SWEET_BERRY_BUSH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TALL_GRASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TALL_SEAGRASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TARGET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TIPPED_ARROW:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TNT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TNT_MINECART:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TORCH:
			e.setCancelled(true);
			Bukkit.getServer().dispatchCommand(p, "heco buy 1 torch");
			break;
		case TOTEM_OF_UNDYING:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TRADER_LLAMA_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TRAPPED_CHEST:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TRIDENT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TRIPWIRE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TRIPWIRE_HOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TROPICAL_FISH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TROPICAL_FISH_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TROPICAL_FISH_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TUBE_CORAL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TUBE_CORAL_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TUBE_CORAL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TUBE_CORAL_WALL_FAN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TURTLE_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TURTLE_HELMET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TURTLE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TWISTING_VINES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case TWISTING_VINES_PLANT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case VEX_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case VILLAGER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case VINDICATOR_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case VINE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case VOID_AIR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WALL_TORCH:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WANDERING_TRADER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_BUTTON:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_DOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_FENCE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_FENCE_GATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_FUNGUS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_FUNGUS_ON_A_STICK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_HYPHAE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_NYLIUM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_PLANKS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_PRESSURE_PLATE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_ROOTS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_SLAB:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_STAIRS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_STEM:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_TRAPDOOR:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_WALL_SIGN:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WARPED_WART_BLOCK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WATER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WATER_BUCKET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WEEPING_VINES:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WEEPING_VINES_PLANT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WET_SPONGE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHEAT:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHEAT_SEEDS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_TULIP:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WHITE_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WITCH_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WITHER_ROSE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WITHER_SKELETON_SKULL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WITHER_SKELETON_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WITHER_SKELETON_WALL_SKULL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOLF_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOODEN_AXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOODEN_HOE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOODEN_PICKAXE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOODEN_SHOVEL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WOODEN_SWORD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WRITABLE_BOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case WRITTEN_BOOK:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_BED:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_CARPET:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_CONCRETE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_CONCRETE_POWDER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_DYE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_GLAZED_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_SHULKER_BOX:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_STAINED_GLASS:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_STAINED_GLASS_PANE:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_TERRACOTTA:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_WALL_BANNER:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case YELLOW_WOOL:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOGLIN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIE_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIE_HORSE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIE_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIE_VILLAGER_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIE_WALL_HEAD:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		case ZOMBIFIED_PIGLIN_SPAWN_EGG:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		default:
			e.setCancelled(true); Bukkit.getServer().dispatchCommand(p, "heco buy 1 "); break;
		
		}
	}
	
}
