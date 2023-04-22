package com.jasonplayzs20.main.Listener;

import com.jasonplayzs20.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

import static com.jasonplayzs20.main.Commands.AimBot.playerArrayList;

public class AimbotListener implements Listener {
    private final Main plugin;// reference to main class

    public AimbotListener(Main plugin) {
        this.plugin = plugin;
    }

    public static void launchArrow(Arrow arrow, Entity entity, Double damage) {
        Location arrowLoc = arrow.getLocation();
        Location entityLoc = entity.getLocation();
        Location change = arrowLoc.subtract(entityLoc);
        arrow.setVelocity(change.toVector().multiply(-0.7));
        arrow.setDamage(damage);
    }

    public static Entity searchEntity(Location location, Player originalSender) {
        List<Entity> near = (List<Entity>) location.getWorld().getNearbyEntities(location, 20, 10, 20);
        for (Entity entity : near) {
            if (!(entity.isDead())) {
                if (entity != originalSender && entity.getType() != EntityType.ARROW && entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.EXPERIENCE_ORB) {
                    return entity;
                }

            }
//            if (entity.getType() != EntityType.ARROW || entity.getType() != EntityType.AREA_EFFECT_CLOUD || entity.getType() != EntityType.DROPPED_ITEM || entity.getType() != EntityType.ITEM_FRAME || entity.getType() != EntityType.GLOW_ITEM_FRAME || entity != originalSender|| entity.getType() != EntityType.FALLING_BLOCK) {
//                return entity;
//            }
        }
        return null;
    }

    public void arrowDown(Arrow arrow, Player original) {
        for (int i = 1; i < 500; i++) {
            Location arrowLocation2 = arrow.getLocation();
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {

                    if (arrow.getVelocity().getBlockY() < 0) {
                        launchArrow(arrow, searchEntity(arrowLocation2, original), arrow.getDamage());
                        arrow.setGravity(false);
                        return;
                    }

                }
            };
            // Run the task on this plugin in 3 seconds (60 ticks)
            task.runTaskLater(plugin, 3 * i);
        }


    }

    @EventHandler
    public void aimbotListener(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getEntity();
        Arrow arrow = (Arrow) e.getProjectile();


        if (!(playerArrayList.contains(player))) {
            return;
        }
        arrowDown(arrow, player);
    }
}