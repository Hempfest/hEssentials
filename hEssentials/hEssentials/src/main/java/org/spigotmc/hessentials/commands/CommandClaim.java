package org.spigotmc.hessentials.commands;

import com.youtube.hempfest.hempcore.HempCore;
import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import com.youtube.hempfest.hempcore.gui.GuiLibrary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.gui.claim.InventoryClaimMenu;
import org.spigotmc.hessentials.listener.Claim;
import org.spigotmc.hessentials.util.heHook;

public class CommandClaim extends BukkitCommand {

	heHook api = heHook.getHook();

	public CommandClaim() {
		super("claim");
		setDescription("Primary command for hEssentials.");
		setAliases(Arrays.asList("hclaim", "hc"));
		setPermission("hessentials.claim");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}


	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Claiming)");
			return true;
		}

		final Player p = (Player) sender;
		heHook api = heHook.getHook();
		int length = args.length;
		if (length == 0) {
			if (!p.hasPermission(this.getPermission())) {
				api.lib.sendNoPermission(p, this.getPermission());
				return true;
			}
			GuiLibrary gui = HempCore.guiManager(p);
			new InventoryClaimMenu(gui).open();
			List<String> menu = new ArrayList<String>();

			menu.add("/claim &7<&aset&7> &7<&8claimName&7> &f- Claim a chunk of land.\n");
			menu.add("/claim &7<&aautoclaim&7> &f- Toggle auto claiming.\n");
			menu.add("/claim &7<&cdelete&7> &7<&8claimName&7> &f- Unclaim a chunk of land.\n");
			menu.add("/claim &7<&eadduser&7> &7<&8claimName&7> &f- Give a user permission to your land.\n");
			menu.add("/claim &7<&cremoveuser&7> &7<&8claimName&7> &f- Revoke permission to your land from a user.\n");
			if (p.hasPermission(this.getPermission() + ".teleport")) {
				menu.add("/claim &7<&ffind&7> &7<&8claimName&7> &f- Locate a chunk you own.\n");
			}
			if (p.hasPermission(this.getPermission() + ".teleport")) {
				menu.add("/claim &7<&6goto&7> &7<&8claimName&7> &f- Teleport to an owned chunk.\n");
			}
			sendMessage(p, api.lib.color(api.lib.getPrefix() + "Private land claiming.\n " + menu.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "")));
			if (Bukkit.getServer().getVersion().contains("1.15")) {
				api.u.sendComponent(p, Text_R2.textRunnable("&7Claim List: &7[", "&b&oClick Here", "&7]", api.lib.getPrefix() + "&7Click me to show your claim list", "claim list"));
			} else if (Bukkit.getServer().getVersion().contains("1.16")) {
				api.u.sendComponent(p, new Text().textRunnable("&7Claim List: &7[", "&b&oClick Here", "&7]", api.lib.getPrefix() + "&7Click me to show your claim list", "claim list"));
			}
			return true;

		}
				if (length == 1) {
					if (args[0].equalsIgnoreCase("autoclaim")) {
						if (!p.hasPermission(this.getPermission() + ".autoclaim")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".autoclaim");
							return true;
						}
						if (!api.u.hud.containsKey(p.getName())) {
							api.u.hud.put(p.getName(), true);
							sendMessage(p, api.u.getPrefix() + "&a&oNow auto-claiming. Walk to claim land.");
						} else {
							if (api.u.hud.get(p.getName())) {
								api.u.hud.put(p.getName(), false);
								sendMessage(p, api.u.getPrefix() + "&c&oNo longer auto-claiming.");
								return true;
							}
							api.u.hud.put(p.getName(), true);
							sendMessage(p, api.u.getPrefix() + "&a&oNow auto-claiming. Walk to claim land.");
							return true;
						}
						// being claiming automatically
						return true;
					}
					if (args[0].equalsIgnoreCase("find")) {
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&efind&7> &7<&8claimName&7> ", "&f&oExample: &7/claim find &eSurvival"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&efind&7> &7<&8claimName&7> ", "&f&oExample: &7/claim find &eSurvival"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
						if (!p.hasPermission(this.getPermission() + ".set")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".set");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&aset&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&aset&7> &7<&8claimName&7> ", "&f&oExample: &7/claim set &eSurvival"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("del")) {
						if (!p.hasPermission(this.getPermission() + ".delete")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".delete");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim delete &cSurvival"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&cdelete&7> &7<&8claimName&7> ", "&f&oExample: &7/claim delete &cSurvival"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("goto") || args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("tp")) {
						if (!p.hasPermission(this.getPermission() + ".teleport")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".teleport");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&6goto&7> &7<&8claimName&7> ", "&f&oExample: &7/claim goto &eSurvival"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&6goto&7> &7<&8claimName&7> ", "&f&oExample: &7/claim goto &eSurvival"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
						Claim claim = new Claim(p);
						if (!p.hasPermission(this.getPermission() + ".list")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".list");
							return true;
						}
						claim.list();
						return true;
					}
					//unknown usage
					return true;
				}
				if (length == 2) {
					Claim claim = new Claim(p, args[1], null);
					if (args[0].equalsIgnoreCase("find")) {
						if (!p.hasPermission(this.getPermission() + ".locate")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".locate");
							return true;
						}
						if (claim.chunkNull()) {
							sendMessage(p, api.lib.getPrefix() + "&7&oClaim not found.");
							return true;
						}
						if (!heHook.getPlayerHook(p).pc.getClaimList(p).contains(args[1])) {
							sendMessage(p, api.lib.getPrefix() + "&7&oClaim not found.");
							return true;
						}
						claim.connectSpace(p);
						claim.StopTimer();
						return true;
					}
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s")) {
						if (!p.hasPermission(this.getPermission() + ".set")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".set");
							return true;
						}
						if (claim.claimList().contains(args[1])) {
							api.lib.sendNoPermission(p, "Land by the name of " + '"' + args[1] + '"' + " already created.");
							return true;
						}
						claim.set();
						return true;
					}
					if (args[0].equalsIgnoreCase("users")) {
						sendMessage(p, api.lib.getPrefix() + args[1] + " User-List: &e&o" + claim.getUserList(p, args[1]));
						return true;
					}
					if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("del")) {
						if (!p.hasPermission(this.getPermission() + ".delete")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".delete");
							return true;
						}
						claim.delete();
						return true;
					}
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&eadduser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim adduser &eHempfest"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						sendMessage(p, "&7You need to specify a name.");
						if (Bukkit.getServer().getVersion().contains("1.15")) {
							api.u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest"));
						} else if (Bukkit.getServer().getVersion().contains("1.16")) {
							api.u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&c&oremoveuser&7> &7<&8claimName&7> &7<&8playerName&7> ", "&7/claim removeuser &c&oHempfest"));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("goto") || args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("tp")) {
						if (!p.hasPermission(this.getPermission() + ".teleport")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".teleport");
							return true;
						}
						claim.teleport();
						return true;
					}
				}
				if (length == 3) {
					String claimName = args[1];
					String target = args[2];
					Claim claim = new Claim(p, claimName, target);
					if (args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("add")) {
						if (!p.hasPermission(this.getPermission() + ".adduser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".adduser");
							return true;
						}
						if (target.equals(p.getName())) {
							sendMessage(p, api.lib.getPrefix() + "&c&oYou cannot add yourself as a user.");
							return true;
						}
						if (claim.getUserList(p, claim.getClaimName(p.getLocation())).contains(target)) {
							if (claim.isClaimOwner()) {
								Bukkit.dispatchCommand(p, "claim removeuser " + claimName + " " + target);
							}
							return true;
						}
						claim.add();
						return true;
					}
					if (args[0].equalsIgnoreCase("removeuser") || args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("ru")) {
						if (!p.hasPermission(this.getPermission() + ".removeuser")) {
							api.lib.sendNoPermission(p, this.getPermission() + ".removeuser");
							return true;
						}
						if (target.equals(p.getName())) {
							sendMessage(p, api.lib.getPrefix() + "&c&oYou cannot add yourself as a user.");
							return true;
						}
						claim.take();
						return true;
					}
				}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

}
