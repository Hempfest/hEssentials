package org.spigotmc.hessentials.gui.economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.spigotmc.hessentials.commands.economy.EcoItems;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.gui.Menu;
import org.spigotmc.hessentials.util.variables.Strings;


public class EcoBUY2 extends Menu {

	public EcoBUY2(Gui gui) {
		super(gui);
	}
	
	Config me;
	
	@Override
	public String getMenuName() {
		
		return Strings.color("&6&lEconomy &7Buy: &a&oPage 2");
	}

	@Override
	public int getSlots() {
		return 54;
	}

	
	@Override
	public void handleMenu(InventoryClickEvent e) {
		//Player p = (Player) e.getWhoClicked();
		
		EcoItems.iterateItems(e);

	}

	@Override
	public void setMenuItems() {
		
		me = new Config("hEconomy");
		FileConfiguration m = me.getConfig();
		EcoItems.fillerGlassMenu2(inventory);
		EcoItems.iterateConfigItemsBuying2(me, m, inventory);

	}

}
