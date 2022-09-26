package view.screen;

import controller.controller.VillageScreenController;
import controller.obsever.VillageScreenObserver;
import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class VillageScreen extends JFrame implements VillageScreenObserver{
    
    private VillageScreenController controller;
    private JLabel labelPlayer;
    
    public VillageScreen() {
        super();
        controller = new VillageScreenController(this);
        initialize();
    }
    
    private void initialize() {
        //iniciar componentes aqui
        setSize(GlobalVariables.SCREEN_WIDTH, GlobalVariables.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon(GlobalVariables.IMAGES.get(GlobalVariables.VILLAGE_SCREEN_MAP_KEY))));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        initializeActions();
    }
    
    private void initializeActions(){
        //iniciar metodos dos componentes aqui
        addPlayer();
        onMove();
    }

    private void onMove(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                controller.actionMove(e);
                updatePositionPlayer();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void addPlayer(){
        labelPlayer = new JLabel(new ImageIcon(GlobalVariables.IMAGES.get(controller.getPlayer().getImageIdleName())));
        updatePositionPlayer();
        add(labelPlayer);
    }

    @Override
    public void updateLabelPlayer(String image) {
        labelPlayer.setIcon(new ImageIcon(GlobalVariables.IMAGES.get(image)));
    }

    @Override
    public void updatePositionPlayer() {
        labelPlayer.setBounds(controller.getPlayer().getRow(), controller.getPlayer().getColumn(), GlobalVariables.TILE_SIZE,  GlobalVariables.TILE_SIZE * 2);
    }
}
