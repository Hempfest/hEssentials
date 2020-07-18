package org.spigotmc.hessentials.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.spigotmc.hessentials.commands.claim.ClaimUtil;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.Checks;
import org.spigotmc.hessentials.util.Message;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class PlayerListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		UUID uuid = p.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		if (!p.hasPlayedBefore()) {
			Utils.hud.put(p.getName(), Boolean.valueOf(true));
			e.setJoinMessage(Strings.getFirstJoinMSG(p));
			Utils.npbMOTD(p);
			Utils.createPlayerConfig(p);
			ClaimUtil.updateClaimUser(p);
			if (Checks.canUseScoreboard()) {
				Utils.createScoreboard(p);
				Utils.animateScoreTitle(p);
			}
			return;
		}
		if (!pd.exists()) {
			Utils.createPlayerConfig(p);
		}
		ClaimUtil.updateClaimUser(p);
		Utils.hud.put(p.getName(), Boolean.valueOf(true));
		e.setJoinMessage(Strings.getJoinMSG(p));
		Utils.MOTD(p);
		Utils.matchIP(p);
		Utils.matchUsername(p);
		Utils.matchLTP(p);
		if (Checks.canUseScoreboard()) {
			Utils.createScoreboard(p);
			Utils.animateScoreTitle(p);
		}

		return;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		// UUID uuid = p.getUniqueId();
		// PlayerData pd = new PlayerData(uuid);
		e.setQuitMessage(Strings.getLeaveMSG(p));
		if (Checks.canUseScoreboard()) {
		Utils.removeScoreboard(p);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent e) {
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

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		Config message = new Config(Strings.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		if (Utils.Chat_MUTED) {
			if (e.getPlayer().hasPermission("hessentials.staff.mutechat")) {
				for (Player a : Bukkit.getOnlinePlayers())
					Message.textHoverable(a, "&2[", "&4&l^",
							"&2] " + e.getPlayer().getDisplayName() + " &7&o" + e.getMessage(),
							Strings.getPrefix() + "&a&oThis player has permission to speak right now.");
				e.setCancelled(true);
			} else {
				e.getPlayer().sendMessage(Strings.color(m.getString("Messages.Player-Responses.Cannot-Speak")));
			}
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onMOTD(ServerListPingEvent e) {
		Config mo = new Config("MOTD");
		FileConfiguration m = mo.getConfig();
		String motd = Strings.color(m.getString("MOTD-Server-List").replaceAll("%prefix%", Strings.getPrefix()));
		e.setMotd(motd);
		e.setMaxPlayers(m.getInt("Server-Max-Players"));
	}

}
