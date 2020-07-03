package org.spigotmc.hessentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.hessentials.commands.HelpCommand;
import org.spigotmc.hessentials.commands.MessageCommand;
import org.spigotmc.hessentials.commands.OnlineListCommand;
import org.spigotmc.hessentials.commands.ReplyCommand;
import org.spigotmc.hessentials.commands.staff.CFUpdateCommand;
import org.spigotmc.hessentials.commands.staff.ReloadCommand;
import org.spigotmc.hessentials.commands.staff.StaffHelpCommand;
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
		Utils.registerCommand(new HelpCommand());
		Utils.registerCommand(new OnlineListCommand());
		Utils.registerCommand(new MessageCommand());
		Utils.registerCommand(new ReplyCommand());
		Utils.registerCommand(new ReloadCommand());
		Utils.registerCommand(new CFUpdateCommand());
		Utils.registerCommand(new StaffHelpCommand());
	}

}
