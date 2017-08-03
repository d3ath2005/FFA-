package me.d3ath2005.ffa;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.d3ath2005.ffa.commands.CommandStats;
import me.d3ath2005.ffa.listeners.DeathListener;
import me.d3ath2005.ffa.listeners.LogListener;

public class MainClass extends JavaPlugin {

	PluginDescriptionFile pdf = getDescription();
	public String prefix = "&8[&6FFA&8] ";

	public void onEnable() {
		getLogger().info("Enabled -- v" + pdf.getVersion());
		registerEvts();
		registerCmds();
		statsFile();
	}

	public void onDisable() {
		getLogger().info("Disabled -- v" + pdf.getVersion());
	}

	public void registerCmds() {
		getCommand("stats").setExecutor(new CommandStats(this));
	}

	public void registerEvts() {
		PluginManager manager = Bukkit.getPluginManager();
		manager.registerEvents(new LogListener(this), this);
		manager.registerEvents(new DeathListener(), this);
	}

	public void statsFile() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		File stats = new File(getDataFolder(), "stats.yml");
		if (!stats.exists()) {
			try {
				stats.createNewFile();
			} catch (IOException e) {
				getLogger().info("Could not create file stats.yml");
			}
		}
	}
}