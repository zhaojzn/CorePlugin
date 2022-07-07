package org.zhaojason.testingplugin.Events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zhaojason.testingplugin.Main;
import org.zhaojason.testingplugin.utils.PickaxeUtils;

import java.util.List;

public class BlockBreakListener implements Listener {

    private final Main main;
    public BlockBreakListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(e.getPlayer().getInventory().getItemInMainHand() != null)
        {
            ItemStack i =  e.getPlayer().getInventory().getItemInMainHand();
            NBTItem nbti = new NBTItem(i);
            if(i.getItemMeta() != null) {
                if(i.getItemMeta().hasDisplayName()){
                    if(nbti.hasKey("PickaxeXP")){
                        e.setDropItems(false);
                        Integer nbti_xp = nbti.getInteger("PickaxeXP");
                        nbti_xp = nbti_xp + 1;
                        nbti.setInteger("PickaxeXP", nbti_xp);
                        PickaxeUtils p = new PickaxeUtils(main);

                        if(p.isUpgradeable(nbti)){
                            nbti = p.levelUpPickaxe(nbti);
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your pickaxe has been leveled up!"));
                        }
                        List<String> lore = p.updateLore(nbti);

                        ItemStack finalItem = nbti.getItem();
                        ItemMeta finalItemMeta = finalItem.getItemMeta();
                        finalItemMeta.setLore(lore);
                        finalItem.setItemMeta(finalItemMeta);

                        player.getInventory().setItemInMainHand(finalItem);
                    }else{
                        e.setCancelled(true);
                        player.sendMessage("You have a broken pickaxe");

                    }
                }
            }
        }




    }
}
