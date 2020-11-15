package org.spigotmc.hessentials.listener.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.spigotmc.hessentials.commands.staff.CommandGod;

import java.util.UUID;


public class EntityDamagedEvent implements Listener {
    @EventHandler
    public void EntityDamagedEvent(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){ //If Damaged Is Player
            Player p = (Player) event.getEntity(); //Player Object so This Event Can later Be Expanded
            UUID uuid = p.getUniqueId(); //Player UUID

            System.out.println("Player Was Dammaged With Id:" + uuid.toString() + "");
            System.out.println(CommandGod.GodPlayers);
            if (CommandGod.GodPlayers.contains(uuid)){
                event.setCancelled(true);
            }
        }
    }
}
