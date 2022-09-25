package view.screen;

import controller.obsever.GameFrameObserver;
import view.global.GlobalVariables;

import javax.swing.*;

public class GameFrame extends JFrame implements GameFrameObserver {
    
    public GameFrame() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Plantae Village");
        setSize(GlobalVariables.SCREEN_WIDTH, GlobalVariables.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new InitialScreen());
        setVisible(true);
    }
    
    @Override
    public void navigateToVillageScreen() {
        setContentPane(new VillageScreen());
        revalidate();
        repaint();
    }
}
