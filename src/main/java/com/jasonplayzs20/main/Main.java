package com.jasonplayzs20.main;

import com.jasonplayzs20.main.Commands.*;
import com.jasonplayzs20.main.Enchantments.AimbotEnchantment;
import com.jasonplayzs20.main.Listener.AimbotListener;
import com.jasonplayzs20.main.Listener.ArrowListener;
import com.jasonplayzs20.main.Listener.walkListener;
import com.jasonplayzs20.main.mixxed.WallPhaser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

import static com.jasonplayzs20.main.Enchantments.AimbotEnchantment.launchArrow;
import static com.jasonplayzs20.main.Enchantments.AimbotEnchantment.searchEntity;

public final class Main extends JavaPlugin {

    private static Main mainInstance;

    public static Main getMainInstance() {
        return mainInstance;
    }
    @Override
    public void onEnable() {
        mainInstance = this;

        AimbotEnchantment AimbotListenerEnchantment = new AimbotEnchantment();
        Bukkit.getPluginManager().registerEvents(AimbotListenerEnchantment, this);
        registerEnchantment(AimbotListenerEnchantment);

        this.getCommand("aim").setExecutor(new AimBot());
        this.getCommand("velo").setExecutor(new VolocityAd());
        this.getCommand("armor").setExecutor(new armorCommand());
        this.getCommand("killArmor").setExecutor(new KillArmorStand());
        this.getCommand("cloud").setExecutor(new CloudCommand());
        this.getCommand("phase").setExecutor(new WallPhaser());
        ArrowListener arrowListener = new ArrowListener(this);
        AimbotListener aimbotListener = new AimbotListener(this);

        getServer().getPluginManager().registerEvents(new ArrowListener(this), this);
        getServer().getPluginManager().registerEvents(new walkListener(), this);
        getServer().getPluginManager().registerEvents(new WallPhaser(), this);


    }

    private void registerEnchantment(Enchantment enchantment) {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
            task.runTaskLater(this, 3 * i);
        }


    }
}
