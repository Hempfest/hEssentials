package org.spigotmc.hessentials.gui.claim;


import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.formatting.string.ColoredString;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import com.youtube.hempfest.hempcore.gui.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.util.heHook;

public class InventoryClaimMenu extends Menu {

    public InventoryClaimMenu(GuiLibrary gui) {
        super(gui);
    }

    @Override
    public String getMenuName() {
        return new ColoredString("▬▬▬▬▬▬▬▬▬▬▬▬▬[CLAIM MENU]▬▬▬▬▬▬▬▬▬▬▬▬▬", ColoredString.ColorType.MC).toString();
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        GuiLibrary gui = HempCore.guiManager(p);
        Material mat = e.getCurrentItem().getType();
        String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(HempCore.getInstance(), "claim"), PersistentDataType.STRING);
        switch (mat) {
            case PLAYER_HEAD:
                gui.setData(name);
                new InventoryClaimUsers(gui).open();
                break;
            case SKELETON_SKULL:
                gui.setData(name);
                new InventoryClaimAdd(gui).open();
                break;
            case PAPER:
                gui.setData2(p.getName());
                new InventoryClaimsList(gui).open();
                break;
            case DIAMOND_PICKAXE:
                Bukkit.dispatchCommand(p, "claim autoclaim");
                p.closeInventory();
                break;
            default:
                break;
        }


    }

    @Override
    public void setMenuItems() {
        ItemStack autoclaim = makeItem(Material.DIAMOND_PICKAXE, "&7[&a&lAUTO-CLAIM&7]", "", "&e&oDescription: &7By clicking here", "&7you will begin claiming the very", "&7land you stand on as you walk around.");
        ItemStack claimlist = makeItem(Material.PAPER, "&7[&2CLAIM-LIST&7]", "", "&e&oDescription: &7Clicking this will open", "&7another sub-menu containing your", "&7current private-claim list.");
        inventory.setItem(10, autoclaim);
        inventory.setItem(12, claimlist);
        heHook api = heHook.getPlayerHook(guiLibrary.getViewer());
        if (api.pc.isInClaim(guiLibrary.getViewer().getLocation())) {
            if (api.pc.isClaimOwner()) {
                ItemStack users = makePersistentItem(Material.PLAYER_HEAD, "&7[&6REMOVE-USERS&7]", "claim", api.pc.getClaimName(guiLibrary.getViewer().getLocation()), "", "&e&oDescription: &7View and manage the user", "&7list for your claim " + '"' + api.pc.getClaimName(guiLibrary.getViewer().getLocation()) + '"');
                ItemStack players = makePersistentItem(Material.SKELETON_SKULL, "&7[&6ADD-USERS&7]", "claim", api.pc.getClaimName(guiLibrary.getViewer().getLocation()), "", "&e&oDescription: &7View and add players", "&7from the player list to your claim " + '"' + api.pc.getClaimName(guiLibrary.getViewer().getLocation()) + '"');
                inventory.setItem(14, users);
                inventory.setItem(16, players);
            }
        }
        setFillerGlass();
    }
}
