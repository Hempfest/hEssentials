package org.spigotmc.hessentials;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.common.reflect.ClassPath;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
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
import org.spigotmc.hessentials.commands.staff.*;
import org.spigotmc.hessentials.listener.Claim;
import org.spigotmc.hessentials.listener.events.EntityDamagedEvent;
import org.spigotmc.hessentials.listener.events.Events;
import org.spigotmc.hessentials.util.CommandsRegistrar;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.timers.Region;
import org.spigotmc.hessentials.util.timers.Wild;
import org.spigotmc.hessentials.util.variables.Placeholders;


public class HempfestEssentials extends JavaPlugin {

	//Instance
	public static HempfestEssentials instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Utils u = new Utils();

	//Start server
	public void onEnable() {
		setInstance(this);
		u.createConfiguration();
		u.createCV();
		u.updateInvsee();
		//u.runClaimEvent();
		CommandsRegistrar.registerCommands("org.spigotmc.hessentials.commands", this);
		//registerCommands();
		registerEvents();
		Claim.loadClaims();
	}

	public void onDisable() {
		Events.vanishPlayer.clear();
		Events.staffGui.clear();
		Utils.invStorage.clear();
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

		PluginManager PM = getServer().getPluginManager();

		PM.registerEvents(new Events(), this);
		PM.registerEvents(new EntityDamagedEvent(), this);
	}

	/*
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
}
