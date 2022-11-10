package controller.controller;

import controller.obsever.GameFrameObserver;
import controller.obsever.MarketScreenObserver;
import global.GlobalVariables;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class MarketScreenController {
    
    private MarketScreenObserver observer;
    private Map map;
    private Teleport currentTeleport;
    private Interactive currentInteractive;
    private boolean firstInteraction;
    private final static TileContent[][] MARKET_TILES_CONTENT = new TileContent[21][39];
    private final static java.util.Map<String, Interactive> INTERACTIVES = new HashMap<>();
    private final static java.util.Map<String, Teleport> TELEPORTS = new HashMap<>();
    
    //INITIALIZE STATIC ITENS
    static {
        try {
            //TILE CONTENT
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/global/market.properties"));
            for(int row = 0; row < 21; row++) {
                for(int column = 0; column < 39; column++) {
                    try {
                        MARKET_TILES_CONTENT[row][column] = TileContent.valueOf(properties.getProperty(row + "x" + column));
                    } catch(IllegalArgumentException e) {
                        MARKET_TILES_CONTENT[row][column] = TileContent.EMPTY;
                    }
                }
            }
        } catch(IOException e) {
            System.exit(0);
        }
    }
    
    public MarketScreenController(MarketScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, MARKET_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(20, 19));
        currentTeleport = null;
        currentInteractive = null;
        firstInteraction = true;
        //INTERACTIVES
        INTERACTIVES.put("11x13", new Interactive(11, 13, Repository.getInstance().getNpcLines(8)));
        INTERACTIVES.put("11x14", new Interactive(11, 14, Repository.getInstance().getNpcLines(9)));
        INTERACTIVES.put("14x27", new Interactive(14, 27, Repository.getInstance().getNpcLines(10)));
        INTERACTIVES.put("17x24", new Interactive(17, 24, Repository.getInstance().getNpcLines(11)));
        //TELEPORTS
        TELEPORTS.put("20x19", new Teleport(20, 19, "navigateToVillageScreen"));
    }
    
    public void teleport(GameFrameObserver gameFrameObserver) {
        try {
            Method method = MarketScreenController.class.getDeclaredMethod(currentTeleport.getTELEPORT_METHOD_NAME(), GameFrameObserver.class);
            method.invoke(this, gameFrameObserver);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            return;
        }
    }
    
    public void interact() {
        if (currentInteractive != null) {
            if (firstInteraction) {
                observer.openDialog();
                firstInteraction = false;
            }
            String message = currentInteractive.readNextLine();
            observer.updateDialogMessage(message);
            if (message.isEmpty()) {
                firstInteraction = true;
            }
        }
    }
    
    //Chamada reflexiva
    public void navigateToVillageScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToVillageScreen(9, 33);
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
            if (currentTeleport != null) {
                observer.disableTeleport();
                currentTeleport = null;
            }
            if (currentInteractive != null) {
                observer.disableInteractive();
                currentInteractive = null;
            }
            return false;
        }
        if (map.getTileContents()[row][column].equals(TileContent.TELEPORT)) {
            currentTeleport = TELEPORTS.get(row+"x"+column);
            observer.enableTeleport(row-2, column);
            return false;
        }
        else if (map.getTileContents()[row][column].equals(TileContent.INTERACTIVE)) {
            currentInteractive = INTERACTIVES.get(row+"x"+column);
            observer.enableInteractive(row-2, column);
            return false;
        }
        return true;
    }
}
