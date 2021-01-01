package org.spigotmc.hessentials.listener.events.hempfest;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.Claim;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class ClaimListener implements Listener {

	private static heHook api = heHook.getHook();

	private final Utils u = new Utils();

	@EventHandler(priority = EventPriority.NORMAL)
	public void onFireHit(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Fireball) {
			if (e.getEntity().getShooter() instanceof Player) {
				Player p = (Player) e.getEntity().getShooter();
				api = heHook.getPlayerHook(p);
				if (api.pc.isInClaim(e.getEntity().getLocation())) {
					p.sendMessage(api.lib.color(api.lib.getPrefix() + api.lib.getCannotUse(p, api.pc.getClaimName(e.getEntity().getLocation()), api.pc.getClaimOwner(e.getEntity().getLocation()))));
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityExplode(EntityExplodeEvent event) {
		Entity ent = event.getEntity();
		Claim location = new Claim();
		if (ent instanceof Creeper || ent instanceof Fireball || ent instanceof TNTPrimed) {
			if (location.isInClaim(ent.getLocation())) {
				event.setCancelled(true);
				Player p = Bukkit.getPlayer(location.getClaimOwner(ent.getLocation()));
				if (p != null) {
					u.sendMessage(p, u.getPrefix() + "&c&oAn explosion just happened at your place " + '"' + location.getClaimName(ent.getLocation()) + '"');
					u.sendMessage(p, "&e&oBut don't worry, you're protected :)");
				}
			}

		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onExplosionPrime(ExplosionPrimeEvent event) {


		Entity ent = event.getEntity();
		Claim location = new Claim();
		if (location.isInClaim(ent.getLocation())) {
			event.setCancelled(true);
			Player p = Bukkit.getPlayer(location.getClaimOwner(ent.getLocation()));
			if (p != null) {
				u.sendMessage(p, u.getPrefix() + "&c&oAn explosion just happened at your place " + '"' + location.getClaimName(ent.getLocation()) + '"');
				u.sendMessage(p, "&e&oBut don't worry, you're protected :)");
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBucketRelease(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		api = heHook.getPlayerHook(p);
		if (api.pc.isInClaim(blocks)) {
			DataManager dm = new DataManager();
			Config pd = dm.getClaimData(p);
			List<String> Claims = pd.getConfig().getStringList("claim_data");
			if (!Claims.contains(api.pc.getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(blocks);
				String claimOwner = api.pc.getClaimOwner(blocks);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotPlace(p, claimName, claimOwner));
			}
		}
	}


	@EventHandler(priority = EventPriority.NORMAL)
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		api = heHook.getPlayerHook(p);
		if (api.pc.isInClaim(blocks)) {
			DataManager dm = new DataManager();
			Config pd = dm.getClaimData(p);
			List<String> Claims = pd.getConfig().getStringList("claim_data");
			if (!Claims.contains(api.pc.getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(blocks);
				String claimOwner = api.pc.getClaimOwner(blocks);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotBreak(p, claimName, claimOwner));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location bloc = block.getLocation();
		if (api.pc.isInClaim(bloc)) {
			DataManager dm = new DataManager();
			Config pd = dm.getClaimData(p);
			List<String> Claims = pd.getConfig().getStringList("claim_data");
			if (!Claims.contains(api.pc.getClaimName(bloc))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(bloc);
				String claimOwner = api.pc.getClaimOwner(bloc);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotPlace(p, claimName, claimOwner));
			}
		}
	}

}
