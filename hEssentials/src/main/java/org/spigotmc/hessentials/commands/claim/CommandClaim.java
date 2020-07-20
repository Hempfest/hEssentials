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
		setAliases(Arrays.asList("hclaim", "hc"));
		setPermission("hessentials.claim");
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
					if (!p.hasPermission(this.getPermission())) {
						Strings.sendNoPermission(p, this.getPermission());
						return true;
					}
					Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aset&7/&cdelete&7/&eadduser&7/&6goto&7/&5list&7> ", Strings.getPrefix() + "&7Type a sub command to learn how to use it.");
					Message.textRunnable(p, "&7Claim List: &7[", "&b&oClick Here", "&7]", Strings.getPrefix() + "&7Click me to show your claim list", "claim list");
					return true;
					
				}
				if (length == 1) {
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
						if (!p.hasPermission(this.getPermission() + ".create")) {
							Strings.sendNoPermission(p, this.getPermission() + ".create");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&aset&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival");
						return true;
					}
					if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("del")) {
						if (!p.hasPermission(this.getPermission() + ".delete")) {
							Strings.sendNoPermission(p, this.getPermission() + ".delete");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim delete &cSurvival");
						return true;
					}
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest");
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest");
						return true;
					}
					if (args[0].equalsIgnoreCase("goto") || args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("tp")) {
						if (!p.hasPermission(this.getPermission() + ".teleport")) {
							Strings.sendNoPermission(p, this.getPermission() + ".teleport");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&6goto&7> &7<&8claimName&7> ", "&f&oExample: &7/claim goto &eSurvival");
						return true;
					}
					if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
						if (!p.hasPermission(this.getPermission() + ".list")) {
							Strings.sendNoPermission(p, this.getPermission() + ".list");
							return true;
						}
						sendMessage(p, Strings.getInvalidUsage() + commandLabel + " goto &7<&f&oclaimName&7>");
						sendMessage(p, "&7" + ClaimUtil.getClaimList(p).toString());
						return true;
					}
					//unknown usage
					return true;
				}
				if (length == 2) {
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
						if (!p.hasPermission(this.getPermission() + ".create")) {
							Strings.sendNoPermission(p, this.getPermission() + ".create");
							return true;
						}
						ClaimUtil.saveChunk(p, args[1]);
						return true;
					}
					if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("del")) {
						if (!p.hasPermission(this.getPermission() + ".delete")) {
							Strings.sendNoPermission(p, this.getPermission() + ".delete");
							return true;
						}
						ClaimUtil.deleteChunk(p, args[1]);
						return true;
					}
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest");
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						Message.textHoverable(p, Strings.getPrefix() + Strings.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest");
						return true;
					}
					if (args[0].equalsIgnoreCase("goto") || args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("tp")) {
						if (!p.hasPermission(this.getPermission() + ".teleport")) {
							Strings.sendNoPermission(p, this.getPermission() + ".teleport");
							return true;
						}
						ClaimUtil.teleportChunk(p, args[1]);
						return true;
					}
				}
				if (length == 3) {
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						ClaimUtil.addClaimUser(p, args[1], args[2]);
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							Strings.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						ClaimUtil.removeClaimUser(p, args[1], args[2]);
						return true;
					}
				}

			

		sendMessage(p, Strings.getPrefix() + Strings.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
