package fr.villomega.survivalplus.commands;

import fr.villomega.survivalplus.SurvivalPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class CommandRespawn implements CommandExecutor {

    private SurvivalPlus survivalPlus;

    public CommandRespawn(SurvivalPlus survivalPlus) {
        this.survivalPlus = survivalPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            String name = player.getName();

            Inventory inventory = player.getInventory();

            if(inventory.contains(Material.DIAMOND_BLOCK)){

                inventory.removeItem(new ItemStack(Material.DIAMOND_BLOCK, 1));

                File file = new File(survivalPlus.getDataFolder(), "death.yml");
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                String key = "players." + name;
                ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

                if (configurationSection == null){
                    player.sendMessage("Tu n'es pas encore mort fdp");
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
            }else {
                player.sendMessage("Tu doit avoir un bloc de diamand dans ton inventaire enculé");
            }

        }
        return false;
    }
}
