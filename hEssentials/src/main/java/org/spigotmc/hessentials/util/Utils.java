package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.PlayerData;

import m.h.clans.util.ClanAPI;

public class Utils {

	public static ClanAPI api;

	private static int taskID;
	private static int MUTED_taskID;
	private static int Tracking_taskID;

	public static boolean Chat_MUTED = true;
	public static HashMap<String, Boolean> hud = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> recieved = new HashMap<String, Boolean>();

	public Utils(ClanAPI api) {
		Utils.api = api;
	}

	// Reply hashmap
	public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
	// Socialspy hashmap
	public static HashMap<Player, String> socialspy = new HashMap<Player, String>();

	public static void msg(Player player, String s) {
		sendMessage(player, s);
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
	public static void resetTracking(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable() {
			public void run() {

				removeScoreboard(p);
				if (Utils.Chat_MUTED) {
					createMutedScoreboard(p);
					animateMutedTitle(p);
				} else if (!Utils.Chat_MUTED) {
					createScoreboard(p);
					animateScoreTitle(p);
				}

			}
		}, 20L * 60);
	}

	public static void resetBoard(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(HempfestEssentials.getInstance(), new Runnable() {
			public void run() {

				removeScoreboard(p);
				createScoreboard(p);
				animateScoreTitle(p);

			}
		}, 20L * 60);
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
		Config motd = new Config("MOTD");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
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
		return;
	}

	public static void removeScoreboard(Player p) {
		BoardLobby board = new BoardLobby(p.getUniqueId());
		if (board.hasID())
			board.stop();
	}

	public static void remScore(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard emptyBoard = manager.getMainScoreboard();
		p.setScoreboard(emptyBoard);
		Utils.hud.put(p.getName(), Boolean.valueOf(false));

	}

