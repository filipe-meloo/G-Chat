package me.filipe.gchat.automessages;

import me.filipe.gchat.GChat;
import me.filipe.gchat.data.SettingsData;
import me.filipe.gchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class AutoMessage {

    private GChat plugin;
    public AutoMessage(GChat plugin) {
        this.plugin = plugin;

        BukkitTask task = new BukkitRunnable() {
            int lastValue = 6;
            public void run() {
                String[] mensagens = new String[5];
                TextComponent[] hover = new TextComponent[5];

                mensagens[0] = Utils.chat("&x&7&2&8&9&d&a&lDiscord: &7Junta-te ao nosso &x&7&2&8&9&d&aDiscord &7para ficares a par de todas as &n&enovidades&7!");
                hover[0] = new TextComponent("(Clica aqui)");
                hover[0].setItalic(true);
                hover[0].setColor(ChatColor.YELLOW);
                hover[0].setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://discord.m4stercraft.fun"));
                hover[0].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("discord.m4stercraft.fun").color(ChatColor.GRAY).italic(true).create()));

                mensagens[1] = Utils.chat("&x&f&f&3&e&0&0&lLoja: &7Passa pela nossa &eloja &7para ajudares o servidor!");
                hover[1] = new TextComponent("(Clica aqui)");
                hover[1].setItalic(true);
                hover[1].setColor(ChatColor.YELLOW);
                hover[1].setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://loja.m4stercraft.fun"));
                hover[1].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("loja.m4stercraft.fun").color(ChatColor.GRAY).italic(true).create()));

                mensagens[2] = Utils.chat("&x&f&f&f&7&0&0&lAjuda: &7Os staffs estão sempre disponíveis a ajudar! &7Basta usares &e/report &7para pedires ajuda! (Não abuses deste comando.)");
                hover[2] = new TextComponent("(Clica aqui)");
                hover[2].setItalic(true);
                hover[2].setColor(ChatColor.YELLOW);
                hover[2].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report"));
                hover[2].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("/report").color(ChatColor.GRAY).italic(true).create()));

                mensagens[3] = Utils.chat("&x&0&0&e&c&f&f&lBugs: &7Os staffs estão sempre a tentar descobrir novos bugs! &7Se descobrires algum, &creporta SEMPRE &7para o bom funcionamento do servidor. Existe quem diga que poderás receber uma &6recompensa&7! &4(Não abuses dos bugs. Serás punido!)");
                hover[3] = new TextComponent("(Clica aqui)");
                hover[3].setItalic(true);
                hover[3].setColor(ChatColor.YELLOW);
                hover[3].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report"));
                hover[3].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("/report").color(ChatColor.GRAY).italic(true).create()));

                mensagens[4] = Utils.chat("&x&7&d&0&0&0&0&lCheats: &7Cumpre as regras e não uses programas para modificar a tua experiência. &7Se te sentes desconfortável, incomodado podes falar com a staff para te ajudar. Tens o nosso &x&7&2&8&9&d&a&lDiscord &7disponível!)");
                hover[4] = new TextComponent("(Clica aqui)");
                hover[4].setItalic(true);
                hover[4].setColor(ChatColor.YELLOW);
                hover[4].setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://discord.m4stercraft.fun"));
                hover[4].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("discord.m4stercraft.fun").color(ChatColor.GRAY).italic(true).create()));


                Random rand = new Random();
                int randInt = rand.nextInt(5);
                while (lastValue == randInt) {
                    randInt = rand.nextInt(5);
                }
                lastValue = randInt;

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!SettingsData.getSm(player.getUniqueId())) continue;
                    player.sendMessage("");
                    player.sendMessage(mensagens[randInt]);
                    player.spigot().sendMessage(hover[randInt]);
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }

            }
        }.runTaskTimer(this.plugin,12000 ,12000);

    }

}
