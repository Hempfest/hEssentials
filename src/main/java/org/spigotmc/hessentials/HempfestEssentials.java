package org.spigotmc.hessentials;

import java.util.logging.Logger;
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
import org.spigotmc.hessentials.commands.CommandTrack;
import org.spigotmc.hessentials.commands.staff.*;
import org.spigotmc.hessentials.listener.events.Events;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.timers.Region;
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
		registerCommands();
		registerEvents();
	}

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}
	

	public void registerEvents() {
		Region timer = new Region();
		timer.runTaskTimerAsynchronously(this, 2, 10);
		getServer().getPluginManager().registerEvents(new Events(), getInstance());
		if (u.checkforPH()) {
			new Placeholders(this).register();
			log.info(String.format("[%s] - PlaceholderAPI FOUND!", getDescription().getName()));
		} else {
			log.info(String.format("[%s] - PlaceholderAPI not detected!", getDescription().getName()));
		}
	}

	public void registerCommands() {
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
		u.registerCommand(new CommandNight());
		u.registerCommand(new CommandTrack());
		u.registerCommand(new CommandGive());
		u.registerCommand(new CommandItem());
		u.registerCommand(new CommandClaim());
		u.registerCommand(new CommandTeleport());
		u.registerCommand(new CommandGMS());
		u.registerCommand(new CommandGMC());
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

}
