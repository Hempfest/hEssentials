package org.spigotmc.hessentials.util.timers;

import com.youtube.hempfest.centerspawn.CenterSpawn;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.hessentials.util.heHook;


public class Wild extends BukkitRunnable {

	HashMap<UUID, Boolean> sent = new HashMap<>();
	heHook api = heHook.getHook();

	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (CenterSpawn.isInSpawn(p.getLocation())) {
					for (Entity e : p.getNearbyEntities(1, 1, 1)) {
						if (e instanceof EnderCrystal) {
							if (!sent.containsKey(p.getUniqueId())) {
								sent.put(p.getUniqueId(), false);
							}

							EnderCrystal ent = (EnderCrystal) e;
							ent.setBeamTarget(p.getLocation());
							int x = new Random().nextInt(3000);
							int y = 85;
							int z = new Random().nextInt(3000);
							World w = p.getWorld();

							boolean isOnLand = false;
							while (!isOnLand) {
								Location teleportLocation = null;
								teleportLocation = new Location(w, x, y, z);
								if (teleportLocation.getBlock().getType() != Material.AIR) {
									isOnLand = true;
								} else
									y--;
								if (!sent.get(p.getUniqueId())) {
									sent.put(p.getUniqueId(), true);
									p.teleport(new Location(w, teleportLocation.getX(), teleportLocation.getY() + 1,
											teleportLocation.getZ()), PlayerTeleportEvent.TeleportCause.END_GATEWAY);
									api.u.sendMessage(p, api.u.getPrefix() + "&aYou've been teleported to somewhere random.");

								}
							}
							return;
						}
						break;
					}
					sent.put(p.getUniqueId(), false);
				}
			}
		}
	}
}
