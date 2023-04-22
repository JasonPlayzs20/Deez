package com.jasonplayzs20.main.Commands;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CloudCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();
        AreaEffectCloud areaEffectCloud = (AreaEffectCloud) world.spawnEntity(player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        areaEffectCloud.setDuration(29999999);
        Particle particle = Particle.REDSTONE;
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK,0);
        areaEffectCloud.setParticle(particle,dustOptions);
        areaEffectCloud.setRadius((float) 0);

        AreaEffectCloud areaEffectCloud1 = (AreaEffectCloud) world.spawnEntity(player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        areaEffectCloud1.setDuration(29999999);
        Particle particle1 = Particle.REDSTONE;
        Particle.DustOptions dustOptions1 = new Particle.DustOptions(Color.BLACK,0);
        areaEffectCloud1.setParticle(particle,dustOptions);
        areaEffectCloud1.setRadius((float) 0);


        AreaEffectCloud areaEffectCloud2 = (AreaEffectCloud) world.spawnEntity(player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        areaEffectCloud2.setDuration(29999999);
        Particle particle2 = Particle.REDSTONE;
        Particle.DustOptions dustOptions2 = new Particle.DustOptions(Color.BLACK,0);
        areaEffectCloud2.setParticle(particle1,dustOptions1);
        areaEffectCloud2.setRadius((float) 0);

        areaEffectCloud1.addPassenger(areaEffectCloud2);
        areaEffectCloud.addPassenger(areaEffectCloud1);
        player.addPassenger(areaEffectCloud);
        return false;
    }
}
