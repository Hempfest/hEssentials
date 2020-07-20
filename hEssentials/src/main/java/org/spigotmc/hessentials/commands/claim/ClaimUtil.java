package org.spigotmc.hessentials.commands.claim;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.variables.Strings;

public class ClaimUtil implements Listener {

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	 @EventHandler(priority = EventPriority.HIGH)
	  public void onBucketRelease(PlayerBucketEmptyEvent e) {
		  Player p = e.getPlayer();
		  Block block = e.getBlock();
			Location blocks = block.getLocation();
			if (isInClaim(blocks)) {
				if (!getClaimList(p).contains(getClaimName(blocks))) {
					e.setCancelled(true);
					String claimName = getClaimName(blocks);
					String claimOwner = getClaimOwner(blocks);
					sendMessage(p, Strings.getPrefix() + Strings.getCannotPlace(p, claimName, claimOwner));
					return;
				}
			}
	  }
	@EventHandler(priority = EventPriority.HIGH)
	public void onChestUse(PlayerInteractEvent e) {
		try {
		Block block = e.getClickedBlock();
		Location blocks = block.getLocation();
		Player p = e.getPlayer();
		if (isInClaim(blocks)) {
			if (block.getType().isInteractable()) {
			if (!getClaimList(p).contains(getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = getClaimName(blocks);
				String claimOwner = getClaimOwner(blocks);
				sendMessage(p, Strings.getPrefix() + Strings.getCannotUse(p, claimName, claimOwner));
				return;
				}
			return;
			}
		}
		} catch (NullPointerException ex) {
			return;
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		if (isInClaim(blocks)) {
			if (!getClaimList(p).contains(getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = getClaimName(blocks);
				String claimOwner = getClaimOwner(blocks);
				sendMessage(p, Strings.getPrefix() + Strings.getCannotBreak(p, claimName, claimOwner));
				return;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBreak(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		Location blocks = block.getLocation();
		if (isInClaim(blocks)) {
			if (!getClaimList(p).contains(getClaimName(blocks))) {
				e.setCancelled(true);
				String claimName = getClaimName(blocks);
				String claimOwner = getClaimOwner(blocks);
				sendMessage(p, Strings.getPrefix() + Strings.getCannotPlace(p, claimName, claimOwner));
				return;
			}
		}
	}

	// Check if the location of the player is a claim
	public static boolean isInClaim(Location loc) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
			int x = d.getInt("Claims-Location." + s + ".X");
			int z = d.getInt("Claims-Location." + s + ".Z");
			String w = d.getString("Claims-Location." + s + ".World");
			if ((loc.getChunk().getX() <= x) && (loc.getChunk().getZ() <= z) && (loc.getChunk().getX() >= x)
					&& (loc.getChunk().getZ() >= z) && loc.getWorld().getName().equals(w)) {
				return true;
			}

		}

		return false;
	}

	// Check if the player in the claim is the claim owner
	public static boolean isClaimOwner(Player p) {
		if (getClaimOwner(p.getLocation()).equals(p.getName())) {
			return true;
		}
		return false;
	}

	// Return the players claim list
	public static List<String> getClaimList(Player p) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		PlayerData pd = new PlayerData(p.getUniqueId());
		List<String> Claims = pd.getConfig().getStringList("Claims");
		for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
			if (d.getString("Claims-Location." + s + ".Owner").equals(p.getName())) {

				if (!Claims.contains(s.toString())) {
					Claims.add(s);
					pd.getConfig().set("Claims", Claims);
					pd.saveConfig();
				}

			}
		}
		return Claims;

	}

	// Check if the player is a claim user
	public static boolean isClaimUser(Player p) {
		PlayerData pd = new PlayerData(p.getUniqueId());
		if (isInClaim(p.getLocation()) && !isClaimOwner(p)) {
			if (pd.getConfig().getStringList("Claims").contains(getClaimName(p.getLocation()))) {
				return true;

			}
		}
		return false;
	}

	// Update all users in the game with permission to claims that grant access.
	public static void updateClaimUser(Player p) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		if (isInClaim(p.getLocation())) {
			PlayerData pd = new PlayerData(p.getUniqueId());
			List<String> Claim = pd.getConfig().getStringList("Claims");
			if (d.getString("Claims-Location." + getClaimName(p.getLocation()) + ".User").contains(p.getName())
					&& !pd.getConfig().getStringList("Claims").contains(getClaimName(p.getLocation()))) {

				if (!Claim.contains(getClaimName(p.getLocation()))) {
					Claim.add(getClaimName(p.getLocation()));
					pd.getConfig().set("Claims", Claim);
					pd.saveConfig();
					sendMessage(p, Strings.getPrefix() + "You were given permission to the land &7(&f"
							+ getClaimName(p.getLocation()) + "&7) &rby: " + getClaimOwner(p.getLocation()));
				}
			} else if (!d.getString("Claims-Location." + getClaimName(p.getLocation()) + ".User").contains(p.getName())) {
				if (isClaimOwner(p)) {
					return;
				}
				if (Claim.contains(getClaimName(p.getLocation()))) {
				Claim.remove(getClaimName(p.getLocation()));
				pd.getConfig().set("Claims", Claim);
				pd.saveConfig();
				sendMessage(p, Strings.getPrefix() + "You were removed permission to the land &7(&f"
						+ getClaimName(p.getLocation()) + "&7) &rby: " + getClaimOwner(p.getLocation()));
				return;
				}
			}

		}
	}

	// Grant access to a claim to an online user
	public static void addClaimUser(Player p, String claimName, String target) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		PlayerData da = new PlayerData(p.getUniqueId());
		// chefk if the players in the claim and if it exists
		if (!getClaimName(p.getLocation()).equals(claimName)) {
			sendMessage(p, Strings.getPrefix() + "You must be standing within the claim to add users!");
			return;
		}
		if (!da.getConfig().getStringList("Claims").contains(claimName)) {
			sendMessage(p, Strings.getPrefix() + "You do not own a claim by the name of: " + claimName);
			return;
		}

		if (isInClaim(p.getLocation()) && isClaimOwner(p)) {
			for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
				List<String> Claims = d.getStringList("Claims-Location" + s + ".User");
				if (!Claims.contains(target) && s.equals(claimName)) {
					Claims.add(target);
				}
				Player online = Bukkit.getPlayer(target);
				if (online != null) {
					PlayerData pd = new PlayerData(online.getUniqueId());
					List<String> Claim = pd.getConfig().getStringList("Claims");
					if (!Claim.contains(claimName)) {
						Claim.add(claimName);
						pd.getConfig().set("Claims", Claim);
						pd.saveConfig();
						sendMessage(online, Strings.getPrefix() + "You were given permission to the land &7(&f"
								+ claimName + "&7) &rby: " + p.getName());
					}
				}
				d.set("Claims-Location." + s + ".User", Claims);
				data.saveConfig();
			}

			sendMessage(p, Strings.getPrefix() + "You just added user (" + target + ") to land: " + claimName);

		}
	}
	
	// Remove access to a claim from an online user
	public static void removeClaimUser(Player p, String claimName, String target) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		PlayerData da = new PlayerData(p.getUniqueId());
		// chefk if the players in the claim and if it exists
		if (!getClaimName(p.getLocation()).equals(claimName)) {
			sendMessage(p, Strings.getPrefix() + "You must be standing within the claim to remove users!");
			return;
		}
		if (!da.getConfig().getStringList("Claims").contains(claimName)) {
			sendMessage(p, Strings.getPrefix() + "You do not own a claim by the name of: " + claimName);
			return;
		}

		if (isInClaim(p.getLocation()) && isClaimOwner(p)) {
			for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
				List<String> Claims = d.getStringList("Claims-Location" + s + ".User");
				if (Claims.contains(target) && s.equals(claimName)) {
					Claims.remove(target);
				}
				Player online = Bukkit.getPlayer(target);
				if (online != null) {
					PlayerData pd = new PlayerData(online.getUniqueId());
					List<String> Claim = pd.getConfig().getStringList("Claims");
					if (Claim.contains(claimName)) {
						Claim.remove(claimName);
						pd.getConfig().set("Claims", Claim);
						pd.saveConfig();
						sendMessage(online, Strings.getPrefix() + "You were removed permission to the land &7(&f"
								+ claimName + "&7) &rby: " + p.getName());
					}
				}
				d.set("Claims-Location." + s + ".User", Claims);
				data.saveConfig();
			}

			sendMessage(p, Strings.getPrefix() + "You just removed user (" + target + ") from land: " + claimName);

		}
	}

	// Get the claim name at the specified location
	public static String getClaimName(Location loc) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		if (isInClaim(loc)) {
			for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
				int x = d.getInt("Claims-Location." + s + ".X");
				int z = d.getInt("Claims-Location." + s + ".Z");
				if (x == loc.getChunk().getX() && z == loc.getChunk().getZ()) {
					return s.toString();
				}
			}
		}
		return "Wild";
	}

	// Get the claim owner of the specified location
	public static String getClaimOwner(Location loc) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		if (isInClaim(loc)) {
			for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
				int x = d.getInt("Claims-Location." + s + ".X");
				int z = d.getInt("Claims-Location." + s + ".Z");
				if (x == loc.getChunk().getX() && z == loc.getChunk().getZ()) {
					return d.getString("Claims-Location." + s + ".Owner");
				}
			}
		} else

			return "Unowned";
		return "Unowned";
	}

	// Delete the claim of a player
	public static void deleteChunk(Player p, String claimName) {
		Location location = p.getLocation();
		Config data = new Config("Claims");
		String ID = claimName.toString();
		PlayerData pd = new PlayerData(p.getUniqueId());
		List<String> Claim = pd.getConfig().getStringList("Claims");
		if (isInClaim(location) && !isClaimOwner(p)) {
			sendMessage(p, Strings.getPrefix() + "You do not own this land.\nOwner: " + getClaimOwner(location));
			return;
		}
		if (isInClaim(location) && isClaimOwner(p)) {
			List<String> Claims = data.getConfig().getStringList("Claims-List");
			if (Claims.contains(ID)) {
				Claims.remove(ID);
			}
			if (Claim.contains(ID)) {
				Claim.remove(ID);
				pd.getConfig().set("Claims", Claim);
				pd.saveConfig();
			}
			data.getConfig().set("Claims-List", Claims);
			data.getConfig().set("Claims-Location." + ID, null);
			data.saveConfig();
			sendMessage(p, Strings.getPrefix() + "You just un-claimed land: " + claimName);
			return;
		}

	}

	public static void teleportChunk(Player p, String claimName) {
		PlayerData pd = new PlayerData(p.getUniqueId());
		Config data = new Config("Claims");
		int x = data.getConfig().getInt("Claims-Location." + claimName + ".X");
		int y = 150;
		int z = data.getConfig().getInt("Claims-Location." + claimName + ".Z");
		if (pd.getConfig().getStringList("Claims").contains(claimName)) {

			boolean isOnLand = false;
			while (isOnLand == false) {
				Location teleportLocation = null;
				teleportLocation = new Location(p.getWorld(), x << 4, y, z << 4);

				if (teleportLocation.getBlock().getType() != Material.AIR) {
					isOnLand = true;
				} else
					y--;

				p.teleport(new Location(p.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1,
						teleportLocation.getZ()));
				
			}
			sendMessage(p, Strings.getPrefix() + "&aTeleported &7to: &e" + claimName);
		}

	}

	// Create a claim for the player
	public static void saveChunk(Player p, String claimName) {
		Location location = p.getLocation();
		Config data = new Config("Claims");
		PlayerData pd = new PlayerData(p.getUniqueId());
		List<String> Claim = pd.getConfig().getStringList("Claims");
		String ID = claimName.toString();
		if (HempfestEssentials.getInstance().getServer().getPluginManager().getPlugin("Clans") != null) {
			if (m.h.clans.util.claim.ClaimUtil.isInClaim(p.getLocation())) {
				sendMessage(p,
						Strings.getPrefix() + "You do not own this land.\nOwner: " + m.h.clans.util.claim.ClaimUtil
								.getClaimOwner(m.h.clans.util.claim.ClaimUtil.getClaimID(p.getLocation())));
				return;
			}
		}
		if (isInClaim(location) && !isClaimOwner(p)) {
			sendMessage(p, Strings.getPrefix() + "You do not own this land.\nOwner: " + getClaimOwner(location));
			return;
		}
		if (isInClaim(location) && isClaimOwner(p)) {
			sendMessage(p, Strings.getPrefix() + "You already own this land.\nClaim: " + getClaimName(location));
			return;
		}
		List<String> Claims = data.getConfig().getStringList("Claims-List");
		if (!Claims.contains(ID)) {
			Claims.add(ID);
		}
		if (!Claim.contains(ID)) {
			Claim.add(ID);
			pd.getConfig().set("Claims", Claim);
			pd.saveConfig();
		}
		data.getConfig().set("Claims-List", Claims);
		int x = location.getChunk().getX();
		int z = location.getChunk().getZ();
		World w = location.getWorld();
		data.getConfig().set("Claims-Location." + ID + ".X", x);
		data.getConfig().set("Claims-Location." + ID + ".Z", z);
		data.getConfig().set("Claims-Location." + ID + ".World", w.getName());
		data.getConfig().set("Claims-Location." + ID + ".Owner", p.getName());
		data.getConfig().createSection("Claims-Location." + ID + ".User");
		data.saveConfig();
		sendMessage(p, Strings.getPrefix() + "You just claimed land: " + claimName);
		return;

	}

}
