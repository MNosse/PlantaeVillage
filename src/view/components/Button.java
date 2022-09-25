package view.components;

import view.global.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    
    private int width;
    private int height;
    private int borderRadius;
    private boolean over;
    private Color backgroundColor;
    private Color backgroundColorOver;
    private Color borderColor1;
    private Color borderColor2;
    
    public Button(int width, int height, int borderRadius, Color backgroundColor, Color backgroundColorOver, Color borderColor1, Color borderColor2) {
        setContentAreaFilled(false);
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(GlobalVariables.FONT_1.deriveFont(GlobalVariables.ONE_PIXEL_SIZE*25f));
        setForeground(new Color(220, 220, 220));
        setText("Jogar");
        
        this.width = width;
        this.height = height;
        this.borderRadius = borderRadius;
        over = false;
        this.backgroundColor = backgroundColor;
        setBackground(backgroundColor);
        this.backgroundColorOver = backgroundColorOver;
        this.borderColor1 = borderColor1;
        this.borderColor2 = borderColor2;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(borderColor1);
                repaint();
                if (over)
                    setBackground(backgroundColorOver);
                else
                    setBackground(backgroundColor);
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(borderColor1);
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                if (over)
                    setBackground(backgroundColorOver);
                else
                    setBackground(backgroundColor);
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(backgroundColorOver);
                over = true;
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                over = false;
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(borderColor2);
        g2D.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        g2D.setColor(borderColor1);
        g2D.fillRoundRect(GlobalVariables.ONE_PIXEL_SIZE*3, GlobalVariables.ONE_PIXEL_SIZE*3, (width-GlobalVariables.ONE_PIXEL_SIZE*6), (height-GlobalVariables.ONE_PIXEL_SIZE*6), borderRadius, borderRadius);
        g2D.setColor(getBackground());
        g2D.fillRoundRect(GlobalVariables.ONE_PIXEL_SIZE*6, GlobalVariables.ONE_PIXEL_SIZE*6, (width-GlobalVariables.ONE_PIXEL_SIZE*12), (height-GlobalVariables.ONE_PIXEL_SIZE*12), borderRadius, borderRadius);
        super.paintComponent(g);
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getBorderRadius() {
        return borderRadius;
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public Color getBackgroundColorOver() {
        return backgroundColorOver;
    }
    
    public void setBackgroundColorOver(Color backgroundColorOver) {
        this.backgroundColorOver = backgroundColorOver;
    }
    
    public Color getBorderColor1() {
        return borderColor1;
    }
    
    public void setBorderColor1(Color borderColor1) {
        this.borderColor1 = borderColor1;
    }
    
    public Color getBorderColor2() {
        return borderColor2;
    }
    
    public void setBorderColor2(Color borderColor2) {
        this.borderColor2 = borderColor2;
    }
}
