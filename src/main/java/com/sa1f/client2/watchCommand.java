package com.sa1f.client2;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class watchCommand implements CommandExecutor {

	Client2 main;
	watchCommand(Client2 main){
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player watcher = (Player) sender;
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					if(isVanished(watcher)){
						watcher.hidePlayer(Client2.getPlugin(Client2.class), target); // Hide the target player only for the spectator

						lockonTarget(watcher, target);
						watcher.sendMessage("You are now watching " + target.getName() + " without movement.");
						return true;

					}else{
						watcher.sendMessage("You have to be vanished to use this command!");
						return false;
					}


				}else{
					watcher.sendMessage("Player not found!");
					return false;
				}

			}
			return false;
		}
		return false;
	}

	private void lockonTarget(Player watcher, Player target) {
		watcher.setGameMode(GameMode.SPECTATOR);

		int taskId = Bukkit.getScheduler().runTaskTimer(Client2.getPlugin(Client2.class), () -> {
			if(watcher.isOnline() && target.isOnline()) {
				watcher.teleport(target.getLocation());
				watcher.setRotation(target.getLocation().getYaw(), target.getLocation().getPitch());

			}else{
				stopWatching(watcher);
				watcher.sendMessage("Player is no longer online.");
			}
		}, 0L, 1L).getTaskId();

		main.watchTasks.put(watcher, taskId);
		watcher.sendMessage("You are now watching " + target.getName() + " with task ID: " + taskId);
	}

	public static boolean isVanished(Player player) {
		// Return true if the player is vanished; can be based on metadata, set, etc.
		return player.hasMetadata("vanished");
	}

	public void stopWatching(Player watcher) {
		if (main.watchTasks.containsKey(watcher)) {
			int taskId = main.watchTasks.remove(watcher); // Retrieve and remove the task ID
			Bukkit.getScheduler().cancelTask(taskId); // Cancel only the specific task for this watcher
			watcher.setGameMode(GameMode.SURVIVAL); // Reset game mode
			watcher.sendMessage("You have stopped watching.");
		}
	}

}
