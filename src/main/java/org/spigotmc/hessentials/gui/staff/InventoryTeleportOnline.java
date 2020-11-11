package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.util.heHook;

public class InventoryTeleportOnline extends Pagination {

    heHook api = heHook.getHook();

    public InventoryTeleportOnline(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + "&6&l&oOnline Players");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ArrayList<String> players = new ArrayList<>(api.u.getAllUserIDs());
        String player = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HempCore.getInstance(), "player"), PersistentDataType.STRING);
        Material mat = e.getCurrentItem().getType();
        GuiLibrary gui = HempCore.guiManager(p);
        switch (mat) {
            case PLAYER_HEAD:
                OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(player));
                Bukkit.dispatchCommand(p, "tp " + target.getName());
                p.closeInventory();
                break;
            case BARRIER:
                p.closeInventory();
                break;
            case DARK_OAK_BUTTON:
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Left")) {
                    if (page == 0) {
                        p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                    } else {
                        page = page - 1;
                        super.open();
                    }
                } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())
                        .equalsIgnoreCase("Right")) {
                    if (!((index + 1) >= players.size())) {
                        page = page + 1;
                        super.open();
                    } else {
                        p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                    }
                }
                break;
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();
        ArrayList<String> players = new ArrayList<>(api.u.getAllUserIDs());
        if (players != null && !players.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= players.size())
                    break;
                if (players.get(index) != null) {
                    ItemStack player = makePersistentItem(Material.PLAYER_HEAD, "&e&l&o" + api.u.usernameFromUUID(UUID.fromString(players.get(index))), "player", players.get(index), "", "Click to teleport.");
                    if (Bukkit.getOfflinePlayer(UUID.fromString(players.get(index))).isOnline()) {
                        inventory.addItem(player);
                    }

                }
            }
        }
        setFillerGlassLight();
    }
}
