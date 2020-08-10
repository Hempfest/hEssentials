package org.spigotmc.hessentials.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.util.variables.Strings;


public abstract class Menu implements InventoryHolder {

	/*
	 * Defines the behavior and attributes of all menus in our plugin
	 */

	// Protected values that can be accessed in the menus
	protected Gui gui;
	protected Inventory inventory;
	protected ItemStack FILLER_GLASS = makeItem(Material.BLACK_STAINED_GLASS_PANE, " ");
	protected ItemStack FILLER_GLASS_LIGHT = makeItem(Material.RED_STAINED_GLASS_PANE,
			Strings.color("&7&oOther clans will appear here."));

	// Constructor for Menu. Pass in a gui so that
	// we have information on who's menu this is and
	// what info is to be transfered
	public Menu(Gui gui) {
		this.gui = gui;
	}

	// let each menu decide their name
	public abstract String getMenuName();

	// let each menu decide their slot amount
	public abstract int getSlots();

	// let each menu decide how the items in the menu will be handled when clicked
	public abstract void handleMenu(InventoryClickEvent e);

	// let each menu decide what items are to be placed in the inventory menu
	public abstract void setMenuItems();

	// When called, an inventory is created and opened for the player
	public void open() {
		// The owner of the inventory created is the Menu itself,
		// so we are able to reverse engineer the Menu object from the
		// inventoryHolder in the MenuListener class when handling clicks
		inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

		// grab all the items specified to be used for this menu and add to inventory
		this.setMenuItems();

		// open the inventory for the player
		gui.getViewer().openInventory(inventory);
	}

	// Overridden method from the InventoryHolder interface
	@Override
	public Inventory getInventory() {
		return inventory;
	}

	// Helpful utility method to fill all remaining slots with "filler glass"
	public void setFillerGlass() {
		for (int i = 0; i < getSlots(); i++) {
			if (inventory.getItem(i) == null) {
				inventory.setItem(i, FILLER_GLASS);
			}
		}
	}

	public ItemStack makeItem(Material material, String displayName, String... lore) {

		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);

		itemMeta.setLore(Arrays.asList(lore));
		item.setItemMeta(itemMeta);

		return item;
	}

}
