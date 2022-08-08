package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GChatCommand implements CommandExecutor {

    private GChat plugin;
    public GChatCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("gchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gchat")) {
            if (!sender.hasPermission(plugin.getConfig().getString("gchat.permission"))) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.no-perms")));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.invalid-message")));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.rl")));
                plugin.reloadConfig();
            } else {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.invalid-message")));
                return true;
            }

            return true;
        }
        return false;
    }
}
