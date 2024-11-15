package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class canflyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

		if(sender instanceof Player){
			Player player = (Player) sender;
			if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					boolean isFlying = target.isFlying();
					player.sendMessage(target.getName() + " is " + (isFlying ? "flying" : "not flying"));

					boolean haspermissionfly = target.hasPermission("canfly.use");
					player.sendMessage(target.getName() + " has permission to fly: " + (haspermissionfly ? "yes" : "no"));
				}else{
					player.sendMessage("Player not found!");
					return false;
				}
			}else{
				player.sendMessage("Invalid arguments! /canfly <player>");
				return false;
			}
		}

		return false;
	}


}
