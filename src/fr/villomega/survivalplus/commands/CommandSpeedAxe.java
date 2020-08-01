package fr.villomega.survivalplus.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandSpeedAxe implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            Inventory inventory = player.getInventory();
            ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK, 5);

            if (inventory.contains(Material.DIAMOND_BLOCK, 5)) {

                ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);

                ItemMeta itemMeta = pickaxe.getItemMeta();
                itemMeta.setDisplayName("§c§lSuper-Pioche");
                itemMeta.addEnchant(Enchantment.DIG_SPEED, 1000, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemMeta.setUnbreakable(true);
                itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.addEnchant(Enchantment.LUCK, 3, true);

                pickaxe.setItemMeta(itemMeta);

                inventory.addItem(pickaxe);

                inventory.removeItem(new ItemStack(Material.DIAMOND_BLOCK, 5));

                player.sendMessage("Pioche give");
            }
        }

        return false;
    }
}
