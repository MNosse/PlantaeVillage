package view.global;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GlobalVariables {
    public static final int TILE_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.02342606149341142);
    public static final int ONE_PIXEL_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.000732064421669107);
    public static final int SCREEN_WIDTH = TILE_SIZE * 39;
    public static final int SCREEN_HEIGHT = TILE_SIZE * 21;
    public static final Font FONT_1;
    public static final HashMap<String, Image> IMAGES = new HashMap<>();
    public static final String INITIAL_SCREEN_MAP_KEY = "INITIAL_SCREEN";
    public static final String VILLAGE_SCREEN_MAP_KEY = "VILLAGE_SCREEN";
    static {
        try {
            FONT_1 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/assets/fonts/font1.TTF"));
            IMAGES.put(INITIAL_SCREEN_MAP_KEY, ImageIO.read(new File("./src/view/assets/images/initial_screen.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH));
            IMAGES.put(VILLAGE_SCREEN_MAP_KEY, ImageIO.read(new File("./src/view/assets/images/village.png")).getScaledInstance(TILE_SIZE*39, TILE_SIZE*21, Image.SCALE_SMOOTH));
        } catch(IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
