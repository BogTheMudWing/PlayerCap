package io.github.stonley890.playercap.commands;

import io.github.stonley890.playercap.PlayerCap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CmdPlayercap implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (args.length == 0) sender.sendMessage(PlayerCap.PREFIX + "The current player limit is " + PlayerCap.playerlimit + ".");
        else if (args.length == 1) {

            try {

                // Parse new limit.
                int newLimit = Integer.parseInt(args[0]);

                if (newLimit < 0) {
                    sender.sendMessage(PlayerCap.PREFIX + ChatColor.RED + "You cannot set the player limit to a negative number.");
                    return true;
                }

                // Store new limit
                PlayerCap.playerlimit = newLimit;
                // Set new limit.
                PlayerCap.getPlugin().getServer().setMaxPlayers(PlayerCap.playerlimit);
                // Notify sender.
                sender.sendMessage(PlayerCap.PREFIX + "Player limit set to " + newLimit);

            } catch (NumberFormatException e) {
                sender.sendMessage(PlayerCap.PREFIX + ChatColor.RED + "That is not a valid integer.");
            }

        } else sender.sendMessage(PlayerCap.PREFIX + ChatColor.RED + "Too many arguments! /playercap [limit]");

        return true;
    }
}
