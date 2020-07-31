package fr.villomega.survivalplus;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandInfo implements CommandExecutor {

    private SurvivalPlus survivalPlus;

    public CommandInfo(SurvivalPlus survivalPlus) {
        this.survivalPlus = survivalPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player){

            Player player = (Player) commandSender;
            if (args.length < 2){
                player.sendMessage("Il manque des arguments, la commande est:");
                player.sendMessage("/info <type de donnée> <nom de la donnée>");
            }else {
                String data = args[0];
                String dataName = args[1];
                String name = player.getName();

                if (data.equalsIgnoreCase("home")){
                    String homeName = dataName;
                    File file = new File(survivalPlus.getDataFolder(), "homes.yml");
                    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                    String key = "players." + name + "." + homeName;
                    ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

                    if (configurationSection == null){
                        player.sendMessage("Crée un home d'abord enculé");
                    }else {
                        String world = configurationSection.getString("world");
                        double x = configurationSection.getDouble("x");
                        double y = configurationSection.getDouble("y");
                        double z = configurationSection.getDouble("z");
                        float yaw = (float) configurationSection.getDouble("yaw");
                        float pitch = (float) configurationSection.getDouble("pitch");

                        player.sendMessage(homeName + ":");
                        player.sendMessage("     world: " + world);
                        player.sendMessage("     x: " + x);
                        player.sendMessage("     y: " + y);
                        player.sendMessage("     z: " + z);
                        player.sendMessage("     yaw: " + yaw);
                        player.sendMessage("     pitch: " + pitch);

                    }
                }

            }

        }

        return false;
    }
}
