package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.menus.SettingsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChatSettingsCommand implements CommandExecutor {

    private GChat plugin;
    public ChatSettingsCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("chatsettings").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("chatsettings")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getConfig().getString("gchat.messages.console"));
                return true;
            }

            Player player = (Player) sender;
            Inventory inv = SettingsMenu.getChatSettings(player);
            player.openInventory(inv);

            return true;
        }
        return false;
    }
}
