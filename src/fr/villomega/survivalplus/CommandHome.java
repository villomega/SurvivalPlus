package fr.villomega.survivalplus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandHome implements CommandExecutor {
    private  SurvivalPlus survivalPlus;
    public CommandHome(SurvivalPlus survivalPlus) {
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
            }else {
                homeName = args[0];
            }

            File file = new File(survivalPlus.getDataFolder(), "homes.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            String key = "players." + name + "." + homeName;
            ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

            if (configurationSection == null){
                player.sendMessage("Crée un home d'abord enculé");
            }else{
                World world = Bukkit.getWorld(configurationSection.getString("world"));
                double x = configurationSection.getDouble("x");
                double y = configurationSection.getDouble("y");
                double z = configurationSection.getDouble("z");
                float yaw = (float) configurationSection.getDouble("yaw");
                float pitch = (float) configurationSection.getDouble("pitch");

                Location location = new Location(world, x, y, z, yaw, pitch);

                player.teleport(location);
                player.sendMessage("T'es tp adopté ");

            }
        }
        return false;
    }
}
