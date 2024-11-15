package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class potioneffects implements CommandExecutor {
	//potionstate - tells you what potion affects players have at that time and for how long (if you can’t add like times then just it showing which affects)

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("potionstate.use")) {
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						player.sendMessage("Potion effects of " + target.getName() + " are: ");
						target.getActivePotionEffects().forEach(potionEffect -> {
							int level = potionEffect.getAmplifier() + 1; // Amplifier starts from 0, so add 1
							String levelSuffix = level > 1 ? " " + level : ""; // Use Roman numerals for level
							player.sendMessage((getFormattedPotionEffectName(potionEffect.getType()) + levelSuffix + " for " + (potionEffect.getDuration()/20) + " seconds"));
						});
						return true;
					}else{
						player.sendMessage("Player not found!");
						return false;
					}
				}else{
					player.sendMessage("Invalid arguments! /potionstate <player>");
					return false;
				}
			}
		}
		return false;

	}

	public static String getFormattedPotionEffectName(PotionEffectType type){
		String [] words = type.getName().split("_");

		StringBuilder formattedName = new StringBuilder();

		for(String word : words){
			formattedName.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
		}

		return formattedName.toString().trim();

	}
}
