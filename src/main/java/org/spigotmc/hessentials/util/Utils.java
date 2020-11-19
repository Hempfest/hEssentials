package org.spigotmc.hessentials.util;

import com.youtube.hempfest.hempcore.formatting.component.Text;
import com.youtube.hempfest.hempcore.formatting.component.Text_R2;
import com.youtube.hempfest.hempcore.formatting.string.ColoredString;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.youtube.hempfest.hempcore.library.Entities;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.listener.events.Events;
import org.spigotmc.hessentials.util.timers.Region;
import org.spigotmc.hessentials.util.variables.StringLibrary;


public class Utils extends StringLibrary {

	static heHook api = heHook.getHook();

	private boolean Chat_MUTED;
	public HashMap<String, Boolean> hud = new HashMap<String, Boolean>();
	public HashMap<String, Boolean> recieved = new HashMap<String, Boolean>();
	public int stop = 0;
	// Reply hashmap
	public HashMap<Player, Player> reply = new HashMap<Player, Player>();
	// Socialspy hashmap
	public HashMap<Player, String> socialspy = new HashMap<Player, String>();

	private String claimToLocate;
	private Player playerToTell;


	public void sendMessage(Player player, String s) {
		player.sendMessage(api.lib.color(s));
	}

	public void sendComponent(Player player, TextComponent text) {
		player.spigot().sendMessage(text);
	}

	// *
	//
	// Check if the player has other players nearby.
	//
	public boolean canWarp(Player p) {
		for (Entity e : p.getNearbyEntities(32.0D, 32.0D, 32.0D)) {
			if (e instanceof Player)

				return false;
		}
		return true;
	}

