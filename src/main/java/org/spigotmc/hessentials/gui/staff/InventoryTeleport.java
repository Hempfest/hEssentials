package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.util.heHook;

public class InventoryTeleport extends Menu {

    heHook api = heHook.getHook();

    public InventoryTeleport(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + " &3&oSelect a player type.");
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
                new InventoryTeleportOnline(gui).open();
                break;
            case SKELETON_SKULL:
                new InventoryTeleportOffline(gui).open();
                break;
            case BARRIER:
                p.closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack online = makeItem(Material.PLAYER_HEAD, "&7[&b&oOnline&7]", "", "Click to view a list of online players");
        ItemStack offline = makeItem(Material.SKELETON_SKULL, "&7[&c&oOffline&7]", "", "Click to view a list of offline players");
        ItemStack exit = makeItem(Material.BARRIER, "&7[&4&oExit&7]", "");
        inventory.setItem(3, online);
        inventory.setItem(5, offline);
        inventory.setItem(8, exit);
        setFillerGlass();
    }
}
