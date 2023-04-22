package com.jasonplayzs20.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VolocityAd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args[0].toString() == "zombie") {
            for (Entity en : player.getNearbyEntities(10, 10, 10)) {
                en.setVelocity(new Vector(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
            }
        }

        return false;
    }
}
