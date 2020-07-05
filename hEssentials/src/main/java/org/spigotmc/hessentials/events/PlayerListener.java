package org.spigotmc.hessentials.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.Utils;

public class PlayerListener implements Listener{


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player)e.getPlayer();
		UUID uuid = p.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		if (!p.hasPlayedBefore()) {
			Utils.npbMOTD(p);
			Utils.createPlayerConfig(p);
			return;
		}
		if (!pd.exists()) {
			Utils.createPlayerConfig(p);	
		}
		Utils.MOTD(p);
		Utils.matchIP(p);
		Utils.matchUsername(p);
		return;
	}
	

}
