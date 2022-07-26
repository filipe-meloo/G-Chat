package me.filipe.gchat.utils;

import org.bukkit.entity.Player;

public class GetRank {

    public static String rankPlayer (Player p) {
        //Verifica o rank do jogador
        // Gerente
        if (p.hasPermission("g.utils.gerente")) {
            return "&x&a&2&0&0&0&0&lGerente &x&a&2&0&0&0&0" + p.getDisplayName();
        }
        // Admin
        else if (p.hasPermission("g.utils.admin")) {
            return "&x&0&0&2&e&f&f&lAdmin &x&0&0&2&e&f&f" + p.getDisplayName();
        }
        // Mod
        else if (p.hasPermission("g.utils.mod")) {
            return "&x&0&0&b&4&0&5&lModerador &x&0&0&b&4&0&5" + p.getDisplayName();
        }
        // Construtor
        else if (p.hasPermission("g.utils.construtor")) {
            return "&x&3&b&f&e&4&1&lConstrutor &x&3&b&f&e&4&1" + p.getDisplayName();
        }
        // Ajudante
        else if (p.hasPermission("g.utils.ajudante")) {
            return "&x&f&f&f&f&0&0&lAjudante &x&f&f&f&f&0&0" + p.getDisplayName();
        }
        // Dragon
        else if (p.hasPermission("g.utils.dragon")) {
            return "&x&f&7&0&0&f&f&lDragon &x&f&7&0&0&f&f" + p.getDisplayName();
        }
        // Magma
        else if (p.hasPermission("g.utils.magma")) {
            return "&x&f&f&5&1&0&0&lMagma &x&f&f&5&1&0&0" + p.getDisplayName();
        }
        // Zombie
        else if (p.hasPermission("g.utils.zombie")) {
            return "&x&2&5&7&5&0&a&lZombie &x&2&5&7&5&0&a" + p.getDisplayName();
        }
        // Membro
        else {
            return "&7" + p.getDisplayName();
        }
    }

}
