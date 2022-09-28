package controller.controller;

import controller.obsever.GameFrameObserver;
import controller.obsever.VillageScreenObserver;
import model.CollisionObject;
import model.Map;
import model.Player;
import view.global.GlobalVariables;
import view.screen.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InitialScreenController {
    
    public void navigateToVillageScreen(Container container) {
        GameFrameObserver gameFrame = (GameFrameObserver) container;
        gameFrame.navigateToVillageScreen();
    }
}
