package controller.controller;

import controller.obsever.VillageScreenObserver;
import model.CollisionObject;
import model.Map;
import model.Player;
import view.global.GlobalVariables;;import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    private List<CollisionObject> collisionObjects = new ArrayList<>();
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH);
        map.setPlayer(new Player(GlobalVariables.TILE_SIZE*19, GlobalVariables.TILE_SIZE*17));
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*0, GlobalVariables.TILE_SIZE*0, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*39));
        map.setCollisionObjects(collisionObjects);
    }

    public Player getPlayer() {
        return map.getPlayer();
    }

    public void actionMove(KeyEvent e){
        int yAux = getPlayer().getColumn();
        int xAux = getPlayer().getRow();
        // pra cima
        if (e.getKeyCode() == 38) {
            yAux -= 32;
            getPlayer().walkUp();
            observer.updateLabelPlayer(getPlayer().getImageAnimationName());
        }

        // pra baixo
        if (e.getKeyCode() == 40) {
            yAux += 32;
            getPlayer().walkDown();
            observer.updateLabelPlayer(getPlayer().getImageAnimationName());
        }

        // pra esquerda
        if (e.getKeyCode() == 37) {
            xAux -= 32;
            getPlayer().walkLeft();
            observer.updateLabelPlayer(getPlayer().getImageAnimationName());
        }

        // pra direita
        if (e.getKeyCode() == 39) {
            xAux += 32;
            getPlayer().walkRight();
            observer.updateLabelPlayer(getPlayer().getImageAnimationName());
        }

        if (!hasObject(yAux, xAux)) {
            getPlayer().setColumn(yAux);
            getPlayer().setRow(xAux);
        }
    }

    public boolean hasObject(int y, int x) {
        int rightSideHero = x + GlobalVariables.TILE_SIZE * 1;
        int leftSideHero = x;
        int bottomSideHero = y + GlobalVariables.TILE_SIZE * 2;
        int topSideHero = y;

        int rightSideElement = 0;
        int leftSideElement = 0;
        int bottomSideElement = 0;
        int topSideElement = 0;

        boolean hasObject = false;

        for (CollisionObject l : collisionObjects) {
            boolean right = false, left = false, top = false, bottom = false;
            rightSideElement = l.getROW() + l.getWIDTH();
            leftSideElement = l.getROW();
            bottomSideElement = l.getCOLUMN() + l.getHEIGHT();
            topSideElement = l.getCOLUMN();

            if (rightSideHero >= leftSideElement) {
                right = true;
            }
            if (topSideHero <= bottomSideElement) {
                top = true;
            }
            if (bottomSideHero >= topSideElement) {
                bottom = true;
            }
            if (leftSideHero <= rightSideElement) {
                left = true;
            }

            if (right && left && top && bottom) {
                hasObject = true;
            }
        }
        return hasObject;
    }
}
