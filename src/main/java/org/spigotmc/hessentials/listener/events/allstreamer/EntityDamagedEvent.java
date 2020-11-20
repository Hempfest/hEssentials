package org.spigotmc.hessentials.listener.events.allstreamer;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.spigotmc.hessentials.commands.staff.CommandGod;


public class EntityDamagedEvent implements Listener {
    @EventHandler
    public void EntityDamagedEvent(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){ //If Damaged Is Player
            Player p = (Player) event.getEntity(); //Player Object so This Event Can later Be Expanded
            UUID uuid = p.getUniqueId(); //Player UUID
            if (CommandGod.GodPlayers.contains(uuid)){
                event.setCancelled(true);
            }
        }
    }
}
