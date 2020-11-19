package org.spigotmc.hessentials.commands.staff;


import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandGamemode extends BukkitCommand {

	List<String> arguments = new ArrayList<String>();
	heHook api = heHook.getHook();

	public CommandGamemode() {
		super("gamemode");
		setAliases(Arrays.asList("gm", "hgm"));
		setPermission("hessentials.staff.gamemode");
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			int length = args.length;
			if (length == 0) {
				sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <&csurvival/creative/adventure&f> &7<&8playerName&7> ");
				return true;
			}

			if (length == 1) {
				sendMessage(sender, api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel + " <&csurvival/creative/adventure&f> &7<&8playerName&7> ");
				return true;
			}

			if (length == 2) {
				Player target = Bukkit.getPlayer(args[1]);

				if (target != null) {
					if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						target.setGameMode(GameMode.CREATIVE);
						sendMessage(target, api.lib.getPrefix() + "Now in game-mode creative.");
						sendMessage(sender, api.lib.getPrefix() + target.getName() + "'s now in game-mode creative.");
						return true;
					} else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						target.setGameMode(GameMode.SURVIVAL);
						sendMessage(target, api.lib.getPrefix() + "Now in game-mode survival.");
						sendMessage(sender, api.lib.getPrefix() + target.getName() + "'s now in game-mode survival.");
						return true;
					} else if (args[0].equalsIgnoreCase("adventure")  || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
						target.setGameMode(GameMode.ADVENTURE);
						sendMessage(target, api.lib.getPrefix() + "Now in game-mode adventure.");
						sendMessage(sender, api.lib.getPrefix() + target.getName() + "'s now in game-mode adventure.");
						return true;
					} else if (args[0].equalsIgnoreCase("spectator")  || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
						target.setGameMode(GameMode.SPECTATOR);
						sendMessage(target, api.lib.getPrefix() + "Now in game-mode spectator.");
						sendMessage(sender, api.lib.getPrefix() + target.getName() + "'s now in game-mode spectator.");
						return true;
					}
					sendMessage(sender, api.lib.getPrefix() + "&4Unknown gamemode type " + '"' + args[0] + '"' + ".");
					sendMessage(sender, api.lib.getPrefix() + "Gamemodes: &7Survival, Creative, Adventure, Spectator");
				} else {
					sendMessage(sender, api.lib.getPrefix() + "&4Player " + '"' + args[1] + '"' + " not found.");
					return true;
				}
				return true;
			}
			return true;
		}
		Player p = (Player) sender;
		Utils u = new Utils();
		if (!p.hasPermission(this.getPermission())) {
			api.lib.sendNoPermission(p, this.getPermission());
			return true;
		}
		int length = args.length;
		if (length == 0) {
			if (Bukkit.getServer().getVersion().contains("1.15")) {
				u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " <&csurvival/creative/adventure&f> &7<&8playerName&7> ", "&f&oExample: &7/gamemode &eSurvival\n&f&oExample: &7/gamemode &eSurvival &aHempfest"));
			} else if (Bukkit.getServer().getVersion().contains("1.16")) {
				u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " <&csurvival/creative/adventure&f> &7<&8playerName&7> ", "&f&oExample: &7/gamemode &eSurvival\n&f&oExample: &7/gamemode &eSurvival &aHempfest"));
			}
			return true;
		}

		if (length == 1) {
			if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
				p.setGameMode(GameMode.CREATIVE);
				sendMessage(p, api.lib.getPrefix() + "Now in game-mode creative.");
				return true;
			}
			if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
				p.setGameMode(GameMode.SURVIVAL);
				sendMessage(p, api.lib.getPrefix() + "Now in game-mode survival.");
				return true;
			}
			if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
				p.setGameMode(GameMode.ADVENTURE);
				sendMessage(p, api.lib.getPrefix() + "Now in game-mode adventure.");
				return true;
			}
			if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
				p.setGameMode(GameMode.SPECTATOR);
				sendMessage(p, api.lib.getPrefix() + "Now in game-mode spectator.");
				return true;
			}
			sendMessage(p, api.lib.getPrefix() + "&4Unknown gamemode type " + '"' + args[0] + '"' + ".");
			sendMessage(p, api.lib.getPrefix() + "Gamemodes: &7Survival, Creative, Adventure");
			return true;
		}

		if (length == 2) {
			Player target = Bukkit.getPlayer(args[1]);

			if (target != null) {
				if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1") && p.hasPermission(this.getPermission() + ".other")) {
					target.setGameMode(GameMode.CREATIVE);
					sendMessage(target, api.lib.getPrefix() + "Now in game-mode creative.");
					sendMessage(p, api.lib.getPrefix() + target.getName() + "'s now in game-mode creative.");
					return true;
				} else if (args[0].equalsIgnoreCase("survival")  || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0") && p.hasPermission(this.getPermission() + ".other")) {
					target.setGameMode(GameMode.SURVIVAL);
					sendMessage(target, api.lib.getPrefix() + "Now in game-mode survival.");
					sendMessage(p, api.lib.getPrefix() + target.getName() + "'s now in game-mode survival.");
					return true;
				} else if (args[0].equalsIgnoreCase("adventure")  || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2") && p.hasPermission(this.getPermission() + ".other")) {
					target.setGameMode(GameMode.ADVENTURE);
					sendMessage(target, api.lib.getPrefix() + "Now in game-mode adventure.");
					sendMessage(p, api.lib.getPrefix() + target.getName() + "'s now in game-mode adventure.");
					return true;
				} else if (args[0].equalsIgnoreCase("spectator")  || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3") && p.hasPermission(this.getPermission() + ".other")) {
					target.setGameMode(GameMode.SPECTATOR);
					sendMessage(target, api.lib.getPrefix() + "Now in game-mode spectator.");
					sendMessage(p, api.lib.getPrefix() + target.getName() + "'s now in game-mode spectator.");
					return true;
				} else if (!p.hasPermission(this.getPermission() + ".other")) {
					api.lib.sendNoPermission(p, this.getPermission() + ".other");
					return true;
				}
				sendMessage(p, api.lib.getPrefix() + "&4Unknown gamemode type " + '"' + args[0] + '"' + ".");
				sendMessage(p, api.lib.getPrefix() + "Gamemodes: &7Survival, Creative, Adventure");
			} else {
				sendMessage(p, api.lib.getPrefix() + "&4Player " + '"' + args[1] + '"' + " not found.");
				return true;
			}
			return true;
		}

		sendMessage(p, api.lib.getPrefix() + "You entered the command wrong.");
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {

		if (arguments.isEmpty()) {
			arguments.add("creative");
			arguments.add("adventure");
			arguments.add("survival");
			arguments.add("spectator");
		}

		List<String> result = new ArrayList<String>();
		if (args.length == 1) {

			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		return null;
	}

}
