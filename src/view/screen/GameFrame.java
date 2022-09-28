package view.screen;

import controller.obsever.GameFrameObserver;
import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements GameFrameObserver{
    
    public GameFrame() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Plantae Village");
        setVisible(true);
        setSize(GlobalVariables.SCREEN_WIDTH, (int) (getSize().getHeight()+GlobalVariables.SCREEN_HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new InitialScreen());
        revalidate();
        repaint();
    }
    
    @Override
    public void navigateToVillageScreen() {
        VillageScreen villageScreen = new VillageScreen();
        setContentPane(villageScreen);
        revalidate();
        repaint();
        villageScreen.setFocusable(true);
        villageScreen.requestFocusInWindow();
    }
}
