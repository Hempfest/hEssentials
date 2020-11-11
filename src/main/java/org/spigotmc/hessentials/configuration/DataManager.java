package org.spigotmc.hessentials.configuration;

import java.io.File;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;

public class DataManager {


    public DataManager() {
    }

    public Config getUser(Player p) {
        return new Config(p.getUniqueId().toString(), "Players/user_data");
    }

    public Config getUser(UUID u) {
        return new Config(u.toString(), "Players/user_data");
    }

    public Config getClaimData(Player p) {
        return new Config(p.getUniqueId().toString(), "Players/claim_data");
    }

    public Config getClaimData(UUID u) {
        return new Config(u.toString(), "Players/claim_data");
    }

    public Config getHomeData(Player p) {
        return new Config(p.getUniqueId().toString(), "Players/home_data");
    }

    public Config getHomeData(UUID u) {
        return new Config(u.toString(), "Players/home_data");
    }

    public Config getMisc(String name) {
        return new Config(name, null);
    }

    public Config requestData(String data) {
        return new Config(data, "Configuration/request_data");
    }


    public File getUserFiles() {
        final File dir = new File(Config.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        File d = new File(dir.getParentFile().getPath(), HempfestEssentials.getInstance().getDescription().getName() + "/" + "Players/user_data" + "/");
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }


}
