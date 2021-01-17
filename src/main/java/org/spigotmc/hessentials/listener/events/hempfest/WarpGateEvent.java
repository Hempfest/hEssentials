package org.spigotmc.hessentials.listener.events.hempfest;

import com.youtube.hempfest.centerspawn.CenterSpawn;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.hessentials.util.heHook;

public class WarpGateEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final Player p;

	private final HashMap<UUID, Boolean> sent = new HashMap<>();

	private final heHook api = heHook.getHook();

	private Location location;

	private boolean cancelled;

	private String message;

	private Entity entity;


	public WarpGateEvent(Player p) {
		this.p = p;
	}


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Checks if a location is safe (solid ground with 2 breathable blocks)
	 *
	 * @param location Location to check
	 * @return True if location is safe
	 */
	public boolean hasSurface(Location location) {
		Block feet = location.getBlock();
		Block head = feet.getRelative(BlockFace.UP);
		if (!feet.getType().isAir() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isAir() && !head.getType().isAir()) {
			return false; // not transparent (will suffocate)
		}
		return feet.getRelative(BlockFace.DOWN).getType().isSolid(); // not solid
	}

	public void setGateLocation(Location location) {
		this.location = location;
	}

	public void setGateMessage(String message) {
		this.message = message;
	}

	public Block getGateBlock() {
		return entity.getLocation().getBlock();
	}

	public EnderCrystal getGate() {
		return (EnderCrystal) entity;
	}

	public Player getPlayer() {
		return p;
	}

	public void runEvent() {
		boolean environment;
		if (Bukkit.getPluginManager().isPluginEnabled("CenterSpawn")) {
			environment = CenterSpawn.isInSpawn(p.getLocation());
		} else {
			environment = heHook.getPlayerHook(p).pc.isInClaim(p.getLocation());
		}
		if (environment) {
			for (Entity e : p.getNearbyEntities(1, 1, 1)) {
				if (e instanceof EnderCrystal) {
					if (!sent.containsKey(p.getUniqueId())) {
						sent.put(p.getUniqueId(), false);
					}

					EnderCrystal ent = (EnderCrystal) e;
					if (this.entity == null) {
						this.entity = ent;
					}
					ent.setBeamTarget(p.getLocation());
					int x = new Random().nextInt(3000);
					int y = 150;
					int z = new Random().nextInt(3000);
					World w = p.getWorld();
					Location teleportLocation = new Location(w, x, y, z);
					y = teleportLocation.getWorld().getHighestBlockYAt(teleportLocation);
					teleportLocation.setY(y);
					if (!hasSurface(teleportLocation)) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(api.u.color(api.u.getPrefix() + "Searching for suitable location...")));
						p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 1);
						teleportLocation = new Location(w, x, y, z);
					}
					if (!sent.get(p.getUniqueId())) {
						sent.put(p.getUniqueId(), true);
						if (this.location != null) {
							teleportLocation = this.location;
						}
						for (Entity et : p.getNearbyEntities(30, 30, 30)) {
							if (et instanceof Player) {
								Player t = (Player) et;
								t.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10, 1);
							}
						}

						p.teleport(teleportLocation, PlayerTeleportEvent.TeleportCause.END_GATEWAY);
						String message = "&aYou've been teleported to somewhere random.";
						if (this.message != null) {
							message = this.message;
						}
						api.u.sendMessage(p, api.u.getPrefix() + message);
						p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 10, 1);
					}

					return;
				}
				break;
			}
			sent.put(p.getUniqueId(), false);
		}
	}


	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}
}
