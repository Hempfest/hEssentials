package org.spigotmc.hessentials.commands.claim;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.variables.Message;
import org.spigotmc.hessentials.util.variables.Strings;

public class CommandClaim extends BukkitCommand {

	public CommandClaim() {
		super("claim");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hclaim"));
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, Strings.getPrefix() + "This is a player only command.");
			return true;
		}
		
		final Player p = (Player) sender;
		int length = args.length;
				if (length == 0) {
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aset/&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival\n&f&oExample: &7/claim delete &eSurvival");
					sendMessage(p, ClaimUtil.getClaimList(p).toString());
					return true;
					
				}
				if (length == 1) {
					if (args[0].equalsIgnoreCase("set")) {
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aset/&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival\n&f&oExample: &7/claim delete &eSurvival");
						return true;
					}
					if (args[0].equalsIgnoreCase("delete")) {
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aset/&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival\n&f&oExample: &7/claim delete &eSurvival");
						return true;
					}
					//unknown usage
					return true;
				}
				if (length == 2) {
					if (args[0].equalsIgnoreCase("set")) {
						ClaimUtil.saveChunk(p, args[1]);
						
						return true;
					}
					if (args[0].equalsIgnoreCase("delete")) {
						ClaimUtil.deleteChunk(p, args[1]);
						
						return true;
					}
					if (args[0].equalsIgnoreCase("adduser")) {
						// needs more args
						return true;
					}
					if (args[0].equalsIgnoreCase("goto")) {
						ClaimUtil.teleportChunk(p, args[1]);
						return true;
					}
				}
				if (length == 3) {
					if (args[0].equalsIgnoreCase("adduser")) {
						ClaimUtil.addClaimUser(p, args[1], args[2]);
						return true;
					}
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
