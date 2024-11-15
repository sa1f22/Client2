package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class openInvCommand implements CommandExecutor {

	//openinv - opens a players inventory and allows people with this perm to edit the inventory as well.

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

		if(sender instanceof Player){
			Player player = (Player) sender;
			if(player.hasPermission("openinv.use")){
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						player.openInventory(target.getInventory());
						return true;
					}
				}else{
					player.sendMessage("Invalid arguments! /openinv <player>");
					return false;
				}
			}
		}

		return false;
	}


}
