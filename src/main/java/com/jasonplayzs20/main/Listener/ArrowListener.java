package com.jasonplayzs20.main.Listener;

import com.jasonplayzs20.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Date;
public class ArrowListener implements Listener {
    private final Main plugin;// reference to main class

    public ArrowListener(Main plugin) {
        this.plugin = plugin;
    }


    public static Location getBlock(int x, int y, int z,Player player) {
        //player.sendMessage("Iniciated");
        x = Math.round(x);
        y = Math.round(y);
        z = Math.round(z);
        //player.sendMessage("Math done");
        Block block = player.getWorld().getBlockAt(x,y,z);
        //player.sendMessage("Indicated block");

        if (!(block.getType().isAir() || !(block.getType().isSolid()))) {
//            player.sendMessage("Found block");
            return block.getLocation();
        }
//        player.sendMessage("Not found. Researching");
        return getBlock(x, y-1, z,player);
    }

    public void Action(Arrow arrow, Player player)  {
        Location arrowLocation = arrow.getLocation();
        for (int i = 0; i<300; i++) {
            Bukkit.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
                @Override
                public void run() {
                    if (arrow.isDead()) {
                        return;
                    }
                    Location location = arrow.getLocation();
                    World world = player.getWorld();
//                    player.sendMessage(location.toString());
                    FallingBlock fallingBlock = (FallingBlock) world.spawnEntity(getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), player),EntityType.FALLING_BLOCK);
                    fallingBlock.setVelocity(new Vector(0,2.5,0));
                }
            }, (i/2)+3);
            i++;
        }

    }

//    @EventHandler
//    public static void onArrowGround(ProjectileHitEvent e) {
//        Projectile projectile = e.getEntity();
//        Player player = (Player) projectile.getShooter();
////        player.sendMessage("removed arrow that you shot ;-;");
//        if (projectile instanceof Arrow) {
//            projectile.remove();
//
//        }
//    }

//    @EventHandler
//    public void onArrowShoot(EntityShootBowEvent e)  {
//
//        if (!(e.getEntity() instanceof Player)) return;
//        Player player = (Player) e.getEntity();
//        World world = player.getWorld();
//        Arrow arrow = (Arrow) e.getProjectile();
//        //player.sendMessage(arrow.getLocation().toString());
//        Action(arrow,player);
//    }
}
