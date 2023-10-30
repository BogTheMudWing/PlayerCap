package io.github.stonley890.playercap;

import io.github.stonley890.playercap.commands.CmdPlayercap;
import io.github.stonley890.playercap.listeners.ListenPlayerLogin;
import io.github.stonley890.playercap.listeners.ListenServerPing;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class PlayerCap extends JavaPlugin {

    public static PlayerCap plugin;

    public final String VERSION = getDescription().getVersion();
    public static final String PREFIX = ChatColor.YELLOW + "[" + ChatColor.WHITE + "PlayerCap" + ChatColor.YELLOW + "] " + ChatColor.RESET;

    public static int playerlimit;

    @Override
    public void onEnable() {

        plugin = this;

        // Initialize listeners
        getServer().getPluginManager().registerEvents(new ListenServerPing(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerLogin(), this);

        // Initialize commands
        Objects.requireNonNull(getServer().getPluginCommand("playercap")).setExecutor(new CmdPlayercap());

        // Create config if needed
        getDataFolder().mkdir();
        saveDefaultConfig();

        // Retrieve saved limit from config.yml
        int savedLimit = getConfig().getInt("playerlimit");

        // If it is less than 0 (or does not exist), set to default.
        if (savedLimit < 0) playerlimit = getServer().getMaxPlayers();
        // Otherwise, use saved limit.
        else {
            playerlimit = savedLimit;
            getServer().setMaxPlayers(playerlimit);
        }

        // Start message
        getLogger().log(Level.INFO, "PlayerCap" + VERSION + ": Override the player limit!");

    }

    public static PlayerCap getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {

        // Save config
        getConfig().set("playerlimit", playerlimit);
        saveConfig();

    }
}
