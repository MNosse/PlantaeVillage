package view.screen;

import controller.controller.VillageScreenController;
import controller.obsever.VillageScreenObserver;
import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;


public class VillageScreen extends JLabel implements VillageScreenObserver {
    
    private VillageScreenController controller;
    
    public VillageScreen() {
        super(new ImageIcon(GlobalVariables.IMAGES.get(GlobalVariables.VILLAGE_SCREEN_MAP_KEY)));
        controller = new VillageScreenController(this);
        initialize();
    }
    
    private void initialize() {
        //iniciar componentes aqui
        initializeActions();
    }
    
    private void initializeActions(){
        //iniciar metodos dos componentes aqui
    }
}
