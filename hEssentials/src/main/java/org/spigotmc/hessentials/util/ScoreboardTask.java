package org.spigotmc.hessentials.util;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.spigotmc.hessentials.HempfestEssentials;

public class ScoreboardTask extends BukkitRunnable {
	
	
	//Reference
	
	private HempfestEssentials HempfesEssentials;
	private List<String> title;
	private Map<Integer, List<String>> scoreboardContentMap;
	private int maxCycles;
	
	public ScoreboardTask(HempfestEssentials HempfestEssentials, List<String> title, Map<Integer, List<String>> scoreboardContentMap, int maxCycles) {
		this.HempfesEssentials = HempfestEssentials;
		this.scoreboardContentMap = scoreboardContentMap;
		this.maxCycles = maxCycles;
		this.title = title;
	}
	
	private String previousTitle = null;
	private int currentCycle = 0;
	
	@SuppressWarnings("deprecation")
	public void run() {
		org.bukkit.scoreboard.Scoreboard scoreboard = HempfesEssentials.getServer().getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("test", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		if (maxCycles == currentCycle) {
			currentCycle = 0;
		}
		
		if (title.size() > currentCycle) {
			objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', (title.get(currentCycle))));
			previousTitle = title.get(currentCycle);
		} else {
			if (previousTitle == null) {
				objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', ("&cNo Title :(")));
			} else {
				objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', (previousTitle)));
			}
		}
		
		for (Map.Entry<Integer, List<String>> entry : scoreboardContentMap.entrySet()) {
			if (!(entry.getValue().size() < currentCycle) && entry.getValue().size() != 0) {
				String text = entry.getValue().get(currentCycle);
				objective.getScore(ChatColor.translateAlternateColorCodes('&', text)).setScore(entry.getKey());
			}
		}
		
		for (Player player : HempfesEssentials.getServer().getOnlinePlayers()) {
			player.setScoreboard(scoreboard);
		}
		currentCycle++;
	}
	
}
