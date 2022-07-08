package org.zhaojason.testingplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setFormat(e.getPlayer().getName() + " » " + e.getMessage());
    }

}
