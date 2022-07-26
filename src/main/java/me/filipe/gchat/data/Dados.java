package me.filipe.gchat.data;

import me.filipe.gchat.GChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class Dados {

    private GChat plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    private static Map<UUID, Boolean> globalEnabled = new HashMap<UUID, Boolean>();
    private static Map<UUID, Boolean> localEnabled = new HashMap<UUID, Boolean>();
    private static Map<UUID, Boolean> tellEnabled = new HashMap<UUID, Boolean>();

    //Anúncios
    private static Map<UUID, Boolean> broadcastEnabled = new HashMap<UUID, Boolean>();
    private static Map<UUID, Boolean> serverMessages = new HashMap<UUID, Boolean>();

    public Dados(GChat plugin) {
        this.plugin = plugin;

        //Guarda e inicia o data.yml
        saveDefaultConfig();
    }

    public void reloadConfig() {
        //Se não existir cria.
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultSream = this.plugin.getResource("data.yml");
        if (defaultSream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultSream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "[gchat] Impossível guardar \"data.yml\" ");
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        if (!this.configFile.exists()) {
            this.plugin.saveResource("data.yml", false);
        }
    }

}
