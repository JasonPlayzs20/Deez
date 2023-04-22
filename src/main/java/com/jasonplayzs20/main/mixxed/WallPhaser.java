package com.jasonplayzs20.main.mixxed;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;

public class WallPhaser implements CommandExecutor, Listener {

    public static ArrayList<Player> playerArrayList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (!(playerArrayList.contains(player))){
            playerArrayList.add(player);
            player.sendMessage(ChatColor.GREEN+"You are now a wall phaser!!");
        }else {
            playerArrayList.remove(player);
            player.sendMessage(ChatColor.RED+"You are no longer a wall phaser!");
        }
        return false;
    }

    @EventHandler
    public static void onShift(PlayerToggleSneakEvent e){
        Player player = e.getPlayer();
        if (!(playerArrayList.contains(player))) return;
        Location playerLoc = player.getLocation().add(0,-1.5,0);
        Block block = playerLoc.getBlock();
        if (block.isLiquid() || block.isEmpty()) return;
        player.setGameMode(GameMode.SPECTATOR);

    }

    @EventHandler
    public static void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if (!(playerArrayList.contains(player))) return;
        Location playerLoc = player.getLocation().add(0,0,0);
        Block block2 = playerLoc.add(0,0.95,0).getBlock();
        playerLoc = player.getLocation().add(0,-0.1,0);
        Block block1 = playerLoc.getBlock();

        if (!(block1.getType().isSolid()) && !(block2.getType().isSolid())) {
            player.setGameMode(GameMode.SURVIVAL);
        }

    }




}
