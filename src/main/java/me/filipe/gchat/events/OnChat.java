package me.filipe.gchat.events;

import me.filipe.gchat.GChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class OnChat implements Listener {

    private GChat plugin;
    public OnChat(GChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChatLocal(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();


    }

}
