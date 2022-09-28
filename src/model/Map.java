package model;

import java.util.HashMap;

public class Map {
    private final int HEIGHT;
    private final int WIDTH;
    private Player player;
    private TileContent[][] tileContents;
    private java.util.Map<String, Interactive> interactives;
    
    public Map(int height, int width, TileContent[][] tileContents, java.util.Map<String, Interactive> interactives) {
        HEIGHT = height;
        WIDTH = width;
        this.tileContents = tileContents;
        this.interactives = interactives;
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
    
    public TileContent[][] getTileContents() {
        return tileContents;
    }
    
    public void setTileContents(TileContent[][] tileContents) {
        this.tileContents = tileContents;
    }
    
    public java.util.Map<String, Interactive> getInteractives() {
        return interactives;
    }
    
    public void setInteractives(java.util.Map<String, Interactive> interactives) {
        this.interactives = interactives;
    }
}
