package org.zhaojason.testingplugin.events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zhaojason.testingplugin.Main;
import org.zhaojason.testingplugin.data.DataManager;
import org.zhaojason.testingplugin.utils.PickaxeUtils;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Locale;

public class blockBreakEvent implements Listener {

    private final Main main;
    public blockBreakEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e){
        Player player = e.getPlayer();
        e.getPlayer().getInventory().getItemInMainHand();
        ItemStack i =  e.getPlayer().getInventory().getItemInMainHand();
        if(i.getType().equals((Material.AIR))){
            return;
        }
        NBTItem nbti = new NBTItem(i);
        if(!i.getType().toString().toLowerCase(Locale.ROOT).contains("pickaxe")){
            return;
        }
        if(i.getItemMeta() != null) {
            if(i.getItemMeta().hasDisplayName()){
                if(nbti.hasKey("PickaxeXP")){
                    DataManager data = new DataManager(main);
                    if(data.checkGenExists(e.getBlock().getLocation(), e.getBlock().getType())){
                        Material block = e.getBlock().getType();
                        e.setDropItems(false);
                        e.getPlayer().getInventory().addItem(new ItemStack(e.getBlock().getType()));
                        e.getBlock().getLocation().getWorld().setBlockData(e.getBlock().getLocation(), Material.BEDROCK.createBlockData());
                        Bukkit.getScheduler().runTaskLater(main, () ->
                                        e.getBlock().getLocation().getWorld().setBlockData(e.getBlock().getLocation(), block.createBlockData()),
                                40);
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
                        e.setCancelled(true);
                    }

                }else{
                    e.setCancelled(true);
                    player.sendMessage("You have a broken pickaxe");

                }
            }
        }


    }
}
