package me.filipe.gchat.menus;

import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.ImpHolder;
import me.filipe.gchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SettingsMenu {

    public static Inventory getChatSettings(Player player) {

        Inventory inv = Bukkit.createInventory(new ImpHolder(), 5*9, Utils.chat("Opções - Chat"));

        //Itens do Menu
        List<String> lore = new ArrayList<String>();

        lore.clear(); lore.add(""); lore.add("&7Clica para alterar o"); lore.add("&b&lChat Global&7."); lore.add("");
        ItemStack global = Utils.createItem(Material.MAP, 1, "&b&lChat Global", lore);
        ItemStack global_dye1 = Utils.createItem(Material.LIME_DYE, 1, "&a&lChat Global - [✔] Ativado", lore);
        ItemStack global_dye0 = Utils.createItem(Material.GRAY_DYE, 1, "&c&lChat Global - [✗] Desativado", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para alterar o"); lore.add("&e&lChat Local&7."); lore.add("");
        ItemStack local = Utils.createItem(Material.WRITABLE_BOOK, 1, "&e&lChat Local", lore);
        ItemStack local_dye1 = Utils.createItem(Material.LIME_DYE, 1, "&a&lChat Local - [✔] Ativado", lore);
        ItemStack local_dye0 = Utils.createItem(Material.GRAY_DYE, 1, "&c&lChat Local - [✗] Desativado", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para alterar as"); lore.add("&d&lMensagens Privadas&7."); lore.add("");
        ItemStack tell = Utils.createItem(Material.SPYGLASS, 1, "&d&lMensagens Privadas", lore);
        ItemStack tell_dye1 = Utils.createItem(Material.LIME_DYE, 1, "&a&lMensagens Privadas - [✔] Ativadas", lore);
        ItemStack tell_dye0 = Utils.createItem(Material.GRAY_DYE, 1, "&c&lMensagens Privadas - [✗] Desativadas", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para alterar as"); lore.add("&6&lMensagens Automáticas&7."); lore.add("");
        ItemStack sm = Utils.createItem(Material.REPEATING_COMMAND_BLOCK, 1, "&6&lMensagens Automáticas", lore);
        ItemStack sm_dye1 = Utils.createItem(Material.LIME_DYE, 1, "&a&lMensagens Automáticas - [✔] Ativadas", lore);
        ItemStack sm_dye0 = Utils.createItem(Material.GRAY_DYE, 1, "&c&lMensagens Automáticas - [✗] Desativadas", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para alterar os"); lore.add("&x&0&0&f&f&2&8&lAnúncios&7."); lore.add("");
        ItemStack broadcast = Utils.createItem(Material.GOAT_HORN, 1, "&x&0&0&f&f&2&8&lAnúncios", lore);
        ItemStack broadcast_dye1 = Utils.createItem(Material.LIME_DYE, 1, "&a&lAnúncios - [✔] Ativados", lore);
        ItemStack broadcast_dye0 = Utils.createItem(Material.GRAY_DYE, 1, "&c&lAnúncios - [✗] Desativados", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para fechar o menu."); lore.add("");
        ItemStack barrier = Utils.createItem(Material.BARRIER, 1, "&cFechar", lore);

        inv.setItem(40, barrier);

        inv.setItem(11, global);
        inv.setItem(12, local);
        inv.setItem(13, tell);
        inv.setItem(14, sm);
        inv.setItem(15, broadcast);

        if (SettingsData.getGlobal(player.getUniqueId())) {
            inv.setItem(20, global_dye1);
        } else {
            inv.setItem(20, global_dye0);
        }

        if (SettingsData.getLocal(player.getUniqueId())) {
            inv.setItem(21, local_dye1);
        } else {
            inv.setItem(21, local_dye0);
        }

        if (SettingsData.getTell(player.getUniqueId())) {
            inv.setItem(22, tell_dye1);
        } else {
            inv.setItem(22, tell_dye0);
        }

        if (SettingsData.getSm(player.getUniqueId())) {
            inv.setItem(23, sm_dye1);
        } else {
            inv.setItem(23, sm_dye0);
        }

        if (SettingsData.getBroad(player.getUniqueId())) {
            inv.setItem(24, broadcast_dye1);
        } else {
            inv.setItem(24, broadcast_dye0);
        }

        return inv;
    }

}
