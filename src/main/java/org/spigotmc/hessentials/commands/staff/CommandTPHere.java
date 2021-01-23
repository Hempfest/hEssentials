package org.spigotmc.hessentials.commands.staff;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

import java.util.Arrays;

public class CommandTPHere extends BukkitCommand {

    heHook api = heHook.getHook();

    public CommandTPHere() {
        super("tphere");
        setDescription("Primary command for hEssentials.");
        setAliases(Arrays.asList("hteleporthere", "htphere", "tphere"));
        setPermission("hessentials.staff.teleporthere");
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            // /tp - tp player to player
            int length = args.length;
            if (length == 0) {
                sendMessage(sender, api.lib.getPrefix() + "/" + commandLabel + " <player1>");
                return true;
            }
            if (length == 1) {
                Player target1 = Bukkit.getPlayer(args[0]);
                if(target1 == sender){
                    sendMessage(sender, api.lib.getPrefix() + " - &c&oYou can't teleport yourself.");
                    return true;
                }
                if (target1 == null) {
                    sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " - &c&oPlayer not found.");
                    return true;
                }
                Player target2 = (Player) sender;
                Location t2loc = target2.getLocation();
                target1.teleport(t2loc);
                sendMessage(sender, api.lib.getPrefix() + "Teleporting player " + '"' + target1.getName() + '"' + " to you.");
                return true;
            }
            sendMessage(sender, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
            return true;
        }
        return false;
    }
}
