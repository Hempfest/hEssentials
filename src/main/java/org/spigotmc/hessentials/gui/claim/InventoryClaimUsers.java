package org.spigotmc.hessentials.gui.claim;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class InventoryClaimUsers extends Pagination {

    Utils u;

    heHook api = heHook.getHook();

    public InventoryClaimUsers(GuiLibrary gui) {
        super(gui);
    }

    @Override
    public String getMenuName() {
        return guiLibrary.getData() + " | User list" + api.lib.color(" &d&oClick to remove.");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        u = new Utils();
        Player p = (Player) e.getWhoClicked();
        api = heHook.getPlayerHook(p);
        ArrayList<String> claim = new ArrayList<String>(api.pc.getUserList(p, guiLibrary.getData()));
        String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(HempCore.getInstance(), "player"), PersistentDataType.STRING);
        Material mat = e.getCurrentItem().getType();
        GuiLibrary gui = HempCore.guiManager(p);
        switch (mat) {
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
                    if (!((index + 1) >= claim.size())) {
                        page = page + 1;
                        super.open();
                    } else {
                        p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                    }
                }
                break;
            case TOTEM_OF_UNDYING:
                new InventoryClaimMenu(gui).open();
                break;
            case PLAYER_HEAD:
                Bukkit.dispatchCommand(p, "claim removeuser " + this.guiLibrary.getData() + " " + name);
                String claimm = this.guiLibrary.getData();
                gui.setData(claimm);
                new InventoryClaimUsers(gui).open();
                break;
            case BARRIER:
                p.closeInventory();
                break;
            default:
                break;
        }
    }

    @Override
    public void setMenuItems() {
        u = new Utils();
        api = heHook.getPlayerHook(guiLibrary.getViewer());
        ItemStack back = makeItem(Material.TOTEM_OF_UNDYING, "&a&oGo back.", "");
        inventory.setItem(45, back);
        addMenuBorder();
        ArrayList<String> claim = new ArrayList<String>(api.pc.getUserList(guiLibrary.getViewer(), guiLibrary.getData()));
        ///////////////////////////////////// Pagination loop template
        if (claim != null && !claim.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= claim.size())
                    break;
                if (claim.get(index) != null) {
                    ///////////////////////////
                    // Create an item from our collection and place it into the inventory
                    ItemStack playerIcon = makePersistentItem(Material.PLAYER_HEAD, "&b&oUser: &e&n" + claim.get(index), "player", claim.get(index), "", "&e&oDescription: &7Click to prevent me from", "&7using your private-claim.");
                    inventory.addItem(playerIcon);
                    ////////////////////////
                }
            }
        }
        setFillerGlassLight();
        ////////////////////////

    }
}