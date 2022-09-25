package model;

public class Plant {
    private final String IMAGE_NAME;
    private final String PLANT_GROUP;
    private final boolean IS_VASCULAR;
    private final String REPRODUCTION_TYPE;
    private final boolean WATER_DEPENDENT_REPRODUCTION;
    private final String MAIN_LIFE_CYCLE;
    private final boolean HAS_ROOTS;
    private final boolean HAS_STEM;
    private final boolean HAS_LEAVES;
    private final boolean HAS_SEED;
    private final boolean HAS_FLOWER;
    private final String WHERE_FIND_IT;
    
    public Plant(String imageName, String plantGroup, boolean isVascular, String reproductionType,
                 boolean waterDependentReproduction, String mainLifeCycle, boolean hasRoots, boolean hasStem,
                 boolean hasLeaves, boolean hasSeed, boolean hasFlower, String whereFindIt) {
        IMAGE_NAME = imageName;
        PLANT_GROUP = plantGroup;
        IS_VASCULAR = isVascular;
        REPRODUCTION_TYPE = reproductionType;
        WATER_DEPENDENT_REPRODUCTION = waterDependentReproduction;
        MAIN_LIFE_CYCLE = mainLifeCycle;
        HAS_ROOTS = hasRoots;
        HAS_STEM = hasStem;
        HAS_LEAVES = hasLeaves;
        HAS_SEED = hasSeed;
        HAS_FLOWER = hasFlower;
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
    
    public String getREPRODUCTION_TYPE() {
        return REPRODUCTION_TYPE;
    }
    
    public boolean getWATER_DEPENDENT_REPRODUCTION() {
        return WATER_DEPENDENT_REPRODUCTION;
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
    
    public boolean getHAS_SEED() {
        return HAS_SEED;
    }
    
    public boolean getHAS_FLOWER() {
        return HAS_FLOWER;
    }
    
    public String getWHERE_FIND_IT() {
        return WHERE_FIND_IT;
    }
}
