package controller.controller;

import controller.obsever.GameFrameObserver;
import controller.obsever.VillageScreenObserver;
import model.*;
import global.GlobalVariables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    private boolean isTeleportEnabled;
    private final static TileContent[][] VILLAGE_TILES_CONTENT = new TileContent[21][39];
    private final static java.util.Map<String, Interactive> INTERACTIVES = new HashMap<>();
    private final static java.util.Map<String, Teleport> TELEPORTS = new HashMap<>();
    
    //INITIALIZE STATIC ITENS
    static {
        try {
            //TILE CONTENT
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/global/village.properties"));
            for(int row = 0; row < 21; row++) {
                for(int column = 0; column < 39; column++) {
                    try {
                        VILLAGE_TILES_CONTENT[row][column] = TileContent.valueOf(properties.getProperty(row + "x" + column));
                    } catch(IllegalArgumentException e) {
                        VILLAGE_TILES_CONTENT[row][column] = TileContent.EMPTY;
                    }
                }
            }
            //INTERACTIVES
            INTERACTIVES.put("16x31", new Interactive(16, 31, new LinkedList<>(Arrays.asList("Oi", "Tchau"))));
            //TELEPORTS
            TELEPORTS.put("9x5", new Teleport(9, 5, "navigateToHouseScreen"));
            TELEPORTS.put("9x33", new Teleport(9, 33, "navigateToMarketScreen"));
            TELEPORTS.put("16x24", new Teleport(16, 24, "navigateToTrailerScreen"));
        } catch(IOException e) {
            System.exit(0);
        }
    }
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, VILLAGE_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(17, 19));
        isTeleportEnabled = false;
    }
    
    public VillageScreenController(VillageScreenObserver observer, int plawerRow, int playerColumn) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, VILLAGE_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(plawerRow, playerColumn));
        map.getPlayer().setWalkState(new WalkStateDown(map.getPlayer()));
        isTeleportEnabled = false;
    }
    
    //Chamada reflexiva
    public void navigateToHouseScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToHouseScreen();
    }
    
    //Chamada reflexiva
    public void navigateToMarketScreen(GameFrameObserver gameFrame) {
                gameFrame.navigateToMarketScreen();
    }
    
    //Chamada reflexiva
    public void navigateToTrailerScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToTrailerScreen();
    }
    
    public int getPlayerRow() {
        return map.getPlayer().getRow();
    }
    
    public int getPlayerColumn() {
        return map.getPlayer().getColumn();
    }
    
    public String getPlayerImageIdleName() {
        return map.getPlayer().getImageIdleName();
    }
    
    public String getPlayerImageAnimationName() {
        return map.getPlayer().getImageAnimationName();
    }
    
    public void walkUp() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateUp();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (row > 0) {
            if(!hasObject(row-1, column)) {
                player.walkUp();
                observer.updatePlayerRowUp(player.getRow());
            }
        }
    }
    
    public void walkDown() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateDown();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (row < map.getTileContents().length-1) {
            if(!hasObject(row+1, column)) {
                player.walkDown();
                observer.updatePlayerRowDown(player.getRow());
            }
        }
    }
    
    public void walkLeft() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateLeft();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (column > 0) {
            if(!hasObject(row, column-1)) {
                player.walkLeft();
                observer.updatePlayerColumnLeft(player.getColumn());
            }
        }
    }
    
    public void walkRight() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateRight();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (column < map.getTileContents()[0].length-1) {
            if(!hasObject(row, column+1)) {
                player.walkRight();
                observer.updatePlayerColumnRight(player.getColumn());
            }
        }
    }
    
    private boolean hasObject(int row, int column) {
        if(map.getTileContents()[row][column].equals(TileContent.EMPTY)) {
            if (isTeleportEnabled) {
                observer.disableTeleport();
                isTeleportEnabled = false;
            }
            return false;
        }
        if (map.getTileContents()[row][column].equals(TileContent.TELEPORT)) {
            Teleport teleport = TELEPORTS.get(row+"x"+column);
            observer.enableTeleport(teleport.getTELEPORT_METHOD_NAME(), row-2, column);
            isTeleportEnabled = true;
            return false;
        }
        else if (map.getTileContents()[row][column].equals(TileContent.INTERACTIVE)) {
            observer.updatePlayerImage(GlobalVariables.PLAYER_ANIMATION_LEFT_KEY);
        }
        return true;
    }
}
