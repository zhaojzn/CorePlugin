package org.zhaojason.testingplugin.enums;

public class BlockGens {
    TypeGens typeGens;

    public BlockGens(TypeGens typeGens){
        this.typeGens = typeGens;
    }

    public enum TypeGens {
        COAL("COALGEN", 2),
        IRON("IRONGEN", 4),
        GOLD("GOLDGEN", 7);

        final String genName;
        final int regenTime;

        TypeGens(String genName, int regenTime) {
            this.genName = genName;
            this.regenTime = regenTime;
        }
        public String getGen(){
            return this.genName;
        }
        public int getRegenTime(){
            return this.regenTime;
        }



    }



}
