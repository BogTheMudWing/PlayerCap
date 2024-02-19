package io.github.stonley890.playercap;

import io.github.stonley890.playercap.commands.CmdPlayercap;
import io.github.stonley890.playercap.commands.CmdSetmotd;
import io.github.stonley890.playercap.listeners.ListenPlayerLogin;
import io.github.stonley890.playercap.listeners.ListenServerPing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    public static String MOTD = null;
    public static Main plugin;

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
        Objects.requireNonNull(getServer().getPluginCommand("setmotd")).setExecutor(new CmdSetmotd());

        // Create config if needed
        boolean dirMade = getDataFolder().mkdir();
        if (!dirMade) Bukkit.getLogger().warning("Data directory could not be created!");
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
        getLogger().log(Level.INFO, "PlayerCap" + VERSION + ": Override the player limit and change the MOTD!");

    }

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {

        // Save config
        getConfig().set("playerlimit", playerlimit);
        saveConfig();

    }
}
