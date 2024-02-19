package io.github.stonley890.playercap.listeners;

import io.github.stonley890.playercap.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.jetbrains.annotations.NotNull;

public class ListenServerPing implements Listener {

    @EventHandler
    public void onPing(@NotNull ServerListPingEvent event) {

        // Change player cap to reflect override
        event.setMaxPlayers(Main.getPlugin().getServer().getMaxPlayers());

        // Change MOTD
        if (Main.MOTD != null) {
            event.setMotd(Main.MOTD);
        }

    }

}
