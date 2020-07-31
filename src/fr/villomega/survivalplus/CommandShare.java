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
import java.io.IOException;

public class CommandShare implements CommandExecutor {

    private SurvivalPlus survivalPlus;

    public CommandShare(SurvivalPlus survivalPlus) {
        this.survivalPlus = survivalPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length < 2) {
                player.sendMessage("T'es un fdp la commande c'est /share <joueur> <donnée a partager>");
            } else {

                if (Bukkit.getPlayer(args[0]).isOnline()) {
                    Player currentPlayer = Bukkit.getPlayer(args[0]);
                    String name = player.getName();
                    if (args[1].equalsIgnoreCase("home")) {
                        String homeName;
                        if (args.length == 2) {
                            homeName = "home";
                        } else {
                            homeName = args[2];
                        }

                        File file = new File(survivalPlus.getDataFolder(), "homes.yml");
                        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                        String key = "players." + name + "." + homeName;
                        ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

                        if (configurationSection == null) {
                            player.sendMessage("Crée un home d'abord enculé");
                        } else {
                            String world = configurationSection.getString("world");
                            double x = configurationSection.getDouble("x");
                            double y = configurationSection.getDouble("y");
                            double z = configurationSection.getDouble("z");
                            float yaw = (float) configurationSection.getDouble("yaw");
                            float pitch = (float) configurationSection.getDouble("pitch");

                            String key2 = "players." + currentPlayer.getName() + "." + player.getName()+ ":" +homeName + ".";

                            configuration.set(key2 + "world", world);
                            configuration.set(key2 + "x", x);
                            configuration.set(key2 + "y", y);
                            configuration.set(key2 + "z", z);
                            configuration.set(key2 + "yaw", yaw);
                            configuration.set(key2 + "pitch", pitch);

                            try {
                                configuration.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            player.sendMessage("Généreux, tu a partagé le home §c" + homeName + " à §6§l" + currentPlayer.getName());
                            currentPlayer.sendMessage("Hey §6§l" + player.getName() + "§r a partagé le home §c" + homeName + "§r, tu peux y accéder avec la commande §a/home " + player.getName() + ":" + homeName);

                        }

                    }

                }else {
                    player.sendMessage("§c§lLe joueur §6§l" + args[0] + " §r§c§lest inexistant ou n'est pas en ligne");
                }

            }


        }
        return false;
    }
}