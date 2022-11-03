package model;

public abstract class CollisionObject {
    private final int ROW;
    private final int COLUMN;
    
    public CollisionObject(int row, int column) {
        ROW = row;
        COLUMN = column;
    }
    
    public int getROW() {
        return ROW;
    }
    
    public int getCOLUMN() {
        return COLUMN;
    }
}
