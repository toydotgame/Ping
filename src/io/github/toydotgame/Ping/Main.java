package io.github.toydotgame.Ping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.material.Command;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {	
	@Override
	public void onEnable() {
		this.getCommand("ping").setExecutor(this);
		System.out.print("[Ping] Plugin loaded successfully!");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				pingGetter(sender, player, player, args[0]);
				return true;
			} else if(args.length == 1) {
				pingGetter(sender, player, getServer().getPlayer(args[0]), args[0]);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Too many arguments!");
				return false;
			}
		} else {
			System.out.println("[Ping] It is recommended that only players use the ping command.");
			return true;
		}
	}
	
	public void pingGetter(CommandSender sender, Player playerSender, Player selectedPlayer, String arg) {
		int ping = ((CraftPlayer) selectedPlayer).getHandle().ping;
		if(playerSender == selectedPlayer) {
			sender.sendMessage("Your ping is " + String.valueOf(ping) + "ms.");
		} else {
			if(ping != 0) {
				sender.sendMessage("The ping of " + selectedPlayer.getName() + " is " + String.valueOf(ping) + "ms.");
			} else {
				sender.sendMessage(ChatColor.RED + "Could not find player \"" + arg + "\".");
			}
		}
	}
}
