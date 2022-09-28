package controller.controller;

import controller.obsever.VillageScreenObserver;
import model.CollisionObject;
import model.Map;
import model.Player;
import view.global.GlobalVariables;
import java.util.ArrayList;
import java.util.List;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    private List<CollisionObject> collisionObjects = new ArrayList<>();
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH);
        map.setPlayer(new Player(GlobalVariables.TILE_SIZE*17, GlobalVariables.TILE_SIZE*19));
        collisionObjects.add(new CollisionObject(0, 0, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*39));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*19, 0, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*39));
        collisionObjects.add(new CollisionObject(0, 0, GlobalVariables.TILE_SIZE*21, GlobalVariables.TILE_SIZE*2));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*37, GlobalVariables.TILE_SIZE*21, GlobalVariables.TILE_SIZE*2));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*2));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*5, GlobalVariables.TILE_SIZE*6, GlobalVariables.TILE_SIZE));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*6, GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*9, GlobalVariables.TILE_SIZE*9, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*12, GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*13));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*14, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*18, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*22, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*26, GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*27, GlobalVariables.TILE_SIZE*9, GlobalVariables.TILE_SIZE*3));
        collisionObjects.add(new CollisionObject(0, GlobalVariables.TILE_SIZE*30, GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*7));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*30, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*2));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*32, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*34, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*35, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*2));
        map.setCollisionObjects(collisionObjects);
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
        map.getPlayer().walkUp();
        observer.updatePlayerImage(getPlayerImageAnimationName());
        if (!hasObjectUp(rowAux, map.getPlayer().getColumn())) {
            observer.updatePlayerRow(map.getPlayer().getRow());
        } else {
            map.getPlayer().setRow(rowAux);
            observer.updatePlayerImage(getPlayerImageIdleName());
            observer.updatePlayerRow(map.getPlayer().getRow());
        }
    }
    
    public void walkDown() {
        int rowAux = map.getPlayer().getRow();
        map.getPlayer().walkDown();
        observer.updatePlayerImage(getPlayerImageAnimationName());
        if (!hasObjectDown(rowAux, map.getPlayer().getColumn())) {
            observer.updatePlayerRow(map.getPlayer().getRow());
        } else {
            map.getPlayer().setRow(rowAux);
            observer.updatePlayerImage(getPlayerImageIdleName());
            observer.updatePlayerRow(map.getPlayer().getRow());
        }
    }
    
    public void walkLeft() {
        int columnAux = map.getPlayer().getColumn();
        map.getPlayer().walkLeft();
        observer.updatePlayerImage(getPlayerImageAnimationName());
        if (!hasObjectLeft(map.getPlayer().getRow(), columnAux)) {
            observer.updatePlayerColumn(map.getPlayer().getColumn());
        } else {
            map.getPlayer().setColumn(columnAux);
            observer.updatePlayerImage(getPlayerImageIdleName());
            observer.updatePlayerColumn(map.getPlayer().getColumn());
        }
    }
    
    public void walkRight() {
        int columnAux = map.getPlayer().getColumn();
        map.getPlayer().walkRight();
        observer.updatePlayerImage(getPlayerImageAnimationName());
        if (!hasObjectRight(map.getPlayer().getRow(), columnAux)) {
            observer.updatePlayerColumn(map.getPlayer().getColumn());
        } else {
            map.getPlayer().setColumn(columnAux);
            observer.updatePlayerImage(getPlayerImageIdleName());
            observer.updatePlayerColumn(map.getPlayer().getColumn());
        }
    }
    
    private boolean hasObjectUp(int row, int column) {
        for (CollisionObject l : collisionObjects) {
            if (l.getCOLUMN() <= column) {
                if (l.getCOLUMN() + l.getWIDTH() > column) {
                    if ((l.getROW() + l.getHEIGHT()) == (row + GlobalVariables.TILE_SIZE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasObjectDown(int row, int column) {
        for (CollisionObject l : collisionObjects) {
            if (l.getCOLUMN() <= column) {
                if (l.getCOLUMN() + l.getWIDTH() > column) {
                    if (l.getROW() == (row + GlobalVariables.TILE_SIZE*2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasObjectLeft(int row, int column) {
        for (CollisionObject l : collisionObjects) {
            if (l.getROW() <= (row + GlobalVariables.TILE_SIZE)) {
                if (l.getROW() + l.getHEIGHT() > (row + GlobalVariables.TILE_SIZE)) {
                    if (l.getCOLUMN() + l.getWIDTH() == column) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasObjectRight(int row, int column) {
        for (CollisionObject l : collisionObjects) {
            if (l.getROW() <= (row + GlobalVariables.TILE_SIZE)) {
                if (l.getROW() + l.getHEIGHT() > (row + GlobalVariables.TILE_SIZE)) {
                    if (l.getCOLUMN() == (column + GlobalVariables.TILE_SIZE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
