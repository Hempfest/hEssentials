package org.spigotmc.hessentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.CommandHelp;
import org.spigotmc.hessentials.commands.CommandMessage;
import org.spigotmc.hessentials.commands.CommandOnlineList;
import org.spigotmc.hessentials.commands.CommandReply;
import org.spigotmc.hessentials.commands.staff.CommandBan;
import org.spigotmc.hessentials.commands.staff.CommandCFUpdate;
import org.spigotmc.hessentials.commands.staff.CommandFly;
import org.spigotmc.hessentials.commands.staff.CommandGMC;
import org.spigotmc.hessentials.commands.staff.CommandGMS;
import org.spigotmc.hessentials.commands.staff.CommandGamemode;
import org.spigotmc.hessentials.commands.staff.CommandInvsee;
import org.spigotmc.hessentials.commands.staff.CommandKick;
import org.spigotmc.hessentials.commands.staff.CommandReload;
import org.spigotmc.hessentials.commands.staff.CommandSocialSpy;
import org.spigotmc.hessentials.commands.staff.CommandStaffHelp;
import org.spigotmc.hessentials.commands.staff.CommandUnban;
import org.spigotmc.hessentials.commands.staff.CommandWhois;
import org.spigotmc.hessentials.events.PlayerListener;
import org.spigotmc.hessentials.util.Utils;



public class HempfestEssentials extends JavaPlugin {

	//Instance
	public static HempfestEssentials instance;
	
	
	//Start server
	public void onEnable() {
		setInstance(this);
		Utils.createConfiguration();
		registerCommands();
		registerEvents();
		Utils.updateLobbyBoard();
	}
	

	public static HempfestEssentials getInstance() {
		return instance;
	}

	public static void setInstance(HempfestEssentials instance) {
		HempfestEssentials.instance = instance;
	}
	

	public void registerEvents() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), getInstance());
		
	}

	public void registerCommands() {
		Utils.registerCommand(new CommandHelp());
		Utils.registerCommand(new CommandGMS());
		Utils.registerCommand(new CommandGMC());
		Utils.registerCommand(new CommandOnlineList());
		Utils.registerCommand(new CommandMessage());
		Utils.registerCommand(new CommandReply());
		Utils.registerCommand(new CommandReload());
		Utils.registerCommand(new CommandCFUpdate());
		Utils.registerCommand(new CommandStaffHelp());
		Utils.registerCommand(new CommandSocialSpy());
		Utils.registerCommand(new CommandFly());
		Utils.registerCommand(new CommandInvsee());
		//Utils.registerCommand(new CommandGamemode());
		Utils.registerTabCommand("gamemode", new CommandGamemode(), new CommandGamemode());
		Utils.registerCommand(new CommandWhois());
		Utils.registerCommand(new CommandKick());
		Utils.registerCommand(new CommandBan());
		Utils.registerCommand(new CommandUnban());
	}

}
