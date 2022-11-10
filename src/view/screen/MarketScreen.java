package view.screen;

import controller.controller.MarketScreenController;
import controller.obsever.GameFrameObserver;
import controller.obsever.MarketScreenObserver;
import global.GlobalVariables;
import model.Repository;
import view.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MarketScreen extends JPanel implements MarketScreenObserver {
    
    private MarketScreenController controller;
    private JButton btnQuit, btnContinue;
    private JLabel lblPlayer, lblTeleport, lblInteractive, lblDialog, lblPause;
    private JTextArea txaDialogText;
    private boolean interactiveEnabled, teleportEnabled;
    private Thread threadWalk;
    
    public MarketScreen() {
        controller = new MarketScreenController(this);
        threadWalk = new Thread();
        interactiveEnabled = false;
        teleportEnabled = false;
        initialize();
    }
    
    private void initialize() {
        //JLABEL lblPlayer
        lblPlayer = new JLabel(GlobalVariables.IMAGES.get(controller.getPlayerImageIdleName()));
        lblPlayer.setBounds(controller.getPlayerColumn()*GlobalVariables.TILE_SIZE, (controller.getPlayerRow()-1)*GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc1
        JLabel lblNpc1 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC10_KEY));
        lblNpc1.setBounds(GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*9, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc2
        JLabel lblNpc2 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC11_KEY));
        lblNpc2.setBounds(GlobalVariables.TILE_SIZE*14, GlobalVariables.TILE_SIZE*9, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc3
        JLabel lblNpc3 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC12_KEY));
        lblNpc3.setBounds(GlobalVariables.TILE_SIZE*27, GlobalVariables.TILE_SIZE*12, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc4
        JLabel lblNpc4 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC13_KEY));
        lblNpc4.setBounds(GlobalVariables.TILE_SIZE*24, GlobalVariables.TILE_SIZE*15, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblTeleport
        lblTeleport = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.TELEPORT_ICON_KEY));
        lblTeleport.setBounds(0, 0, 0, 0);
        //JLABEL lblInteractive
        lblInteractive = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.INTERACTIVE_ICON_KEY));
        lblTeleport.setBounds(0, 0, 0, 0);
        //JTEXTAREA txaDialogText
        txaDialogText = new JTextArea();
        txaDialogText.setFont(GlobalVariables.FONT_3.deriveFont(GlobalVariables.ONE_PIXEL_SIZE*17f));
        txaDialogText.setLineWrap(true);
        txaDialogText.setWrapStyleWord(true);
        txaDialogText.setVisible(true);
        txaDialogText.setEditable(false);
        txaDialogText.setMinimumSize(new Dimension((GlobalVariables.TILE_SIZE*12), (GlobalVariables.TILE_SIZE*2)));
        txaDialogText.setPreferredSize(new Dimension((GlobalVariables.TILE_SIZE*12), (GlobalVariables.TILE_SIZE*2)));
        //JLABEL lblDialog
        lblDialog = new JLabel();
        lblDialog.setVisible(true);
        lblDialog.setBounds(GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*3);
        lblDialog.setIcon(GlobalVariables.IMAGES.get(GlobalVariables.DIALOG_BOX_KEY));
        lblDialog.setLayout(new GridBagLayout());
        lblDialog.setVisible(false);
        lblDialog.add(txaDialogText, new GridBagConstraints());
        //JLabel lblPlant
        JLabel lblPlant = new JLabel(GlobalVariables.IMAGES.get(Repository.getInstance().getPlant().getIMAGE_NAME()));
        //JBUTTON btnContinue
        btnContinue = new view.components.Button((GlobalVariables.TILE_SIZE * 7), (GlobalVariables.TILE_SIZE * 2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Continuar", 30f);
        //JBUTTON btnQuit
        btnQuit = new Button((GlobalVariables.TILE_SIZE * 7), (GlobalVariables.TILE_SIZE * 2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Sair", 30f);
        //JLABEL lblPause;
        GridBagConstraints constraints = new GridBagConstraints();
        lblPause = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.PAUSE_SCREEN_MAP_KEY));
        lblPause.setBounds(0, 0, GlobalVariables.SCREEN_WIDTH, GlobalVariables.SCREEN_HEIGHT);
        lblPause.setLayout(new GridBagLayout());
        lblPause.add(lblPlant);
        constraints.insets = new Insets(GlobalVariables.TILE_SIZE,  0, 0, 0);
        constraints.gridy = 1;
        lblPause.add(btnContinue, constraints);
        constraints.gridy = 2;
        lblPause.add(btnQuit, constraints);
        lblPause.setVisible(false);
        //THIS
        setLayout(null);
        add(lblPause);
        add(lblDialog);
        add(lblTeleport);
        add(lblInteractive);
        add(lblPlayer);
        add(lblNpc1);
        add(lblNpc2);
        add(lblNpc3);
        add(lblNpc4);
        initializeActions();
    }
    
    private void initializeActions() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GameFrameObserver gameFrameObserver = (GameFrameObserver) getParent().getParent().getParent();
                if (!lblPause.isVisible()) {
                    if(!threadWalk.isAlive()) {
                        if(!lblDialog.isVisible()) {
                            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                                controller.walkUp();
                            } else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                                controller.walkDown();
                            } else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                                controller.walkLeft();
                            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                                controller.walkRight();
                            }
                        }
                        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                            if(teleportEnabled) {
                                controller.teleport(gameFrameObserver);
                            } else if(interactiveEnabled) {
                                controller.interact();
                            }
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    lblPause.setVisible(true);
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
    
        btnContinue.addActionListener(click -> {
            lblPause.setVisible(false);
        });
    
        btnQuit.addActionListener(click -> {
            GameFrameObserver gameFrameObserver = (GameFrameObserver) getParent().getParent().getParent();
            gameFrameObserver.navigateToInitialScreen();
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
    public void enableTeleport(int row, int column) {
        lblTeleport.setBounds(GlobalVariables.TILE_SIZE*column, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE);
        teleportEnabled = true;
    }
    
    @Override
    public void disableTeleport() {
        lblTeleport.setBounds(0, 0, 0, 0);
        teleportEnabled = false;
    }
    
    @Override
    public void enableInteractive(int row, int column) {
        lblInteractive.setBounds(GlobalVariables.TILE_SIZE*column, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE);
        interactiveEnabled = true;
    }
    
    @Override
    public void disableInteractive() {
        lblInteractive.setBounds(0, 0, 0, 0);
        interactiveEnabled = false;
    }
    
    @Override
    public void openDialog() {
        int row = 1;
        if (lblPlayer.getY()/GlobalVariables.TILE_SIZE < 10) {
            row = 17;
        }
        lblDialog.setBounds(GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*3);
        lblDialog.setVisible(true);
    }
    
    @Override
    public void updateDialogMessage(String message) {
        txaDialogText.setText(message);
        if (!message.isEmpty()) {
            lblDialog.setText(message);
        } else {
            lblDialog.setVisible(false);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GlobalVariables.IMAGES.get(GlobalVariables.MARKET_SCREEN_MAP_KEY).getImage(), 0, 0, null);
    }
}
