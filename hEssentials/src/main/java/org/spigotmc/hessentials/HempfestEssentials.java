package org.spigotmc.hessentials;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.CommandBuy;
import org.spigotmc.hessentials.commands.CommandDeposit;
import org.spigotmc.hessentials.commands.CommandHelp;
import org.spigotmc.hessentials.commands.CommandMessage;
import org.spigotmc.hessentials.commands.CommandOnlineList;
import org.spigotmc.hessentials.commands.CommandPay;
import org.spigotmc.hessentials.commands.CommandReply;
import org.spigotmc.hessentials.commands.CommandSell;
import org.spigotmc.hessentials.commands.CommandTrack;
import org.spigotmc.hessentials.commands.CommandWithdraw;
import org.spigotmc.hessentials.commands.claim.ClaimUtil;
import org.spigotmc.hessentials.commands.claim.CommandClaim;
import org.spigotmc.hessentials.commands.economy.CommandEconomy;
import org.spigotmc.hessentials.commands.economy.Economy;
import org.spigotmc.hessentials.commands.homes.CommandDelhome;
import org.spigotmc.hessentials.commands.homes.CommandHome;
import org.spigotmc.hessentials.commands.homes.CommandHomes;
import org.spigotmc.hessentials.commands.homes.CommandSethome;
import org.spigotmc.hessentials.commands.staff.CommandBan;
import org.spigotmc.hessentials.commands.staff.CommandCFUpdate;
import org.spigotmc.hessentials.commands.staff.CommandDay;
import org.spigotmc.hessentials.commands.staff.CommandFly;
import org.spigotmc.hessentials.commands.staff.CommandGMC;
import org.spigotmc.hessentials.commands.staff.CommandGMS;
import org.spigotmc.hessentials.commands.staff.CommandGamemode;
import org.spigotmc.hessentials.commands.staff.CommandGive;
import org.spigotmc.hessentials.commands.staff.CommandHomelist;
import org.spigotmc.hessentials.commands.staff.CommandInvsee;
import org.spigotmc.hessentials.commands.staff.CommandItem;
import org.spigotmc.hessentials.commands.staff.CommandKick;
import org.spigotmc.hessentials.commands.staff.CommandKickAll;
import org.spigotmc.hessentials.commands.staff.CommandMuteChat;
import org.spigotmc.hessentials.commands.staff.CommandNight;
import org.spigotmc.hessentials.commands.staff.CommandPlayerhome;
import org.spigotmc.hessentials.commands.staff.CommandReload;
import org.spigotmc.hessentials.commands.staff.CommandSocialSpy;
import org.spigotmc.hessentials.commands.staff.CommandSuffix;
import org.spigotmc.hessentials.commands.staff.CommandTeleport;
import org.spigotmc.hessentials.commands.staff.CommandUnban;
import org.spigotmc.hessentials.commands.staff.CommandWhois;
import org.spigotmc.hessentials.commands.tprequest.CommandTpRequest;
import org.spigotmc.hessentials.events.PlayerListener;
import org.spigotmc.hessentials.util.Placeholders;
import org.spigotmc.hessentials.util.Region;
import org.spigotmc.hessentials.util.Utils;
import org.spigotmc.hessentials.util.variables.Checks;



public class HempfestEssentials extends JavaPlugin {

	//Instance
	public static HempfestEssentials instance;
	private static final Logger log = Logger.getLogger("Minecraft");
	//Start server
	public void onEnable() {
		setInstance(this);
		Utils.createConfiguration();
		Utils.updateInvsee();
		registerCommands();
		registerTabCommands();
		registerEvents();
		runTimers();
	
	}
	

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}
	

	public void registerEvents() {

		getServer().getPluginManager().registerEvents(new PlayerListener(), getInstance());
		getServer().getPluginManager().registerEvents(new ClaimUtil(), getInstance());
		getServer().getPluginManager().registerEvents(new Economy(), getInstance());
		if (!Checks.economyEnabled()) {
            log.severe(String.format("[%s] - Economy disabled!", getDescription().getName()));
        }
		if (Checks.checkforPH()) {
			new Placeholders(this).register();
			log.info(String.format("[%s] - PlaceholderAPI FOUND!", new Object[] { getDescription().getName() }));
		} else {
			log.info(String.format("[%s] - PlaceholderAPI not detected!", new Object[] { getDescription().getName() }));
		}
	}
	
	public void runTimers() {
		 Region Refresh = new Region();
		 Refresh.runTaskTimerAsynchronously(this, 20L, 20L);
	}

	public void registerCommands() {
		Utils.registerCommand(new CommandHome());
		Utils.registerCommand(new CommandHomes());
		Utils.registerCommand(new CommandSuffix());
		Utils.registerCommand(new CommandHomelist());
		Utils.registerCommand(new CommandPlayerhome());
		Utils.registerCommand(new CommandSethome());
		Utils.registerCommand(new CommandDelhome());
		Utils.registerCommand(new CommandHelp());
		Utils.registerCommand(new CommandBuy());
		Utils.registerCommand(new CommandSell());
		Utils.registerCommand(new CommandWithdraw());
		Utils.registerCommand(new CommandDeposit());
		Utils.registerCommand(new CommandPay());
		Utils.registerCommand(new CommandDay());
		Utils.registerCommand(new CommandNight());
		Utils.registerCommand(new CommandTrack());
		Utils.registerCommand(new CommandGive());
		Utils.registerCommand(new CommandItem());
		Utils.registerCommand(new CommandClaim());
		Utils.registerCommand(new CommandTeleport());
		Utils.registerCommand(new CommandGMS());
		Utils.registerCommand(new CommandGMC());
		Utils.registerCommand(new CommandOnlineList());
		Utils.registerCommand(new CommandMessage());
		Utils.registerCommand(new CommandReply());
		Utils.registerCommand(new CommandReload());
		Utils.registerCommand(new CommandCFUpdate());
		Utils.registerCommand(new CommandSocialSpy());
		Utils.registerCommand(new CommandFly());
		Utils.registerCommand(new CommandEconomy());
		Utils.registerCommand(new CommandInvsee());
		Utils.registerCommand(new CommandMuteChat());
		Utils.registerCommand(new CommandWhois());
		Utils.registerCommand(new CommandKick());
		Utils.registerCommand(new CommandKickAll());
		Utils.registerCommand(new CommandBan());
		Utils.registerCommand(new CommandUnban());
	}
	
	public void registerTabCommands() {
		Utils.registerTabCommand("teleportrequest", new CommandTpRequest(), new CommandTpRequest());
		Utils.registerTabCommand("gamemode", new CommandGamemode(), new CommandGamemode());
	}

}
