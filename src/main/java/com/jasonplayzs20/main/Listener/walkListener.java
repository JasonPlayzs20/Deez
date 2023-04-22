package com.jasonplayzs20.main.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class walkListener implements Listener {

    public Entity GetTop(Entity entity) {
        entity.sendMessage("entity:" + String.valueOf(entity));
        if (entity.getPassengers().size() == 0) {
            return entity;
        }

        return GetTop(entity.getPassengers().get(0));
    }

    @EventHandler
    public void PlayerWalkEvent(PlayerMoveEvent event) {

        Player player = (Player) event.getPlayer();
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (player.getPassengers().size() == 0) {
                return;
            }
            if (entity.getType() == EntityType.PLAYER) {
                return;
            }
            if (entity.getType() == EntityType.ARMOR_STAND) {
                return;
            }
            if (!(entity instanceof LivingEntity)) {
                return;
            }

            Entity t = GetTop(player.getPassengers().get(0));
            t.addPassenger(entity);

        }
    }


//    @EventHandler
//    public void Entityaaa(EntityEvent e) {
//        Entity entity = e.getEntity();
//        for (Entity ent : entity.getNearbyEntities(5,5,5)) {
//            if (entity.getType() == EntityType.PLAYER) {
//                return;
//            }
//            if (entity.getType() == EntityType.ARMOR_STAND) {
//                return;
//            }
//            if (!(entity instanceof LivingEntity)) {
//                return;
//            }
//            Entity t = GetTop(entity);
//            t.addPassenger(ent);
//        }
//    }
}
