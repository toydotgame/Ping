package io.github.toydotgame.Ping;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {
	@Override
	public void onEnable() {
		System.out.print("[Ping] Plugin successfully loaded!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
		Player playerSender = (Player) sender;
			if(playerSender.hasPermission("ping.use")) {
				if(args.length == 0) {
					getPing(sender, command, label, args, playerSender, playerSender);
					return true;
				} else if(args.length == 1) {
					if(playerSender.hasPermission("ping.others")) {
						if(getServer().getPlayerExact(args[0]) != null) {
							getPing(sender, command, label, args, playerSender, getServer().getPlayer(args[0]));
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "The player \"" + args[0] + "\" could not be found!");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You have insufficient permissions to ping other players!");
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Invalid argument amount!");
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You have insufficuent permissions to use that command!");
				return true;
			}
		} else {
			System.out.print("[Ping] It is reccomended that only players use this command!");
			return true;
		}
	}

	private void getPing(CommandSender sender, Command command, String label, String[] args, Player playerSender, Player selectedPlayer) {
		Thread getPingThread = new Thread() {
			public void run() {
				int ping = 0;
				try {
					Object entityPlayer = selectedPlayer.getClass().getMethod("getHandle").invoke(selectedPlayer);
					ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {}
				
				if(ping != 0) {
					if(selectedPlayer == playerSender) {
						sender.sendMessage("Your ping is " + String.valueOf(ping) + "ms.");
					} else {
						sender.sendMessage("The ping of " + args[0] + " is " + String.valueOf(ping) + "ms.");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "An error occurred, and no ping time could be found.");
				}
			}
		};
		getPingThread.start();
	}
}
