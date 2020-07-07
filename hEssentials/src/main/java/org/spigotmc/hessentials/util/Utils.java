package org.spigotmc.hessentials.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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

	public static void removeScooreboard(Player p) {
		LobbyBoard board = new LobbyBoard(p.getUniqueId());
		if (board.hasID())
			board.stop();
	}

	public static void animateScoreTitle(final Player p) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HempfestEssentials.instance, new Runnable() {

			int count = 0;
			LobbyBoard board = new LobbyBoard(p.getUniqueId());

			public void run() {
				if (!board.hasID())
					board.setID(taskID);
				if (count == 15)
					count = 0;

				switch (count) {
				case 0:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &2h&a&oEssentials &7>>"));
					break;
				case 1:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&oh&2E&a&ossentials &7>>"));
					break;
				case 2:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohE&2s&a&osentials &7>>"));
					break;
				case 3:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEs&2s&a&oentials &7>>"));
					break;
				case 4:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEss&2e&a&ontials &7>>"));
					break;
				case 5:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEsse&2n&a&otials &7>>"));
					break;
				case 6:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssen&2t&a&oials &7>>"));
					break;
				case 7:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssent&2i&a&oals &7>>"));
					break;
				case 8:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssenti&2a&a&ols &7>>"));
					break;
				case 9:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssentia&2l&a&os &7>>"));
					break;
				case 10:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssential&2s &7>>"));
					break;
				case 11:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &a&ohEssentials &7>>"));
					break;
				case 12:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + "&7>>"));
					break;
				case 13:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + "&7>>"));
					break;
				case 14:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Strings.color("&7<< &2" + Strings.getScorePrefix() + "&7>>"));
					createScoreboard(p);
					break;
				}
				count++;
			}

		}, 0, 10);
	}
	
	public static void updateLobbyBoard() {
		for (Player p : Bukkit.getOnlinePlayers()) {
		createScoreboard(p);
		animateScoreTitle(p);
		}
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
		score.setScore(10);
		//if (Utils.SB_Twolines()) 
			
			score2.setScore(9);
		
		
		//if (Utils.SB_Threelines()) 
			
			score3.setScore(8);
		
		
		//if (Utils.SB_Fourlines()) 
			
			score4.setScore(7);
		
		
		//if (Utils.SB_Fivelines()) 
			
			score5.setScore(6);
		
		
		//if (Utils.SB_Sixlines()) 
			
			score6.setScore(5);
		
		
		//if (Utils.SB_Sevenlines()) 
		
			score7.setScore(4);
		
		
		//if (Utils.SB_Eightlines()) 
		
			score8.setScore(3);
		
		
		//if (Utils.SB_Ninelines()) 
			
			score9.setScore(2);
		
		
		//if (Utils.SB_Tenlines()) 
			
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
			f.set("USERNAME", (Object) player.getLastPlayed());
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

	public static boolean allPagesActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")
				&& help.getConfig().getString("Three-Pages?").equals("yes")) {
			return true;
		}
		return false;
	}

	public static boolean onePageActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("no")
				&& help.getConfig().getString("Three-Pages?").equals("no")) {
			return true;
		}
		return false;
	}

	public static boolean twoPagesActive() {
		Config help = new Config("Help");
		if (help.getConfig().getString("Two-Pages?").equals("yes")) {
			return true;
		}
		return false;
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
