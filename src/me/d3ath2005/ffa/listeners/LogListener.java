package me.d3ath2005.ffa.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.d3ath2005.ffa.MainClass;

public class LogListener implements Listener {

	private MainClass plugin;

	public LogListener(MainClass plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(color("&8[&a+&8] &a" + player.getName()));

		if (player.hasPlayedBefore()) {
			File stats = new File(plugin.getDataFolder(), "stats.yml");
			YamlConfiguration statsYml = YamlConfiguration.loadConfiguration(stats);

			statsYml.set(player.getName().toLowerCase() + ".kills", 0);
			statsYml.set(player.getName().toLowerCase() + ".deaths", 0);
			try {
				statsYml.save(stats);
			} catch (IOException e) {
				plugin.getLogger().info("Failed to register data for player " + player.getName().toLowerCase());
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(color("&8[&c-&8] &c" + player.getName()));
	}

	private String color(String format) {
		return ChatColor.translateAlternateColorCodes('&', format);
	}
}