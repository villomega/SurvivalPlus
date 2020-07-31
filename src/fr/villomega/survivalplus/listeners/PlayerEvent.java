package fr.villomega.survivalplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§a§l[+]§r§6§l " + player.getName());
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§c§l[-]§r§6§l " + player.getName());
    }


}
