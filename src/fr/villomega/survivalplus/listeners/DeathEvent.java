package fr.villomega.survivalplus.listeners;

import fr.villomega.survivalplus.SurvivalPlus;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;

public class DeathEvent implements Listener {

    private SurvivalPlus survivalPlus;

    public DeathEvent(SurvivalPlus survivalPlus) {
        this.survivalPlus = survivalPlus;
    }

    @EventHandler
    public void onPlayerDead(PlayerDeathEvent event){

        Player player = event.getEntity();

        File file = new File(survivalPlus.getDataFolder(), "death.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        String key = "players." + player.getName() + ".";

        Location deathLocation = player.getLocation();

        String world = deathLocation.getWorld().getName();
        double x = deathLocation.getX();
        double y = deathLocation.getY();
        double z = deathLocation.getZ();
        float yaw = deathLocation.getYaw();
        float pitch = deathLocation.getPitch();

        configuration.set(key + "world", world);
        configuration.set(key + "x", x);
        configuration.set(key + "y", y);
        configuration.set(key + "z", z);
        configuration.set(key + "yaw", yaw);
        configuration.set(key + "pitch", pitch);

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
