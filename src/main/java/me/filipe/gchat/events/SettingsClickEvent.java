package me.filipe.gchat.events;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.Dados;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.menus.SettingsMenu;
import me.filipe.gchat.utils.ImpHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SettingsClickEvent implements Listener {

    private GChat plugin;
    public SettingsClickEvent(GChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!(event.getInventory().getHolder() instanceof ImpHolder)) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getClickedInventory() == event.getView().getBottomInventory()) return;


        if (event.getCurrentItem().getType() == Material.BARRIER) {
            player.closeInventory();
        }

        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).contains("Chat Global")) {
            //Chat Global
            SettingsData.setGlobal(player.getUniqueId(), !SettingsData.getGlobal(player.getUniqueId()));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).contains("Chat Local")) {
            //Chat Global
            SettingsData.setLocal(player.getUniqueId(), !SettingsData.getLocal(player.getUniqueId()));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).contains("Mensagens Privadas")) {
            //Chat Global
            SettingsData.setTell(player.getUniqueId(), !SettingsData.getTell(player.getUniqueId()));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).contains("Mensagens Automáticas")) {
            //Chat Global
            SettingsData.setSm(player.getUniqueId(), !SettingsData.getSm(player.getUniqueId()));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).contains("Anúncios")) {
            //Chat Global
            SettingsData.setBroad(player.getUniqueId(), !SettingsData.getBroad(player.getUniqueId()));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        Inventory inv = SettingsMenu.getChatSettings(player);
        for (int i = 0; i < inv.getSize();  i++) {
            event.getView().getTopInventory().setItem(i, inv.getItem(i));
        }
        player.updateInventory();
    }

}
