package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;
import java.util.Arrays;

public class CommandFeed extends BukkitCommand {
    heHook api = heHook.getHook();

    public CommandFeed() {
        super("Feed");
        setDescription("Primary command for hEssentials.");
        setAliases(Arrays.asList("hfeed"));
        setPermission("hessentials.staff.feed");
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    public boolean FeedPlayer(String PlayerName,boolean HideNotifiyer){
        Player p = Bukkit.getPlayer(PlayerName);
        if (p != null){
            p.setHealth(20.0);
            p.setFoodLevel(15);
            p.setSaturation(5);
            if (!HideNotifiyer){
                sendMessage(p, api.lib.getPrefix() + "&a&oYou Have Been Feed!.");
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int length = args.length;

        if (!(sender instanceof Player)) { //Sender Is Console
            if (length > 0) {
                if (length == 2){
                    FeedPlayer(args[0],Boolean.parseBoolean(args[1]));
                }else {
                    FeedPlayer(args[0],true);
                }

                sendMessage(sender, api.lib.getPrefix() + "&a&o" + args[0] + " Has Been Feed!");
                return true;
            }
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission(this.getPermission())) {
            api.lib.sendNoPermission(p, this.getPermission());
            return true;
        }

        boolean status;
        if (length == 0){
            status = FeedPlayer(args[0],Boolean.parseBoolean(p.getDisplayName()));

            if (status){
                sendMessage(p, api.lib.getPrefix() + "&a&o You Have Been Feed!");
            }else {
                sendMessage(p, api.lib.getPrefix() + "&a&o There Was An Error!");
            }
        }
        else if (length > 0) {
            if (length == 2){
                status = FeedPlayer(args[0],Boolean.parseBoolean(args[1]));
            }else {
                status = FeedPlayer(args[0],false);
            }

            if (status){
                sendMessage(p, api.lib.getPrefix() + "&a&o" + args[0] + " Has Been Feed!");
            }else {
                sendMessage(p, api.lib.getPrefix() + "&a&o" + args[0] + " Has Not Been Found!");
            }
            return true;
        }

        sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
        return true;
    }
}
