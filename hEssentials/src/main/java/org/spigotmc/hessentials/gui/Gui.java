package org.spigotmc.hessentials.gui;

import org.bukkit.entity.Player;

/*
Companion class to all menus. This is needed to pass information across the entire
 menu system no matter how many inventories are opened or closed.

 Each player has one of these objects, and only one.
 */

public class Gui {

	private Player viewer;

	private String itemToBuy;

	public Gui(Player p) {
		this.viewer = p;
	}

	public Player getViewer() {
		return viewer;
	}

	public String getItemToBuy() {
		return itemToBuy;
	}

	public void setItemToBuy(String item) {
		this.itemToBuy = item;
	}
}
