package me.filipe.gchat;

import me.filipe.gchat.data.Dados;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class GChat extends JavaPlugin {

    public Dados data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.data = new Dados(this);



        Bukkit.getConsoleSender().sendMessage("G-Chat On.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("G-Chat Off.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ggamble")) {
            if (!sender.hasPermission(getConfig().getString("ggamble.permission"))) {
                sender.sendMessage(Utils.chat(getConfig().getString("gchat.messages.no-perms")));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Utils.chat(getConfig().getString("gchat.messages.invalid-message")));
                return true;
            }

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    sender.sendMessage(Utils.chat(this.getConfig().getString("gchat.messages.rrl")));
                    this.reloadConfig();
                }
            }

            return true;
        }
        return false;
    }

}
