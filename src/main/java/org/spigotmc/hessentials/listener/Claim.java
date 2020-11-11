package org.spigotmc.hessentials.listener;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.gui.claim.InventoryClaimMenu;
import org.spigotmc.hessentials.util.heHook;
import org.spigotmc.hessentials.util.variables.Formatter;

public class Claim implements Formatter {
    Player p;
    String targetName;
    Player target;
    heHook api = heHook.getHook();
    String claimName;

    public static HashMap<String[], int[]> claimMap = new HashMap<>();

    public static HashMap<UUID, List<String>> playerClaimMap = new HashMap<>();

    public Claim() {
    }

    public Claim(Player p) {
        this.p = p;
    }

    public Claim(Player p, Player target) {
        this.p = p;
        this.target = target;
    }

    public Claim(Player p, String claimName, String targetName) {
        this.p = p;
        this.claimName = claimName;
        this.targetName = targetName;
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    private boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    @Override
    public void set() {
        Location location = p.getLocation();
        DataManager dm = new DataManager();
        Config pd = dm.getClaimData(p.getUniqueId());
        Config data = dm.requestData("Claims");

        String ID = claimName;
        if (!isAlphaNumeric(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "Invalid claim name. Try another.");
            return;
        }
        if (isInClaim(location) && !isClaimOwner()) {
            sendMessage(p, api.lib.getPrefix() + "You do not own this land.\nOwner: " + getClaimOwner(location));
            return;
        }
        if (isInClaim(location) && isClaimOwner()) {
            sendMessage(p, api.lib.getPrefix() + "You already own this land.\nClaim: " + getClaimName(location));
            return;
        }
        if (getClaimList(p).size() >= maxWarps(p.getName())) {
            sendMessage(p, api.lib.getPrefix() + "You already have set your max amount of claims: " + maxWarps(p.getName()));
            return;
        }
        if (claimList().contains(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "A claim with this name already exists!");
            return;
        }
        List<String> Claim = pd.getConfig().getStringList("claim_data");
        if (!Claim.contains(ID)) {
            Claim.add(ID);
            pd.getConfig().set("claim_data", Claim);
            pd.saveConfig();
        }
        int x = location.getChunk().getX();
        int z = location.getChunk().getZ();
        World w = location.getWorld();
        data.getConfig().set("Location." + ID + ".X", x);
        data.getConfig().set("Location." + ID + ".Z", z);
        data.getConfig().set("Location." + ID + ".World", w.getName());
        data.getConfig().set("Location." + ID + ".Owner", p.getName());
        data.getConfig().createSection("Location." + ID + ".User");
        data.saveConfig();
        sendMessage(p, api.lib.getPrefix() + "You just claimed land: " + claimName);
        GuiLibrary gui = HempCore.guiManager(p);
        new InventoryClaimMenu(gui).open();
        return;
    }

    @Override
    public void delete() {
        Location location = p.getLocation();
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        Config pd = dm.getClaimData(p);
        String ID = claimName;
        List<String> Claim = pd.getConfig().getStringList("claim_data");
        if (isInClaim(location) && !isClaimOwner()) {
            sendMessage(p, api.lib.getPrefix() + "You do not own this land.\nOwner: " + getClaimOwner(location));
            return;
        }
        if (isInClaim(location) && isClaimOwner()) {
            if (Claim.contains(ID)) {
                Claim.remove(ID);
                pd.getConfig().set("claim_data", Claim);
                pd.saveConfig();
            }
            data.getConfig().set("Location." + ID, null);
            data.saveConfig();
            sendMessage(p, api.lib.getPrefix() + "You just un-claimed land: " + claimName);
            return;
        }
        sendMessage(p, api.lib.getPrefix() + "You do not own this land.\nOwner: " + "&oUn-claimed");
        return;
    }

    @Override
    public void add() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        Config da = dm.getClaimData(p);
        FileConfiguration d = data.getConfig();
        // chefk if the players in the claim and if it exists
        if (!getClaimName(p.getLocation()).equals(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "You must be standing within the claim to add users!");
            return;
        }
        if (!da.getConfig().getStringList("claim_data").contains(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "You do not own a claim by the name of: " + claimName);
            return;
        }

        if (isInClaim(p.getLocation()) && isClaimOwner()) {
            for (String s : d.getConfigurationSection("Location").getKeys(false)) {
                List<String> Claims = d.getStringList("Location." + s + ".User");
                if (!Claims.contains(targetName) && s.equals(claimName)) {
                    Claims.add(targetName);
                }
                Player online = Bukkit.getPlayer(targetName);
                if (online != null) {
                    Config pd = dm.getClaimData(online);
                    List<String> Claim = pd.getConfig().getStringList("claim_data");
                    if (!Claim.contains(claimName)) {
                        Claim.add(claimName);
                        pd.getConfig().set("claim_data", Claim);
                        pd.saveConfig();
                        sendMessage(online, api.lib.getPrefix() + "You were given permission to the land &7(&f"
                                + claimName + "&7) &rby: " + p.getName());
                    }
                }
                d.set("Location." + s + ".User", Claims);
                data.saveConfig();
            }

            sendMessage(p, api.lib.getPrefix() + "You just added user (" + targetName + ") to land: " + claimName);

        }
    }

