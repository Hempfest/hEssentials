package org.spigotmc.hessentials.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.Strings;
import org.spigotmc.hessentials.util.Utils;

public class PlayerListener implements Listener{


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player)e.getPlayer();
		UUID uuid = p.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		if (!p.hasPlayedBefore()) {
			e.setJoinMessage(Strings.getFirstJoinMSG(p));
			Utils.npbMOTD(p);
			Utils.createPlayerConfig(p);
			return;
		}
		if (!pd.exists()) {
			Utils.createPlayerConfig(p);	
		}
		e.setJoinMessage(Strings.getJoinMSG(p));
		Utils.MOTD(p);
		Utils.matchIP(p);
		Utils.matchUsername(p);
		Utils.matchLTP(p);
		return;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = (Player)e.getPlayer();
		//UUID uuid = p.getUniqueId();
		//PlayerData pd = new PlayerData(uuid);
		e.setQuitMessage(Strings.getLeaveMSG(p));
	}
	

}
