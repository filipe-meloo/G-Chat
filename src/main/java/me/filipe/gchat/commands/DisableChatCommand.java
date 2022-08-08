package me.filipe.gchat.commands;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisableChatCommand implements CommandExecutor {

    private GChat plugin;
    public DisableChatCommand(GChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("chatdisable").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("chatdisable")) {

            if (!sender.hasPermission("g.utils.staff")) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("gchat.permission")));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("chatdisable.invalid-use")));
                return true;
            }

            if (args[0].equals("l")) {
                SettingsData.toggleLocal();
                boolean a = SettingsData.getLocalEnabled();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("");
                    if (a) {
                        player.sendMessage(Utils.chat(plugin.getConfig().getString("chatdisable.locale").replaceAll("%prefix%", Utils.chat(plugin.getConfig().getString("gchat.prefix")))));
                    } else {
                        player.sendMessage(Utils.chat(plugin.getConfig().getString("chatdisable.locald").replaceAll("%prefix%", Utils.chat(plugin.getConfig().getString("gchat.prefix")))));
                    }
                    player.sendMessage("");
                }
            }

            if (args[0].equals("g")) {
                SettingsData.toggleGlobal();
                boolean a = SettingsData.getGlobalEnabled();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("");
                    if (a) {
                        player.sendMessage(Utils.chat(plugin.getConfig().getString("chatdisable.globale").replaceAll("%prefix%", Utils.chat(plugin.getConfig().getString("gchat.prefix")))));
                    } else {
                        player.sendMessage(Utils.chat(plugin.getConfig().getString("chatdisable.globald").replaceAll("%prefix%", Utils.chat(plugin.getConfig().getString("gchat.prefix")))));
                    }
                    player.sendMessage("");
                }
            }

            return true;
        }
        return false;
    }
}
