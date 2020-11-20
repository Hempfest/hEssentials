package org.spigotmc.hessentials;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import com.google.common.collect.Sets;
import com.youtube.hempfest.hempcore.HempCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.CommandClaim;
import org.spigotmc.hessentials.commands.CommandDelhome;
import org.spigotmc.hessentials.commands.CommandHelp;
import org.spigotmc.hessentials.commands.CommandHome;
import org.spigotmc.hessentials.commands.CommandHomes;
import org.spigotmc.hessentials.commands.CommandJump;
import org.spigotmc.hessentials.commands.CommandMessage;
import org.spigotmc.hessentials.commands.CommandOnlineList;
import org.spigotmc.hessentials.commands.CommandReply;
import org.spigotmc.hessentials.commands.CommandSethome;
import org.spigotmc.hessentials.commands.staff.CommandBan;
import org.spigotmc.hessentials.commands.staff.CommandBroadcast;
import org.spigotmc.hessentials.commands.staff.CommandDay;
import org.spigotmc.hessentials.commands.staff.CommandFeed;
import org.spigotmc.hessentials.commands.staff.CommandFly;
import org.spigotmc.hessentials.commands.staff.CommandGMA;
import org.spigotmc.hessentials.commands.staff.CommandGMC;
import org.spigotmc.hessentials.commands.staff.CommandGMS;
import org.spigotmc.hessentials.commands.staff.CommandGMSP;
import org.spigotmc.hessentials.commands.staff.CommandGamemode;
import org.spigotmc.hessentials.commands.staff.CommandGive;
import org.spigotmc.hessentials.commands.staff.CommandGod;
import org.spigotmc.hessentials.commands.staff.CommandHeal;
import org.spigotmc.hessentials.commands.staff.CommandHomelist;
import org.spigotmc.hessentials.commands.staff.CommandInvsee;
import org.spigotmc.hessentials.commands.staff.CommandItem;
import org.spigotmc.hessentials.commands.staff.CommandKick;
import org.spigotmc.hessentials.commands.staff.CommandKickAll;
import org.spigotmc.hessentials.commands.staff.CommandMuteChat;
import org.spigotmc.hessentials.commands.staff.CommandNight;
import org.spigotmc.hessentials.commands.staff.CommandPlayerhome;
import org.spigotmc.hessentials.commands.staff.CommandPowerTool;
import org.spigotmc.hessentials.commands.staff.CommandReload;
import org.spigotmc.hessentials.commands.staff.CommandSocialSpy;
import org.spigotmc.hessentials.commands.staff.CommandSpawnMob;
import org.spigotmc.hessentials.commands.staff.CommandStaff;
import org.spigotmc.hessentials.commands.staff.CommandSuffix;
import org.spigotmc.hessentials.commands.staff.CommandTeleport;
import org.spigotmc.hessentials.commands.staff.CommandUnban;
import org.spigotmc.hessentials.commands.staff.CommandUpdate;
import org.spigotmc.hessentials.commands.staff.CommandWhois;
import org.spigotmc.hessentials.listener.Claim;
import org.spigotmc.hessentials.listener.Events;
import org.spigotmc.hessentials.listener.events.allstreamer.EntityDamagedEvent;
import org.spigotmc.hessentials.util.CommandsRegistrar;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.timers.Region;
import org.spigotmc.hessentials.util.timers.Wild;
import org.spigotmc.hessentials.util.variables.Placeholders;


public class HempfestEssentials extends JavaPlugin {

	// Instance
	public static HempfestEssentials instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Utils u = new Utils();

	// Start server
	public void onEnable() {
		setInstance(this);
		u.createConfiguration();
		u.createCV();
		u.updateInvsee();
		HempCore.registerAllCommandsAutomatically("org.spigotmc.hessentials.commands", this);
		registerEvents();
		Claim.loadClaims();
	}

	public void onDisable() {
		Events.vanishPlayer.clear();
		Events.staffGui.clear();
		Utils.invStorage.clear();
		Wild.saved.clear();
		Region.saved.clear();
	}

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}
	

	public void registerEvents() {
		Region timer = new Region();
		Wild wild = new Wild();
		timer.runTaskTimerAsynchronously(this, 20, 20);
		wild.runTaskTimer(this, 10, 80);
		if (u.checkforPH()) {
			new Placeholders(this).register();
			log.info(String.format("[%s] - PlaceholderAPI FOUND!", getDescription().getName()));
		} else {
			log.info(String.format("[%s] - PlaceholderAPI not detected!", getDescription().getName()));
		}

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Events(), this);
		pm.registerEvents(new EntityDamagedEvent(), this);
		}
	}
/*

Old method to register command (new method does this automatically in the CommandRegistrar class)
	public void registerCommands() {
		u.registerCommand(new CommandSpawnMob());
		u.registerCommand(new CommandPowerTool());
		u.registerCommand(new CommandBroadcast());
		u.registerCommand(new CommandHome());
		u.registerCommand(new CommandHomes());
		u.registerCommand(new CommandHomelist());
		u.registerCommand(new CommandPlayerhome());
		u.registerCommand(new CommandSethome());
		u.registerCommand(new CommandDelhome());
		u.registerCommand(new CommandSuffix());
		u.registerCommand(new CommandHelp());
		u.registerCommand(new CommandDay());
		u.registerCommand(new CommandHeal());
		u.registerCommand(new CommandFeed());
		u.registerCommand(new CommandGod());
		u.registerCommand(new CommandNight());
		//u.registerCommand(new CommandTrack());
		u.registerCommand(new CommandGive());
		u.registerCommand(new CommandItem());
		u.registerCommand(new CommandClaim());
		u.registerCommand(new CommandTeleport());
		u.registerCommand(new CommandGMS());
		u.registerCommand(new CommandGMC());
		u.registerCommand(new CommandGMA());
		u.registerCommand(new CommandGMSP());
		u.registerCommand(new CommandOnlineList());
		u.registerCommand(new CommandMessage());
		u.registerCommand(new CommandReply());
		u.registerCommand(new CommandReload());
		u.registerCommand(new CommandUpdate());
		u.registerCommand(new CommandSocialSpy());
		u.registerCommand(new CommandFly());
		u.registerCommand(new CommandStaff());
		u.registerCommand(new CommandInvsee());
		u.registerCommand(new CommandMuteChat());
		u.registerCommand(new CommandWhois());
		u.registerCommand(new CommandKick());
		u.registerCommand(new CommandKickAll());
		u.registerCommand(new CommandBan());
		u.registerCommand(new CommandUnban());
		u.registerCommand(new CommandJump());
		u.registerCommand(new CommandGamemode());
	}

 */
