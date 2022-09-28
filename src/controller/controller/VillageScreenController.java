package controller.controller;

import controller.obsever.VillageScreenObserver;
import model.Interactive;
import model.Map;
import model.Player;
import model.TileContent;
import view.global.GlobalVariables;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        TileContent[][] tileContents = new TileContent[21][39];
        for (int row = 0; row < 21; row++) {
            for (int column = 0; column < 39; column++) {
                TileContent tileContent = null;
                String property = GlobalVariables.VILLAGE_PROPERTIES.getProperty(row+"x"+column);
                switch(property) {
                    case "EMPTY":
                        tileContent = TileContent.EMPTY;
                        break;
                    case "COLLISION":
                        tileContent = TileContent.COLLISION;
                        break;
                    case "INTERACTIVE":
                        tileContent = TileContent.INTERACTIVE;
                        break;
                    default:
                        tileContent = TileContent.EMPTY;
                        break;
                }
                tileContents[row][column] = tileContent;
            }
        }
        java.util.Map<String, Interactive> interactives = new HashMap<>();
        
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, tileContents, interactives);
        map.setPlayer(new Player(17, 19));
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
        int rowAux = map.getPlayer().getRow();
        if (rowAux > 0) {
            map.getPlayer().walkUp();
            observer.updatePlayerImage(getPlayerImageAnimationName());
            if(!hasObject(rowAux, map.getPlayer().getColumn())) {
                observer.updatePlayerRow(map.getPlayer().getRow());
            } else {
                map.getPlayer().setRow(rowAux);
                observer.updatePlayerImage(getPlayerImageIdleName());
                observer.updatePlayerRow(map.getPlayer().getRow());
            }
        }
    }

    public void walkDown() {
        int rowAux = map.getPlayer().getRow();
        if (rowAux < map.getTileContents().length-1) {
            map.getPlayer().walkDown();
            observer.updatePlayerImage(getPlayerImageAnimationName());
            if(!hasObject(rowAux, map.getPlayer().getColumn())) {
                observer.updatePlayerRow(map.getPlayer().getRow());
            } else {
                map.getPlayer().setRow(rowAux);
                observer.updatePlayerImage(getPlayerImageIdleName());
                observer.updatePlayerRow(map.getPlayer().getRow());
            }
        }
    }

    public void walkLeft() {
        int columnAux = map.getPlayer().getColumn();
        if (columnAux > 0) {
            map.getPlayer().walkLeft();
            observer.updatePlayerImage(getPlayerImageAnimationName());
            if(!hasObject(map.getPlayer().getRow(), columnAux)) {
                observer.updatePlayerColumn(map.getPlayer().getColumn());
            } else {
                map.getPlayer().setColumn(columnAux);
                observer.updatePlayerImage(getPlayerImageIdleName());
                observer.updatePlayerColumn(map.getPlayer().getColumn());
            }
        }
    }

    public void walkRight() {
        int columnAux = map.getPlayer().getColumn();
        if (columnAux < map.getTileContents()[0].length-1) {
            map.getPlayer().walkRight();
            observer.updatePlayerImage(getPlayerImageAnimationName());
            if(!hasObject(map.getPlayer().getRow(), columnAux)) {
                observer.updatePlayerColumn(map.getPlayer().getColumn());
            } else {
                map.getPlayer().setColumn(columnAux);
                observer.updatePlayerImage(getPlayerImageIdleName());
                observer.updatePlayerColumn(map.getPlayer().getColumn());
            }
        }
    }
    
    private boolean hasObject(int row, int column) {
        if (map.getTileContents()[row][column].equals(TileContent.EMPTY)) {
            return false;
        }
        return true;
    }
}
