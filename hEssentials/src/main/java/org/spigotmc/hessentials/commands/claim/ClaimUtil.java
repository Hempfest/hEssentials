package org.spigotmc.hessentials.commands.claim;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.util.variables.Strings;

public class ClaimUtil {
	
	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}
	
	// Check if the location of the player is a claim
	public static boolean isInClaim(Location loc) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		 for (String s : d.getConfigurationSection("Claims-Location").getKeys(false)) {
			 int x = d.getInt("Claims-Location." + s + ".X");
			 int z = d.getInt("Claims-Location." + s + ".Z");
			 if ((loc.getChunk().getX() <= x) && (loc.getChunk().getZ() <= z) && (loc.getChunk().getX() >= x) && (loc.getChunk().getZ() >= z)) {
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
		if (d.getString("Claims-Location." + getClaimName(p.getLocation()) + ".User").contains(p.getName()) && !pd.getConfig().getStringList("Claims").contains(getClaimName(p.getLocation()))) {
			
			if (!Claim.contains(getClaimName(p.getLocation()))) {
				Claim.add(getClaimName(p.getLocation()));
				pd.getConfig().set("Claims", Claim);
				pd.saveConfig();
				sendMessage(p, Strings.getPrefix() + "You were given permission to the land &7(&f" + getClaimName(p.getLocation()) + "&7) &rby: " + getClaimOwner(p.getLocation()));
			}
		} else {
			return;
		}
			
		}
	}
	
	// Grant access to a claim to an online user
	public static void addClaimUser(Player p, String claimName, String target) {
		Config data = new Config("Claims");
		FileConfiguration d = data.getConfig();
		
		//chefk if the players in the claim and if it exists
		
		
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
					sendMessage(online, Strings.getPrefix() + "You were given permission to the land &7(&f" + claimName + "&7) &rby: " + p.getName());
				}
			}
			d.set("Claims-Location." + s + ".User", Claims);
			data.saveConfig();
			}
			
			sendMessage(p, Strings.getPrefix() + "You just added user (" + target + ") to land: " + claimName);
			
		}
	}
	
	// Get the claim name at the specified location
	static String getClaimName(Location loc) {
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
		return "N/A";
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
		
		return "N/A";
		return "N/A";
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
	
	// Create a claim for the player
	public static void saveChunk(Player p, String claimName) {
		Location location = p.getLocation();
		Config data = new Config("Claims");
		PlayerData pd = new PlayerData(p.getUniqueId());
		List<String> Claim = pd.getConfig().getStringList("Claims");
		String ID = claimName.toString();
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
		data.getConfig().set("Claims-Location." + ID + ".X", x);
		data.getConfig().set("Claims-Location." + ID + ".Z", z);
		data.getConfig().set("Claims-Location." + ID + ".Owner", p.getName());
		data.getConfig().createSection("Claims-Location." + ID + ".User");
		data.saveConfig();
		sendMessage(p, Strings.getPrefix() + "You just claimed land: " + claimName);
		return;
		
		
	}
	
}
