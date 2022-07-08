package org.zhaojason.testingplugin.enums;

import org.bukkit.Material;
import org.zhaojason.testingplugin.utils.Chat;

public class BlockGens {
    String name;
    Material material;

    public BlockGens(String name) {
        this.name = name;
    }
    public BlockGens(Material material) {
        this.material = material;
    }

    public TypeGens getGenType(){
        switch (name){
            case "COALGEN":
                return TypeGens.COAL;
            case "IRONGEN":
                return TypeGens.IRON;
            case "GOLDGEN":
                return TypeGens.GOLD;
            default:
                return null;
        }
    }

    public enum TypeGens {
        COAL(Material.COAL_ORE, "COALGEN", 2, Chat.color("&8Coal gen ")),
        IRON(Material.IRON_ORE, "IRONGEN", 4, Chat.color("&7Iron gen ")),
        GOLD(Material.GOLD_ORE, "GOLDGEN", 7, Chat.color("&6Gold gen "));

        final String genName;
        final int regenTime;
        final Material material;
        final String displayName;

        TypeGens(Material material, String genName, int regenTime, String displayName) {
            this.material = material;
            this.genName = genName;
            this.regenTime = regenTime;
            this.displayName = displayName;
        }

        public String getGen() { return this.genName; }
        public Material getMaterial() {return material; }
        public int getRegenTime() { return this.regenTime; }
        public String getDisplayName() { return this.displayName; }
    }


}
