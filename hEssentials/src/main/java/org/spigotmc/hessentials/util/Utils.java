package org.spigotmc.hessentials.util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;
import org.spigotmc.hessentials.gui.Gui;
import org.spigotmc.hessentials.util.variables.Component;
import org.spigotmc.hessentials.util.variables.Strings;

import addon.chat.hessentials.GroupAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;


public class Utils {


	public static boolean Chat_MUTED = false;
	public static HashMap<String, Boolean> hud = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> title_claim = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> title_claim2 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> title_claim3 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> title_claim4 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> hud_muted = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> hud_tracking = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> recieved = new HashMap<String, Boolean>();
	public static HashMap<Player, String> view = new HashMap<Player, String>();
	public static HashMap<Player, Gui> guiManager = new HashMap<>();
	
	public static int stop = 0;
	
	// Reply hashmap
	public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
	// Socialspy hashmap
	public static HashMap<Player, String> socialspy = new HashMap<Player, String>();
	

	public static void sendMessage(Player player, String s) {
		player.sendMessage(Strings.color(s));
	}
	
	public void sendComponent(Player player, TextComponent text) {
		player.spigot().sendMessage((BaseComponent) text);
	}

	// *
	//
	// Check if the player has other players nearby.
	//
	public static boolean canWarp(Player p) {
		for (Entity e : p.getNearbyEntities(32.0D, 32.0D, 32.0D)) {
			if (e instanceof Player)

				return false;
		}
		return true;
	}

	public static void registerTabCommand(String cmdName, TabCompleter completer, CommandExecutor executor) {
		try {
			HempfestEssentials.instance.getCommand(cmdName).setExecutor(executor);
			HempfestEssentials.instance.getCommand(cmdName).setTabCompleter(completer);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void registerCommand(BukkitCommand command) {
		try {

			final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			commandMapField.setAccessible(true);

			final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
			commandMap.register(command.getLabel(), command);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	// *
	//
	// Refresh Invsee viewers
	//
	public static void updateInvsee() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.getInstance(), new Runnable() {
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					InventoryView invsee = p.getOpenInventory();
					if (invsee.getTitle().equals(p.getName() + "'s Invsee")) {
						String name = invsee.getTitle().replaceAll("'s Invsee", "");
						Player pInventory = Bukkit.getPlayer(name);
						p.closeInventory();
						Utils.openPlayerInventory(p, pInventory);
						return;
					}
				}

			}
		}, 40L, 40L);
	}

	// * NOT WORKING
	//
	// Remove tracking board after 1 minute
	//

