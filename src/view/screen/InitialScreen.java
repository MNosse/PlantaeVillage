package view.screen;

import controller.controller.InitialScreenController;
import controller.obsever.GameFrameObserver;
import global.GlobalVariables;
import view.components.Button;

import javax.swing.JPanel;
import java.awt.*;


public class InitialScreen extends JPanel {
    
    InitialScreenController controller;
    private Button btnPlay;
    
    public InitialScreen() {
        controller = new InitialScreenController();
        initialize();
    }
    
    private void initialize() {
        //LAYOUT
        GridBagLayout layout = new GridBagLayout();
        //CONSTRAINTS
        GridBagConstraints constraints = new GridBagConstraints();
        //JBUTTON PLAY
        btnPlay = new Button((GlobalVariables.TILE_SIZE * 5), (GlobalVariables.TILE_SIZE * 2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2));
        //JLABEL BACKGROUND
        setLayout(layout);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets((GlobalVariables.TILE_SIZE * 11), 0, 0, 0);
        add(btnPlay, constraints);
        setVisible(true);
        initializeActions();
    }
    
    private void initializeActions() {
        btnPlay.addActionListener(event -> navigateToVillageScreen());
    }
    
    private void navigateToVillageScreen() {
        controller.navigateToVillageScreen((GameFrameObserver) getParent().getParent().getParent());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GlobalVariables.IMAGES.get(GlobalVariables.INITIAL_SCREEN_MAP_KEY).getImage(), 0, 0, null);
    }
}
