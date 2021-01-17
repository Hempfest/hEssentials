package org.spigotmc.hessentials.listener;

import com.youtube.hempfest.centerspawn.CenterSpawn;
import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.gui.staff.InventoryConfiguration;
import org.spigotmc.hessentials.gui.staff.InventoryTeleport;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class EventListener implements Listener {
	private static heHook api = heHook.getHook();

	Utils u = new Utils();

	public List<UUID> sleeping = new ArrayList<>();

	public List<UUID> frozenDudes = new ArrayList<>();

	public static HashMap<UUID, Boolean> staffGui = new HashMap<>();

	public static HashMap<UUID, Boolean> vanishPlayer = new HashMap<>();

	public static HashMap<UUID, Boolean> inventoryOpen = new HashMap<>();

	@EventHandler(priority = EventPriority.NORMAL)
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
			u.createHomeSection(p);
			api.pc.createPlayerData();
			u.MOTD(p);
			inventoryOpen.put(e.getPlayer().getUniqueId(), false);
			return;
		}
		if (vanishPlayer.containsKey(p.getUniqueId())) {
			if (vanishPlayer.get(p.getUniqueId())) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					a.hidePlayer(HempfestEssentials.getInstance(), p);
				}
				api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&3&oYou are now invisible!");
			}
		}
		for (Player cVis : Bukkit.getOnlinePlayers()) {
			if (vanishPlayer.containsKey(cVis.getUniqueId())) {
				if (vanishPlayer.get(cVis.getUniqueId())) {
					if (!cVis.getName().equals(p.getName())) {
						p.hidePlayer(HempfestEssentials.getInstance(), cVis);
					}
				}
			}
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
		api.u.resetItems(p);
		inventoryOpen.put(e.getPlayer().getUniqueId(), false);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void dropStaffItem(PlayerDropItemEvent e) {
		if (EventListener.staffGui.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(EventListener.staffGui.get(e.getPlayer().getUniqueId()));
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onInventoryOpen(InventoryOpenEvent event) {
		Player player = (Player) event.getPlayer();
		if (event.getInventory().getType() == InventoryType.MERCHANT)
			if (!player.hasPermission("hessentials.villager.trading")) {
				event.setCancelled(true);
				api.u.sendMessage(player, "&c&oVillager trading is disabled due to performance and exploitation reasons.");
			}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvOpen(InventoryOpenEvent e) {
		inventoryOpen.put(e.getPlayer().getUniqueId(), true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvClose(InventoryCloseEvent e) {
		inventoryOpen.put(e.getPlayer().getUniqueId(), false);
	}

	// GUI interact event
	@EventHandler(priority = EventPriority.NORMAL)
	public void onMenuClick(InventoryClickEvent e) {
		Player whoClicked = (Player) e.getWhoClicked();
		if (EventListener.staffGui.containsKey(whoClicked.getUniqueId())) {
			e.setCancelled(EventListener.staffGui.get(whoClicked.getUniqueId()));
		}

		String menu = e.getView().getTitle();

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

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBed(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		int half = Math.round(Bukkit.getOnlinePlayers().size() / 2);
		if (!u.day(p)) {
			if (Bukkit.getOnlinePlayers().size() == 2) {
				sleeping.clear();
				Bukkit.broadcastMessage(api.lib.getPrefix() + "Half population voted. Making it day.");
				Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(p.getLocation().getWorld()).getName())).setTime(0L);
			}
			if (sleeping.size() == half) {
				sleeping.clear();
				Bukkit.broadcastMessage(api.lib.getPrefix() + "Half population voted. Making it day.");
				Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(p.getLocation().getWorld()).getName())).setTime(0L);
				return;
			}

			if (!sleeping.contains(p.getUniqueId())) {
				sleeping.add(p.getUniqueId());
				Bukkit.broadcastMessage(api.lib.getPrefix() + "Player " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " wants to make it day. (Lay down)");
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onTeleportEvent(PlayerTeleportEvent event) {
		Player p = event.getPlayer();
		if (p.hasPermission("hessentials.staff")) {
			DataManager dm = new DataManager();
			Config user = dm.getUser(p.getUniqueId());
			user.getConfig().set("Past-Location", event.getFrom());
			user.saveConfig();
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onDeathEvent(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		if (p.hasPermission("hessentials.staff")) {
			DataManager dm = new DataManager();
			Config user = dm.getUser(p.getUniqueId());
			user.getConfig().set("Past-Location", p.getLocation());
			user.saveConfig();
		}
		if (staffGui.containsKey(p.getUniqueId())) {
			e.setKeepInventory(true);
			e.getDrops().clear();
		}

	}

	private static boolean isInventoryFull(Player p) {
		return (p.getInventory().firstEmpty() == -1);
	}

	@EventHandler(priority = EventPriority.NORMAL)
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
		inventoryOpen.remove(e.getPlayer().getUniqueId());
		if (staffGui.containsKey(p.getUniqueId())) {
			if (staffGui.get(p.getUniqueId())) {
				staffGui.put(p.getUniqueId(), false);
				p.getInventory().clear();
				ItemStack[] contents = Utils.invStorage.get(p.getUniqueId());
				for (ItemStack item : contents) {
					if (item != null) {
						if (isInventoryFull(p)) {
							p.getWorld().dropItemNaturally(p.getLocation(), item);
						} else
							p.getInventory().addItem(item);
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
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

	@EventHandler(priority = EventPriority.NORMAL)
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			if (staffGui.containsKey(p.getUniqueId())) {
				e.setCancelled(true);
				e.getEntity().remove();
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Player) && (e.getEntity() instanceof EnderCrystal)) {
			Player p = (Player) e.getDamager();
			EnderCrystal ec = (EnderCrystal) e.getEntity();


			if (Bukkit.getPluginManager().isPluginEnabled("CenterSpawn")) {
				if (CenterSpawn.isInSpawn(ec.getLocation())) {
					if (!p.hasPermission("hessentials.gate.break")) {
						ec.setInvulnerable(true);
						api.u.sendMessage(p, api.u.getPrefix() + "&c&oYou wish to destroy everyones way out? How dare you...");
						e.setCancelled(true);
					}
				}
			} else {
				if (heHook.getPlayerHook(p).pc.isInClaim(ec.getLocation())) {
					if (!p.hasPermission("hessentials.gate.break")) {
						ec.setInvulnerable(true);
						api.u.sendMessage(p, api.u.getPrefix() + "&c&oYou wish to destroy everyones way out? How dare you...");
						e.setCancelled(true);
					}
				}
			}

		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPowerTool(PlayerInteractEvent e) {
		//Event listener for power tool command
		if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (e.getPlayer().hasPermission("hEssentials.staff.powertool")) {
				if (e.getItem() != null) {
					Player p = e.getPlayer();
					ItemStack holding = p.getInventory().getItemInMainHand();
					if (holding.getItemMeta().getLore() != null) {
						if (holding.getItemMeta().getLore().get(0).contains(ChatColor.LIGHT_PURPLE + "Current Command: ")) {
							String command = holding.getItemMeta().getLore().get(0).replace(ChatColor.LIGHT_PURPLE + "Current Command: /", "");
							Bukkit.dispatchCommand(p, command);
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onFrozenMove(PlayerMoveEvent e) {
		if (frozenDudes.contains(e.getPlayer().getUniqueId())) {
			e.setTo(e.getFrom());
			api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&c&oYou are frozen and unable to move!");
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onUtilUse(PlayerInteractEvent e) {
		List<Action> allActions = new ArrayList<>(Arrays.asList(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK));
		if (!inventoryOpen.get(e.getPlayer().getUniqueId())) {
			for (Action action : allActions) {
				if (e.getAction().equals(action)) {

					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

					if (item.hasItemMeta()) {
						if (item.getItemMeta().hasDisplayName()) {
							String itemDisplay = item.getItemMeta().getDisplayName();
							if (itemDisplay.equals(api.u.color("&7[&3&lVANISH&7]"))) {
								if (vanishPlayer.containsKey(e.getPlayer().getUniqueId())) {
									if (vanishPlayer.get(e.getPlayer().getUniqueId())) {
										// disable it
										vanishPlayer.put(e.getPlayer().getUniqueId(), false);
										for (Player a : Bukkit.getOnlinePlayers()) {
											a.showPlayer(HempfestEssentials.getInstance(), e.getPlayer());
										}
										api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&a&oYou are now visible!");
										ItemMeta meta = item.getItemMeta();
										meta.setLore(Arrays.asList(" ", api.lib.color("&oStatus: &c&nOff")));
										item.setType(Material.PURPLE_DYE);
										item.setItemMeta(meta);
									} else {
										//enable it
										vanishPlayer.put(e.getPlayer().getUniqueId(), true);
										for (Player a : Bukkit.getOnlinePlayers()) {
											a.hidePlayer(HempfestEssentials.getInstance(), e.getPlayer());
										}
										api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&3&oYou are now invisible!");
										ItemMeta meta = item.getItemMeta();
										meta.setLore(Arrays.asList(" ", api.lib.color("&oStatus: &a&nOn")));
										item.setType(Material.LIME_DYE);
										item.setItemMeta(meta);
										return;
									}
								} else {
									// enable it
									vanishPlayer.put(e.getPlayer().getUniqueId(), true);
									for (Player a : Bukkit.getOnlinePlayers()) {
										a.hidePlayer(HempfestEssentials.getInstance(), e.getPlayer());
									}
									api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&3&oYou are now invisible!");
									ItemMeta meta = item.getItemMeta();
									meta.setLore(Arrays.asList(" ", api.lib.color("&oStatus: &a&nOn")));
									item.setType(Material.LIME_DYE);
									item.setItemMeta(meta);
								}
							}
							if (itemDisplay.equals(api.u.color("&7[&c&lTELEPORT LIST&7]"))) {
								GuiLibrary gui = HempCore.guiManager(e.getPlayer());
								new InventoryTeleport(gui).open();
								e.setCancelled(true);
							}
							if (itemDisplay.equals(api.u.color("&7[&b&lFREEZE TARGET&7]"))) {
								Entity ent = api.u.getNearestEntityInSight(e.getPlayer(), 40);
								if (ent instanceof Player) {
									Player target = (Player) ent;
									if (target.isOp()) {
										return;
									}
									if (!frozenDudes.contains(target.getUniqueId())) {
										frozenDudes.add(target.getUniqueId());
										api.u.sendMessage(target, api.u.getPrefix() + "&c&oYou are now frozen!");
										api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&a&o" + target.getName() + " now frozen!");
									} else {
										frozenDudes.remove(target.getUniqueId());
										api.u.sendMessage(target, api.u.getPrefix() + "&b&oYou are now un-frozen!");
										api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&b&o" + target.getName() + " now un-frozen!");
									}
								}
								e.setCancelled(true);
							}

							if (itemDisplay.equals(api.u.color("&7[&6&lCONFIG&7]"))) {
								new InventoryConfiguration(HempCore.guiManager(e.getPlayer())).open();
								e.setCancelled(true);
								return;
							}
							if (itemDisplay.equals(api.u.color("&7[&5&oOPEN INV&7]"))) {
								Entity ent = api.u.getNearestEntityInSight(e.getPlayer(), 40);
								if (ent instanceof Player) {
									Player target = (Player) ent;
									api.u.openPlayerInventory(e.getPlayer(), target);
								}
								e.setCancelled(true);
								return;
							}

							if (itemDisplay.equals(api.u.color("&7[&a&lTELEPORT VISIBLE&7]"))) {
								Location loc = null;
								try {
									loc = e.getPlayer().getTargetBlockExact(100).getLocation();
								} catch (NullPointerException ex) {
									api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&c&oThat distance is too great! Unable to travel");
									e.setCancelled(true);
									return;
								}
								Location newLoc = new Location(e.getPlayer().getWorld(), loc.getX(), loc.getY(), loc.getZ(), e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());
								e.getPlayer().teleport(newLoc);
								api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&a&oTeleporting to position in line of sight");
								e.setCancelled(true);
							}
							if (itemDisplay.equals(api.u.color("&7[&4&lRANDOM TP&7]"))) {
								List<UUID> tempID = new ArrayList<>();
								for (Player a : Bukkit.getOnlinePlayers()) {
									if (!a.getName().equals(e.getPlayer().getName()))
										tempID.add(a.getUniqueId());
								}
								if (tempID.size() > 0) {
									int random = new Random().nextInt(tempID.size());
									UUID idtoGet = tempID.get(random);
									Player p = Bukkit.getPlayer(idtoGet);
									api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&a&oTeleporting to player &f&o" + p.getName());
									e.getPlayer().teleport(p);
								} else {
									api.u.sendMessage(e.getPlayer(), api.u.getPrefix() + "&c&oThere is no one to teleport to.");
								}
								e.setCancelled(true);
							}
						}
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

	@EventHandler(priority = EventPriority.HIGH)
	public void onSpawnItem(PlayerSwapHandItemsEvent e){
		if(staffGui.containsKey(e.getPlayer().getUniqueId())){
			if(staffGui.get(e.getPlayer().getUniqueId())){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onItemPickup(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (staffGui.containsKey(p.getUniqueId())) {
				if (staffGui.get(p.getUniqueId())) {
					e.setCancelled(true);
				}
			}
		}
	}

}
