package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.gui.claim.InventoryClaims;
import org.spigotmc.hessentials.util.heHook;

public class InventoryConfiguration extends Menu {

    heHook api = heHook.getHook();

    public InventoryConfiguration(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + " &c&oStaff GUI");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Material mat = e.getCurrentItem().getType();
        GuiLibrary gui = HempCore.guiManager(p);
        switch (mat) {
            case PLAYER_HEAD:
                new InventoryPlayers(gui).open();
                break;
            case IRON_BARS:
                new InventoryBanned(gui).open();
                break;
            case MAP:
                new InventoryClaims(gui).open();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack log = makeItem(Material.PLAYER_HEAD, "&7[&b&oLogged-Players&7]", "", "Click to view a list of logged players.");
        ItemStack banned = makeItem(Material.IRON_BARS, "&7[&b&oBanned-Players&7]", "", "Click to view a list of &4&nbanned&r players.");
        ItemStack claims = makeItem(Material.MAP, "&7[&b&oServer-Claims&7]", "", "Click to view a list of all server claims.");
        inventory.setItem(2, log);
        inventory.setItem(4, banned);
        inventory.setItem(6, claims);
        setFillerGlass();
    }
}
