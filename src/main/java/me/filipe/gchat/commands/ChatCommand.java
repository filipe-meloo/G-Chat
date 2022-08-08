package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.GetRank;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class ChatCommand implements CommandExecutor {

    private GChat plugin;
    public ChatCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("g").setExecutor(this);
        plugin.getCommand("l").setExecutor(this);
    }

    // /global
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("g")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getConfig().getString("gchat.messages.console"));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("globalchat.use")));
                return true;
            }

            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if (!SettingsData.getGlobalEnabled() && !player.hasPermission("g.utils.staff")) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.chatg")));
                return true;
            }

            if (!SettingsData.getGlobal(uuid)) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.global")));
                return true;
            }

            String mensagem = "";
            for (int i = 0; i < args.length; i++) {
                mensagem += (args[i] + " ");
            }

            for (Player send : Bukkit.getOnlinePlayers()) {
                if (send.getUniqueId() == uuid) continue;
                if (!SettingsData.getGlobal(send.getUniqueId())) continue;

                if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("globalchat.adm-space")) {
                    send.sendMessage("");
                }

                if (player.hasPermission("g.utils.vip")) {
                    send.sendMessage(
                            Utils.chat(plugin.getConfig().getString("globalchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
                    );
                } else {
                    send.sendMessage(
                            Utils.chat(plugin.getConfig().getString("globalchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
                    );
                }

                if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("globalchat.adm-space")) {
                    send.sendMessage("");
                }

            }

            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("globalchat.adm-space")) {
                player.sendMessage("");
            }

            if (player.hasPermission("g.utils.vip")) {
                player.sendMessage(
                        Utils.chat(plugin.getConfig().getString("globalchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
                );
            } else {
                player.sendMessage(
                        Utils.chat(plugin.getConfig().getString("globalchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
                );
            }

            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("globalchat.adm-space")) {
                player.sendMessage("");
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("l")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getConfig().getString("gchat.messages.console"));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("localchat.use")));
                return true;
            }

            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
            Location location = player.getLocation();

            if (!SettingsData.getLocalEnabled() && !player.hasPermission("g.utils.staff")) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.chatl")));
                return true;
            }

            if (!SettingsData.getLocal(uuid)) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.local")));
                return true;
            }

            String mensagem = "";
            for (int i = 0; i < args.length; i++) {
                mensagem += (args[i] + " ");
            }

            boolean a = false;
            for (Player send : Bukkit.getOnlinePlayers()) {
                if (send.getUniqueId() == uuid) continue;
                if (!SettingsData.getLocal(send.getUniqueId())) continue;

                Location locSend = send.getLocation();
                if (location.distanceSquared(locSend) > (plugin.getConfig().getInt("localchat.radius") ^ 2)) continue;

                a = true;
                if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                    send.sendMessage("");
                }

                if (player.hasPermission("g.utils.vip")) {
                    send.sendMessage(
                            Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
                    );
                } else {
                    send.sendMessage(
                            Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
                    );
                }

                if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                    send.sendMessage("");
                }

            }

            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                player.sendMessage("");
            }

            if (player.hasPermission("g.utils.vip")) {
                player.sendMessage(
                        Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player)) + mensagem)
                );
            } else {
                player.sendMessage(
                        Utils.chat(plugin.getConfig().getString("localchat.prefix").replaceAll("%player%", GetRank.rankPlayer(player))) + mensagem
                );
            }

            if (player.hasPermission("g.utils.gerente") || player.hasPermission("g.utils.admin") && plugin.getConfig().getBoolean("localchat.adm-space")) {
                player.sendMessage("");
            }

            if (!a) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("localchat.no-one")));
            }
            return true;
        }
        return false;
    }

}