    @Override
    public void take() {
        DataManager dm = new DataManager();
        Config da = dm.getClaimData(p);
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        // chefk if the players in the claim and if it exists
        if (!getClaimName(p.getLocation()).equals(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "You must be standing within the claim to remove users!");
            return;
        }
        if (!da.getConfig().getStringList("claim_data").contains(claimName)) {
            sendMessage(p, api.lib.getPrefix() + "You do not own a claim by the name of: " + claimName);
            return;
        }

        if (isInClaim(p.getLocation()) && isClaimOwner()) {
            for (String s : d.getConfigurationSection("Location").getKeys(false)) {
                List<String> Claims = d.getStringList("Location." + s + ".User");
                if (Claims.contains(targetName) && s.equals(claimName)) {
                    Claims.remove(targetName);
                }
                Player online = Bukkit.getPlayer(targetName);
                if (online != null) {
                    Config pd = dm.getClaimData(online);
                    List<String> Claim = pd.getConfig().getStringList("claim_data");
                    if (Claim.contains(claimName)) {
                        Claim.remove(claimName);
                        pd.getConfig().set("claim_data", Claim);
                        pd.saveConfig();
                        sendMessage(online, api.lib.getPrefix() + "You were removed permission to the land &7(&f"
                                + claimName + "&7) &rby: " + p.getName());
                    }
                }
                d.set("Location." + s + ".User", Claims);
                data.saveConfig();
            }

            sendMessage(p, api.lib.getPrefix() + "You just removed user (" + targetName + ") from land: " + claimName);

        }
    }

    @Override
    public void teleport() {
        teleportChunk();
    }

    @Override
    public void list() {
        sendMessage(p, api.lib.getPrefix() + "" + getClaimList(p).toString());
    }

    @Override
    public void listOther() {
        sendMessage(p, api.lib.getPrefix() + "" + getClaimList(target).toString());
    }

