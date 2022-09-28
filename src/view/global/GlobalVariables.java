package view.global;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GlobalVariables {
    public static final int TILE_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.02342606149341142);
    public static final int ONE_PIXEL_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.000732064421669107);
    public static final int SCREEN_WIDTH = TILE_SIZE * 39;
    public static final int SCREEN_HEIGHT = (TILE_SIZE * 21);
    public static final Font FONT_1;
    public static final HashMap<String, ImageIcon> IMAGES = new HashMap<>();
    public static final String INITIAL_SCREEN_MAP_KEY = "INITIAL_SCREEN";
    public static final String VILLAGE_SCREEN_MAP_KEY = "VILLAGE_SCREEN";
    public static final String PLAYER_IDLE_TOP_KEY = "PLAYER_IDLE_TOP";
    public static final String PLAYER_IDLE_DOWN_KEY = "PLAYER_IDLE_DOWN";
    public static final String PLAYER_IDLE_LEFT_KEY = "PLAYER_IDLE_LEFT";
    public static final String PLAYER_IDLE_RIGHT_KEY = "PLAYER_IDLE_RIGHT";
    public static final String PLAYER_ANIMATION_TOP_KEY = "PLAYER_ANIMATION_TOP";
    public static final String PLAYER_ANIMATION_DOWN_KEY = "PLAYER_ANIMATION_DOWN";
    public static final String PLAYER_ANIMATION_LEFT_KEY = "PLAYER_ANIMATION_LEFT";
    public static final String PLAYER_ANIMATION_RIGHT_KEY = "PLAYER_ANIMATION_RIGHT";
    public static final String FOUNTAIN_KEY = "FOUNTAIN_KEY";

    static {
        try {
            FONT_1 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/assets/fonts/font1.TTF"));
            IMAGES.put(INITIAL_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/initial_screen.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(VILLAGE_SCREEN_MAP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/village.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_IDLE_TOP_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/player_fem/amelia_top.png")).getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_IDLE_DOWN_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/player_fem/amelia_down.png")).getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_IDLE_LEFT_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/player_fem/amelia_left.png")).getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_IDLE_RIGHT_KEY, new ImageIcon(ImageIO.read(new File("./src/view/assets/images/player_fem/amelia_right.png")).getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_SMOOTH)));
            IMAGES.put(PLAYER_ANIMATION_TOP_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_fem/gif/amelia_top_run.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_ANIMATION_DOWN_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_fem/gif/amelia_down_run.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_ANIMATION_LEFT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_fem/gif/amelia_left_run.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(PLAYER_ANIMATION_RIGHT_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/player_fem/gif/amelia_right_run.gif").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE*2, Image.SCALE_DEFAULT)));
            IMAGES.put(FOUNTAIN_KEY, new ImageIcon(new ImageIcon("./src/view/assets/images/fountain.gif").getImage().getScaledInstance(TILE_SIZE*3, TILE_SIZE*3, Image.SCALE_DEFAULT)));
        } catch(IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
