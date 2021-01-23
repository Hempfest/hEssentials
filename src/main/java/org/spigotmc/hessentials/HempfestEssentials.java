package org.spigotmc.hessentials;

import com.youtube.hempfest.hempcore.command.CommandBuilder;
import com.youtube.hempfest.hempcore.event.EventBuilder;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.staff.CommandGod;
import org.spigotmc.hessentials.listener.EventListener;
import org.spigotmc.hessentials.util.Claim;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.timers.SyncRegionArea;
import org.spigotmc.hessentials.util.timers.SyncWarpGate;
import org.spigotmc.hessentials.util.variables.Placeholders;


public class HempfestEssentials extends JavaPlugin {

	// Instance
	public static HempfestEssentials instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Utils u = new Utils();
	public HashMap<String[], int[]> claimMap = new HashMap<>();

	// Start server
	public void onEnable() {
		setInstance(this);
		u.createConfiguration();
		u.createCV();
		u.updateInvsee();
		CommandBuilder builder = new CommandBuilder(this);
		EventBuilder ebuilder = new EventBuilder(this);
		builder.compileFields("org.spigotmc.hessentials.commands");
		ebuilder.compileFields("org.spigotmc.hessentials.listener");
		registerTimers();
		Claim.loadClaims();
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
			for (Player p : Bukkit.getOnlinePlayers()) {
				EventListener.vanishPlayer.put(p.getUniqueId(), false);
				EventListener.inventoryOpen.put(p.getUniqueId(), false);
			}
		}, 20);
		new WorldCreator("Build").type(WorldType.FLAT).createWorld();
		new WorldCreator("War").type(WorldType.FLAT).createWorld();
	}

	public void onDisable() {
		EventListener.vanishPlayer.clear();
		EventListener.staffGui.clear();
		Utils.invStorage.clear();
		CommandGod.GodPlayers.clear();
	}

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}


	public void registerTimers() {
		SyncRegionArea timer = new SyncRegionArea();
		SyncWarpGate syncWarpGate = new SyncWarpGate();
		timer.runTaskTimerAsynchronously(this, 20, 20);
		syncWarpGate.runTaskTimer(this, 10, 80);
		if (u.checkforPH()) {
			new Placeholders(this).register();
			log.info(String.format("[%s] - PlaceholderAPI FOUND!", getDescription().getName()));
		} else {
			log.info(String.format("[%s] - PlaceholderAPI not detected!", getDescription().getName()));
		}
	}
}
