package view.screen;

import controller.controller.VillageScreenController;
import controller.obsever.VillageScreenObserver;
import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class VillageScreen extends JPanel implements VillageScreenObserver{
    
    private VillageScreenController controller;
    private JLabel lblPlayer;
    private JLabel lblFountain;
    private Thread threadWalk;
    private boolean isThreadWalkFree;
    
    public VillageScreen() {
        controller = new VillageScreenController(this);
        threadWalk = new Thread();
        isThreadWalkFree = true;
        initialize();
    }
    
    private void initialize() {
        //JLABEL lblPlayer
        lblPlayer = new JLabel(GlobalVariables.IMAGES.get(controller.getPlayerImageIdleName()));
        lblPlayer.setBounds(controller.getPlayerColumn()*GlobalVariables.TILE_SIZE, controller.getPlayerRow()*GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblFountain
        lblFountain = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.FOUNTAIN_KEY));
        lblFountain.setBounds(GlobalVariables.TILE_SIZE*18, GlobalVariables.TILE_SIZE*4, GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*3);
        //THIS
        setLayout(null);
        add(lblPlayer);
        add(lblFountain);
        initializeActions();
    }
    
    private void initializeActions() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isThreadWalkFree) {
                    isThreadWalkFree = false;
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
                    } else {
                        isThreadWalkFree = true;
                    }
                }
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            threadWalk.join();
                        } catch(InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        updatePlayerImage(controller.getPlayerImageIdleName());
                    }
                });
                thread.start();
            }
        });
    }

    @Override
    public void updatePlayerImage(String imageKey) {
        lblPlayer.setIcon(GlobalVariables.IMAGES.get(imageKey));
    }

    @Override
    public void updatePlayerRow(int row) {
        int rowView = row * GlobalVariables.TILE_SIZE;
        if (rowView < lblPlayer.getY()) {
            int rowAux = (lblPlayer.getY()-rowView);
            threadWalk = new Thread(new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    try {
                        while (count < rowAux) {
                            lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()-GlobalVariables.ONE_PIXEL_SIZE);
                            Thread.sleep(8);
                            count++;
                        }
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lblPlayer.setLocation(lblPlayer.getX(), rowView);
                        isThreadWalkFree = true;
                    }
                }
            });
            threadWalk.start();
        } else if (rowView > lblPlayer.getY()) {
            int rowAux = (rowView-lblPlayer.getY());
            threadWalk = new Thread(new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    try {
                        while (count < rowAux) {
                            lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()+GlobalVariables.ONE_PIXEL_SIZE);
                            Thread.sleep(8);
                            count++;
                        }
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lblPlayer.setLocation(lblPlayer.getX(), rowView);
                        isThreadWalkFree = true;
                    }
                }
            });
            threadWalk.start();
        } else {
            isThreadWalkFree = true;
        }
    }
    
    @Override
    public void updatePlayerColumn(int column) {
        int columnView = column * GlobalVariables.TILE_SIZE;
        if (columnView < lblPlayer.getX()) {
            int columnAux = (lblPlayer.getX()-columnView);
            threadWalk = new Thread(new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    try {
                        while (count < columnAux) {
                            lblPlayer.setLocation(lblPlayer.getX()-GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                            Thread.sleep(8);
                            count++;
                        }
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lblPlayer.setLocation(columnView, lblPlayer.getY());
                        isThreadWalkFree = true;
                    }
                }
            });
            threadWalk.start();
        } else if (columnView > lblPlayer.getX()) {
            int columnAux = (columnView-lblPlayer.getX());
            threadWalk = new Thread(new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    try {
                        while (count < columnAux) {
                            lblPlayer.setLocation(lblPlayer.getX()+GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                            Thread.sleep(8);
                            count++;
                        }
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lblPlayer.setLocation(columnView, lblPlayer.getY());
                        isThreadWalkFree = true;
                    }
                }
            });
            threadWalk.start();
        } else {
            isThreadWalkFree = true;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GlobalVariables.IMAGES.get(GlobalVariables.VILLAGE_SCREEN_MAP_KEY).getImage(), 0, 0, null);
    }
}
