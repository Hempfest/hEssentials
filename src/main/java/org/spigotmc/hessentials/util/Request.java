package org.spigotmc.hessentials.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;

public class Request {

	heHook api = heHook.getHook();
	Utils u = new Utils();
	Player p;

	public Request(Player p) {
		this.p = p;
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	public void acceptRequest(final Config data) {
		List<String> requests = new ArrayList<>(data.getConfig().getStringList("Request-List." + p.getName()));
		if (!requests.isEmpty()) {
			String dest = requests.get(0);
			final Player destination = Bukkit.getPlayer(dest);
			if (destination != null) {
				Location loc = p.getLocation();

				if (!u.canWarp(destination)) {
					if (destination.hasPermission("hessentials.teleport.fast")) {
						destination.sendMessage(api.lib.getPrefix() + ChatColor.GREEN + " Acting swifly (With permisson)");
						destination.teleport(loc);
						requests.remove(requests.get(0));
						data.getConfig().set("Request-List." + p.getName(), requests);
						u.recieved.put(p.getName(), Boolean.valueOf(false));
						data.saveConfig();
						return;
					}

					destination.sendMessage(" ");
					sendMessage(destination, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
					sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
					destination.sendMessage(ChatColor.GRAY + "                         " + api.lib.getPrefix());
					destination.sendMessage(ChatColor.GRAY + "         You are being targeted by a " + ChatColor.RED + "Player");
					destination.sendMessage(ChatColor.GRAY + "                   Warp delayed " + ChatColor.RED + "10s" + ChatColor.GRAY + "            ");
					destination.sendMessage(" ");
					Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), () -> {

						destination.teleport(loc);
						requests.remove(requests.get(0));
						data.getConfig().set("Request-List." + p.getName(), requests);
						data.saveConfig();

					}, 200L);
					return;
				}
				if (u.canWarp(destination)) {
					destination.teleport(loc);
					u.recieved.put(p.getName(), Boolean.valueOf(false));
					sendMessage(destination, ChatColor.GREEN + "'" + p.getName() + "' has accepted your request!. Teleporting.");
					sendMessage(p, ChatColor.GREEN + "Request to teleport from '" + destination.getName() + "' has been accepted.");
					requests.remove(requests.get(0));
					data.getConfig().set("Request-List." + p.getName(), requests);
					data.saveConfig();
					return;
				}

			} else
				sendMessage(p, ChatColor.RED + "Seems the player whom sent the request has logged off.");
			u.recieved.put(p.getName(), Boolean.valueOf(false));
			List<String> reqs = new ArrayList<>();
			reqs.remove(getToGet());
			data.getConfig().set("Request-List." + p.getName(), reqs);
			data.saveConfig();
			return;
		} else
			sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
		return;
	}

	public void denyRequest(Config data) {
		List<String> requests = new ArrayList<>(data.getConfig().getStringList("Request-List." + p.getName()));
		if (!requests.isEmpty()) {
			Player destination = Bukkit.getPlayer(requests.get(0));
			if (destination != null) {
				u.recieved.put(p.getName(), Boolean.valueOf(false));
				sendMessage(destination, ChatColor.RED + "'" + p.getName() + "' has denied your request!");
				sendMessage(p, ChatColor.RED + "Request to teleport from '" + destination.getName() + "' has been denied.");
				requests.remove(requests.get(0));
				data.getConfig().set("Request-List." + p.getName(), requests);
				data.saveConfig();
				return;
			}
			sendMessage(p, ChatColor.RED + "Seems the player whom sent the request has logged off.");
			requests.remove(requests.get(0));
			data.getConfig().set("Request-List." + p.getName(), requests);
			data.saveConfig();
		} else
			sendMessage(p, ChatColor.RED + "You don't have any pending requests.");
		return;
	}

	public void sendRequest(final Player other, final Config data) {
		if (other.getName().equals(p.getName())) {
			sendMessage(p, ChatColor.DARK_RED + "You cannot request to teleport to yourself!");
			return;
		}
		List<String> reqs = new ArrayList<>(data.getConfig().getConfigurationSection("Request-List").getKeys(false));
		if (!reqs.contains(other.getName()) || !data.getConfig().getStringList("Request-List." + other.getName()).contains(p.getName())) {
			List<String> requests = new ArrayList<>(data.getConfig().getStringList("Request-List." + other.getName()));
			requests.add(p.getName());
			data.getConfig().set("Request-List." + other.getName(), requests);
			data.saveConfig();
			p.sendMessage(" ");
			sendMessage(p, ChatColor.GREEN + "Sent a teleport request to: " + ChatColor.GRAY + other.getName() + "\nRequest will expire in 1m 30s.");
			p.sendMessage(" ");
			other.sendMessage(" ");
			sendMessage(other, ChatColor.GREEN + "'" + p.getName() + "' wishes to teleport to you.");
			if (Bukkit.getServer().getVersion().contains("1.15"))
				api.lib.goRequest2("/tpa accept", "/tpa deny", p, other);
			if (Bukkit.getServer().getVersion().contains("1.16"))
				api.lib.goRequest("/tpa accept", "/tpa deny", p, other);
			sendMessage(other, "Request expires in:" + ChatColor.GRAY + " 1m 30s.");
			other.sendMessage(" ");
			setToGet(p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), () -> {
				if (data.getConfig().getStringList("Request-List." + other.getName()).contains(requests.get(0))) {
					sendMessage(other, ChatColor.GRAY + "Teleport request has expired.");
					sendMessage(p, ChatColor.GREEN + "You may now send another teleport request.");
					List<String> request = new ArrayList<>(data.getConfig().getStringList("Request-List." + other.getName()));
					request.remove(requests.get(0));
					data.getConfig().set("Request-List." + other.getName(), request);
					data.saveConfig();
				}
			}, 1800L);
		} else
			sendMessage(p, ChatColor.RED + "'" + other.getName() + '"' + " already has a teleport request pending from you.");
		return;
	}

	private String toGet;

	public void setToGet(String p) {
		this.toGet = p;
	}

	public String getToGet() {
		return this.toGet;
	}


}
