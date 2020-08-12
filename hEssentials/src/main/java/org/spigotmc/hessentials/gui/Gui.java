package org.spigotmc.hessentials.gui;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/*
Companion class to all menus. This is needed to pass information across the entire
 menu system no matter how many inventories are opened or closed.

 Each player has one of these objects, and only one.
 */

public class Gui {

	private Player viewer;
	
	private String viewer2;

	private String itemToBuy;
	
	private String homeToView;
	
	private UUID uuidToGet;
	
	private UUID uuidToGet2;
	
	private String offlineUUID;
	
	public Gui() {
		
	}
	
	public Gui(Player p) {
		this.viewer = p;
		this.uuidToGet = p.getUniqueId();
	}
	
	public Gui(Player p, String viewer) {
		this.viewer = p;
		this.viewer2 = viewer;
		this.uuidToGet = p.getUniqueId();
	}
	
	public Gui(Player p, Player target) {
		this.viewer = p;
		this.uuidToGet = p.getUniqueId();
		this.uuidToGet2 = target.getUniqueId();
	}
	
	public Gui(OfflinePlayer target) {
		offlineUUID = target.getUniqueId().toString();
	}
	
	public String getOfflineUUID() {
		return offlineUUID;
	}
	
	public UUID getUUID() {
		return uuidToGet;
	}
	
	public UUID getUUID2() {
		return uuidToGet2;
	}
	
	public void setUUID(UUID uuidToGet) {
		this.uuidToGet = uuidToGet;
	}
	
	public void setUUID2(UUID uuidToGet2) {
		this.uuidToGet2 = uuidToGet2;
	}
	
	public void setViewer(Player viewer) {
		this.viewer = viewer;
	}
	
	public void setViewer2(String viewer2) {
		this.viewer2 = viewer2;
	}

	public Player getViewer() {
		return viewer;
	}
	
	public String getViewer2() {
		return viewer2;
	}

	public String getItemToBuy() {
		return itemToBuy;
	}
	
	public String homeToView() {
		return homeToView;
	}
	
	public void setOfflineUUID(String offlineUUID) {
		this.offlineUUID = offlineUUID;
	}
	
	public void setHomeToView(String homeToView) {
		this.homeToView = homeToView;
	}

	public void setItemToBuy(String item) {
		this.itemToBuy = item;
	}
}
