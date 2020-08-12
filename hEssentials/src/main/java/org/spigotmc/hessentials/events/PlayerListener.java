package org.spigotmc.hessentials.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.InventoryHolder;
import org.spigotmc.hessentials.commands.claim.ClaimUtil;
import org.spigotmc.hessentials.commands.economy.Economy;
import org.spigotmc.hessentials.commands.economy.EconomyData;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.gui.Menu;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Component;
import org.spigotmc.hessentials.util.variables.Strings;

import addon.chat.hessentials.GroupAPI;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		UUID uuid = p.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		EconomyData pl = new EconomyData(uuid);
		if (!p.hasPlayedBefore()) {
			Utils.hud.put(p.getName(), Boolean.valueOf(true));
			e.setJoinMessage(Strings.getFirstJoinMSG(p));
			Utils.createPlayerConfig(p);
			ClaimUtil.updateClaimUser(p);
			Economy.createPlayerData(p);
			if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
    			
    			GroupAPI chat = new GroupAPI();
    			chat.updateGroup(p);
    			if (!chat.hasSuffix(p)) {
    				pd.getConfig().set("SUFFIX", "Default");
    				pd.saveConfig();
    				return;
    			}
    		}
			return;
		}
		if (!pd.exists()) {
			Utils.createPlayerConfig(p);
		}
		if (!pl.exists()) {
			Economy.createPlayerData(p);
		}
		Utils.title_claim.put(p.getName(), false);
		Utils.title_claim2.put(p.getName(), false);
		Utils.title_claim3.put(p.getName(), false);
		Utils.title_claim4.put(p.getName(), false);
		ClaimUtil.updateClaimUser(p);
		Utils.hud.put(p.getName(), Boolean.valueOf(true));
		e.setJoinMessage(Strings.getJoinMSG(p));
		Utils.MOTD(p);
		return;
	}
	
	// GUI interact event
		@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
		public void onMenuClick(InventoryClickEvent e) {

			InventoryHolder holder = e.getInventory().getHolder();
			// If the inventoryholder of the inventory clicked on
			// is an instance of Menu, then gg. The reason that
			// an InventoryHolder can be a Menu is because our Menu
			// class implements InventoryHolder!!
			if (holder instanceof Menu) {
				e.setCancelled(true); // prevent them from fucking with the inventory
				if (e.getCurrentItem() == null) { // deal with null exceptions
					return;
				}
				// Since we know our inventoryholder is a menu, get the Menu Object representing
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
					Utils.openPlayerInventory(whoClicked, pInventory);
					return;
				}
			}

		}
		
		@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
		public void onBed(PlayerBedEnterEvent e) {
			Player p = e.getPlayer();
			Config voting = new Config("bed_voting");
			ConfigurationSection section = voting.getConfig().getConfigurationSection("Bed-Voting");
			if (Bukkit.getOnlinePlayers().size() <= 4 && !Utils.day(p)) {
				Bukkit.broadcastMessage(Strings.getPrefix() + "Too little players online. Making it day.");
				Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
				return;
			}
			if (Bukkit.getOnlinePlayers().size() > 4 && !Utils.day(p)) {
				if (section.getInt("Day-Voting") == 5 - 1) {
					Bukkit.broadcastMessage(Strings.getPrefix() + "5 players voted. Making it day.");
					Bukkit.getWorld(p.getLocation().getWorld().getName()).setTime(0L);
					section.set("Day-Voting", 0);
					voting.saveConfig();
					return;
				}
				section.set("Day-Voting", section.getInt("Day-Voting") + 1);
				Bukkit.broadcastMessage(Strings.getPrefix() + "Player " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " wants to make it day. (Lay down)");
				voting.saveConfig();
				return;
			}
			return;
		}
		
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		// UUID uuid = p.getUniqueId();
		// PlayerData pd = new PlayerData(uuid);
		e.setQuitMessage(Strings.getLeaveMSG(p));
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent e) {
		Config message = new Config(Strings.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		if (Utils.Chat_MUTED) {
			if (e.getPlayer().hasPermission("hessentials.staff.mutechat")) {
				for (Player a : Bukkit.getOnlinePlayers())
					Component.textHoverable(a, "&2[", "&4&l^",
							"&2] " + e.getPlayer().getDisplayName() + " &7&o" + e.getMessage(),
							Strings.getPrefix() + "&a&oThis player has permission to speak right now.");
				
			} else {
				e.getPlayer().sendMessage(Strings.color(m.getString("Messages.Player-Responses.Cannot-Speak")));
			}
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onMOTD(ServerListPingEvent e) {
		Config mo = new Config("Messages");
		FileConfiguration m = mo.getConfig();
		String motd = Strings.color(m.getString("MOTD-Server-List").replaceAll("%prefix%", Strings.getPrefix()));
		e.setMotd(motd.replaceAll("%next%", Strings.color("\n")));
		e.setMaxPlayers(m.getInt("Server-Max-Players"));
	}

}
