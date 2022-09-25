package view.screen;

import controller.controller.InitialScreenController;
import view.components.Button;
import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;


public class InitialScreen extends JLabel {
    
    InitialScreenController controller;
    private Button btnPlay;
    
    public InitialScreen() {
        super(new ImageIcon(GlobalVariables.IMAGES.get(GlobalVariables.INITIAL_SCREEN_MAP_KEY)));
        controller = new InitialScreenController();
        initialize();
    }
    
    private void initialize() {
        //LAYOUT
        GridBagLayout layout = new GridBagLayout();
        //CONSTRAINTS
        GridBagConstraints constraints = new GridBagConstraints();
        //JBUTTON PLAY
        btnPlay = new Button((GlobalVariables.TILE_SIZE*5), (GlobalVariables.TILE_SIZE*2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2));
        //JLABEL BACKGROUND
        setLayout(layout);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets((GlobalVariables.TILE_SIZE*11), 0, 0, 0);
        add(btnPlay, constraints);
        setVisible(true);
        initializeActions();
    }
    
    private void initializeActions(){
        btnPlay.addActionListener(event -> navigateToVillageScreen());
    }
    
    private void navigateToVillageScreen() {
        controller.navigateToVillageScreen(getRootPane().getParent());
    }
}
