package fr.villomega.survivalplus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CommandDelHome implements CommandExecutor {

    private  SurvivalPlus survivalPlus;
    public CommandDelHome(SurvivalPlus survivalPlus) {
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

            File file = new File(survivalPlus.getDataFolder(), "homes.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            String key = "players." + name + "." + homeName;

            if (configuration.get(key) == null){
                player.sendMessage("Tu n'as aucun home qui porte ce nom.");
            }else {
                configuration.set(key, null);

                player.sendMessage("Le home " + homeName + " a bien été suprimmé.");
                try {
                    configuration.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
