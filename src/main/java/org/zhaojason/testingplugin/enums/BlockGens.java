package org.zhaojason.testingplugin.enums;

import org.bukkit.Material;

public class BlockGens {
    String name;

    public BlockGens(String name) {
        this.name = name;
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
        COAL(Material.COAL_ORE, "COALGEN", 2),
        IRON(Material.IRON_ORE, "IRONGEN", 4),
        GOLD(Material.GOLD_ORE, "GOLDGEN", 7);

        final String genName;
        final int regenTime;
        final Material material;

        TypeGens(Material material, String genName, int regenTime) {
            this.material = material;
            this.genName = genName;
            this.regenTime = regenTime;
        }

        public String getGen() { return this.genName; }
        public Material getMaterial() {return material; }
        public int getRegenTime() { return this.regenTime;}


    }


}
