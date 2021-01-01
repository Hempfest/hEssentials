package org.spigotmc.hessentials.listener.events.hempfest;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.spigotmc.hessentials.util.Claim;
import org.spigotmc.hessentials.util.heHook;

public class ClaimUpdateEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player p;

    private boolean cancelled;

    public ClaimUpdateEvent(Player p) {
        this.p = p;
    }

    private heHook papi() {
        return heHook.getPlayerHook(p);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Claim getClaim() {
        return new Claim(p, papi().pc.getClaimName(p.getLocation()), null);
    }

    public void runEvent() {

        papi().pc.updateClaimUser();
        if (heHook.getHook().u.hud.containsKey(p.getName())) {
            if (heHook.getHook().u.hud.get(p.getName())) {
                papi().pc.autoChunk(p);
            }
        }
        if (papi().pc.isInClaim(p.getLocation())) {
            if (papi().pc.getClaimName(p.getLocation()).equals(heHook.getHook().u.getClaimToLocate()) && heHook.getHook().u.getPlayerToTell().equals(p)) {
                Bukkit.getScheduler().cancelTask(heHook.getHook().u.stop);
                heHook.getHook().u.stop = 0;
                heHook.getHook().u.setClaimToLocate(null);
                p.spawnParticle(Particle.FIREWORKS_SPARK, p.getEyeLocation(), 5);
                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 6.0F, 1.0F);
                p.playEffect(EntityEffect.FIREWORK_EXPLODE);
                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 10.0F, 1.0F);
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10.0F, 1.0F);
                papi().u.sendMessage(p, papi().u.getPrefix() + "&a&oYou found the claim!");
            }
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
