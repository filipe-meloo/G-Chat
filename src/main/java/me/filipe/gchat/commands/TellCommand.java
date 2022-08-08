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

public class TellCommand implements CommandExecutor {

    private GChat plugin;
    public TellCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("tell").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tell")) {
            //Verifier consola
            if (args.length < 2) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.invalidpm")));
                return true;
            }

            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!SettingsData.getTell(p.getUniqueId())) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.tell")));
                    return true;
                }
            }

            Player receiver = Bukkit.getPlayer(args[0]);
            if (receiver != null) {
                if (!receiver.isOnline()) {
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.offline")));
                    return true;
                }
            } else {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.offline")));
                return true;
            }

            if (sender instanceof Player) {
                if (!SettingsData.getTell(receiver.getUniqueId())) {
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-msg.tell-other").replaceAll("%player%", GetRank.rankPlayer(receiver))));
                    return true;
                }
            }

            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getUniqueId() == receiver.getUniqueId()) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.messages.itself")));
                    return true;
                }
            }

            String mensagem = "";
            for (int i = 1; i < args.length; i++) {
                mensagem += args[i];
            }

            if (sender.hasPermission("g.utils.vip")) {
                sender.sendMessage(Utils.chat(
                        plugin.getConfig().getString("pmchat.prefix-sender")
                                .replaceAll("%sender%", sender.getName())
                                .replaceAll("%receiver%", receiver.getDisplayName()) + mensagem));

                receiver.sendMessage(Utils.chat(
                        plugin.getConfig().getString("pmchat.prefix-receiver")
                                .replaceAll("%sender%", sender.getName())
                                .replaceAll("%receiver%", receiver.getDisplayName()) + mensagem));
            } else {
                sender.sendMessage(Utils.chat(
                        plugin.getConfig().getString("pmchat.prefix-sender")
                                .replaceAll("%sender%", sender.getName())
                                .replaceAll("%receiver%", receiver.getDisplayName())) + mensagem);

                receiver.sendMessage(Utils.chat(
                        plugin.getConfig().getString("pmchat.prefix-receiver")
                                .replaceAll("%sender%", sender.getName())
                                .replaceAll("%receiver%", receiver.getDisplayName())) + mensagem);
            }
            return true;
        }
        return false;
    }
}
