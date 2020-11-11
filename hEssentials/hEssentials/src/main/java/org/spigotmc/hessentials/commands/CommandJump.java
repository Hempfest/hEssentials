package org.spigotmc.hessentials.commands;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.listener.Request;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.heHook;

public class CommandJump extends BukkitCommand {

	List<String> arguments = new ArrayList<String>();
	heHook api = heHook.getHook();

	public CommandJump() {
		super("jump");
		setAliases(Arrays.asList("tpa", "htpa"));
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, api.lib.getPrefix() + "This is a player only command. (Tpa)");
			return true;
		}

		final Player p = (Player) sender;
		Utils u = new Utils();
		int length = args.length;
		Request request = new Request(p);
		DataManager dm = new DataManager();
		final Config data = dm.requestData("Teleports");

		if (length == 0) {
			if (Bukkit.getServer().getVersion().contains("1.15")) {
				u.sendComponent(p, Text_R2.textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&aaccept&7,&cdeny&7> ", "&f&l|", " &7<&cplayerName&7>", "&f&oExample: &7/tpa &aAccept\n&f&oExample: &7/tpa &cDeny", "&f&oExample: &7/tpa &eHempfest"));
			} else if (Bukkit.getServer().getVersion().contains("1.16")) {
				u.sendComponent(p, new Text().textHoverable(api.lib.getPrefix() + api.lib.getInvalidUsage() + commandLabel, " &7<&aaccept&7,&cdeny&7> ", "&f&l|", " &7<&cplayerName&7>", "&f&oExample: &7/tpa &aAccept\n&f&oExample: &7/tpa &cDeny", "&f&oExample: &7/tpa &eHempfest"));
			}
			return true;
		}

		if (length == 1) {

			if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a")) {
				request.acceptRequest(data);
				return true;
			}

			if (args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("d")) {
				request.denyRequest(data);
				return true;
			}

			final Player other = Bukkit.getPlayer(args[0]);
			if (other == null) {
				sendMessage(p, ChatColor.RED + "No online player by the name of '" + args[0] + "' was found.");
				return true;
			}
			request.sendRequest(other, data);
			return true;
		}


		sendMessage(p, api.lib.getPrefix() + api.lib.color("Try " + '"' + "/hhelp" + '"' + " for help - Unknown sub-command."));
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		if (arguments.isEmpty()) {
			arguments.add("accept");
			arguments.add("deny");
			for (Player pl : Bukkit.getOnlinePlayers())
				arguments.add(pl.getName());
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