	public static void animateScoreTitle(final Player p) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.instance, new Runnable() {

			int count = 0;
			BoardLobby board = new BoardLobby(p.getUniqueId());

			public void run() {
				if (!board.hasID())
					board.setID(taskID);
				if (count == 15)
					count = 0;

				switch (count) {
				case 0:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2h&a&oEs&2s&a&oentials &7>>"));
					break;
				case 1:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&oh&2E&a&oss&2e&a&ontials &7>>"));
					break;
				case 2:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohE&2s&a&ose&2n&a&otials &7>>"));
					break;
				case 3:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEs&2s&a&oent&2i&a&oals &7>>"));
					break;
				case 4:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEss&2e&a&onti&2a&a&ols &7>>"));
					break;
				case 5:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEsse&2n&a&otia&2l&a&os &7>>"));
					break;
				case 6:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssen&2t&a&oial&2s &7>>"));
					break;
				case 7:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssent&2i&a&oals &7>>"));
					break;
				case 8:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssenti&2a&a&ols &7>>"));
					break;
				case 9:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentia&2l&a&os &7>>"));
					break;
				case 10:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssential&2s &7>>"));
					break;
				case 11:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentials &7>>"));
					break;
				case 12:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + " &7>>"));
					break;
				case 13:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + " &7>>"));
					break;
				case 14:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + " &7>>"));
					createScoreboard(p);
					break;
				}
				count++;
			}

		}, 0, 5);
	}

	public static void sendChat_Muted() {
		Config message = new Config("Messages");
		FileConfiguration m = message.getConfig();
		String muted = m.getString("Messages.Player-Responses.Chat-Muted");
		Bukkit.broadcastMessage(Strings.getPrefix() + Strings.color(muted));
	}

	public static void sendChat_Unmuted() {
		Config message = new Config("Messages");
		FileConfiguration m = message.getConfig();
		String unmuted = m.getString("Messages.Player-Responses.Chat-UnMuted");
		Bukkit.broadcastMessage(Strings.getPrefix() + Strings.color(unmuted));
	}

	public static void animateMutedTitle(final Player p) {
		MUTED_taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.instance, new Runnable() {

			int count = 0;
			BoardMuted board = new BoardMuted(p.getUniqueId());

			public void run() {
				if (!board.hasID())
					board.setID(MUTED_taskID);
				if (count == 15)
					count = 0;

				switch (count) {
				case 0:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2h&a&oEssentials &7>>"));
					break;
				case 1:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&oh&2E&a&ossentials &7>>"));
					break;
				case 2:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohE&2s&a&osentials &7>>"));
					break;
				case 3:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEs&2s&a&oentials &7>>"));
					break;
				case 4:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEss&2e&a&ontials &7>>"));
					break;
				case 5:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEsse&2n&a&otials &7>>"));
					break;
				case 6:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssen&2t&a&oials &7>>"));
					break;
				case 7:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssent&2i&a&oals &7>>"));
					break;
				case 8:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssenti&2a&a&ols &7>>"));
					break;
				case 9:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentia&2l&a&os &7>>"));
					break;
				case 10:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssential&2s &7>>"));
					break;
				case 11:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentials &7>>"));
					break;
				case 12:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &4" + "MUTED" + " &7>>"));
					break;
				case 13:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &4" + "MUTED" + " &7>>"));
					break;
				case 14:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &4" + "MUTED" + " &7>>"));
					createMutedScoreboard(p);
					break;
				}
				count++;
			}

		}, 0, 5);
	}

	public static void updateLobbyBoard() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			createScoreboard(p);
			animateScoreTitle(p);
			Utils.hud.put(p.getName(), Boolean.valueOf(true));
		}
	}

	public static void animateTrackingTitle(final Player p) {
		Tracking_taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.instance, new Runnable() {

			int count = 0;
			BoardTrack board = new BoardTrack(p.getUniqueId());

			public void run() {
				if (!board.hasID())
					board.setID(Tracking_taskID);
				if (count == 15)
					count = 0;

				switch (count) {
				case 0:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &2h&a&oEssentials &7>>"));
					break;
				case 1:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&oh&2E&a&ossentials &7>>"));
					break;
				case 2:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohE&2s&a&osentials &7>>"));
					break;
				case 3:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEs&2s&a&oentials &7>>"));
					break;
				case 4:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEss&2e&a&ontials &7>>"));
					break;
				case 5:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEsse&2n&a&otials &7>>"));
					break;
				case 6:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssen&2t&a&oials &7>>"));
					break;
				case 7:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssent&2i&a&oals &7>>"));
					break;
				case 8:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssenti&2a&a&ols &7>>"));
					break;
				case 9:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentia&2l&a&os &7>>"));
					break;
				case 10:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssential&2s &7>>"));
					break;
				case 11:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &a&ohEssentials &7>>"));
					break;
				case 12:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &6" + "Tracking" + " &7>>"));
					break;
				case 13:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &6&l" + "Tracking" + " &7>>"));
					break;
				case 14:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.setDisplayName(Strings.color("&7<< &e&l" + "Tracking" + " &7>>"));
					createTrackerScoreboard(p);
					break;
				}
				count++;
			}

		}, 0, 5);
	}

	public static void createTrackerScoreboard(Player p) {
		int cuantos = 0;
		float configdistance = 1000;
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("hEssentials-a1", "dummy", Strings.color("&7<< &e&lTracking &7>>"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		for (Entity entity : p.getNearbyEntities(configdistance, 250.0D, configdistance)) {

			if (entity instanceof Player) {

				cuantos++;
				Player Jugador = (Player) entity;
				if (Jugador.getGameMode() == GameMode.SURVIVAL) {

					p.setCompassTarget(Jugador.getLocation());
					int distancia = (int) p.getLocation().distance(Jugador.getLocation());
					Score score = obj
							.getScore(Strings.color("&a&o" + Jugador.getName() + ": &7" + distancia + " &fblocks"));
					score.setScore(10);
					p.sendMessage(Jugador.getName() + " is " + distancia + "blocks away");
				}
			}
		}

		if (cuantos == 0) {
			Score score = obj.getScore(Strings.color("&c&oNo near by players."));
			score.setScore(10);
		}
		p.setScoreboard(board);
	}

	public static void trackPlayers(Player p) {
		int cuantos = 0;
		float configdistance = 1000;

		for (Entity entity : p.getNearbyEntities(configdistance, 250.0D, configdistance)) {

			if (entity instanceof Player) {

				cuantos++;
				Player Jugador = (Player) entity;
				if (Jugador.getGameMode() == GameMode.SURVIVAL) {
					int distancia = (int) p.getLocation().distance(Jugador.getLocation());
					p.sendMessage(Strings.color("&a&o" + Jugador.getName() + ": &7" + distancia + " &fblocks away."));
				}
			}
		}

		if (cuantos == 0) {
			p.sendMessage(Strings.color("&c&oNo near by players."));
		}
	}

	public static void createMutedScoreboard(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("hEssentials-a1", "dummy", Strings.color("&7<< &4&oMUTED &7>>"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score = obj.getScore(Strings.color("&f&lChat: &4Muted"));
		score.setScore(10);

		p.setScoreboard(board);
	}

	public static void createScoreboard(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("hEssentials-a1", "dummy",
				Strings.color("&7<< &a&ohEssentials &7>>"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score = obj.getScore(Lists.sendSB_Line1(p));
		Score score2 = obj.getScore(Lists.sendSB_Line2(p));
		Score score3 = obj.getScore(Lists.sendSB_Line3(p));
		Score score4 = obj.getScore(Lists.sendSB_Line4(p));
		Score score5 = obj.getScore(Lists.sendSB_Line5(p));
		Score score6 = obj.getScore(Lists.sendSB_Line6(p));
		Score score7 = obj.getScore(Lists.sendSB_Line7(p));
		Score score8 = obj.getScore(Lists.sendSB_Line8(p));
		Score score9 = obj.getScore(Lists.sendSB_Line9(p));
		Score score10 = obj.getScore(Lists.sendSB_Line10(p));
		Score score11 = obj.getScore(Strings.getChatMuted());
		if (Utils.Chat_MUTED) {
			score11.setScore(11);
		}
		score.setScore(10);
		// if (Utils.SB_Twolines())

		score2.setScore(9);

		// if (Utils.SB_Threelines())

		score3.setScore(8);

		// if (Utils.SB_Fourlines())

		score4.setScore(7);

		// if (Utils.SB_Fivelines())

		score5.setScore(6);

		// if (Utils.SB_Sixlines())

		score6.setScore(5);

		// if (Utils.SB_Sevenlines())

		score7.setScore(4);

		// if (Utils.SB_Eightlines())

		score8.setScore(3);

		// if (Utils.SB_Ninelines())

		score9.setScore(2);

		// if (Utils.SB_Tenlines())

		score10.setScore(1);

		p.setScoreboard(board);
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
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config motd = new Config("MOTD");
		Config score = new Config("Scoreboard");
		help.reload();
		motd.reload();
		messages.reload();
		score.reload();
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
		Config motd = new Config("MOTD");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		Config.copy(in, messages.getFile());
		Config.copy(in2, help.getFile());
		Config.copy(in3, motd.getFile());
	}

	public static void createConfiguration() {
		Config messages = new Config("Messages");
		Config help = new Config("Help");
		Config motd = new Config("MOTD");
		Config score = new Config("Scoreboard");
		InputStream in = HempfestEssentials.instance.getResource("Messages.yml");
		InputStream in2 = HempfestEssentials.instance.getResource("Help.yml");
		InputStream in3 = HempfestEssentials.instance.getResource("MOTD.yml");
		InputStream in4 = HempfestEssentials.instance.getResource("Scoreboard.yml");
		if (!messages.exists()) {
			Config.copy(in, messages.getFile());
		}
		if (!help.exists()) {
			Config.copy(in2, help.getFile());
		}
		if (!motd.exists()) {
			Config.copy(in3, motd.getFile());
		}
		if (!score.exists()) {
			Config.copy(in4, score.getFile());
		}

	}

	public static int convertDouble(Double d) {
		return d.intValue();
	}

}
