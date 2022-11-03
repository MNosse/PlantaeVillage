package model;

public class Map {
    private final int HEIGHT;
    private final int WIDTH;
    private Player player;
    private TileContent[][] tileContents;
    private java.util.Map<String, Interactive> interactives;
    private java.util.Map<String, Teleport> teleports;
    
    public Map(int height, int width, TileContent[][] tileContents, java.util.Map<String, Interactive> interactives, java.util.Map<String, Teleport> teleports) {
        HEIGHT = height;
        WIDTH = width;
        this.tileContents = tileContents;
        this.interactives = interactives;
        this.teleports = teleports;
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
    
    public java.util.Map<String, Teleport> getTeleports() {
        return teleports;
    }
    
    public void setTeleports(java.util.Map<String, Teleport> teleports) {
        this.teleports = teleports;
    }
}
