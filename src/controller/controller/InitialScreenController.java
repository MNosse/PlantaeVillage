package controller.controller;

import controller.obsever.GameFrameObserver;

import java.awt.*;

public class InitialScreenController {
    
    public void navigateToVillageScreen(Container container) {
        GameFrameObserver gameFrame = (GameFrameObserver) container;
        gameFrame.navigateToVillageScreen();
    }
}
