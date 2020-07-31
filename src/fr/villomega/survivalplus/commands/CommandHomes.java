package fr.villomega.survivalplus.commands;

import com.mysql.fabric.xmlrpc.base.Array;
import fr.villomega.survivalplus.SurvivalPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Set;

public class CommandHomes implements CommandExecutor {

    private SurvivalPlus survivalPlus;

    public CommandHomes(SurvivalPlus survivalPlus) {
        this.survivalPlus = survivalPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            String name = player.getName();

            File file = new File(survivalPlus.getDataFolder(), "homes.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            String key = "players." + name;

            Set<String> homes = configuration.getConfigurationSection(key).getKeys(false);
            player.sendMessage(homes.toString());
        }
        return false;
    }
}
