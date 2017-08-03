package me.d3ath2005.ffa.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player killed = event.getEntity();
		if (killed.getKiller().getType() == EntityType.PLAYER) {
			Player killer = killed.getKiller();

			killed.spigot().respawn();
			Bukkit.broadcastMessage(color("&6" + killer.getName() + "&ewas slain by&6" + killed.getName()));
		} else {

		}
	}

	private String color(String format) {
		return ChatColor.translateAlternateColorCodes('&', format);
	}
}