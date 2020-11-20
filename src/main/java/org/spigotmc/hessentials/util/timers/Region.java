package org.spigotmc.hessentials.util.timers;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.listener.events.hempfest.ClaimUpdateEvent;


public class Region extends BukkitRunnable {

	public static HashMap<UUID, ClaimUpdateEvent> saved = new HashMap<>();

	private static ClaimUpdateEvent claimManager(Player p) {
		ClaimUpdateEvent e;
		if (!saved.containsKey(p.getUniqueId())) {
			e = new ClaimUpdateEvent(p);
			saved.put(p.getUniqueId(), e);
			return e;
		} else
			return saved.get(p.getUniqueId());
	}

	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ClaimUpdateEvent event = claimManager(p);
				if (!event.isCancelled()) {
					event.runEvent();
				}

			}
		}
	}
}