	public void registerTabCommand(String cmdName, TabCompleter completer, CommandExecutor executor) {
		try {
			HempfestEssentials.instance.getCommand(cmdName).setExecutor(executor);
			HempfestEssentials.instance.getCommand(cmdName).setTabCompleter(completer);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public Location getLocationFromCommandArguments(Player p, String[] args, int argsLocStart){ //getLocationFromCommandArguments(player, args, 0);
		Location loc = null;
		double x = 0;
		double y = 0;
		double z = 0;
		String xStr = args[argsLocStart];
		String yStr = args[argsLocStart + 1];
		String zStr = args[argsLocStart + 2];
		if (xStr.contains("~")) {
			xStr = xStr.replace("~", "");
			if(!xStr.isEmpty()) {
				x = p.getLocation().getX() + Double.parseDouble(xStr);
			}else{
				x = p .getLocation().getX();
			}
		}else{
			x = Double.parseDouble(xStr);
		}
		if (yStr.contains("~")) {
			yStr = yStr.replace("~", "");
			if(!yStr.isEmpty()) {
				y = p.getLocation().getY() + Double.parseDouble(yStr);
			}else{
				y = p .getLocation().getY();
			}
		}else{
			y = Double.parseDouble(yStr);
		}
		if (zStr.contains("~")) {
			zStr = zStr.replace("~", "");
			if(!zStr.isEmpty()) {
				z = p.getLocation().getZ() + Double.parseDouble(zStr);
			}else{
				z = p .getLocation().getZ();
			}
		}else{
			z = Double.parseDouble(zStr);
		}
		loc = new Location(p.getWorld(), x, y, z);
		return loc;
	}


	public boolean spawnMobs(Player p, ArrayList<String> mobs, Location spawnLocation){
		ArrayList<Entity> entities = new ArrayList<>();
		for (String mob : mobs) {
			if (Entities.getMaterial(mob) != null) {
				entities.add(spawnLocation.getWorld().spawnEntity(spawnLocation, Entities.getMaterial(mob)));
			} else {
				p.sendMessage(api.lib.color(api.lib.getPrefix() + ChatColor.RED + "The mob " + mob + " does not exist!"));
				entities.forEach(Entity::remove);
				return false;
			}
		}
		for (int i = 1; i < entities.size(); i++) {
			entities.get(i -1).addPassenger(entities.get(i));
		}
		return true;
	}
	public boolean spawnMobs(ArrayList<String> mobs, Location spawnLocation) {
		ArrayList<Entity> entities = new ArrayList<>();

		for (String mob : mobs) {
			if (Entities.getMaterial(mob) != null) {
				entities.add(spawnLocation.getWorld().spawnEntity(spawnLocation, Entities.getMaterial(mob)));
			} else {
				break;
			}
		}
		return true;
	}

	public boolean isMuted() {
		return Chat_MUTED;
	}

	public void setMuted(boolean result) {
		this.Chat_MUTED = result;
	}

	// *
	//
	// Refresh Invsee viewers
	//
	public void updateInvsee() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.getInstance(), new Runnable() {
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					InventoryView invsee = p.getOpenInventory();
					if (invsee.getTitle().equals(p.getName() + "'s Invsee")) {
						String name = invsee.getTitle().replaceAll("'s Invsee", "");
						Player pInventory = Bukkit.getPlayer(name);
						p.closeInventory();
						openPlayerInventory(p, pInventory);
						return;
					}
				}

			}
		}, 40L, 40L);
	}

	/**
	 * Sends the HELP as a paginated list of strings in chat to a player
	 *
	 * @param sender              The sender to send the list to
	 * @param list                The list to paginate
	 * @param page                The page number to display.
	 * @param contentLinesPerPage The count of all available entries
	 */
	public void paginateHelp(Player sender, List<String> list, int page, int contentLinesPerPage) {
		int totalPageCount = 1;
		if((list.size() % contentLinesPerPage) == 0) {
			if(list.size() > 0) {
				totalPageCount = list.size() / contentLinesPerPage;
			}
		} else {
			totalPageCount = (list.size() / contentLinesPerPage) + 1;
		}

		if(page <= totalPageCount) {
			//beginline
			if(list.isEmpty()) {
				sender.sendMessage(ChatColor.WHITE + "The list is empty!");
			} else {
				int i = 0, k = 0;
				page--;

				for (String entry : list) {
					k++;
					if ((((page * contentLinesPerPage) + i + 1) == k) && (k != ((page * contentLinesPerPage) + contentLinesPerPage + 1))) {
						i++;
						String a = entry.replaceAll("%player%", sender.getName());
						String b = a.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
						String c = b.replaceAll("%max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
						String d = c.replaceAll("%prefix%", api.lib.getPrefix());
						String e = d.replaceAll("%page%", String.valueOf(page + 1));
						String f = e.replaceAll("%page_total%", String.valueOf(totalPageCount));
						sender.sendMessage(api.lib.color(f));

					}
				}
				int point;
				point = page + 1;
				if (page >= 1) {
					int last;
					last = point - 1;
					point = point + 1;
					if (Bukkit.getServer().getVersion().contains("1.15"))
						sendComponent(sender, Text_R2.textRunnable("&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help " + last, "help " + point));
					if (Bukkit.getServer().getVersion().contains("1.16"))
						sendComponent(sender, new Text().textRunnable("&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help " + last, "help " + point));

				}
				if (page == 0) {
					point = page + 1 + 1;
					if (Bukkit.getServer().getVersion().contains("1.15"))
						sendComponent(sender, Text_R2.textRunnable("&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help " + point));
					if (Bukkit.getServer().getVersion().contains("1.16"))
						sendComponent(sender, new Text().textRunnable("&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help " + point));

				}

			}
			//endline
		} else {
			sender.sendMessage(ChatColor.YELLOW + "There are only " + ChatColor.WHITE + totalPageCount + ChatColor.YELLOW + " pages!");
		}
	}

	public void paginateStaffHelp(Player sender, List<String> list, int page, int contentLinesPerPage) {
		int totalPageCount = 1;
		if((list.size() % contentLinesPerPage) == 0) {
			if(list.size() > 0) {
				totalPageCount = list.size() / contentLinesPerPage;
			}
		} else {
			totalPageCount = (list.size() / contentLinesPerPage) + 1;
		}

		if(page <= totalPageCount) {
			//beginline
			if(list.isEmpty()) {
				sender.sendMessage(ChatColor.WHITE + "The list is empty!");
			} else {
				int i = 0, k = 0;
				page--;

				for (String entry : list) {
					k++;
					if ((((page * contentLinesPerPage) + i + 1) == k) && (k != ((page * contentLinesPerPage) + contentLinesPerPage + 1))) {
						i++;
						String a = entry.replaceAll("%player%", sender.getName());
						String b = a.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
						String c = b.replaceAll("%max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
						String d = c.replaceAll("%prefix%", api.lib.getPrefix());
						String e = d.replaceAll("%page%", String.valueOf(page + 1));
						String f = e.replaceAll("%page_total%", String.valueOf(totalPageCount));
						sender.sendMessage(api.lib.color(f));

					}
				}
				int point;
				point = page + 1;
				if (page >= 1) {
					int last;
					last = point - 1;
					point = point + 1;
					if (Bukkit.getServer().getVersion().contains("1.15"))
						sendComponent(sender, Text_R2.textRunnable("&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help staff " + last, "help staff " + point));
					if (Bukkit.getServer().getVersion().contains("1.16"))
						sendComponent(sender, new Text().textRunnable("&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help staff " + last, "help staff " + point));

				}
				if (page == 0) {
					point = page + 1 + 1;
					if (Bukkit.getServer().getVersion().contains("1.15"))
						sendComponent(sender, Text_R2.textRunnable("&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help staff " + point));
					if (Bukkit.getServer().getVersion().contains("1.16"))
						sendComponent(sender, new Text().textRunnable("&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help staff " + point));

				}

			}
			//endline
		} else {
			sender.sendMessage(ChatColor.YELLOW + "There are only " + ChatColor.WHITE + totalPageCount + ChatColor.YELLOW + " pages!");
		}
	}

	public String sendOnlineList() {
		StringBuilder string = new StringBuilder();
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (string.length() > 0)
				string.append(api.lib.color("&7, &r"));
			string.append(onlinePlayers.getDisplayName());
		}
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(api.lib.getMessagesUsed());
		String online = messages.getConfig().getString("Messages.Player-Responses.Online-List").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = online.replaceAll("%players%", string.toString());
		String prefix = players.replaceAll("%prefix%", messages.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return api.lib.color(message);
	}

	public boolean didRecieve(Player player) {
		return recieved.containsKey(player.getName()) && recieved.get(player.getName()).booleanValue();
	}

	public boolean isInt(String e) {
		try {
			Integer.parseInt(e);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	//Check if is double
	public boolean isDouble(String e) {
		try {
			Double.parseDouble(e);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean checkforPH() {
		return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
	}

	// HUD check
	public boolean autoClaiming(Player player) {
		return hud.containsKey(player.getName()) && hud.get(player.getName()).booleanValue();
	}

	public void setClaimToLocate(String claimToLocate) {
		this.claimToLocate = claimToLocate;
	}

	public String getClaimToLocate() {
		return this.claimToLocate;
	}

	public void setPlayerToTell(Player playerToTell) {
		this.playerToTell = playerToTell;
	}

	public Player getPlayerToTell() {
		return this.playerToTell;
	}

	public void runClaimEvent() {
		// Region Refresh = new Region();
		final Region region = new Region();
		region.runTaskTimerAsynchronously(HempfestEssentials.getInstance(), 20L, 30L);
	}

	public UUID usernameToUUID(String username) {
		DataManager dm = new DataManager();
		for (String s : getAllUserIDs()) {
			Config pd = dm.getUser(UUID.fromString(s));
			if (pd.exists()) {
				FileConfiguration f = pd.getConfig();
				String name = f.getString("USERNAME");
				if (name.equalsIgnoreCase(username)) {
					return UUID.fromString(s);
				}
			}
		}
		return null;
	}

	public String usernameFromUUID(UUID id) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(id);
		if (player == null) return null;
		return player.getName();
	}

	public List<String> getAllUserIDs() {
		DataManager dm = new DataManager();
		List<String> users = new ArrayList<String>();
		for (File file : dm.getUserFiles().listFiles()) {
			users.add(file.getName().replace(".yml", ""));
		}
		return users;
	}

	public List<String> getBannedUsers() {
		DataManager dm = new DataManager();
		Config users = dm.requestData("Banlist");
		FileConfiguration fc = users.getConfig();
		List<String> array = new ArrayList<>();
		for (String user : fc.getConfigurationSection("Users").getKeys(false))
			array.add(user);
		return array;
	}

	public String getBanReason(String player) {
		DataManager dm = new DataManager();
		Config users = dm.requestData("Banlist");
		FileConfiguration fc = users.getConfig();
		return fc.getString("Users." + player + ".reason");
	}

	public static HashMap<UUID, ItemStack[]> invStorage = new HashMap<>();

	private void storeItems(UUID id, PlayerInventory inv) {
		ItemStack[] contents = inv.getContents();
		List<ItemStack> temp = new ArrayList<>();
		Collections.addAll(temp, contents);
		contents = temp.toArray(new ItemStack[temp.size()]);
		int amount = contents.length;
		ItemStack[] clonedContents = new ItemStack[amount];
		for (int i = 0; i < amount; ++i) {
			ItemStack item = contents[i];
			if (item != null) {
				clonedContents[i] = item.clone();
			}
		}
		invStorage.put(id, clonedContents);
	}

	public Entity getNearestEntityInSight(Player player, int range) {
		ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
		ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
		ArrayList<Location> sight = new ArrayList<>();
		for (int i = 0;i<sightBlock.size();i++)
			sight.add(sightBlock.get(i).getLocation());
		for (int i = 0;i<sight.size();i++) {
			for (int k = 0;k<entities.size();k++) {
				if (Math.abs(entities.get(k).getLocation().getX()-sight.get(i).getX())<1.3) {
					if (Math.abs(entities.get(k).getLocation().getY()-sight.get(i).getY())<1.5) {
						if (Math.abs(entities.get(k).getLocation().getZ() - sight.get(i).getZ()) < 1.3) {
							return entities.get(k);
						}
					}
				}
			}
		}
		return null;
	}

	public void resetItems(Player p) {
		if (!Events.staffGui.containsKey(p.getUniqueId())) {
			List<String> names = new ArrayList<>(Arrays.asList(color("&7[&4&lRANDOM TP&7]"), color("&7[&c&lTELEPORT LIST&7]"), color("&7[&a&lTELEPORT VISIBLE&7]"), color("&7[&5&oOPEN INV&7]"), color("&7[&b&lFREEZE TARGET&7]"), color("&7[&3&lVANISH&7]")));
			for (ItemStack item : p.getInventory().getContents()) {
				if (item != null) {
					if (item.hasItemMeta()) {
						if (item.getItemMeta().hasDisplayName()) {
							if (names.contains(item.getItemMeta().getDisplayName())) {
								p.getInventory().clear();
								sendMessage(p, getPrefix() + "It looks like you left before we could load your inventory..");
								return;
							}
						}
					}
				}
			}
		}
	}

	public List<ItemStack> getStaffItems() {
		ItemStack randomTp = makeItem(Material.COMPASS, "&7[&4&lRANDOM TP&7]", "", "&b&oClick to teleport");
		ItemStack skullTp = makeItem(Material.SKELETON_SKULL, "&7[&c&lTELEPORT LIST&7]", "", "&b&oClick to open teleport GUI");
		ItemStack jumpTp = makeItem(Material.STICK, "&7[&a&lTELEPORT VISIBLE&7]", "", "&b&oClick to teleport to the location you're looking.");
		ItemStack invSee = makeItem(Material.ENDER_EYE, "&7[&5&oOPEN INV&7]", "", "&b&oOpen the inventory of the player you look at.");
		ItemStack freezePlayer = makeItem(Material.PACKED_ICE, "&7[&b&lFREEZE TARGET&7]", "", "&b&oClick to freeze the player you look at.");
		ItemStack vanishP = makeItem(Material.PURPLE_DYE, "&7[&3&lVANISH&7]", "", "&oStatus: &c&nOff");
		ItemStack config = makeItem(Material.PAPER, "&7[&6&lCONFIG&7]", "", "&b&oClick to view player information.");
		return new ArrayList<>(Arrays.asList(randomTp, skullTp, jumpTp, invSee, freezePlayer, vanishP, config));
	}

	public void sendStaffMenu(Player p) {
		storeItems(p.getUniqueId(), p.getInventory());
		p.getInventory().clear();
		ItemStack randomTp = makeItem(Material.COMPASS, "&7[&4&lRANDOM TP&7]", "", "&b&oClick to teleport");
		ItemStack skullTp = makeItem(Material.SKELETON_SKULL, "&7[&c&lTELEPORT LIST&7]", "", "&b&oClick to open teleport GUI");
		ItemStack jumpTp = makeItem(Material.STICK, "&7[&a&lTELEPORT VISIBLE&7]", "", "&b&oClick to teleport to the location you're looking.");
		ItemStack invSee = makeItem(Material.ENDER_EYE, "&7[&5&oOPEN INV&7]", "", "&b&oOpen the inventory of the player you look at.");
		ItemStack freezePlayer = makeItem(Material.PACKED_ICE, "&7[&b&lFREEZE TARGET&7]", "", "&b&oClick to freeze the player you look at.");
		ItemStack vanishP = makeItem(Material.PURPLE_DYE, "&7[&3&lVANISH&7]", "", "&oStatus: &c&nOff");
		ItemStack config = makeItem(Material.PAPER, "&7[&6&lCONFIG&7]", "", "&b&oClick to view player information.");
		if (Events.vanishPlayer.containsKey(p.getUniqueId()) && Events.vanishPlayer.get(p.getUniqueId())) {
			vanishP = makeItem(Material.LIME_DYE, "&7[&3&lVANISH&7]", "", "&oStatus: &a&nOn");
		}
		p.getInventory().setItem(0, randomTp);
		p.getInventory().setItem(1, skullTp);
		if (p.hasPermission("hessentials.staff.extra")) {
			p.getInventory().setItem(2, config);
		}
		p.getInventory().setItem(4, jumpTp);
		p.getInventory().setItem(6, invSee);
		p.getInventory().setItem(7, freezePlayer);
		p.getInventory().setItem(8, vanishP);

	}

	protected List<String> color(String... text) {
		ArrayList<String> convert = new ArrayList<>();
		for (String t : text) {
			convert.add(new ColoredString(t, ColoredString.ColorType.MC).toString());
		}
		return convert;
	}

	public ItemStack makeItem(Material material, String displayName, String... lore) {

		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		assert itemMeta != null;
		itemMeta.setDisplayName(new ColoredString(displayName, ColoredString.ColorType.MC).toString());

		itemMeta.setLore(color(lore));
		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack makePersistentItem(Material material, String displayName, String key, String data, String... lore) {

		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		assert itemMeta != null;
		itemMeta.setDisplayName(new ColoredString(displayName, ColoredString.ColorType.MC).toString());
		itemMeta.getPersistentDataContainer().set(new NamespacedKey(HempfestEssentials.getInstance(), key), PersistentDataType.STRING, data);
		itemMeta.setLore(color(lore));
		item.setItemMeta(itemMeta);

		return item;
	}

	public void openPlayerInventory(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(target, 54, api.lib.color(target.getName() + " : click to update"));
		Inventory targets = target.getInventory();
		ItemStack b1 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm1 = b1.getItemMeta();
		bm1.setDisplayName(api.lib.color("&a&lBOOTS &f&l/\\"));
		b1.setItemMeta(bm1);
		ItemStack b2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm2 = b2.getItemMeta();
		bm2.setDisplayName(api.lib.color("&a&lLEGGINGS &f&l/\\"));
		b2.setItemMeta(bm2);
		ItemStack b3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm3 = b3.getItemMeta();
		bm3.setDisplayName(api.lib.color("&a&lCHEST &f&l/\\"));
		b3.setItemMeta(bm3);
		ItemStack b4 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm4 = b4.getItemMeta();
		bm4.setDisplayName(api.lib.color("&a&lHELMET &f&l/\\"));
		b4.setItemMeta(bm4);
		ItemStack b5 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm5 = b5.getItemMeta();
		bm5.setDisplayName(api.lib.color("&a&lOFF-HAND &f&l/\\"));
		b5.setItemMeta(bm5);
		ItemStack b6 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm6 = b6.getItemMeta();
		bm6.setDisplayName(api.lib.color("&f&l<--"));
		bm6.setLore(Arrays.asList(api.lib.color("&5Armor contents")));
		b6.setItemMeta(bm6);
		inv.setContents(targets.getContents());

		inv.setItem(41, b6);
		inv.setItem(42, b6);
		inv.setItem(43, b6);
		inv.setItem(44, b6);
		inv.setItem(45, b1);
		inv.setItem(46, b2);
		inv.setItem(47, b3);
		inv.setItem(48, b4);
		inv.setItem(49, b5);
		inv.setItem(50, b6);
		inv.setItem(51, b6);
		inv.setItem(52, b6);
		inv.setItem(53, b6);
		p.updateInventory();
		p.openInventory(inv);
	}

	public void MOTD(Player player) {
		DataManager dm = new DataManager();
		Config motd = dm.getMisc(api.lib.getMessagesUsed());
		InputStream in3 = HempfestEssentials.instance.getResource("Messages.yml");
		if (motd.exists()) {
			if (checkforPH()) {
				sendMessage(player, PlaceholderAPI.setPlaceholders(player, api.lib.getMOTD(player)));
			} else {
				sendMessage(player, api.lib.getMOTD(player));
			}

		} else {
			Config.copy(in3, motd.getFile());
			sendMessage(player, api.lib.getPrefix() + "The plugin hasn't yet gotten a chance to create " + '"'
					+ "MOTD.yml" + '"' + "\nCreating it now..");
		}
	}

	public void sendMessage(CommandSender player, String message) {
		player.sendMessage(api.lib.color(message));
		return;
	}

	public void sendPlayerInfo(Player player, Player target) {
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, api.lib.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		if (player.isOp()) {
			sendMessage(player, "&f&oIP &7&l| &2&o" + target.getAddress().toString().replace("/", ""));
		}
		sendMessage(player, "&f&oUUID &7&l| &2&o" + target.getUniqueId().toString());
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		sendMessage(player, "&f&oGamemode &7&l| &2&o" + target.getGameMode());
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
			sendMessage(player, "&f&oGroup &7&l| &2&o" + "?");
		}
		if (Bukkit.getServer().getVersion().contains("1.15"))
			sendComponent(player, Text_R2.textRunnable("&f&oCurrent Location &7&l| ", "&2(&a&oClick&2)", "&a&oClick to teleport to their current location.", "teleport " + target.getName()));
		if (Bukkit.getServer().getVersion().contains("1.16"))
			sendComponent(player, new Text().textRunnable("&f&oCurrent Location &7&l| ", "&2(&a&oClick&2)", "&a&oClick to teleport to their current location.", "teleport " + target.getName()));

		return;
	}

	public Collection<Chunk> getChunksAroundPlayer(Player player) {
		int[] offset = {-1, 0, 1};

		World world = player.getWorld();
		int baseX = player.getLocation().getChunk().getX();
		int baseZ = player.getLocation().getChunk().getZ();

		Collection<Chunk> chunksAroundPlayer = new HashSet<>();
		for (int x : offset) {
			for (int z : offset) {
				Chunk chunk = world.getChunkAt(baseX + x, baseZ + z);
				chunksAroundPlayer.add(chunk);
			}
		}
		return chunksAroundPlayer;
	}

	public void updateConfiguration() {
		DataManager dm = new DataManager();
		Config me = dm.getMisc("Messages");
		FileConfiguration m = me.getConfig();
		if (!m.getString("Version").equals(HempfestEssentials.getInstance().getDescription().getVersion())) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.hasPermission("hessentials.staff")) {
					sendMessage(all, api.lib.getPrefix() + "Configuration updated.");
				}
			}
			File config = new File(HempfestEssentials.instance.getDataFolder(), "Messages.yml");
			config.delete();
			InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
			Config.copy(in, me.getFile());

			return;
		}
	}

	public void sendChat_Muted() {
		DataManager dm = new DataManager();
		Config message = dm.getMisc(api.lib.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		String muted = m.getString("Messages.Player-Responses.Chat-Muted");
		Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(muted));
	}

	public void sendChat_Unmuted() {
		DataManager dm = new DataManager();
		Config message = dm.getMisc(api.lib.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		String unmuted = m.getString("Messages.Player-Responses.Chat-UnMuted");
		Bukkit.broadcastMessage(api.lib.getPrefix() + api.lib.color(unmuted));
	}


	public boolean day(Player p) {
		Server server = Bukkit.getServer();
		long time = server.getWorld(p.getLocation().getWorld().getName()).getTime();

		return time > 0 && time < 13000;
	}


	public String trackPlayers(Player p) {
		int cuantos = 0;
		float configdistance = 1000;

		for (Entity entity : p.getNearbyEntities(configdistance, 250.0D, configdistance)) {

			if (entity instanceof Player) {

				cuantos++;
				Player Jugador = (Player) entity;
				if (Jugador.getGameMode() == GameMode.SURVIVAL) {
					int distancia = (int) p.getLocation().distance(Jugador.getLocation());
					return api.lib.color("&e&o" + Jugador.getName() + ": &7" + distancia + " &fblocks away.");
				}
			}
		}

		if (cuantos == 0) {
			return api.lib.color("&c&oNo near by players.");
		}
		return null;
	}

	public void lastLocation(Player p, String target) {
		DataManager dm = new DataManager();
		try {
			Config pd = dm.getUser(usernameToUUID(target));
			if (pd.exists()) {
				FileConfiguration fc = pd.getConfig();
				p.teleport(fc.getLocation("Last-Location"));
			}
		} catch (Exception e) {
			sendMessage(p, getPrefix() + "They don't have a location logged yet.");
		}
	}

	public void sendOfflinePlayerInfo(Player player, OfflinePlayer target) {
		DataManager dm = new DataManager();
		Config data = dm.getUser(target.getUniqueId());
		String IP = data.getConfig().getString("IP-ADDRESS");
		String UUID = target.getUniqueId().toString();
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, api.lib.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		if (player.isOp()) {
			sendMessage(player, "&f&oIP &7&l| &2&o" + IP);
		}
		sendMessage(player, "&f&oUUID &7&l| &2&o" + UUID);
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
			sendMessage(player, "&f&oGroup &7&l| &2&o" + "?");

		}
		if (Bukkit.getServer().getVersion().contains("1.15"))
			sendComponent(player, Text_R2.textRunnable("&f&oLast Location &7&l| ", "&2(&a&oClick&2)", "&a&oClick to teleport to their log-off location.", "teleport " + target.getName()));
		if (Bukkit.getServer().getVersion().contains("1.16"))
			sendComponent(player, new Text().textRunnable("&f&oLast Location &7&l| ", "&2(&a&oClick&2)", "&a&oClick to teleport to their log-off location.", "teleport " + target.getName()));
		return;
	}


	public void reloadConfiguration() {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(api.lib.getMessagesUsed());
		Config help = dm.getMisc("Help");
		help.reload();
		messages.reload();
	}

	public void createPlayerConfig(Player player) {
		UUID uuid = player.getUniqueId();
		DataManager dm = new DataManager();
		Config pd = dm.getUser(uuid);
		FileConfiguration f = pd.getConfig();
		String name = player.getName();
		String ip = player.getAddress().toString().replace("/", "");
		f.set("USERNAME", name);
		f.set("SUFFIX", "Default");
		f.set("IP-ADDRESS", ip);
		f.set("Last-Time-Played", 0);
		pd.saveConfig();

	}

	public void createHomeSection(Player player) {
		DataManager dm = new DataManager();
		Config pd = dm.getHomeData(player);
		FileConfiguration f = pd.getConfig();
		if (f.getConfigurationSection("Private-Homes") == null) {
			f.createSection("Private-Homes");
			pd.saveConfig();
		}
	}

	public void matchUsername(Player player) {
		UUID uuid = player.getUniqueId();
		DataManager dm = new DataManager();
		Config pd = dm.getUser(uuid);
		FileConfiguration f = pd.getConfig();
		String name = player.getName();
		String section = "USERNAME";
		f.set(section, name);
		pd.saveConfig();
	}

	public void matchIP(Player player) {
		UUID uuid = player.getUniqueId();
		DataManager dm = new DataManager();
		Config pd = dm.getUser(uuid);
		FileConfiguration f = pd.getConfig();
		String ip = player.getAddress().toString().replace("/", "");
		String section = "IP-ADDRESS";
		f.set(section, ip);
		pd.saveConfig();

	}

	public void defaultConfiguration() {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc("Messages");
		Config help = dm.getMisc("Help");
		Config users = dm.requestData("Banlist");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		users.getConfig().createSection("Users");
		users.saveConfig();
		Config.copy(in, messages.getFile());
		Config.copy(in2, help.getFile());

	}

	public void createCV() {
		DataManager dm = new DataManager();
		Config claims = dm.requestData("Claims");
		if (!claims.exists()) {
			FileConfiguration fc = claims.getConfig();
			fc.createSection("Location");
			claims.saveConfig();
		}
	}

	public boolean runningPermissions() {
		return Bukkit.getPluginManager().isPluginEnabled("hPermissions");
	}

	public void createConfiguration() {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc("Messages");
		Config help = dm.getMisc("Help");

		Config users = dm.requestData("Banlist");
		if (!users.exists()) {
			users.getConfig().createSection("Users");
			users.saveConfig();
		}

		Config teleports = dm.requestData("Teleports");
		if (!teleports.exists()) {
			teleports.getConfig().createSection("Request-List");
			teleports.saveConfig();
		}

		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");


		if (!messages.exists()) {
			Config.copy(in, messages.getFile());
		}
		if (!help.exists()) {

			Config.copy(in2, help.getFile());
		}

	}

	public int convertDouble(Double d) {
		return d.intValue();
	}

}
