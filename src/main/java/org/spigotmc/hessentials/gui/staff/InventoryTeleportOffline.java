package org.spigotmc.hessentials.gui.staff;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import java.util.Arrays;
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

public class InventoryTeleportOffline extends Pagination {

    heHook api = heHook.getHook();

    public InventoryTeleportOffline(GuiLibrary gui) {
        super(gui);
    }

    protected String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public String getMenuName() {
        return color(api.lib.getPrefix() + "&6&l&oOffline Players");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ArrayList<OfflinePlayer> players = new ArrayList<>(Arrays.asList(Bukkit.getOfflinePlayers()));
        String player = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(HempCore.getInstance(), "player"), PersistentDataType.STRING);
        Material mat = e.getCurrentItem().getType();
        switch (mat) {
            case PLAYER_HEAD:
                OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(player));
                Bukkit.dispatchCommand(p, "tp " + target.getName());
                p.closeInventory();
                break;
            case BARRIER:
                GuiLibrary gui = HempCore.guiManager(p);
                new InventoryTeleport(gui).open();
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
        ArrayList<OfflinePlayer> players = new ArrayList<>(Arrays.asList(Bukkit.getOfflinePlayers()));
        if (players != null && !players.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= players.size())
                    break;
                if (players.get(index) != null) {
                    ItemStack player = makePersistentItem(Material.PLAYER_HEAD, "&e&l&o" + players.get(index).getName(), "player", players.get(index).getUniqueId().toString(), "", "Click to teleport.");
                    if (!players.get(index).isOnline()) {
                        inventory.addItem(player);
                    }

                }
            }
        }
        setFillerGlassLight();
    }
}
