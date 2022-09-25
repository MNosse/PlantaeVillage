package model;

public class CollisionObject {
    private final int ROW;
    private final int COLUMN;
    private final int HEIGHT;
    private final int WIDTH;
    
    public CollisionObject(int row, int column, int height, int width) {
        ROW = row;
        COLUMN = column;
        HEIGHT = height;
        WIDTH = width;
    }
    
    public int getROW() {
        return ROW;
    }
    
    public int getCOLUMN() {
        return COLUMN;
    }
    
    public int getHEIGHT() {
        return HEIGHT;
    }
    
    public int getWIDTH() {
        return WIDTH;
    }
}
