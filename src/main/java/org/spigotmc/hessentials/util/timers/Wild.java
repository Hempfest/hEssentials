package org.spigotmc.hessentials.util.timers;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.listener.events.hempfest.WarpGateEvent;


public class Wild extends BukkitRunnable {

	public static HashMap<UUID, WarpGateEvent> saved = new HashMap<>();

	private static WarpGateEvent gateManager(Player p) {
		WarpGateEvent e;
		if (!saved.containsKey(p.getUniqueId())) {
			e = new WarpGateEvent(p);
			saved.put(p.getUniqueId(), e);
			return e;
		} else
			return saved.get(p.getUniqueId());
	}


	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				WarpGateEvent e = gateManager(p);
				Bukkit.getPluginManager().callEvent(e);
				if (!e.isCancelled()) {
					e.runEvent();
				}
			}
		}
	}
}
