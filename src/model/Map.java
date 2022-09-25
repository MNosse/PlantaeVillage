package model;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int HEIGHT;
    private final int WIDTH;
    private Player player;
    private List<CollisionObject> collisionObjects;
    
    public Map(int height, int width) {
        HEIGHT = height;
        WIDTH = width;
        collisionObjects = new ArrayList<>();
    }
    
    public int getHEIGHT() {
        return HEIGHT;
    }
    
    public int getWIDTH() {
        return WIDTH;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public List<CollisionObject> getCollisionObjects() {
        return collisionObjects;
    }
    
    public void setCollisionObjects(List<CollisionObject> collisionObjects) {
        this.collisionObjects = collisionObjects;
    }
}
