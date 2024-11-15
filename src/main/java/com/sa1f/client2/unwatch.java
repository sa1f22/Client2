package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class unwatch extends watchCommand implements CommandExecutor {

	public unwatch(Client2 plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if(sender instanceof Player) {

			Player player = (Player) sender;
			if (main.watchTasks.containsKey(player)) {
				stopWatching(player);
				player.sendMessage("You are no longer watching anyone.");
				return true;
			}else {
				player.sendMessage("You are not watching anyone.");
				return false;
			}
		}

		return false;
	}
}
