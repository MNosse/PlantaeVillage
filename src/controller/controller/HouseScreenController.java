package controller.controller;

import controller.obsever.GameFrameObserver;
import controller.obsever.HouseScreenObserver;
import global.GlobalVariables;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class HouseScreenController {
    
    private HouseScreenObserver observer;
    private Map map;
    private boolean isTeleportEnabled;
    private final static TileContent[][] HOUSE_TILES_CONTENT = new TileContent[21][39];
    private final static java.util.Map<String, Interactive> INTERACTIVES = new HashMap<>();
    private final static java.util.Map<String, Teleport> TELEPORTS = new HashMap<>();
    
    //INITIALIZE STATIC ITENS
    static {
        try {
            //TILE CONTENT
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/global/house.properties"));
            for(int row = 0; row < 21; row++) {
                for(int column = 0; column < 39; column++) {
                    try {
                        HOUSE_TILES_CONTENT[row][column] = TileContent.valueOf(properties.getProperty(row + "x" + column));
                    } catch(IllegalArgumentException e) {
                        HOUSE_TILES_CONTENT[row][column] = TileContent.EMPTY;
                    }
                }
            }
            //INTERACTIVES
//            INTERACTIVES.put("16x31", new Interactive(16, 31, new LinkedList<>(Arrays.asList("Oi", "Tchau"))));
            //TELEPORTS
            TELEPORTS.put("20x19", new Teleport(20, 19, "navigateToVillageScreen"));
        } catch(IOException e) {
            System.exit(0);
        }
    }
    
    public HouseScreenController(HouseScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, HOUSE_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(20, 19));
        isTeleportEnabled = false;
    }
    
    //Chamada reflexiva
    public void navigateToVillageScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToVillageScreen(9, 5);
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
