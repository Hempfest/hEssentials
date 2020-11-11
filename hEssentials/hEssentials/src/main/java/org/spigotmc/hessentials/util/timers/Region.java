package org.spigotmc.hessentials.util.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.listener.events.ClaimUpdateEvent;


public class Region extends BukkitRunnable {

	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ClaimUpdateEvent event = new ClaimUpdateEvent(p);
				if (!event.isCancelled()) {
					event.runEvent();
				}
			}
		}
	}
}
