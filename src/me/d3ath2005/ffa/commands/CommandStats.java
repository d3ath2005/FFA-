package me.d3ath2005.ffa.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.d3ath2005.ffa.MainClass;

public class CommandStats implements CommandExecutor {

	private MainClass plugin;

	public CommandStats(MainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("stats") && sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("ffa.stats")) {
				File stats = new File(plugin.getDataFolder(), "stats.yml");
				YamlConfiguration statsYml = YamlConfiguration.loadConfiguration(stats);
				if (args.length == 0) {
					String username = player.getName().toLowerCase();
					player.sendMessage(color("&7Username: &c" + username));
					int kills = statsYml.getInt(username + ".kills");
					player.sendMessage(color("&7Kills: &c" + kills));
					int deaths = statsYml.getInt(username + ".deaths");
					player.sendMessage(color("&7Deaths: &c" + deaths));
					if(deaths == 0) {
						player.sendMessage(color("&7KDR: &c" + kills));
						} else {
							int kdr = kills/deaths;
							player.sendMessage(color("&7KDR: &c" + kdr));
						}
				} else if (args.length == 1) {
					if(statsYml.contains(args[0].toLowerCase())) {
						String target = args[0];
						player.sendMessage(color("&7Username: &c" + target));
						int kills = statsYml.getInt(target + ".kills");
						player.sendMessage(color("&7Kills: &c" + kills));
						int deaths = statsYml.getInt(target + ".deaths");
						player.sendMessage(color("&7Deaths: &c" + deaths));
						if(deaths == 0) {
						player.sendMessage(color("&7KDR: &c" + kills));
						} else {
							int kdr = kills/deaths;
							player.sendMessage(color("&7KDR: &c" + kdr));
						}
					} else {
						player.sendMessage(color(plugin.prefix + "&cPlayer &4" + args[0] + " &cnever joined the server!"));
					}
				} else {
					player.sendMessage(color(plugin.prefix + "Incorrect usage! /stats [player]"));
				}
			}
		}
		return false;
	}

	private String color(String format) {
		return ChatColor.translateAlternateColorCodes('&', format);
	}
}