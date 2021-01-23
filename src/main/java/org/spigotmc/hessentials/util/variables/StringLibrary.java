package org.spigotmc.hessentials.util.variables;

import com.youtube.hempfest.hempcore.formatting.string.ColoredString;
import java.nio.charset.StandardCharsets;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.spigotmc.hessentials.HempfestEssentials;
import org.spigotmc.hessentials.configuration.Config;
import org.spigotmc.hessentials.configuration.DataManager;
import org.spigotmc.hessentials.util.heHook;

public class StringLibrary {

	static heHook api = heHook.getHook();

	public String getUserTitle(Player p) {
		DataManager dm = new DataManager();
		api = heHook.getPlayerHook(p);
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Claim");
		return color(text);
	}

	public String getUserSubTitle(Player p) {
		DataManager dm = new DataManager();
		api = heHook.getPlayerHook(p);
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Wilderness");
		return color(text);
	}

	public String getHomeCreated(Player p, String home) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Player-Home-Create").replaceAll("%home%", home);
		return color(text);
	}

	public String getHomeUpdated(Player p, String home) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Player-Home-Update").replaceAll("%home%", home);
		return color(text);
	}

	public String getHomeDeleted(Player p, String home) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Player-Home-Delete").replaceAll("%home%", home);
		return color(text);
	}

	public String getCannotBreak(Player p, String claimName, String claimOwner) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Claim-Denied-Break").replaceAll("%owner%", claimOwner);
		return color(text.replaceAll("%land%", claimName));
	}

	public String getCannotUse(Player p, String claimName, String claimOwner) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Claim-Denied-Use").replaceAll("%owner%", claimOwner);
		return color(text.replaceAll("%land%", claimName));
	}

	public String getCannotPlace(Player p, String claimName, String claimOwner) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Claim-Denied-Place").replaceAll("%owner%", claimOwner);
		return color(text.replaceAll("%land%", claimName));
	}

	public String getCannotPlaceTNT(Player p, String claimName, String claimOwner) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String text = mess.getString("Messages.Player-Responses.Inside-Claim-Denied-PlaceTNT").replaceAll("%owner%", claimOwner);
		return color(text.replaceAll("%land%", claimName));
	}

	public String getClaimColor(Player p) {
		api = heHook.getPlayerHook(p);
		if (api.pc.isClaimOwner())
			return "&a&o";

		if (api.pc.isClaimUser())
			return "&e&o";

		if (!api.pc.isClaimOwner() || !api.pc.isClaimUser())
			return "&c&o";
		return "&r";
	}

	public String getAREA(Player p, Location loc) {
		api = heHook.getPlayerHook(p);
		if (api.pc.isInClaim(loc)) {
			return getClaimColor(p) + api.pc.getClaimName(loc);
		} else if (!api.pc.isInClaim(loc)) {
			return "&4&lWilderness";
		}
		return "&f&oN/A";
	}

	public void sendReceivedMenu(Player p) {
		p.sendMessage(getPrefix() + color("&a&lSuccessfully received the Menu Item's."));
	}

	public void sendNoPermission(Player p, String permission) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		p.sendMessage(getPrefix() + color(mess.getString("Messages.Player-Responses.No-Permission").replaceAll("%permnode%", permission)));
	}

	public String getPrefix() {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		return color(mess.getString("Messages.Prefix") + " &f");
	}

	public String getFirstJoinMSG(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.First-Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}

	public String getSocialSpyOn(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Social-Spy-Activated").replaceAll("%player%", player.getName());
		return color(MSG.replaceAll("%prefix%", getPrefix()));
	}

	public String getSocialSpyOff(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Social-Spy-Deactivated").replaceAll("%player%", player.getName());
		return color(MSG.replaceAll("%prefix%", getPrefix()));
	}

	public String getJoinMSG(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Join").replaceAll("%player%", player.getName());
		return color(MSG);
	}

	public String getLeaveMSG(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration m = messages.getConfig();
		String MSG = m.getString("Messages.Player-Responses.Player-Leave").replaceAll("%player%", player.getName());
		return color(MSG);
	}

	public String getMessagesUsed() {
		return "Messages";
	}

	public String getInvalidUsage() {
		return "Invalid usage: /";
	}

	public String getChatMuted() {
		return color("&f&oChat: &4Muted");
	}

	public String infinity() {
		String inf = null;
		inf = new String(Character.toString('\u221e').getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		return inf;
	}

	public String getMOTD(Player player) {
		DataManager dm = new DataManager();
		Config motd = dm.getMisc(getMessagesUsed());
		FileConfiguration m = motd.getConfig();
		String section = m.getString("Message-of-the-day").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = section.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", motd.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return color(message);
	}

	public String getNPB_MOTD(Player player) {
		DataManager dm = new DataManager();
		Config motd = dm.getMisc(getMessagesUsed());
		FileConfiguration m = motd.getConfig();
		String section = m.getString("MOTD-First-Join").replaceAll("%online%",
				String.valueOf(Bukkit.getOnlinePlayers().size()));
		String players = section.replaceAll("%player%", player.getName());
		String prefix = players.replaceAll("%prefix%", motd.getConfig().getString("Messages.Prefix"));
		String max = prefix.replaceAll("%max%",
				String.valueOf(HempfestEssentials.getInstance().getServer().getMaxPlayers()));
		String message = max.replaceAll("%next%", "\n");
		return message;
	}

	public String messageSentMSG(Player player, Player target) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Message-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String messageRecievedMSG(Player player, Player target) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Message-Revieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String messageSpyMSG(Player player, Player target) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Message-Spy").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String replySentMSG(Player player, Player target) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Send").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String replyRecievedMSG(Player player, Player target) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Player-Reply-Recieve").replaceAll("%target%", target.getName());
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String socialSpyActivateMSG(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Activated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	public String socialSpyDeactivateMSG(Player player) {
		DataManager dm = new DataManager();
		Config messages = dm.getMisc(getMessagesUsed());
		FileConfiguration mess = messages.getConfig();
		String start = mess.getString("Messages.Player-Responses.Social-Spy-Deactivated");
		String next = start.replaceAll("%player%", player.getName());
		String middle = next.replaceAll("%next%", "\n");
		String last = middle.replaceAll("%prefix%", getPrefix());
		return color(last);
	}

	@SuppressWarnings("deprecation")
	public void goRequest(String Command, String Command2, Player s, Player p) {

		TextComponent text = new TextComponent("§2|§7> §2Click a button to respond. ");
		TextComponent click = new TextComponent("§a[§nACCEPT§a]");
		TextComponent clickb = new TextComponent(" §7| ");
		TextComponent click2 = new TextComponent("§4[§nDENY§4]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text("§3Click to accept the request from '" + p.getName() + "'."))));
		click2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text("§3Click to deny the request from '" + p.getName() + "'."))));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command));
		click2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command2));
		text.addExtra(click);
		text.addExtra(clickb);
		text.addExtra(click2);
		p.spigot().sendMessage(text);

	}

	public void goRequest2(String Command, String Command2, Player s, Player p) {

		TextComponent text = new TextComponent("§2|§7> §2Click a button to respond. ");
		TextComponent click = new TextComponent("§a[§nACCEPT§a]");
		TextComponent clickb = new TextComponent(" §7| ");
		TextComponent click2 = new TextComponent("§4[§nDENY§4]");
		click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Click to accept the request from '" + p.getName() + "'.")).create()));
		click2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3Click to deny the request from '" + p.getName() + "'.")).create()));
		click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command));
		click2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command2));
		text.addExtra(click);
		text.addExtra(clickb);
		text.addExtra(click2);
		p.spigot().sendMessage(text);

	}


	public String color(String text) {
		if (Bukkit.getServer().getVersion().contains("1.16")) {
			return new ColoredString(text, ColoredString.ColorType.HEX).toString();
		} else
			return new ColoredString(text, ColoredString.ColorType.MC).toString();
	}
}
