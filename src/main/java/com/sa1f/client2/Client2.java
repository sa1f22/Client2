package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Client2 extends JavaPlugin {

	public final Map<Player, Integer> watchTasks = new HashMap<>();


	@Override
	public void onEnable() {
		getCommand("openinv").setExecutor(new openInvCommand());
		getCommand("potionstate").setExecutor(new potioneffects());
		getCommand("canfly").setExecutor(new canflyCommand());
		getCommand("watch").setExecutor(new watchCommand(this));
		getCommand("unwatch").setExecutor(new unwatch(this));
		//getCommand("vanish").setExecutor(new Vanished());

		// Plugin startup logic

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
