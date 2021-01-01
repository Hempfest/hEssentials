package org.spigotmc.hessentials.commands.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.listener.EventListener;
import org.spigotmc.hessentials.util.heHook;

public class CommandRename extends BukkitCommand {
    heHook api = heHook.getHook();

    public CommandRename() {
        super("rename");
        setDescription("Primary command for hEssentials.");
        setAliases(Arrays.asList("hrename", "ren"));
        setPermission("hessentials.staff.rename");
        }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        if(!(commandSender instanceof Player)){ //If commandSender is console, not player
            if(args.length==2){
                if(Bukkit.getPlayer(args[0]) != null){//If specified player exists
                    Player p = Bukkit.getPlayer(args[0]);
                    if (!EventListener.vanishPlayer.containsKey(p.getUniqueId()) || EventListener.vanishPlayer.containsKey(p.getUniqueId()) && !EventListener.vanishPlayer.get(p.getUniqueId())) {
                        if (!renameHoldingItem(p, args[1])) {
                            api.u.sendMessage(commandSender, api.lib.getPrefix() + args[0] + " is not holding an item!");
                            return true;
                        } else {//If rename was successfull
                            api.u.sendMessage(commandSender, api.lib.getPrefix() + args[0] + "'s " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to " + args[1] + "!");
                            api.u.sendMessage(p, api.lib.getPrefix() + "An admin renamed your " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to " + args[1] + ".");
                            return true;
                        }
                    }
                }
                api.u.sendMessage(commandSender, api.lib.getPrefix() + "Player: " + args[0] + " is not online!");
            }else{
                api.u.sendMessage(commandSender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <player> <new_item_name>");
            }
        }else{//IF COMMAND SENDER IS A PLAYER
            if(commandSender.hasPermission(this.getPermission())){
                if(args.length == 1){ //IF THEY ARE RENAMING THEIR OWN ITEM
                    Player p = (Player) commandSender;
                    if (!renameHoldingItem(p, args[0])) {
                        api.u.sendMessage(p, api.u.getPrefix() + "You're not holding an item!");
                        return true;
                    }else{//If rename was successfull
                            api.u.sendMessage(p, api.lib.getPrefix() + "Your renamed your " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to '" + args[0] + "&f'.");
                        return true;
                    }
                }else if(args.length == 2) { //IF THEY ARE RENAMING SOMEONE ELSE'S ITEM
                    if(Bukkit.getPlayer(args[0]) != null){//If specified player exists
                        Player p = Bukkit.getPlayer(args[0]);
                        if (!EventListener.vanishPlayer.containsKey(p.getUniqueId()) || EventListener.vanishPlayer.containsKey(p.getUniqueId()) && !EventListener.vanishPlayer.get(p.getUniqueId())) {
                            if (!renameHoldingItem(p, args[1])) {
                                api.u.sendMessage(commandSender, api.lib.getPrefix() + args[0] + " is not holding an item!");
                                return true;
                            } else {//If rename was successfull
                                if (commandSender != p) { //specified player IS NOT command sender
                                    api.u.sendMessage(commandSender, api.lib.getPrefix() + args[0] + "'s " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to " + args[1] + "!");
                                    api.u.sendMessage(p, api.lib.getPrefix() + "An admin renamed your " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to '" + args[1] + "&f'.");
                                } else { //specified player IS command sender
                                    api.u.sendMessage(p, api.lib.getPrefix() + "You renamed your " + p.getInventory().getItemInMainHand().getType().name().toLowerCase() + " to '" + args[1] + "&f'.");
                                }
                            }
                        }else api.u.sendMessage(commandSender, api.lib.getPrefix() + "Player: " + args[0] + " is not online!");
                    }else api.u.sendMessage(commandSender, api.lib.getPrefix() + "Player: " + args[0] + " is not online!");
                }else api.u.sendMessage(commandSender,api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <player> <new_item_name>");
            }else api.u.sendNoPermission((Player) commandSender, this.getPermission());
        }
        return false;
    }


    private boolean renameHoldingItem(Player p, String n) {
        ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
        if (m != null) {
            m.setDisplayName(api.u.color(n));
            p.getInventory().getItemInMainHand().setItemMeta(m);
            return true;
        }
        return false;
    }
}