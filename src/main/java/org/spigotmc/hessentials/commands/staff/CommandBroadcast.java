package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.spigotmc.hessentials.util.heHook;

public class CommandBroadcast extends BukkitCommand {
    heHook api = heHook.getHook();

    public CommandBroadcast() {
        super("broadcast");
        setDescription("Primary staff command for hEssentials.");
        setAliases(Arrays.asList("hbroadcast", "bc", "hbc"));
        setPermission("hessentials.staff.broadcast");
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender.hasPermission(this.getPermission())) {
            int length = args.length;
            if (length == 0) {
                commandSender.sendMessage(api.lib.color("&#b54941Please include a message to broadcast."));
            } else {
                StringBuilder msg = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    msg.append(args[i]).append(" ");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(api.lib.color("&#00bdca&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
                Bukkit.broadcastMessage(api.lib.color(api.lib.getPrefix() + "&#ffe712&l" + msg.toString().trim()));
                Bukkit.broadcastMessage(api.lib.color("&#00bdca&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
                Bukkit.broadcastMessage("");
                return true;
            }
        }
        return false;
    }
}
