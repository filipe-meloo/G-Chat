package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.GetRank;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class StaffCommand implements CommandExecutor {

    private GChat plugin;
    public StaffCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("s").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("s")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getConfig().getString("gchat.messages.console"));
                return true;
            }

            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if (!player.hasPermission("g.utils.staff")) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.no-perms")));
                return true;
            }

            String mensagem = "";
            for (int i = 0; i < args.length; i++) {
                mensagem += (args[i] + " ");
            }
            
            for (Player send : Bukkit.getOnlinePlayers()) {
                if (send.getUniqueId() == uuid) continue;
                if (!send.hasPermission("g.utils.staff")) continue;

                send.sendMessage(Utils.chat(plugin.getConfig().getString("staffchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem));
            }

            player.sendMessage(Utils.chat(plugin.getConfig().getString("staffchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem));
            return true;
        }
        return false;
    }
}
