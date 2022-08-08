package me.filipe.gchat.events;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.GetRank;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class OnChat implements Listener {

    private GChat plugin;
    public OnChat(GChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChatLocal(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location location = player.getLocation();

        String mensagem = event.getMessage();
        event.setMessage("");
        event.setCancelled(true);

        if (!SettingsData.getLocalEnabled() && !player.hasPermission("g.utils.staff")) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.chatl")));
            return;
        }

        if (!SettingsData.getLocal(uuid)) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.local")));
            return;
        }

        boolean a = false;
        for (Player send : Bukkit.getOnlinePlayers()) {
            if (send.getUniqueId() == uuid) continue;
            if (!SettingsData.getLocal(send.getUniqueId())) continue;

            Location locSend = send.getLocation();
            if (location.distanceSquared(locSend) > (plugin.getConfig().getInt("localchat.radius") ^ 2)) continue;

            a = true;
            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                send.sendMessage("");
            }

            if (player.hasPermission("g.utils.vip")) {
                send.sendMessage(
                        Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
                );
            } else {
                send.sendMessage(
                        Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
                );
            }

            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                send.sendMessage("");
            }

        }

        if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
            player.sendMessage("");
        }

        if (player.hasPermission("g.utils.vip")) {
            player.sendMessage(
                    Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
            );
        } else {
            player.sendMessage(
                    Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
            );
        }

        if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
            player.sendMessage("");
        }

        if (!a) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("localchat.no-one")));
        }

        event.setCancelled(true);
    }
}
