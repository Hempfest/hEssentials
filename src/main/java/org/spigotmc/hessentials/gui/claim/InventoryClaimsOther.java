package org.spigotmc.hessentials.gui.claim;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Pagination;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.gui.staff.InventoryConfiguration;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class InventoryClaimsOther extends Pagination {

    Utils u;

    heHook api = heHook.getHook();

    public InventoryClaimsOther(GuiLibrary gui) {
        super(gui);
    }

    @Override
    public String getMenuName() {
        return guiLibrary.getData2() + " | Claim list" + api.lib.color(" &d&oClick to warp.");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        u = new Utils();
        Player p = (Player) e.getWhoClicked();
        DataManager dm = new DataManager();
        Config pd = dm.getClaimData(api.u.usernameToUUID(guiLibrary.getData2()));
        ArrayList<String> claim = new ArrayList<String>(pd.getConfig().getStringList("claim_data"));
        String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(HempCore.getInstance(), "uuid"), PersistentDataType.STRING);
        Material mat = e.getCurrentItem().getType();
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
                new InventoryConfiguration(guiLibrary).open();
                break;
            case GLOBE_BANNER_PATTERN:
                guiLibrary.setData(name);

                Bukkit.dispatchCommand(p, "claim goto " + guiLibrary.getData());
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
        addMenuBorder();
        DataManager dm = new DataManager();
        Config pd = dm.getClaimData(api.u.usernameToUUID(guiLibrary.getData2()));
        ArrayList<String> claim = new ArrayList<String>(pd.getConfig().getStringList("claim_data"));
        ///////////////////////////////////// Pagination loop template
        if (claim != null && !claim.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= claim.size())
                    break;
                if (claim.get(index) != null) {
                    ///////////////////////////
                    // Create an item from our collection and place it into the inventory
                    ItemStack playerItem = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(api.lib.color("&e&oClaim: &a&n" + claim.get(index)));


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