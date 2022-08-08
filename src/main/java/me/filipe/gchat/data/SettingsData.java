package me.filipe.gchat.data;

import me.filipe.gchat.GChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SettingsData {

    private static final Map<UUID, Boolean> global = new HashMap<>();
    private static final Map<UUID, Boolean> local = new HashMap<>();
    private static final Map<UUID, Boolean> tell = new HashMap<>();

    //Anúncios
    private static final Map<UUID, Boolean> broad = new HashMap<>();
    private static final Map<UUID, Boolean> sm = new HashMap<>();

    private static Boolean globalEnabled = true;
    private static Boolean localEnabled = true;

    public static Boolean getGlobalEnabled() {
        return globalEnabled;
    }
    public static void toggleGlobal() {
        boolean a = globalEnabled;
        globalEnabled = !a;
    }

    public static Boolean getLocalEnabled() {
        return localEnabled;
    }
    public static void toggleLocal() {
        boolean a = localEnabled;
        localEnabled = !a;
    }

    public static Boolean getGlobal(UUID uuid) {
        global.putIfAbsent(uuid, true);
        return global.get(uuid);
    }
    public static void setGlobal(UUID uuid, Boolean dado) {
        global.put(uuid, dado);
    }

    public static Boolean getLocal(UUID uuid) {
        local.putIfAbsent(uuid, true);
        return local.get(uuid);
    }
    public static void setLocal(UUID uuid, Boolean dado) {
        local.put(uuid, dado);
    }

    public static Boolean getTell(UUID uuid) {
        tell.putIfAbsent(uuid, true);
        return tell.get(uuid);
    }
    public static void setTell(UUID uuid, Boolean dado) {
        tell.put(uuid, dado);
    }

    public static Boolean getBroad(UUID uuid) {
        broad.putIfAbsent(uuid, true);
        return broad.get(uuid);
    }
    public static void setBroad(UUID uuid, Boolean dado) {
        broad.put(uuid, dado);
    }

    public static Boolean getSm(UUID uuid) {
        sm.putIfAbsent(uuid, true);
        return sm.get(uuid);
    }
    public static void setSm(UUID uuid, Boolean dado) {
        sm.put(uuid, dado);
    }

    public static void saveToConfig(GChat plugin) {

        for (Map.Entry<UUID, Boolean> entry : global.entrySet()) {
            plugin.data.getConfig().set("players." + entry.getKey() + ".global", entry.getValue());
            plugin.data.getConfig().set("players." + entry.getKey() + ".local", local.get(entry.getKey()));
            plugin.data.getConfig().set("players." + entry.getKey() + ".tell", tell.get(entry.getKey()));
            plugin.data.getConfig().set("players." + entry.getKey() + ".sm", sm.get(entry.getKey()));
            plugin.data.getConfig().set("players." + entry.getKey() + ".broad", broad.get(entry.getKey()));
        }

        plugin.data.saveConfig();
    }

    public static void loadOnline(GChat plugin) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            //Carregar dados para as variáveis
            if (plugin.data.getConfig().contains("players." + uuid)) {
                SettingsData.setGlobal(uuid,
                        plugin.data.getConfig().getBoolean("players." + uuid + ".global"));

                SettingsData.setLocal(uuid,
                        plugin.data.getConfig().getBoolean("players." + uuid + ".local"));

                SettingsData.setBroad(uuid,
                        plugin.data.getConfig().getBoolean("players." + uuid + ".broad"));

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
        }
    }

}
