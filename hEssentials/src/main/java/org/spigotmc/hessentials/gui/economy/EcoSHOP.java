package org.spigotmc.hessentials.gui.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.spigotmc.hessentials.commands.economy.EcoItems;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.gui.Menu;
import org.spigotmc.hessentials.util.variables.Strings;


public class EcoSHOP extends Menu {

	public EcoSHOP(Gui gui) {
		super(gui);
	}

	@Override
	public String getMenuName() {
		
		return Strings.color("&6&lEconomy&7: &a&oStore Front");
	}

	@Override
	public int getSlots() {
		return 36;
	}

	
	@Override
	public void handleMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		EcoItems.iterateMenuItems(p, e);

	}

	@Override
	public void setMenuItems() {
		
		EcoItems.fillerGlassMain(inventory);

	}

}
