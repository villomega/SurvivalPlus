package fr.villomega.survivalplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalPlus extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Le plugin survie+ est lancé");
        getCommand("sethome").setExecutor(new CommandSethome(this));
        getCommand("home").setExecutor(new CommandHome(this));
        getCommand("delhome").setExecutor(new CommandDelHome(this));
        getCommand("share").setExecutor(new CommandShare(this));
        getCommand("homes").setExecutor(new CommandHomes(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Le plugin survie+ est éteint");
    }
}
