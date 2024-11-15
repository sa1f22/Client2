package com.sa1f.client2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanished implements CommandExecutor {

	//remove vanish metadata from player if they are vanished when they run this command
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasMetadata("vanished")) {
				player.removeMetadata("vanished", Client2.getPlugin(Client2.class));
				return true;
			}
		}

		return false;
	}
}
