package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;
import java.util.Arrays;

public class CommandHeal extends BukkitCommand {
    heHook api = heHook.getHook();

    public CommandHeal() {
        super("Heal");
        setDescription("Primary command for hEssentials.");
        setAliases(Arrays.asList("hheal", "heal"));
        setPermission("hessentials.staff.heal");
    }

    public void sendMessage(CommandSender player, String message) {
        player.sendMessage(api.lib.color(message));
        return;
    }

    public boolean HealPlayer(String PlayerName){
        Player p = Bukkit.getPlayer(PlayerName);
        if (p != null){
            p.setHealth(20.0);
            p.setFoodLevel(20);
            p.setSaturation(5);
            return true;
        }
        return false;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int length = args.length;

        if (!(sender instanceof Player)) { //Sender Is Console
            if (length > 0) {
                HealPlayer(args[0]);
                sendMessage(sender, api.lib.getPrefix() + "&a&o" + args[0] + " Has Been Healed!");
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
            status = HealPlayer(p.getDisplayName());

            if (status){
                sendMessage(p, api.lib.getPrefix() + "&a&oYou Have Been Healed!");
            }else {
                sendMessage(p, api.lib.getPrefix() + "&a&oThere Was An Error!");
            }
            return true;
        } else {
            status = HealPlayer(args[0]);

            if (status){
                sendMessage(p, api.lib.getPrefix() + "&a&o" + args[0] + " Has Been Healed!");
            }else {
                sendMessage(p, api.lib.getPrefix() + "&a&o" + args[0] + " Has Not Been Found!");
            }
            return true;
        }

    }
}
