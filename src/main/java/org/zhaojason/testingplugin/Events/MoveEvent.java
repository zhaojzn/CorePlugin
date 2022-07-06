package org.zhaojason.testingplugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setFormat(e.getPlayer().getName() + " » " + e.getMessage());
    }

}