    public static void loadClaims() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        for (String s : d.getConfigurationSection("Location").getKeys(false)) {
            int x = d.getInt("Location." + s + ".X");
            int z = d.getInt("Location." + s + ".Z");
            String w = d.getString("Location." + s + ".World");
            String[] ID = {s, w};
            int[] pos = {x, z};
            claimMap.put(ID, pos);
        }
    }

    public boolean isInClaim(Location loc) {
        for (Map.Entry<String[], int[]> entry : claimMap.entrySet()) {
            String[] data = entry.getKey();
            int[] pos = entry.getValue();
            String name = data[0];
            String world = data[1];
            int x = pos[0];
            int z = pos[1];
            if ((loc.getChunk().getX() <= x) && (loc.getChunk().getZ() <= z) && (loc.getChunk().getX() >= x)
                    && (loc.getChunk().getZ() >= z) && loc.getWorld().getName().equals(world)) {
                return true;
            }
        }

        return false;
    }

    public void createPlayerData() {
        DataManager dm = new DataManager();
        Config pl = dm.getClaimData(p);
        FileConfiguration data = pl.getConfig();

        if (!pl.exists()) {
            data.createSection("claim_data");
            pl.saveConfig();
        }


        return;
    }

    public List<String> getUserList(Player p, String claimName) {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        List<String> Claims = d.getStringList("Location." + claimName + ".User");
        if (!d.getConfigurationSection("Location").getKeys(false).contains(claimName)) {
            return new ArrayList<>();
        }
        if (d.getString("Location." + claimName + ".Owner").equals(p.getName())) {
            return Claims;
        }
        List<String> Claim = d.getStringList("Location." + getClaimName(p.getLocation()) + ".User");
        return Claim;
    }

    public static void loadPlayerClaims(Player p) {
        DataManager dm = new DataManager();
        Config pd = dm.getClaimData(p);
        List<String> Claims = pd.getConfig().getStringList("claim_data");
        playerClaimMap.put(p.getUniqueId(), Claims);
    }

    // Return the players claim list
    public List<String> getClaimList(Player p) {
        return playerClaimMap.get(p.getUniqueId());
    }

    public List<String> claimList() {
        List<String> Claims = new ArrayList<>();
        for (Map.Entry<String[], int[]> entry : claimMap.entrySet()) {
            String[] data = entry.getKey();
            String name = data[0];
            Claims.add(name);
        }
        return Claims;

    }

    public int maxWarps(String playername) {
        Player player = Bukkit.getPlayer(playername);
        int returnv = 0;
        if (player == null)
            return 0;
        for (int i = 100; i >= 0; i--) {
            if (player.hasPermission("hessentials.claim.infinite")) {
                returnv = -1;
                break;
            }
            if (player.hasPermission("hessentials.claim." + i)) {
                returnv = i;
                break;
            }
        }
        if (returnv == -1)
            return 999;

        return returnv;
    }

    // Check if the player in the claim is the claim owner
    public boolean isClaimOwner() {
        return getClaimOwner(p.getLocation()).equals(p.getName());
    }

    // Check if the player is a claim user
    public boolean isClaimUser() {
        DataManager dm = new DataManager();
        Config pd = dm.getUser(p);
        if (isInClaim(p.getLocation()) && !isClaimOwner()) {
            return pd.getConfig().getStringList("claim_data").contains(getClaimName(p.getLocation()));
        }
        return false;
    }

    // Get the claim name at the specified location
    public String getClaimName(Location loc) {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        if (isInClaim(loc)) {
            for (String s : d.getConfigurationSection("Location").getKeys(false)) {
                int x = d.getInt("Location." + s + ".X");
                int z = d.getInt("Location." + s + ".Z");
                if (x == loc.getChunk().getX() && z == loc.getChunk().getZ()) {
                    return s;
                }
            }
        }
        return "Wild";
    }

    // Get the claim owner of the specified location
    public String getClaimOwner(Location loc) {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        if (isInClaim(loc)) {
            for (String s : d.getConfigurationSection("Location").getKeys(false)) {
                int x = d.getInt("Location." + s + ".X");
                int z = d.getInt("Location." + s + ".Z");
                if (x == loc.getChunk().getX() && z == loc.getChunk().getZ()) {
                    return d.getString("Location." + s + ".Owner");
                }
            }
        } else

            return "Unowned";
        return "Unowned";
    }

    // Update all users in the game with permission to claims that grant access.
    public void updateClaimUser() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        FileConfiguration d = data.getConfig();
        if (isInClaim(p.getLocation())) {
            Config pd = dm.getClaimData(p);
            List<String> Claim = pd.getConfig().getStringList("claim_data");
            if (d.getStringList("Location." + getClaimName(p.getLocation()) + ".User").contains(p.getName())) {

                if (!Claim.contains(getClaimName(p.getLocation()))) {
                    Claim.add(getClaimName(p.getLocation()));
                    pd.getConfig().set("claim_data", Claim);
                    pd.saveConfig();
                    sendMessage(p, api.lib.getPrefix() + "You were given permission to the land &7(&f"
                            + getClaimName(p.getLocation()) + "&7) &rby: " + getClaimOwner(p.getLocation()));
                }
            } else if (!d.getStringList("Location." + getClaimName(p.getLocation()) + ".User").contains(p.getName().toLowerCase())) {
                if (isClaimOwner()) {
                    return;
                }
                if (Claim.contains(getClaimName(p.getLocation()))) {
                    Claim.remove(getClaimName(p.getLocation()));
                    pd.getConfig().set("claim_data", Claim);
                    pd.saveConfig();
                    sendMessage(p, api.lib.getPrefix() + "You were removed permission to the land &7(&f"
                            + getClaimName(p.getLocation()) + "&7) &rby: " + getClaimOwner(p.getLocation()));
                    return;
                }
            }
            return;
        }
    }

    public Location locateChunk() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        int x = data.getConfig().getInt("Location." + claimName + ".X");
        int y = 110;
        int z = data.getConfig().getInt("Location." + claimName + ".Z");
        Location teleportLocation = new Location(p.getWorld(), x << 4, y, z << 4).add(7, 0, 7);
        return teleportLocation;
    }

    public boolean chunkNull() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        return !data.getConfig().getConfigurationSection("Location").getKeys(false).contains(claimName);
    }

    public void teleportChunk() {
        DataManager dm = new DataManager();
        Config data = dm.requestData("Claims");
        int x = data.getConfig().getInt("Location." + claimName + ".X");
        int y = 150;
        int z = data.getConfig().getInt("Location." + claimName + ".Z");
        String w = data.getConfig().getString("Location." + claimName + ".World");

        boolean isOnLand = false;
        while (isOnLand == false) {
            Location teleportLocation = null;
            teleportLocation = new Location(p.getWorld(), x << 4, y, z << 4).add(7, 0, 7);
            if (teleportLocation.getBlock().getType() != Material.AIR) {
                isOnLand = true;
            } else
                y--;

            p.teleport(new Location(Bukkit.getWorld(w), teleportLocation.getX(), teleportLocation.getY() + 1,
                    teleportLocation.getZ()));

        }
        sendMessage(p, api.lib.getPrefix() + "&aTeleported &7to: &e" + claimName);

    }

    public void StopTimer() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), () -> {
            Bukkit.getScheduler().cancelTask(api.u.stop);
            api.u.stop = 0;
            api.u.setClaimToLocate(null);
        }, 20L * 60);
    }

    // Connect player loc to claim loc
    public void connectSpace(final Player p) {
        if (api.u.stop != 0) {
            p.playSound(p.getLocation(), Sound.ENTITY_GUARDIAN_DEATH, 10, 1);
            sendMessage(p, api.lib.getPrefix() + "&5&oThe tracking system is backed up. Try again later");
            return;
        }
        api.u.setPlayerToTell(p);
        api.u.setClaimToLocate(claimName);
        api.u.stop = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.getInstance(), new Runnable() {
            public void run() {
                Location origin = p.getEyeLocation();  // location where it starts
                Vector target = locateChunk().toVector();  // location where it ends (for the direction only)
                origin.setDirection(target.subtract(origin.toVector()));  // setting direction bc of above information
                Vector increase = origin.getDirection().multiply(1.3); // getting what to increase by

                for (int counter = 0; counter < 10; counter++) { // 5 == blocks to travel
                    Location loc = origin.add(increase);
                    float x = (float) loc.getX();
                    float y = (float) loc.getY();
                    float z = (float) loc.getZ();
                    if (Bukkit.getServer().getVersion().contains("1.15"))
                        loc.getWorld().spawnParticle(Particle.FLAME, x, y, z, 10, 0, 0, 0, 0);
                    if (Bukkit.getServer().getVersion().contains("1.16"))
                        loc.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, x, y, z, 10, 0, 0, 0, 0);
                }
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(api.lib.color("&b&oLocating chunk: &a" + claimName)));
            }

        }, 20L, 40L);
    }

    private String randomID(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    // Create a claim for the player
    public void autoChunk(Player p) {
        DataManager dm = new DataManager();
        Location location = p.getLocation();
        Config data = dm.requestData("Claims");
        Config pd = dm.getClaimData(p);
        List<String> Claim = pd.getConfig().getStringList("claim_data");
        String ID = randomID(5);
        if (isInClaim(location) && !isClaimOwner()) {
            return;
        }
        if (isInClaim(location) && isClaimOwner()) {
            return;
        }
        if (getClaimList(p).size() >= maxWarps(p.getName())) {
            Bukkit.dispatchCommand(p, "claim autoclaim");
            sendMessage(p, api.lib.getPrefix() + "You already have set your max amount of claims: " + maxWarps(p.getName()));
            return;
        }
        if (claimList().contains(ID)) {
            sendMessage(p, api.lib.getPrefix() + "A claim with this name already exists!");
            return;
        }

        if (!Claim.contains(ID)) {
            Claim.add(ID);
            pd.getConfig().set("claim_data", Claim);
            pd.saveConfig();
        }
        int x = location.getChunk().getX();
        int z = location.getChunk().getZ();
        World w = location.getWorld();
        data.getConfig().set("Location." + ID + ".X", x);
        data.getConfig().set("Location." + ID + ".Z", z);
        data.getConfig().set("Location." + ID + ".World", w.getName());
        data.getConfig().set("Location." + ID + ".Owner", p.getName());
        data.getConfig().createSection("Location." + ID + ".User");
        data.saveConfig();
        sendMessage(p, api.lib.getPrefix() + "&a[AUTO] &rYou just claimed land: " + ID);
        return;

    }

}
