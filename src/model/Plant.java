package model;

public class Plant {
    private final String IMAGE_NAME;
    private final String PLANT_GROUP;
    private final boolean IS_VASCULAR;
    private final String DEPENDENT_TO_REPRODUCTION;
    private final String MAIN_LIFE_CYCLE;
    private final boolean HAS_ROOTS;
    private final boolean HAS_STEM;
    private final boolean HAS_LEAVES;
    private final boolean HAS_SPORES;
    private final boolean HAS_SEED;
    private final boolean HAS_FLOWER;
    private final boolean HAS_FRUIT;
    private final String WHERE_FIND_IT;
    
    public Plant(String imageName, String plantGroup, boolean isVascular, String dependentToReproduction,
                 String mainLifeCycle, boolean hasRoots, boolean hasStem,
                 boolean hasLeaves, boolean hasSpores, boolean hasSeed, boolean hasFlower, boolean hasFruit, String whereFindIt) {
        IMAGE_NAME = imageName;
        PLANT_GROUP = plantGroup;
        IS_VASCULAR = isVascular;
        DEPENDENT_TO_REPRODUCTION = dependentToReproduction;
        MAIN_LIFE_CYCLE = mainLifeCycle;
        HAS_ROOTS = hasRoots;
        HAS_STEM = hasStem;
        HAS_LEAVES = hasLeaves;
        HAS_SPORES = hasSpores;
        HAS_SEED = hasSeed;
        HAS_FLOWER = hasFlower;
        HAS_FRUIT = hasFruit;
        WHERE_FIND_IT = whereFindIt;
        
    }
    
    public String getIMAGE_NAME() {
        return IMAGE_NAME;
    }
    
    public String getPLANT_GROUP() {
        return PLANT_GROUP;
    }
    
    public boolean getIS_VASCULAR() {
        return IS_VASCULAR;
    }
    
    public String getDEPENDENT_TO_REPRODUCTION() {
        return DEPENDENT_TO_REPRODUCTION;
    }
    
    public String getMAIN_LIFE_CYCLE() {
        return MAIN_LIFE_CYCLE;
    }
    
    public boolean getHAS_ROOTS() {
        return HAS_ROOTS;
    }
    
    public boolean getHAS_STEM() {
        return HAS_STEM;
    }
    
    public boolean getHAS_LEAVES() {
        return HAS_LEAVES;
    }
    
    public boolean getHAS_SPORES() {
        return HAS_SPORES;
    }
    
    public boolean getHAS_SEED() {
        return HAS_SEED;
    }
    
    public boolean getHAS_FLOWER() {
        return HAS_FLOWER;
    }
    
    public boolean getHAS_FRUITS() {
        return HAS_FRUIT;
    }
    
    public String getWHERE_FIND_IT() {
        return WHERE_FIND_IT;
    }
}
