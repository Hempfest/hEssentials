package org.spigotmc.hessentials.gui.homes;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Home;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class InventoryHomes extends Pagination {

    Utils u;

    heHook api = heHook.getHook();

    public InventoryHomes(GuiLibrary gui) {
        super(gui);
    }

    @Override
    public String getMenuName() {
        return "Home list" + api.lib.color(" &d&oClick to warp.");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        u = new Utils();
        Player p = (Player) e.getWhoClicked();
        ArrayList<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
        guiLibrary.setData(e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(HempCore.getInstance(), "uuid"), PersistentDataType.STRING));
        Home home = new Home(p, guiLibrary.getData());
        if (e.getCurrentItem().getType().equals(Material.MOJANG_BANNER_PATTERN)) {


            home.teleport();

        } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {

            // close inventory
            p.closeInventory();

        } else if (e.getCurrentItem().getType().equals(Material.DARK_OAK_BUTTON)) {
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
        }
    }

    @Override
    public void setMenuItems() {
        u = new Utils();
        addMenuBorder();
        DataManager dm = new DataManager();
        Config claims = dm.getHomeData(guiLibrary.getUUID());
        FileConfiguration c = claims.getConfig();
        // The thing you will be looping through to place items
        ArrayList<String> claim = new ArrayList<String>(c.getConfigurationSection("Private-Homes").getKeys(false));
        ItemStack back = new ItemStack(Material.DARK_OAK_BUTTON, 1);
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(api.lib.color("&a&oGo back."));
        back.setItemMeta(back_meta);
        ///////////////////////////////////// Pagination loop template
        if (claim != null && !claim.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= claim.size())
                    break;
                if (claim.get(index) != null) {
                    ///////////////////////////
                    // Create an item from our collection and place it into the inventory
                    ItemStack playerItem = new ItemStack(Material.MOJANG_BANNER_PATTERN, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(api.lib.color("&aHome: &e&o" + claim.get(index)));


                    playerMeta.setLore(Arrays.asList(api.lib.color(claim.get(index))));
                    playerMeta.getPersistentDataContainer().set(new NamespacedKey(HempCore.getInstance(), "uuid"),
                            PersistentDataType.STRING, claim.get(index));
                    playerItem.setItemMeta(playerMeta);

                    inventory.addItem(playerItem);

                    ////////////////////////
                }
            }
        }
        setFillerGlassLight();
        ////////////////////////

    }
}