package controller.controller;

import controller.obsever.VillageScreenObserver;
import model.CollisionObject;
import model.Map;
import model.Player;
import view.global.GlobalVariables;;import java.util.ArrayList;
import java.util.List;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH);
        map.setPlayer(new Player(GlobalVariables.TILE_SIZE*19, GlobalVariables.TILE_SIZE*18));
        List<CollisionObject> collisionObjects = new ArrayList<>();
        collisionObjects.add(new CollisionObject(GlobalVariables.TILE_SIZE*0, GlobalVariables.TILE_SIZE*0, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE*39));
        map.setCollisionObjects(collisionObjects);
    }
}
