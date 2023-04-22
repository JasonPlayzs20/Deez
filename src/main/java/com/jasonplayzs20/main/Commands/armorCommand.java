package com.jasonplayzs20.main.Commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class armorCommand implements CommandExecutor {
    public Entity GetTop(Entity entity) {
        entity.sendMessage("entity:" + String.valueOf(entity));
        if (entity.getPassengers().size() == 0) {
            return entity;
        }

        return GetTop(entity.getPassengers().get(0));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setMarker(false);
        //armorStand.setVisible(false);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);

        player.addPassenger(armorStand);



        return false;
    }
}
