package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.util.heHook;

public class InventoryPlayers extends Pagination {

    heHook api = heHook.getHook();

    public InventoryPlayers(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + "&3&l&oPlayer configuration");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String player = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HempCore.getInstance(), "player"), PersistentDataType.STRING);
        Material mat = e.getCurrentItem().getType();
        GuiLibrary gui = HempCore.guiManager(p);
        switch (mat) {
            case TOTEM_OF_UNDYING:
                new InventoryStaff(gui).open();
                break;
            case PLAYER_HEAD:
                gui.setData2(player);
                new InventoryPlayer(gui).open();
                break;
            case BARRIER:
                p.closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();
        ArrayList<String> players = new ArrayList<String>(api.u.getAllUserIDs());
        if (players != null && !players.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= players.size())
                    break;
                if (players.get(index) != null) {
                    ItemStack player = makePersistentItem(Material.PLAYER_HEAD, "&b&o" + api.u.usernameFromUUID(UUID.fromString(players.get(index))), "player", players.get(index), "", "Click to edit.");
                    inventory.addItem(player);

                }
            }
        }
        ItemStack back = makeItem(Material.TOTEM_OF_UNDYING, "&a&oGo back.", "");
        inventory.setItem(45, back);
        setFillerGlassLight();
    }
}
