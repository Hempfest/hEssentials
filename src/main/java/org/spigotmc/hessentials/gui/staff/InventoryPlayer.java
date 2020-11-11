package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Menu;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.hessentials.gui.claim.InventoryClaimsOther;
import org.spigotmc.hessentials.gui.homes.InventoryHomesOther;
import org.spigotmc.hessentials.util.heHook;

public class InventoryPlayer extends Menu {

    heHook api = heHook.getHook();

    public InventoryPlayer(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + " &3&o" + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + " management.");
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
        String name = api.u.usernameFromUUID(UUID.fromString(gui.getData2()));
        switch (mat) {
            case MOJANG_BANNER_PATTERN:
                gui.setData2(name);
                new InventoryHomesOther(gui).open();
                break;
            case GLOBE_BANNER_PATTERN:
                gui.setData2(name);
                new InventoryClaimsOther(gui).open();
                break;
            case NETHER_STAR:
                Bukkit.dispatchCommand(p, "whois " + api.u.usernameFromUUID(UUID.fromString(gui.getData2())));
                p.closeInventory();
                break;
            case WATER_BUCKET:
                Bukkit.dispatchCommand(p, "unban " + api.u.usernameFromUUID(UUID.fromString(gui.getData2())));
                p.closeInventory();
                break;
            case LAVA_BUCKET:
                Bukkit.dispatchCommand(p, "ban " + api.u.usernameFromUUID(UUID.fromString(gui.getData2())));
                p.closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack homes = makeItem(Material.MOJANG_BANNER_PATTERN, "&7[&b&oHomes&7]", "", "Click to view a list of " + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + "'s homes");
        ItemStack claims = makeItem(Material.GLOBE_BANNER_PATTERN, "&7[&b&oClaims&7]", "", "Click to view a list of " + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + "'s private-claims");
        ItemStack info = makeItem(Material.NETHER_STAR, "&7[&b&oInfo&7]", "", "Click to view " + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + "'s player information.");
        ItemStack unban = makeItem(Material.WATER_BUCKET, "&7[&c&oUnban&7]", "", "Click to unban player " + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + ".");
        ItemStack ban = makeItem(Material.LAVA_BUCKET, "&7[&4&oBan&7]", "", "Click to ban player " + api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())) + ".");
        if (api.u.getBannedUsers().contains(api.u.usernameFromUUID(UUID.fromString(guiLibrary.getData2())))) {
            inventory.setItem(6, unban);
        } else {
            inventory.setItem(6, ban);
        }
        inventory.setItem(3, claims);
        inventory.setItem(2, homes);
        inventory.setItem(4, info);
        setFillerGlass();
    }
}
