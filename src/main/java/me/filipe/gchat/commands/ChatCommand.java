package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {

    private GChat plugin;
    public ChatCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("g").setExecutor(this);
        plugin.getCommand("l").setExecutor(this);
        plugin.getCommand("s").setExecutor(this);
    }

    // /global
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("g")) {



            return true;
        }
        return false;
    }

}
