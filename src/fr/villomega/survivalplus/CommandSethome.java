package fr.villomega.survivalplus;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CommandSethome implements CommandExecutor {

    private  SurvivalPlus survivalPlus;
    public CommandSethome(SurvivalPlus survivalPlus){
        this.survivalPlus = survivalPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            String name = player.getName();
            String homeName;

            if (args.length == 0){
                homeName = "home";
            }else{
                homeName = args[0];
            }

            Location location = player.getLocation();


            String world = location.getWorld().getName();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            float yaw = location.getYaw();
            float pitch = location.getPitch();

            File file = new File(survivalPlus.getDataFolder(), "homes.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            String key = "players." + name + "." + homeName + ".";

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
            player.sendMessage("Le home "+homeName+" est bien crée");
        }

        return false;
    }
}
