package org.spigotmc.hessentials.listener.events;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.gui.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.InventoryHolder;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.listener.Claim;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class Events implements Listener {
	static heHook api = heHook.getHook();

	Utils u = new Utils();

	public List<UUID> sleeping = new ArrayList<>();

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		api = heHook.getPlayerHook(p);
		UUID uuid = p.getUniqueId();
		DataManager dm = new DataManager();
		Config pd = dm.getUser(uuid);
		if (!p.hasPlayedBefore()) {
			e.setJoinMessage(api.lib.getFirstJoinMSG(p));
			u.createPlayerConfig(p);
			api.pc.updateClaimUser();
			api.pc.createPlayerData();
			return;
		}
		if (!pd.exists()) {
			u.createPlayerConfig(p);
		}
		u.createHomeSection(p);
		u.matchIP(p);
		u.matchUsername(p);
		api.pc.updateClaimUser();
		e.setJoinMessage(api.lib.getJoinMSG(p));
		u.MOTD(p);
	}

	// GUI interact event
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onMenuClick(InventoryClickEvent e) {

		InventoryHolder holder = e.getInventory().getHolder();
		// If the inventory holder of the inventory clicked on
		// is an instance of Menu, then gg. The reason that
		// an InventoryHolder can be a Menu is because our Menu
		// class implements InventoryHolder!!
		if (holder instanceof Menu) {
			e.setCancelled(true); // prevent them from fucking with the inventory
			if (e.getCurrentItem() == null) { // deal with null exceptions
				return;
			}
			// Since we know our inventory holder is a menu, get the Menu Object representing
			// the menu we clicked on
			Menu menu = (Menu) holder;
			// Call the handleMenu object which takes the event and processes it
			menu.handleMenu(e);
		}

		String menu = e.getView().getTitle();
		Player whoClicked = (Player) e.getWhoClicked();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (menu.equals(p.getName() + " : click to update")) {
				String name = menu.replaceAll(" : click to update", "");
				Player pInventory = Bukkit.getPlayer(name);
				e.setCancelled(true);
				whoClicked.closeInventory();
				u.openPlayerInventory(whoClicked, pInventory);
				return;
			}
		}

	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBed(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		int half = Math.round(Bukkit.getOnlinePlayers().size() / 2);
		if (!u.day(p)) {
			if (sleeping.size() == half) {
				sleeping.clear();
				Bukkit.broadcastMessage(api.lib.getPrefix() + "half population voted. Making it day.");
				Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(p.getLocation().getWorld()).getName())).setTime(0L);
				return;
			}

			if (!sleeping.contains(p.getUniqueId())) {
				sleeping.add(p.getUniqueId());
				Bukkit.broadcastMessage(api.lib.getPrefix() + "Player " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " wants to make it day. (Lay down)");
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		DataManager dm = new DataManager();
		Config pd = dm.getUser(p.getUniqueId());
		FileConfiguration fc = pd.getConfig();
		fc.set("Last-Location", p.getLocation());
		pd.saveConfig();
		// UUID uuid = p.getUniqueId();
		// PlayerData pd = new PlayerData(uuid);
		e.setQuitMessage(api.lib.getLeaveMSG(p));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent e) {
		DataManager dm = new DataManager();
		Config message = dm.getMisc(api.lib.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		if (heHook.getHook().u.isMuted() == Boolean.TRUE) {
			if (e.getPlayer().hasPermission("hessentials.staff.mutechat")) {
				for (Player a : Bukkit.getOnlinePlayers())
					a.spigot().sendMessage(new Text().textHoverable("&2[", "&4&l^",
							"&2] " + e.getPlayer().getDisplayName() + " &7&o" + e.getMessage(),
							api.lib.getPrefix() + "&a&oThis player has permission to speak right now."));

			} else {
				e.getPlayer().sendMessage(api.lib.color(m.getString("Messages.Player-Responses.Cannot-Speak")));
			}
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
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

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
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

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
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

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBucketRelease(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		api = heHook.getPlayerHook(p);
		if (api.pc.isInClaim(blocks)) {
			if (!api.pc.getClaimList(p).contains(api.pc.getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(blocks);
				String claimOwner = api.pc.getClaimOwner(blocks);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotPlace(p, claimName, claimOwner));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onChestUse(PlayerInteractEvent e) {
		try {
			Block block = e.getClickedBlock();
			assert block != null;
			Location blocks = block.getLocation();
			Player p = e.getPlayer();
			api = heHook.getPlayerHook(p);
			if (api.pc.isInClaim(blocks)) {
				if (block.getType().isInteractable()) {
					if (!api.pc.getClaimList(p).contains(api.pc.getClaimName(blocks))) {
						e.setCancelled(true);
						String claimName = api.pc.getClaimName(blocks);
						String claimOwner = api.pc.getClaimOwner(blocks);
						api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotUse(p, claimName, claimOwner));
					}
				}
			}
		} catch (NullPointerException ignored) {
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		api = heHook.getPlayerHook(p);
		if (api.pc.isInClaim(blocks)) {
			if (!api.pc.getClaimList(p).contains(api.pc.getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(blocks);
				String claimOwner = api.pc.getClaimOwner(blocks);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotBreak(p, claimName, claimOwner));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location bloc = block.getLocation();
		if (api.pc.isInClaim(bloc)) {
			if (!api.pc.getClaimList(p).contains(api.pc.getClaimName(bloc))) {
				e.setCancelled(true);
				String claimName = api.pc.getClaimName(bloc);
				String claimOwner = api.pc.getClaimOwner(bloc);
				api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotPlace(p, claimName, claimOwner));
			}
			return;
		}
		if (block.getType().equals(Material.TNT)) {
			for (Block bl : getBlocks(block, 4)) {
				Location blocks = bl.getLocation();
				api = heHook.getPlayerHook(p);
				if (api.pc.isInClaim(blocks)) {
					if (!api.pc.getClaimList(p).contains(api.pc.getClaimName(blocks))) {
						e.setCancelled(true);
						String claimName = api.pc.getClaimName(blocks);
						String claimOwner = api.pc.getClaimOwner(blocks);
						api.pc.sendMessage(p, api.lib.getPrefix() + api.lib.getCannotPlaceTNT(p, claimName, claimOwner));
					}
					return;
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onMOTD(ServerListPingEvent e) {
		DataManager dm = new DataManager();
		Config mo = dm.getMisc(api.lib.getMessagesUsed());
		FileConfiguration m = mo.getConfig();
		String motd = api.lib.color(Objects.requireNonNull(m.getString("MOTD-Server-List")).replaceAll("%prefix%", api.lib.getPrefix()));
		e.setMotd(motd.replaceAll("%next%", api.lib.color("\n")));
		e.setMaxPlayers(m.getInt("Server-Max-Players"));
	}

	public ArrayList<Block> getBlocks(Block start, int radius) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++) {
			for (double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++) {
				for (double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++) {
					Location loc = new Location(start.getWorld(), x, y, z);
					blocks.add(loc.getBlock());
				}
			}
		}
		return blocks;
	}

	public BlockFace getBlockFace(Player player) {
		List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
		if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
		Block targetBlock = lastTwoTargetBlocks.get(1);
		Block adjacentBlock = lastTwoTargetBlocks.get(0);
		return targetBlock.getFace(adjacentBlock);
	}


}
