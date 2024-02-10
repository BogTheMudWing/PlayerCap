package io.github.stonley890.playercap.commands;

import io.github.stonley890.playercap.PlayerCap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CmdSetmotd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 0) {
            // Reset MOTD
            PlayerCap.MOTD = null;
            sender.sendMessage(PlayerCap.PREFIX + "Reset MOTD to default:\n" + sender.getServer().getMotd());
        } else {
            // Set MOTD

            StringBuilder builder = new StringBuilder();
            for (String arg : args) {
                builder.append(arg).append(" ");
            }

            String newMotd = builder.toString().replaceAll("&", "§").replaceAll("\\\\n","\n").strip();

            PlayerCap.MOTD = newMotd;
            sender.sendMessage(PlayerCap.PREFIX + "MOTD set to\n" + newMotd);
        }

        return true;
    }
}
