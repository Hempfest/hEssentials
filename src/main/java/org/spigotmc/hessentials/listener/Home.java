package org.spigotmc.hessentials.listener;

import java.util.Set;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;
import org.spigotmc.hessentials.util.variables.Formatter;

public class Home implements Formatter {

    heHook api = heHook.getHook();
    Player p;
    String target;
    String name;

    public Home(Player p, String name) {
        this.p = p;
        this.name = name;
    }

    public Home(Player p, String target, String name) {
        this.target = target;
        this.name = name;
        this.p = p;
    }

    @Override
    public void set() {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        if (countWarps(p) >= maxWarps(p)) {
            sendMessage(p, api.lib.getPrefix()
                    + "You have exceeded your home limit please delete a warp before adding a new one.");
            return;
        }
        if (name.contains(":")) {
            sendMessage(p, api.lib.getPrefix() + "Home names cannot contain a colon ':'.");
            return;
        }
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (list.contains(name)) {
            Location loc = p.getLocation();
            homes.getConfig().set("Private-Homes." + name, loc);
            homes.saveConfig();
            sendMessage(p, api.lib.getPrefix() + api.lib.getHomeUpdated(p, name));
            return;
        }
        if (list.getString(name) == null)
            list.createSection(name);
        Location loc = p.getLocation();
        homes.getConfig().set("Private-Homes." + name, loc);
        homes.saveConfig();
        sendMessage(p, api.lib.getPrefix() + api.lib.getHomeCreated(p, name));
    }

    @Override
    public void delete() {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (!list.contains(name)) {
            sendMessage(p, api.lib.getPrefix() + "Home '" + name + "' doesn't exist");
            return;
        }
        list.set(name, null);
        homes.saveConfig();
        sendMessage(p, api.lib.getPrefix() + api.lib.getHomeDeleted(p, name));
    }

    @Override
    public void add() {

    }

    @Override
    public void take() {

    }

    @Override
    public void teleport() {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        final ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (list == null) {
            return;
        }
        if (!list.contains(name)) {
            sendMessage(p, api.lib.getPrefix() + "Home '" + name + "' doesn't exist");
            return;
        }
        Location loc = homes.getConfig().getLocation("Private-Homes." + name);
        p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

    }

    public void teleportOther() {
        OfflinePlayer offline = Bukkit.getOfflinePlayer(api.u.usernameToUUID(target));
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(offline.getUniqueId());
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (list == null) {
            return;
        }
        if (!list.contains(name)) {
            sendMessage(p, api.lib.getPrefix() + offline.getName() + " doesn't have a home named: " + name);
            return;
        }
        Location loc = homes.getConfig().getLocation("Private-Homes." + name);
        p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        sendMessage(p, api.lib.getPrefix() + "You have teleported to " + offline.getName() + "'s home: " + name);
    }

    @Override
    public void list() {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");

        if (countWarps(p) == 0) {
            sendMessage(p, api.lib.getPrefix() + "Home List &f[&3" + countWarps(p) + "&f/&3"
                    + String.valueOf(maxWarps(p)).replaceAll("999", api.lib.infinity()) + "&f]");
            sendMessage(p, ChatColor.GRAY + "[" + ChatColor.RED + "You have no warps set." + ChatColor.GRAY + "]");
            return;
        }

        sendMessage(p, api.lib.getPrefix() + "Home List &f[&3" + countWarps(p) + "&f/&3"
                + String.valueOf(maxWarps(p)).replaceAll("999", api.lib.infinity()) + "&f]");
        for (String warp : list.getKeys(false)) {
            TextComponent symbol = new TextComponent(api.lib.color("&7- "));

            TextComponent warpname = new TextComponent(api.lib.color("&a" + warp));

            warpname.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/home " + warp));
            warpname.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    (new Text("Click to teleport to '" + ChatColor.BOLD + warp + ChatColor.RESET + "'."))));

            symbol.addExtra(warpname);

            p.spigot().sendMessage(symbol);
        }

        return;
    }

    @Override
    public void listOther() {
        if (api.u.usernameToUUID(target) == null) {
            return;
        }
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(api.u.usernameToUUID(target));
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (countWarps(target) == 0) {
            sendMessage(p, api.lib.getPrefix() + target + "'s Home List §f[§3" + countWarps(target) + "§f/§3"
                    + "?§f]");
            sendMessage(p, ChatColor.GRAY + "[&cThis player has no home set.&7]");
            return;
        }

        sendMessage(p,
                api.lib.getPrefix() + target + "'s Home List §f[§3" + countWarps(target) + "§f/§3" + "?§f]");
        for (String warp : list.getKeys(false)) {
            TextComponent symbol = new TextComponent(api.lib.color("&7- "));

            TextComponent warpname = new TextComponent(api.lib.color("&a" + warp));

            warpname.setClickEvent(
                    new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/playerhome " + target + " " + warp));
            warpname.setHoverEvent(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            (new Text(
                                    "Click to teleport to '" + ChatColor.BOLD + warp + ChatColor.RESET + "'."))));
            symbol.addExtra(warpname);

            p.spigot().sendMessage(symbol);
        }
        return;
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    public int countWarps(Player p) {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (list == null)
            return 0;
        Set<?> warps = list.getKeys(false);
        return warps.size();
    }

    public int countWarps(String playername) {
        DataManager dm = new DataManager();
        Config homes = dm.getHomeData(p);
        FileConfiguration config = homes.getConfig();
        ConfigurationSection list = config.getConfigurationSection("Private-Homes");
        if (list == null)
            return 0;
        Set<?> warps = list.getKeys(false);
        return warps.size();
    }

    public int maxWarps(Player p) {
        int returnv = 0;
        if (p == null)
            return 0;
        for (int i = 100; i >= 0; i--) {
            if (p.hasPermission("hessentials.homes.infinite")) {
                returnv = -1;
                break;
            }
            if (p.hasPermission("hessentials.homes." + i)) {
                returnv = i;
                break;
            }
        }
        if (returnv == -1)
            return 999;

        return returnv;
    }

}
