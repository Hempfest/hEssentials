package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class CommandGod extends BukkitCommand {
    heHook api = heHook.getHook();
    public static ArrayList<UUID> GodPlayers = new ArrayList<UUID>();

    public CommandGod() {
        super("God");
        setDescription("Primary command for hEssentials.");
        setAliases(Arrays.asList("hgod"));
        setPermission("hessentials.staff.god");
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    public void ToggleGod(UUID UUidToToggle){
        if (GodPlayers.contains(UUidToToggle)){
            GodPlayers.remove(UUidToToggle);
        }else {
            GodPlayers.add(UUidToToggle);
        }
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int length = args.length;
        Player p;
        UUID PlayerUUID;

        if (!(sender instanceof Player)) { //Sender Is Console
            if (length > 0) {
                p = Bukkit.getPlayer(args[0]);
                PlayerUUID = p.getUniqueId();

                ToggleGod(PlayerUUID);
                sendMessage(sender, api.lib.getPrefix() + "&a&o" + args[0] + "'s God Mode Was Toggled!");
            }return true;
        }

        if (length > 0){ //Sender Is Player
            p = Bukkit.getPlayer(args[0]);
        }else {
            p = ((Player) sender);
        }
        PlayerUUID = p.getUniqueId();
        ToggleGod(PlayerUUID);

        if (length > 0){
            sendMessage(sender, api.lib.getPrefix() + "&a&o" + args[0] + "'s God Mode Was Toggled!");
        }else {
            sendMessage(sender, api.lib.getPrefix() + "&a&oGod Mode Was Toggled!");
        }

        return true;
    }
}
