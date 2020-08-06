package org.spigotmc.hessentials.gui.economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.spigotmc.hessentials.commands.economy.EcoItems;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.gui.Menu;
import org.spigotmc.hessentials.util.variables.Strings;


public class EcoBUY extends Menu {

	public EcoBUY(Gui gui) {
		super(gui);
	}
	
	Config me;

	@Override
	public String getMenuName() {
		
		return Strings.color("&6&lEconomy &7Buy: &a&oPage 1");
	}

	@Override
	public int getSlots() {
		return 54;
	}

	
	@Override
	public void handleMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		EcoItems.iterateMenuItems(p, e);

	}

	@Override
	public void setMenuItems() {
		me = new Config("hEconomy");
		FileConfiguration m = me.getConfig();
		EcoItems.fillerGlassMenu(inventory);
		EcoItems.iterateConfigItemsBuying(me, m, inventory);

	}

}
