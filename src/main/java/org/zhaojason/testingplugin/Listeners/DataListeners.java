package org.zhaojason.testingplugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.zhaojason.testingplugin.Main;

public class DataListeners implements Listener {

    private Main main;
    public DataListeners(Main main){
        this.main = main;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (!main.getDataManager().hasPlayed(e.getPlayer().getUniqueId())){
            main.getDataManager().newPlayer(e.getPlayer().getUniqueId());
        }

    }
}
