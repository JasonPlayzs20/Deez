package com.jasonplayzs20.main.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class AimBot implements CommandExecutor {
    public static ArrayList<Player> playerArrayList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (!(playerArrayList.contains(player))) {
            playerArrayList.add(player);
            player.sendMessage(ChatColor.GREEN + "You are now a aimbot!!");
        } else {
            playerArrayList.remove(player);
            player.sendMessage(ChatColor.RED + "You are no longer a aimbot!");
        }
        return false;
    }
}