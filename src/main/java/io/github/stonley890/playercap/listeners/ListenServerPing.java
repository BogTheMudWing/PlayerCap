package io.github.stonley890.playercap.listeners;

import io.github.stonley890.playercap.PlayerCap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ListenServerPing implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {

        // Change player cap to reflect override
        event.setMaxPlayers(PlayerCap.getPlugin().getServer().getMaxPlayers());

    }

}
