package view.screen;

import controller.controller.MarketScreenController;
import controller.obsever.GameFrameObserver;
import controller.obsever.MarketScreenObserver;
import global.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MarketScreen extends JPanel implements MarketScreenObserver {
    
    private MarketScreenController controller;
    private JLabel lblPlayer;
    private JLabel lblTeleport;
    private String methodNameTeleport;
    private Thread threadWalk;
    
    public MarketScreen() {
        controller = new MarketScreenController(this);
        threadWalk = new Thread();
        initialize();
    }
    
    private void initialize() {
        //JLABEL lblPlayer
        lblPlayer = new JLabel(GlobalVariables.IMAGES.get(controller.getPlayerImageIdleName()));
        lblPlayer.setBounds(controller.getPlayerColumn()*GlobalVariables.TILE_SIZE, (controller.getPlayerRow()-1)*GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblTeleport
        lblTeleport = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.TELEPORT_ICON_KEY));
        lblTeleport.setBounds(0, 0, 0, 0);
        //THIS
        setLayout(null);
        add(lblPlayer);
        add(lblTeleport);
        initializeActions();
    }
    
    private void initializeActions() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!threadWalk.isAlive()) {
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                        controller.walkUp();
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                        controller.walkDown();
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                        controller.walkLeft();
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                        controller.walkRight();
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (methodNameTeleport != null && !methodNameTeleport.isEmpty()) {
                            try {
                                Method method = MarketScreenController.class.getDeclaredMethod(methodNameTeleport, GameFrameObserver.class);
                                method.invoke(controller, (GameFrameObserver) getParent().getParent().getParent());
                            } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                                return;
                            }
                        }
                    }
                }
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                new Thread(() -> {
                    try {
                        threadWalk.join();
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    } finally {
                        updatePlayerImage(controller.getPlayerImageIdleName());
                    }
                }).start();
            }
        });
    }

    @Override
    public void updatePlayerImage(String imageKey) {
        lblPlayer.setIcon(GlobalVariables.IMAGES.get(imageKey));
    }

    @Override
    public void updatePlayerRowUp(int row) {
        int rowLabel = lblPlayer.getY();
        int newRowLabel = (row-1) * GlobalVariables.TILE_SIZE;
        int rowsDifference = rowLabel - newRowLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < rowsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()-GlobalVariables.ONE_PIXEL_SIZE);
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(lblPlayer.getX(), newRowLabel);
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerRowDown(int row) {
        int rowLabel = lblPlayer.getY();
        int newRowLabel = (row-1) * GlobalVariables.TILE_SIZE;
        int rowsDifference = newRowLabel - rowLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < rowsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()+GlobalVariables.ONE_PIXEL_SIZE);
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(lblPlayer.getX(), newRowLabel);
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerColumnLeft(int column) {
        int columnLabel = lblPlayer.getX();
        int newColumnLabel = column * GlobalVariables.TILE_SIZE;
        int columnsDifference = columnLabel - newColumnLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < columnsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX()-GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(newColumnLabel, lblPlayer.getY());
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerColumnRight(int column) {
        int columnLabel = lblPlayer.getX();
        int newColumnLabel = column * GlobalVariables.TILE_SIZE;
        int columnsDifference = newColumnLabel - columnLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < columnsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX()+GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(newColumnLabel, lblPlayer.getY());
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void enableTeleport(String methodName, int row, int column) {
        lblTeleport.setBounds(GlobalVariables.TILE_SIZE*column, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE);
        methodNameTeleport = methodName;
    }
    
    @Override
    public void disableTeleport() {
        lblTeleport.setBounds(0, 0, 0, 0);
        methodNameTeleport = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GlobalVariables.IMAGES.get(GlobalVariables.MARKET_SCREEN_MAP_KEY).getImage(), 0, 0, null);
    }
}
