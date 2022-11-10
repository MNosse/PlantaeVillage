package global;

import model.TileContent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class GlobalVariables {
    public static final int ONE_PIXEL_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.000732064421669107);
    public static final int TILE_SIZE = ONE_PIXEL_SIZE * 32;
    public static final int SCREEN_WIDTH = TILE_SIZE * 39;
    public static final int SCREEN_HEIGHT = (TILE_SIZE * 21);
    public static final Font FONT_1;
    public static final Font FONT_2;
    public static final Font FONT_3;
    public static final HashMap<String, ImageIcon> IMAGES = new HashMap<>();
    public static final String HOUSE_SCREEN_MAP_KEY = "HOUSE_SCREEN";
    public static final String INITIAL_SCREEN_MAP_KEY = "INITIAL_SCREEN";
    public static final String MARKET_SCREEN_MAP_KEY = "MARKET_SCREEN";
    public static final String VILLAGE_SCREEN_MAP_KEY = "VILLAGE_SCREEN";
    public static final String TRAILER_SCREEN_MAP_KEY = "TRAILER_SCREEN";
    public static final String PAUSE_SCREEN_MAP_KEY = "PAUSE_SCREEN";
    public static final String PLAYER_IDLE_UP_KEY = "PLAYER_IDLE_UP";
    public static final String PLAYER_IDLE_DOWN_KEY = "PLAYER_IDLE_DOWN";
    public static final String PLAYER_IDLE_LEFT_KEY = "PLAYER_IDLE_LEFT";
    public static final String PLAYER_IDLE_RIGHT_KEY = "PLAYER_IDLE_RIGHT";
    public static final String PLAYER_WALKING_UP_KEY = "PLAYER_WALKING_UP";
    public static final String PLAYER_WALKING_DOWN_KEY = "PLAYER_WALKING_DOWN";
    public static final String PLAYER_WALKING_LEFT_KEY = "PLAYER_WALKING_LEFT";
    public static final String PLAYER_WALKING_RIGHT_KEY = "PLAYER_WALKING_RIGHT";
    public static final String NPC1_KEY = "NPC1_KEY";
    public static final String NPC2_KEY = "NPC2_KEY";
    public static final String NPC3_KEY = "NPC3_KEY";
    public static final String NPC4_KEY = "NPC4_KEY";
    public static final String NPC5_KEY = "NPC5_KEY";
    public static final String NPC6_KEY = "NPC6_KEY";
    public static final String NPC7_KEY = "NPC7_KEY";
    public static final String NPC8_KEY = "NPC8_KEY";
    public static final String NPC9_KEY = "NPC9_KEY";
    public static final String NPC10_KEY = "NPC10_KEY";
    public static final String NPC11_KEY = "NPC11_KEY";
    public static final String NPC12_KEY = "NPC12_KEY";
    public static final String NPC13_KEY = "NPC13_KEY";
    public static final String FOUNTAIN_KEY = "FOUNTAIN_KEY";
    public static final String LEFT_LAMP_KEY = "LEFT_LAMP_KEY";
    public static final String RIGHT_LAMP_KEY = "RIGHT_LAMP_KEY";
    public static final String FLOWERS_KEY = "FLOWERS_KEY";
    public static final String TELEPORT_ICON_KEY = "TELEPORT_ICON_KEY";
    public static final String INTERACTIVE_ICON_KEY = "INTERACTIVE_ICON_KEY";
    public static final String DIALOG_BOX_KEY = "DIALOG_BOX_KEY";
    public static final String TEST_BASE_KEY = "TEST_BASE_KEY";
    public static final String BRYOPHYTA_KEY = "BRYOPHYTA_KEY";
    public static final String PTERIDOPHYTA_KEY = "PTERIDOPHYTA_KEY";
    public static final String GYMNOSPERMAE_KEY = "GYMNOSPERMAE_KEY";
    public static final String ANGIOSPERMAE_KEY = "ANGIOSPERMAE_KEY";

    static {
        try {
            FONT_1 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/assets/fonts/font1.TTF"));
            FONT_2 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/assets/fonts/font2.TTF")).deriveFont(GlobalVariables.ONE_PIXEL_SIZE*14f);
            FONT_3 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/assets/fonts/FredokaOne-Regular.ttf"));
            IMAGES.put(HOUSE_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/house.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(INITIAL_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/initial_screen.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(MARKET_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/market.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(VILLAGE_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/village.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(TRAILER_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/trailer.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(PAUSE_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/pause.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_IDLE_UP_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_idle_up.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_IDLE_DOWN_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_idle_down.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_IDLE_LEFT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_idle_left.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_IDLE_RIGHT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_idle_right.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_WALKING_UP_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_walking_up.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_WALKING_DOWN_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_walking_down.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_WALKING_LEFT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_walking_left.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_WALKING_RIGHT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_walking_right.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC1_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc1.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC2_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc2.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC3_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc3.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC4_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc4.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC5_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc5.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC6_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc6.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC7_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc7.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC8_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc8.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC9_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc9.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC10_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc10.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC11_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc11.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC12_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc12.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(NPC13_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/npc13.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(FOUNTAIN_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/fountain.gif").getImage().getScaledInstance(TILE_SIZE*3, TILE_SIZE*3, Image.SCALE_DEFAULT)));
            IMAGES.put(LEFT_LAMP_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/left_lamp.gif").getImage().getScaledInstance(TILE_SIZE*5, TILE_SIZE*5, Image.SCALE_DEFAULT)));
            IMAGES.put(RIGHT_LAMP_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/right_lamp.gif").getImage().getScaledInstance(TILE_SIZE*5, TILE_SIZE*5, Image.SCALE_DEFAULT)));
            IMAGES.put(FLOWERS_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/flowers.gif").getImage().getScaledInstance(TILE_SIZE*7, TILE_SIZE*4, Image.SCALE_DEFAULT)));
            IMAGES.put(TELEPORT_ICON_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/teleport_icon.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
            IMAGES.put(INTERACTIVE_ICON_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/interactive_icon.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
            IMAGES.put(DIALOG_BOX_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/dialog_box.png").getImage().getScaledInstance(TILE_SIZE*13, TILE_SIZE*3, Image.SCALE_DEFAULT)));
            IMAGES.put(TEST_BASE_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/test_base.png")).getScaledInstance(ONE_PIXEL_SIZE*500, ONE_PIXEL_SIZE*600, Image.SCALE_SMOOTH)));
            IMAGES.put(BRYOPHYTA_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/bryophyta.png")).getScaledInstance(TILE_SIZE*7, TILE_SIZE*7, Image.SCALE_SMOOTH)));
            IMAGES.put(PTERIDOPHYTA_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/pteridophyta.png")).getScaledInstance(TILE_SIZE*7, TILE_SIZE*7, Image.SCALE_SMOOTH)));
            IMAGES.put(GYMNOSPERMAE_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/gymnospermae.png")).getScaledInstance(TILE_SIZE*7, TILE_SIZE*7, Image.SCALE_SMOOTH)));
            IMAGES.put(ANGIOSPERMAE_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/angiospermae.png")).getScaledInstance(TILE_SIZE*7, TILE_SIZE*7, Image.SCALE_SMOOTH)));
        } catch(IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
