package org.spigotmc.hessentials;

import com.youtube.hempfest.hempcore.command.CommandBuilder;
import com.youtube.hempfest.hempcore.event.EventBuilder;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.staff.CommandGod;
import org.spigotmc.hessentials.listener.EventListener;
import org.spigotmc.hessentials.util.Claim;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.timers.SyncRegionArea;
import org.spigotmc.hessentials.util.timers.SyncWarpGate;
import org.spigotmc.hessentials.util.variables.Placeholders;


public class HempfestEssentials extends JavaPlugin {

	// Instance
	public static HempfestEssentials instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Utils u = new Utils();
	public HashMap<String[], int[]> claimMap = new HashMap<>();

	// Start server
	public void onEnable() {
		setInstance(this);
		u.createConfiguration();
		u.createCV();
		u.updateInvsee();
		CommandBuilder builder = new CommandBuilder(this);
		EventBuilder ebuilder = new EventBuilder(this);
		builder.compileFields("org.spigotmc.hessentials.commands");
		ebuilder.compileFields("org.spigotmc.hessentials.listener");
		registerTimers();
		new Claim().loadClaims();
		new WorldCreator("Build").createWorld();
	}

	public void onDisable() {
		EventListener.vanishPlayer.clear();
		EventListener.staffGui.clear();
		Utils.invStorage.clear();
		CommandGod.GodPlayers.clear();
	}

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}


	public void registerTimers() {
		SyncRegionArea timer = new SyncRegionArea();
		SyncWarpGate syncWarpGate = new SyncWarpGate();
		timer.runTaskTimerAsynchronously(this, 20, 20);
		syncWarpGate.runTaskTimer(this, 10, 80);
		if (u.checkforPH()) {
			new Placeholders(this).register();
			log.info(String.format("[%s] - PlaceholderAPI FOUND!", getDescription().getName()));
		} else {
			log.info(String.format("[%s] - PlaceholderAPI not detected!", getDescription().getName()));
		}
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
