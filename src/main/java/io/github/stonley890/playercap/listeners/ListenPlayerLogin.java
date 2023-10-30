package io.github.stonley890.playercap.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ListenPlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {

        if (event.getPlayer().hasPermission("playercap.bypass") && event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            // Allow players if they have "playercap.bypass" permission (OPs by default) and would otherwise be kicked by full server.
            event.allow();
        }

    }

}
