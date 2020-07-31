package fr.villomega.survivalplus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
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
        getCommand("info").setExecutor(new CommandInfo(this));

        ShapedRecipe selle = new ShapedRecipe(new ItemStack(Material.SADDLE));
        selle.shape("CCC", "FFF", "DDD");
        selle.setIngredient('C', Material.LEATHER);
        selle.setIngredient('F', Material.IRON_INGOT);
        selle.setIngredient('D', Material.DIAMOND);
        getServer().addRecipe(selle);

        ShapedRecipe etiquette = new ShapedRecipe(new ItemStack(Material.NAME_TAG));
        etiquette.shape("SPD", "DDD", "DDD");
        etiquette.setIngredient('P', Material.PAPER);
        etiquette.setIngredient('S', Material.STRING);
        etiquette.setIngredient('D', Material.DIAMOND);
        getServer().addRecipe(etiquette);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Le plugin survie+ est éteint");
    }
}
