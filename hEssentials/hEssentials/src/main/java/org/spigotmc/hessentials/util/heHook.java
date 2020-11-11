package org.spigotmc.hessentials.util;

import org.bukkit.entity.Player;
import org.spigotmc.hessentials.listener.Claim;
import org.spigotmc.hessentials.util.variables.StringLibrary;

public class heHook {
	static Player pl;
	public Utils u;
	public Claim pc;
	public StringLibrary lib;

	private static heHook api = new heHook(new Utils(), new StringLibrary());

	public static heHook getHook() {
		setApi(api);
		return getApi();
	}

	public static heHook getPlayerHook(Player p) {
		return new heHook(new Utils(), new StringLibrary(), new Claim(p));
	}

	private heHook(Utils u, StringLibrary lib) {
		this.u = u;
		this.lib = lib;
	}

	private heHook(Utils u, StringLibrary lib, Claim pc) {
		this.u = u;
		this.lib = lib;
		this.pc = pc;
	}

	private static heHook getApi() {
		return api;
	}

	private static void setApi(heHook api) {
		heHook.api = api;
	}

}
