package me.filipe.gchat;

import me.filipe.gchat.automessages.AutoMessage;
import me.filipe.gchat.commands.*;
import me.filipe.gchat.data.Dados;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.events.OnChat;
import me.filipe.gchat.events.PlayerLogin;
import me.filipe.gchat.events.SettingsClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GChat extends JavaPlugin {

    public Dados data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.data = new Dados(this);

        SettingsData.loadOnline(this);

        new GChatCommand(this);

        new OnChat(this);
        new ChatCommand(this);
        new StaffCommand(this);
        new TellCommand(this);
        new BroadcastCommand(this);

        new PlayerLogin(this);

        new ChatSettingsCommand(this);
        new SettingsClickEvent(this);

        new DisableChatCommand(this);

        new AutoMessage(this);

        Bukkit.getConsoleSender().sendMessage("G-Chat On.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SettingsData.saveToConfig(this);

        Bukkit.getConsoleSender().sendMessage("G-Chat Off.");
    }

}
