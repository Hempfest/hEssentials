package org.spigotmc.hessentials.commands.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.heHook;

public class CommandSpawnMob extends BukkitCommand {
    heHook api = heHook.getHook();

    public CommandSpawnMob() {
        super("spawnmob");
        setDescription("Primary staff command for hEssentials.");
        setAliases(Arrays.asList("mob", "hmob", "hspawnmob"));
        setPermission("hessentials.staff.spawnmob");
        setUsage("/spawnmob {mob} {location/player}");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> completes = new ArrayList<>();
        List<EntityType> entities = Arrays.asList(EntityType.values());
        if(args.length == 1){
             for (int i = 0; i < entities.size(); i++) {
                     if (entities.get(i).toString().toLowerCase().startsWith(args[0].toLowerCase())) {
                         completes.add(entities.get(i).toString().toLowerCase());
                 }
            }
        }
        return completes;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(commandSender instanceof Player){
            //If command sender is player
            Player p = (Player) commandSender;
            if(p.hasPermission(this.getPermission())){//If player has permission to execute this command
                Location spawnLoc = null;
                boolean hasLocation = false;
                if(args.length < 1 || args.length > 4){//If player did not include a mob to spawn
                    p.sendMessage(api.lib.getPrefix() + ChatColor.RED + "Usage: " + this.getUsage());
                }else if (args.length == 1){ //If player included a mob to spawn on themselves using (/mob creeper)
                    spawnLoc = p.getLocation();
                    hasLocation = true;
                }else if(args.length == 2){ //If player included a person to spawn the mob onto using (/mob creeper {playerName})
                    if(Bukkit.getPlayer(args[1]) != null){spawnLoc = Bukkit.getPlayer(args[1]).getLocation(); hasLocation = true;}else{p.sendMessage(api.lib.color(api.lib.getPrefix() + ChatColor.RED + args[1] + " is not online!")); return false;}
                    //Checks if player in seconds argument exists online and if not, returns false and sends error message
                }else if(args.length == 4){//If player included a location to spawn mob using (/mob creeper {x} {y} {z})
                    if(api.u.getLocationFromCommandArguments(p, args, 1) != null){
                        spawnLoc = api.u.getLocationFromCommandArguments(p, args, 1);
                        hasLocation = true;
                        //Checks if provided location exists or not
                    }else{
                        p.sendMessage(api.lib.color(api.lib.getPrefix() + ChatColor.RED + "'" + args[1] + ", " + args[2] + ", " + args[3] + "' is not a location!"));
                        return false;
                    }
                }
                if(hasLocation){
                    if(api.u.spawnMobs(p, new ArrayList<>(Arrays.asList(args[0].split(","))), spawnLoc)) {
                        String mobStr = "mob";
                        if(Arrays.asList(args[0].split(",")).size() > 1){
                            mobStr = "mobs";
                        }
                        api.u.sendMessage(p, api.lib.getPrefix() + ChatColor.GREEN + "Successfully spawned " + Arrays.asList(args[0].split(",")).size() + " " + mobStr + "!");
                    }
                }
            }else{//Player doesn't have permission
                api.u.sendNoPermission(p, this.getPermission());
            }
        }else{//If command sender is console
            commandSender.sendMessage("You must run this command as a player!");
        }
        return false;
    }

}

