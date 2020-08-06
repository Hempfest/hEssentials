package org.spigotmc.hessentials.commands.economy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyData
{
    private final UUID u;
    private FileConfiguration fc;
    private File file;
    private static final JavaPlugin plugin;
    private static List<EconomyData> configs;
    
    private EconomyData(final Player p) {
        this.u = p.getUniqueId();
        EconomyData.configs.add(this);
    }
    
    public EconomyData(final UUID u) {
        this.u = u;
        EconomyData.configs.add(this);
    }
    
    public Player getOwner() {
        if (this.u == null) {
            try {
                throw new Exception();
            }
            catch (Exception e) {
                getInstance().getLogger().warning("ERR... Player is Null!");
                e.printStackTrace();
            }
        }
        return Bukkit.getPlayer(this.u);
    }
    
    public UUID getOwnerUUID() {
        if (this.u == null) {
            try {
                throw new Exception();
            }
            catch (Exception e) {
                getInstance().getLogger().warning("ERR... Player is Null!");
                e.printStackTrace();
            }
        }
        return this.u;
    }
    
    public static JavaPlugin getInstance() {
        if (EconomyData.plugin == null) {
            try {
                throw new Exception();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return EconomyData.plugin;
    }
    
    public static EconomyData getConfig(final Player p) {
        for (final EconomyData c : EconomyData.configs) {
            if (c.getOwnerUUID().equals(p.getUniqueId())) {
                return c;
            }
        }
        return new EconomyData(p);
    }
    
    public static EconomyData getConfig(final UUID u) {
        for (final EconomyData c : EconomyData.configs) {
            if (c.getOwnerUUID().equals(u)) {
                return c;
            }
        }
        return new EconomyData(u);
    }
    
    public boolean delete() {
        return this.getFile().delete();
    }
    
    public boolean exists() {
        if (this.fc == null || this.file == null) {
            final File temp = new File(getDataFolder(), this.getOwnerUUID() + ".yml");
            if (!temp.exists()) {
                return false;
            }
            this.file = temp;
        }
        return true;
    }
    
    public static File getDataFolder() {
        final File dir = new File(EconomyData.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        final File d = new File(dir.getParentFile().getPath(), getInstance().getName() + "/economy/players/");
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }
    
    public File getFile() {
        if (this.file == null) {
            this.file = new File(getDataFolder(), this.getOwnerUUID() + ".yml");
            if (!this.file.exists()) {
                try {
                    this.file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.file;
    }
    
    public FileConfiguration getConfig() {
        if (this.fc == null) {
            this.fc = (FileConfiguration)YamlConfiguration.loadConfiguration(this.getFile());
        }
        return this.fc;
    }
    
    public void reload() {
        if (this.file == null) {
            this.file = new File(getDataFolder(), this.getOwner().getUniqueId().toString() + ".yml");
            if (!this.file.exists()) {
                try {
                    this.file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.fc = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
        }
    }
    
    public void resetConfig() {
        this.delete();
        this.getConfig();
    }
    
    public void saveConfig() {
        try {
            this.getConfig().save(this.getFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static {
        plugin = JavaPlugin.getProvidingPlugin((Class<?>)EconomyData.class);
        EconomyData.configs = new ArrayList<EconomyData>();
    }
}
