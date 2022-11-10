package controller.controller;

import controller.obsever.GameFrameObserver;
import controller.obsever.VillageScreenObserver;
import model.*;
import global.GlobalVariables;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

public class VillageScreenController {
    
    private VillageScreenObserver observer;
    private Map map;
    private Teleport currentTeleport;
    private Interactive currentInteractive;
    private boolean firstInteraction;
    private final static TileContent[][] VILLAGE_TILES_CONTENT = new TileContent[21][39];
    private final static java.util.Map<String, Interactive> INTERACTIVES = new HashMap<>();
    private final static java.util.Map<String, Teleport> TELEPORTS = new HashMap<>();
    
    //INITIALIZE STATIC ITENS
    static {
        try {
            //TILE CONTENT
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/global/village.properties"));
            for(int row = 0; row < 21; row++) {
                for(int column = 0; column < 39; column++) {
                    try {
                        VILLAGE_TILES_CONTENT[row][column] = TileContent.valueOf(properties.getProperty(row + "x" + column));
                    } catch(IllegalArgumentException e) {
                        VILLAGE_TILES_CONTENT[row][column] = TileContent.EMPTY;
                    }
                }
            }
        } catch(IOException e) {
            System.exit(0);
        }
    }
    
    public VillageScreenController(VillageScreenObserver observer) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, VILLAGE_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(17, 33));
        currentTeleport = null;
        currentInteractive = null;
        firstInteraction = true;
    
        //INTERACTIVES
        //Initial lines
        INTERACTIVES.put("17x33", new Interactive(17, 33, Repository.getInstance().getIntroductionLines()));
        
        INTERACTIVES.put("4x15", new Interactive(4, 15, Repository.getInstance().getNpcLines(0)));
        INTERACTIVES.put("4x21", new Interactive(4, 21, Repository.getInstance().getNpcLines(1)));
        INTERACTIVES.put("5x25", new Interactive(5, 25, Repository.getInstance().getNpcLines(2)));
        INTERACTIVES.put("15x8", new Interactive(15, 8, Repository.getInstance().getNpcLines(3)));
        INTERACTIVES.put("14x16", new Interactive(14, 16, Repository.getInstance().getNpcLines(4)));
        //TELEPORTS
        TELEPORTS.put("9x5", new Teleport(9, 5, "navigateToHouseScreen"));
        TELEPORTS.put("9x33", new Teleport(9, 33, "navigateToMarketScreen"));
        TELEPORTS.put("16x24", new Teleport(16, 24, "navigateToTrailerScreen"));
    }
    
    public VillageScreenController(VillageScreenObserver observer, int plawerRow, int playerColumn) {
        this.observer = observer;
        map = new Map(GlobalVariables.SCREEN_HEIGHT, GlobalVariables.SCREEN_WIDTH, VILLAGE_TILES_CONTENT, INTERACTIVES, TELEPORTS);
        map.setPlayer(new Player(plawerRow, playerColumn));
        map.getPlayer().setWalkState(new WalkStateDown(map.getPlayer()));
        currentTeleport = null;
        currentInteractive = null;
        firstInteraction = true;
        //INTERACTIVES
        //Initial lines
        INTERACTIVES.put("17x33", new Interactive(17, 33, Repository.getInstance().getIntroductionLines()));
        
        INTERACTIVES.put("4x15", new Interactive(4, 15, Repository.getInstance().getNpcLines(0)));
        INTERACTIVES.put("4x21", new Interactive(4, 21, Repository.getInstance().getNpcLines(1)));
        INTERACTIVES.put("5x25", new Interactive(5, 25, Repository.getInstance().getNpcLines(2)));
        INTERACTIVES.put("15x8", new Interactive(15, 8, Repository.getInstance().getNpcLines(3)));
        INTERACTIVES.put("14x16", new Interactive(14, 16, Repository.getInstance().getNpcLines(4)));
        INTERACTIVES.put("17x31", new Interactive(17, 31, Repository.getInstance().getNpcLines(5)));
        //TELEPORTS
        TELEPORTS.put("9x5", new Teleport(9, 5, "navigateToHouseScreen"));
        TELEPORTS.put("9x33", new Teleport(9, 33, "navigateToMarketScreen"));
        TELEPORTS.put("16x24", new Teleport(16, 24, "navigateToTrailerScreen"));
    }
    
    public void isFirstEnter() {
        if (Repository.getInstance().isFirstEnterInVillage()) {
            hasObject(getPlayerRow(), getPlayerColumn());
            currentInteractive = INTERACTIVES.get(getPlayerRow()+"x"+getPlayerColumn());
            interact();
        }
    }
    
    public void teleport(GameFrameObserver gameFrameObserver) {
        try {
            Method method = VillageScreenController.class.getDeclaredMethod(currentTeleport.getTELEPORT_METHOD_NAME(), GameFrameObserver.class);
            method.invoke(this, gameFrameObserver);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            return;
        }
    }
    
    public void interact() {
        if (currentInteractive != null) {
            if (!Repository.getInstance().isFirstEnterInVillage() && currentInteractive.getROW() == 17 && currentInteractive.getCOLUMN() == 33) {
                observer.openTest();
            } else {
                if(firstInteraction) {
                    observer.openDialog();
                    firstInteraction = false;
                }
                String message = currentInteractive.readNextLine();
                observer.updateDialogMessage(message);
                if(message.isEmpty()) {
                    firstInteraction = true;
                }
            }
        }
    }
    
    public boolean[] validateTest(Object[] answers) {
        boolean[] feedback = new boolean[8];
        for (int i = 0; i < feedback.length; i++) {
            feedback[i] = false;
        }
        Plant plant = Repository.getInstance().getPlant();
        if (answers[0] != null && plant.getPLANT_GROUP().equals((String) answers[0])) {
            feedback[0] = true;
        }
        if (answers[1] != null && plant.getIS_VASCULAR() == (boolean) answers[1]) {
            feedback[1] = true;
        }
        if (answers[2] != null && plant.getDEPENDENT_TO_REPRODUCTION().equals((String) answers[2])) {
            feedback[2] = true;
        }
        if (answers[3] != null && plant.getHAS_ROOTS() == (boolean) answers[3]) {
            feedback[3] = true;
        }
        if (answers[4] != null && plant.getHAS_STEM() == (boolean) answers[4]) {
            feedback[4] = true;
        }
        if (answers[5] != null && plant.getHAS_LEAVES() == (boolean) answers[5]) {
            feedback[5] = true;
        }
        if (answers[6] != null) {
            String[] question7String = ((String) answers[6]).split(" - ");
            boolean[] quention7Boolean = new boolean[question7String.length];
            for(int i = 0; i < question7String.length; i++) {
                if(question7String[i].equals("V")) {
                    quention7Boolean[i] = true;
                } else {
                    quention7Boolean[i] = false;
                }
            }
            if(plant.getHAS_SEED() == quention7Boolean[0]) {
                if(plant.getHAS_FRUITS() == quention7Boolean[1]) {
                    if(plant.getHAS_FLOWER() == quention7Boolean[2]) {
                        if(plant.getHAS_SPORES() == quention7Boolean[3]) {
                            feedback[6] = true;
                        }
                    }
                }
            }
        }
        if (answers[7] != null && plant.getWHERE_FIND_IT().equals((String) answers[7])) {
            feedback[7] = true;
        }
        return feedback;
    }
    
    //Chamada reflexiva
    public void navigateToHouseScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToHouseScreen();
    }
    
    //Chamada reflexiva
    public void navigateToMarketScreen(GameFrameObserver gameFrame) {
                gameFrame.navigateToMarketScreen();
    }
    
    //Chamada reflexiva
    public void navigateToTrailerScreen(GameFrameObserver gameFrame) {
        gameFrame.navigateToTrailerScreen();
    }
    
    public int getPlayerRow() {
        return map.getPlayer().getRow();
    }
    
    public int getPlayerColumn() {
        return map.getPlayer().getColumn();
    }
    
    public String getPlayerImageIdleName() {
        return map.getPlayer().getImageIdleName();
    }
    
    public String getPlayerImageAnimationName() {
        return map.getPlayer().getImageAnimationName();
    }
    
    public void walkUp() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateUp();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (row > 0) {
            if(!hasObject(row-1, column)) {
                player.walkUp();
                observer.updatePlayerRowUp(player.getRow());
            }
        }
    }
    
    public void walkDown() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateDown();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (row < map.getTileContents().length-1) {
            if(!hasObject(row+1, column)) {
                player.walkDown();
                observer.updatePlayerRowDown(player.getRow());
            }
        }
    }
    
    public void walkLeft() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateLeft();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (column > 0) {
            if(!hasObject(row, column-1)) {
                player.walkLeft();
                observer.updatePlayerColumnLeft(player.getColumn());
            }
        }
    }
    
    public void walkRight() {
        Player player = map.getPlayer();
        int row = player.getRow();
        int column = player.getColumn();
        player.changeToWalkStateRight();
        observer.updatePlayerImage(getPlayerImageIdleName());
        if (column < map.getTileContents()[0].length-1) {
            if(!hasObject(row, column+1)) {
                player.walkRight();
                observer.updatePlayerColumnRight(player.getColumn());
            }
        }
    }
    
    private boolean hasObject(int row, int column) {
        if(map.getTileContents()[row][column].equals(TileContent.EMPTY)) {
            if (currentTeleport != null) {
                observer.disableTeleport();
                currentTeleport = null;
            }
            if (currentInteractive != null) {
                observer.disableInteractive();
                currentInteractive = null;
            }
            return false;
        }
        if (map.getTileContents()[row][column].equals(TileContent.TELEPORT)) {
            currentTeleport = TELEPORTS.get(row+"x"+column);
            observer.enableTeleport(row-2, column);
            return false;
        }
        else if (map.getTileContents()[row][column].equals(TileContent.INTERACTIVE)) {
            currentInteractive = INTERACTIVES.get(row+"x"+column);
            observer.enableInteractive(row-2, column);
            return false;
        }
        return true;
    }
}