	 /**
     * Sends the HELP as a paginated list of strings in chat to a player
     *
     * @param sender The sender to send the list to
     * @param list The list to paginate
     * @param page The page number to display.
     * @param countAll The count of all available entries
     */
   public void paginateHelp(Player sender, List<String> list, int page, int contentLinesPerPage)
   {
       int totalPageCount = 1;
       if((list.size() % contentLinesPerPage) == 0)
       {
         if(list.size() > 0)
         {
             totalPageCount = list.size() / contentLinesPerPage;
         }    
       }
       else
       {
         totalPageCount = (list.size() / contentLinesPerPage) + 1;
       }

       if(page <= totalPageCount)
       {   
         //beginline
         if(list.isEmpty())
         {
             sender.sendMessage(ChatColor.WHITE + "The list is empty!");
         }
         else
         {
             int i = 0, k = 0;
             page--;

             for (String entry : list)
             {
               k++;
               if ((((page * contentLinesPerPage) + i + 1) == k) && (k != ((page * contentLinesPerPage) + contentLinesPerPage + 1)))
               {
                   i++;
                   String a = entry.replaceAll("%player%", sender.getName());
                   String b = a.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
                   String c = b.replaceAll("%max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
                   String d = c.replaceAll("%prefix%", Strings.getPrefix());
                   String e = d.replaceAll("%page%", String.valueOf(page + 1));
                   String f = e.replaceAll("%page_total%", String.valueOf(totalPageCount));
                   sender.sendMessage(Strings.color(f));
                   
               }
             }
             int point; point = page + 1; if (page >= 1) {
            	 int last; last = point - 1; point = point + 1;
                 sendComponent(sender, Component.textRunnable(sender, "&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help " + last, "help " + point));
            	 } if (page == 0) { 
            		 point = page + 1 + 1;
                	 sendComponent(sender, Component.textRunnable(sender, "&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help " + point));
            	 }
             
         }
  //endline
       }
       else
       {
         sender.sendMessage(ChatColor.YELLOW + "There are only " + ChatColor.WHITE + totalPageCount + ChatColor.YELLOW + " pages!");
       }
   }
   
   public void paginateStaffHelp(Player sender, List<String> list, int page, int contentLinesPerPage)
   {
       int totalPageCount = 1;
       if((list.size() % contentLinesPerPage) == 0)
       {
         if(list.size() > 0)
         {
             totalPageCount = list.size() / contentLinesPerPage;
         }    
       }
       else
       {
         totalPageCount = (list.size() / contentLinesPerPage) + 1;
       }

       if(page <= totalPageCount)
       {   
         //beginline
         if(list.isEmpty())
         {
             sender.sendMessage(ChatColor.WHITE + "The list is empty!");
         }
         else
         {
             int i = 0, k = 0;
             page--;

             for (String entry : list)
             {
               k++;
               if ((((page * contentLinesPerPage) + i + 1) == k) && (k != ((page * contentLinesPerPage) + contentLinesPerPage + 1)))
               {
                   i++;
                   String a = entry.replaceAll("%player%", sender.getName());
                   String b = a.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
                   String c = b.replaceAll("%max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
                   String d = c.replaceAll("%prefix%", Strings.getPrefix());
                   String e = d.replaceAll("%page%", String.valueOf(page + 1));
                   String f = e.replaceAll("%page_total%", String.valueOf(totalPageCount));
                   sender.sendMessage(Strings.color(f));
                   
               }
             }
             int point; point = page + 1; if (page >= 1) {
            	 int last; last = point - 1; point = point + 1;
                 sendComponent(sender, Component.textRunnable(sender, "&7Navigate &b&o&m--&b> &7[", "&c&oBACK&7]", "&7 : [", "&b&oNEXT&7]", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "help staff " + last, "help staff " + point));
            	 } if (page == 0) { 
            		 point = page + 1 + 1;
                	 sendComponent(sender, Component.textRunnable(sender, "&7Navigate &b&o&m--&b> &7[", "&b&oNEXT", "&7]", "&b&oClick to goto the &5&onext page", "help staff " + point));
            	 }
             
         }
  //endline
       }
       else
       {
         sender.sendMessage(ChatColor.YELLOW + "There are only " + ChatColor.WHITE + totalPageCount + ChatColor.YELLOW + " pages!");
       }
   }
   
	
	public static List<String> getEconomyHelp() {
		List<String> help = new ArrayList<>();
		help.add("&6/buy - Buy an ingame item using gold as currency.");
		help.add("&6/sell - Sell an ingame item receiving gold as currency.");
		help.add("&6/pay - Pay an online player in gold.");
		help.add("&6/deposit - Deposit a specified amount of gold into your account.");
		help.add("&6/withdraw - Withdraw a specified amount of gold from your account.");
		return help;
	}
   
   public static Gui guiManager(Player p) {
		Gui gui;
		if (!(guiManager.containsKey(p))) { 

			
			gui = new Gui(p);
			guiManager.put(p, gui);

			return gui;
		} else {
			return guiManager.get(p); 
		}
	}
   
   public static UUID usernameToUUID(String username) {
       for(String s : getAllUserIDs()) {
           PlayerData pd = new PlayerData(UUID.fromString(s));
           if(pd.exists()) {
               FileConfiguration f = pd.getConfig();
               String name = f.getString("USERNAME");
               if(name.equalsIgnoreCase(username)) {
                   return UUID.fromString(s);
               }
           }
       }
       return null;
   }
   
   public static List<String> getAllUserIDs(){
       List<String> users = new ArrayList<String>();
       for(File file : PlayerData.getDataFolder().listFiles()) {
           users.add(file.getName().replace(".yml", ""));
       }
       return users;
   }
   
   public static Gui guiManager(OfflinePlayer p) {
		Gui gui;
		if (!(guiManager.containsKey(p))) { 

			
			gui = new Gui(p);
			guiManager.put((Player) p, gui);

			return gui;
		} else {
			return guiManager.get(p); 
		}
	}
	

	public static void openPlayerInventory(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(target, 54, Strings.color(target.getName() + " : click to update"));
		Inventory targets = target.getInventory();
		ItemStack b1 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm1 = b1.getItemMeta();
		bm1.setDisplayName(Strings.color("&a&lBOOTS &f&l/\\"));
		b1.setItemMeta(bm1);
		ItemStack b2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm2 = b2.getItemMeta();
		bm2.setDisplayName(Strings.color("&a&lLEGGINGS &f&l/\\"));
		b2.setItemMeta(bm2);
		ItemStack b3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm3 = b3.getItemMeta();
		bm3.setDisplayName(Strings.color("&a&lCHEST &f&l/\\"));
		b3.setItemMeta(bm3);
		ItemStack b4 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm4 = b4.getItemMeta();
		bm4.setDisplayName(Strings.color("&a&lHELMET &f&l/\\"));
		b4.setItemMeta(bm4);
		ItemStack b5 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm5 = b5.getItemMeta();
		bm5.setDisplayName(Strings.color("&a&lOFF-HAND &f&l/\\"));
		b5.setItemMeta(bm5);
		ItemStack b6 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta bm6 = b6.getItemMeta();
		bm6.setDisplayName(Strings.color("&f&l<--"));
		bm6.setLore(Arrays.asList(Strings.color("&5Armor contents")));
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

	public static void MOTD(Player player) {
		Config motd = new Config("Messages");
		InputStream in3 = HempfestEssentials.instance.getResource("Messages.yml");
		if (motd.exists()) {
			sendMessage(player, Strings.getMOTD(player));
		} else {
			Config.copy(in3, motd.getFile());
			sendMessage(player, Strings.getPrefix() + "The plugin hasn't yet gotten a chance to create " + '"'
					+ "MOTD.yml" + '"' + "\nCreating it now..");
		}
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(Strings.color(message));
		return;
	}

	public static void sendPlayerInfo(Player player, Player target) {
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, Strings.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		sendMessage(player, "&f&oIP &7&l| &2&o" + target.getAddress().toString().replace("/", ""));
		sendMessage(player, "&f&oUUID &7&l| &2&o" + target.getUniqueId().toString());
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		sendMessage(player, "&f&oGamemode &7&l| &2&o" + target.getGameMode());
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
			GroupAPI api = new GroupAPI();
			sendMessage(player, "&f&oGroup &7&l| &2&o" + api.getGroup(target));
		}
		return;
	}

	public static void updateConfiguration () {
		Config me = new Config("Messages");
		FileConfiguration m = me.getConfig();
		if (!m.getString("Version").equals(HempfestEssentials.getInstance().getDescription().getVersion())) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.hasPermission("hessentials.staff")) {
					sendMessage(all, Strings.getPrefix() + "Configuration updated.");
				}
			}
			 File config = new File(HempfestEssentials.instance.getDataFolder(), "Messages.yml");
			 config.delete();
				InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
			 Config.copy(in, me.getFile());
				
			return;
		}
	}

	public static void remScore(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard emptyBoard = manager.getMainScoreboard();
		p.setScoreboard(emptyBoard);
		Utils.hud.put(p.getName(), Boolean.valueOf(false));

	}

	public static void sendChat_Muted() {
		Config message = new Config(Strings.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		String muted = m.getString("Messages.Player-Responses.Chat-Muted");
		Bukkit.broadcastMessage(Strings.getPrefix() + Strings.color(muted));
	}

	public static void sendChat_Unmuted() {
		Config message = new Config(Strings.getMessagesUsed());
		FileConfiguration m = message.getConfig();
		String unmuted = m.getString("Messages.Player-Responses.Chat-UnMuted");
		Bukkit.broadcastMessage(Strings.getPrefix() + Strings.color(unmuted));
	}

	
	public static boolean day(Player p) {
		Server server = Bukkit.getServer();
		long time = server.getWorld(p.getLocation().getWorld().getName()).getTime();

		if(time > 0 && time < 13000) {
			return true;
		} else {
			return false;
		}
	}
	
	

	public static String trackPlayers(Player p) {
		int cuantos = 0;
		float configdistance = 1000;

		for (Entity entity : p.getNearbyEntities(configdistance, 250.0D, configdistance)) {

			if (entity instanceof Player) {

				cuantos++;
				Player Jugador = (Player) entity;
				if (Jugador.getGameMode() == GameMode.SURVIVAL) {
					int distancia = (int) p.getLocation().distance(Jugador.getLocation());
					return Strings.color("&e&o" + Jugador.getName() + ": &7" + distancia + " &fblocks away.");
				}
			}
		}

		if (cuantos == 0) {
			return Strings.color("&c&oNo near by players.");
		}
		return null;
	}

	public static void sendOfflinePlayerInfo(Player player, OfflinePlayer target) {
		PlayerData data = new PlayerData(target.getUniqueId());
		String IP = data.getConfig().getString("IP-ADDRESS");
		String UUID = target.getUniqueId().toString();
		Date date = new Date(target.getLastPlayed());
		Date date2 = new Date(target.getFirstPlayed());
		sendMessage(player, Strings.getPrefix() + "&a&n" + target.getName() + "'s player information.");
		sendMessage(player, "");
		sendMessage(player, "&f&oIP &7&l| &2&o" + IP);
		sendMessage(player, "&f&oUUID &7&l| &2&o" + UUID);
		sendMessage(player, "&f&oLast Played &7&l| &2&o" + date);
		sendMessage(player, "&f&oFirst Joined &7&l| &2&o" + date2);
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("hEssentialsChat")) {
			
			String group = data.getConfig().getString("GROUP");
			if (group == null) {
				sendMessage(player, "&f&oGroup &7&l| &2&o" + "Default");
			} else {
				sendMessage(player, "&f&oGroup &7&l| &2&o" + group);
			}
			
		}
		return;
	}

	public static void npbMOTD(Player player) {
		Config motd = new Config("MOTD");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		if (motd.exists()) {
			sendMessage(player, Strings.getNPB_MOTD(player));
		} else {
			Config.copy(in3, motd.getFile());
			sendMessage(player, Strings.getPrefix() + "The plugin hasn't yet gotten a chance to create " + '"'
					+ "MOTD.yml" + '"' + "\nCreating it now..");
		}
	}


	public static void reloadConfiguration() {
		Config messages = new Config(Strings.getMessagesUsed());
		Config help = new Config("Help");
		Config claims = new Config("Claims");
		Config heco = new Config("hEconomy");
		Config groups = new Config("Groups");
		Config voting = new Config("bed_voting");
		help.reload();
		messages.reload();
		claims.reload();
		heco.reload();
		groups.reload();
		voting.reload();
	}

	public static void createPlayerConfig(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		f.set("USERNAME", (Object) player.getName());
		f.set("IP-ADDRESS", (Object) player.getAddress().toString().replace("/", ""));
		f.set("Last-Time-Played", 0);
		f.createSection("Private-Homes");
		pd.saveConfig();

	}

	public static void matchUsername(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.getString("USERNAME").matches(player.getName())) {
			f.set("USERNAME", (Object) player.getName());
			pd.saveConfig();
		}
	}

	public static void matchLTP(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.get("Last-Time-Played").equals(player.getLastPlayed())) {
			f.set("Last-Time-Played", (Object) player.getLastPlayed());
			pd.saveConfig();
		}
	}

	public static void matchIP(Player player) {
		UUID uuid = player.getUniqueId();
		PlayerData pd = new PlayerData(uuid);
		FileConfiguration f = pd.getConfig();
		if (!f.getString("IP-ADDRESS").matches(player.getAddress().toString().replace("/", ""))) {
			f.set("IP-ADDRESS", (Object) player.getAddress().toString().replace("/", ""));
			pd.saveConfig();
		}
	}
	
	public static void defaultConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config heco = new Config("hEconomy");
		Config groups = new Config("Groups");
		Config voting = new Config("bed_voting");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("Groups.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in5 = HempfestEssentials.instance.getResource("hEconomy.yml");
		InputStream in6 = HempfestEssentials.instance.getResource("bed_voting.yml");
		Config.copy(in, messages.getFile());
		Config.copy(in2, help.getFile());
		Config.copy(in5, heco.getFile());
		Config.copy(in3, groups.getFile());
		Config.copy(in6, voting.getFile());
	}

	public static void createConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config claims = new Config("Claims");
		Config heco = new Config("hEconomy");
		Config groups = new Config("Groups");
		Config voting = new Config("bed_voting");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("hEconomy.yml");
		InputStream in4 = HempfestEssentials.instance.getResource("Groups.yml");
		InputStream in6 = HempfestEssentials.instance.getResource("Claims.yml");
		InputStream in7 = HempfestEssentials.instance.getResource("bed_voting.yml");
		if (!voting.exists()) {
			Config.copy(in7, voting.getFile());
		}
		if (!messages.exists()) {
			Config.copy(in, messages.getFile());
		}
		if (!help.exists()) {
			Config.copy(in2, help.getFile());
		}
		if (!claims.exists()) {
			Config.copy(in6, claims.getFile());
		}
		if (!heco.exists()) {
			Config.copy(in3, heco.getFile());
		}
		if (!groups.exists()) {
			Config.copy(in4, groups.getFile());
		}

	}

	public static int convertDouble(Double d) {
		return d.intValue();
	}

}
