package org.spigotmc.hessentials.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.hessentials.util.heHook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandPowerTool extends BukkitCommand implements Listener{
    private ArrayList<ItemStack> powerTools = new ArrayList<>();
    heHook api = heHook.getHook();

    public CommandPowerTool() {
        super("powertool");
        setDescription("Primary staff command for hEssentials.");
        setAliases(Arrays.asList("hpowertool", "pt", "powertool"));
        setPermission("hessentials.staff.powertool");
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (commandSender.hasPermission("hessentials.staff.powertool")) {
                if (!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                    if (args.length > 0) {
                        StringBuilder powerCommand = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            if (i == 0 && !args[i].contains("/")) { args[i] = "/" + args[i]; } //If the first argument contains a / then it will remove it so that the command will work with the Disbatch Command method
                            powerCommand.append(args[i] + " ");
                        }
                        ItemStack stack = p.getInventory().getItemInMainHand();
                        ItemMeta meta = stack.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        if(meta.getLore() != null && meta.getLore().get(0).contains(ChatColor.LIGHT_PURPLE + "Current Command: ")){meta.getLore().remove(0); meta.setLore(lore);}
                        lore.add(ChatColor.LIGHT_PURPLE + "Current Command: " + powerCommand.toString().trim());
                        if(meta.getLore() != null){lore.addAll(meta.getLore());};
                        meta.setLore(lore);
                        stack.setItemMeta(meta);
                        p.sendMessage(api.lib.getPrefix() + ChatColor.GREEN + "The command for the item you're holding is now set to '" + powerCommand.toString().trim() + "'!");
                        return true;
                    } else { //No arguments so they are clearing the command from the item
                        ItemStack holding = p.getInventory().getItemInMainHand();
                        if(holding.getItemMeta().getLore()!=null) {
                            if (holding.getItemMeta().getLore().get(0).contains(ChatColor.LIGHT_PURPLE + "Current Command: ")) {
                                ItemStack stack = p.getInventory().getItemInMainHand();
                                ItemMeta meta = stack.getItemMeta();
                                List<String> lore = meta.getLore();
                                lore.remove(0);
                                meta.setLore(lore);
                                stack.setItemMeta(meta);
                                p.sendMessage(api.lib.getPrefix() + ChatColor.GREEN + "You have removed the command from this item!");
                                return true;
                            }else{
                                p.sendMessage(api.lib.getPrefix() + ChatColor.RED + "There is no command assigned to this item! To assign a command, use /" + s + " {command}");
                            }
                        }else{
                            p.sendMessage(api.lib.getPrefix() + ChatColor.RED + "There is no command assigned to this item! To assign a command, use /" + s + " {command}");
                        }
                    }
                } else {
                    p.sendMessage(api.lib.getPrefix() + ChatColor.RED + "You must hold an item to assign a power tool!");
                    return false;
                }
            }else{ //If the player does not have permission
                api.u.sendNoPermission(p, "hessentials.staff.powertool");
            }
        }else{
            Bukkit.getConsoleSender().sendMessage(api.lib.color(api.lib.getPrefix() + ChatColor.RED + "This command can only be executed by players!"));
        }
        return false;
    }
}
