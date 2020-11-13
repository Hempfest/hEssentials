package org.spigotmc.hessentials.util.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.listener.events.WarpGateEvent;


public class Wild extends BukkitRunnable {



	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				WarpGateEvent e = new WarpGateEvent(p);
				Bukkit.getPluginManager().callEvent(e);
				e.runEvent();
			}
		}
	}
}
