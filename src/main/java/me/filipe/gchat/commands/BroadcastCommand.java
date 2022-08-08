package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BroadcastCommand implements CommandExecutor {

    private GChat plugin;
    public BroadcastCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("broadcast").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("g.utils.staff")) {

                if (args.length == 0) {
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("broadcast.invalid-use")));
                    return true;
                }

                String mensagem = "";
                for (int i = 0; i < args.length; i++) {
                    mensagem += (args[i] + " ");
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!SettingsData.getBroad(player.getUniqueId())) continue;
                    player.sendMessage("");
                    player.sendMessage(Utils.chat(
                            plugin.getConfig().getString("broadcast.prefix") + mensagem
                    ));
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }

            }
            return true;
        }
        return false;
    }
}
