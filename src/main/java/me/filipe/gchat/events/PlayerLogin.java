package me.filipe.gchat.events;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.Dados;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.GetRank;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class PlayerLogin implements Listener {

    private GChat plugin;
    public PlayerLogin(GChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        //Carregar dados para as variáveis
        if (plugin.data.getConfig().contains("players." + uuid)) {
            SettingsData.setGlobal(uuid,
                    plugin.data.getConfig().getBoolean("players." + uuid + ".global"));

            SettingsData.setLocal(uuid,
                    plugin.data.getConfig().getBoolean("players." + uuid + ".local"));

            SettingsData.setBroad(uuid,
                    plugin.data.getConfig().getBoolean("players." + uuid + ".local"));

            SettingsData.setSm(uuid,
                    plugin.data.getConfig().getBoolean("players." + uuid + ".sm"));

            SettingsData.setTell(uuid,
                    plugin.data.getConfig().getBoolean("players." + uuid + ".tell"));
        } else {
            SettingsData.setGlobal(uuid, true);
            SettingsData.setLocal(uuid, true);
            SettingsData.setBroad(uuid, true);
            SettingsData.setTell(uuid, true);
            SettingsData.setSm(uuid, true);
        }

        //Mensagem de boas vindas

        List<String> msg = plugin.getConfig().getStringList("welcome");
        new BukkitRunnable() {
            @Override
            public void run() {
                for (String s : msg) {
                    player.sendMessage(Utils.chat(Utils.chat(s)
                            .replaceAll("%player%", GetRank.rankPlayer(player))
                            .replaceAll("%prefix%", plugin.getConfig().getString("gchat.prefix"))));
                }
            }
        }.runTaskLater(plugin, 20);

    }
}
