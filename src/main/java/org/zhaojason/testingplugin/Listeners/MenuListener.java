package org.zhaojason.testingplugin.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        //Player p = (Player) e.getWhoClicked();
        if(ChatColor.translateAlternateColorCodes('&',e.getView().getTitle()).equals(ChatColor.translateAlternateColorCodes('&', "&7Custom menu"))){
            e.setCancelled(true);
        }

    }
}
