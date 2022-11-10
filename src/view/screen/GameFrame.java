package view.screen;

import controller.obsever.GameFrameObserver;
import global.GlobalVariables;

import javax.swing.*;

public class GameFrame extends JFrame implements GameFrameObserver{
    
    public GameFrame() {
        initialize();
    }
    
    private void initialize() {
        //THIS
        setTitle("Plantae Village");
        int width = GlobalVariables.SCREEN_WIDTH;
        int height = GlobalVariables.SCREEN_HEIGHT;
        setVisible(true);
        setSize(width, (int) (getSize().getHeight() + height));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new InitialScreen());
        revalidate();
        repaint();
    }
    
    @Override
    public void navigateToHouseScreen() {
        HouseScreen houseScreen = new HouseScreen();
        setContentPane(houseScreen);
        revalidate();
        repaint();
        houseScreen.setFocusable(true);
        houseScreen.requestFocusInWindow();
    }
    
    @Override
    public void navigateToInitialScreen() {
        InitialScreen initialScreen = new InitialScreen();
        setContentPane(initialScreen);
        revalidate();
        repaint();
        initialScreen.setFocusable(true);
        initialScreen.requestFocusInWindow();
    }
    
    @Override
    public void navigateToMarketScreen() {
        MarketScreen marketScreen = new MarketScreen();
        setContentPane(marketScreen);
        revalidate();
        repaint();
        marketScreen.setFocusable(true);
        marketScreen.requestFocusInWindow();
    }
    
    @Override
    public void navigateToTrailerScreen() {
        TrailerScreen trailerScreen = new TrailerScreen();
        setContentPane(trailerScreen);
        revalidate();
        repaint();
        trailerScreen.setFocusable(true);
        trailerScreen.requestFocusInWindow();
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
    
    @Override
    public void navigateToVillageScreen(int plawerRow, int playerColumn) {
        VillageScreen villageScreen = new VillageScreen(plawerRow, playerColumn);
        setContentPane(villageScreen);
        revalidate();
        repaint();
        villageScreen.setFocusable(true);
        villageScreen.requestFocusInWindow();
    }
}
