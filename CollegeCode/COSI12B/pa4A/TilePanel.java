package pa4A;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TilePanel extends JPanel {
    private TileList tiles;
    private Color color;

    public TilePanel(Color color) {
        setBackground(Color.WHITE);
        tiles = new TileList();
        this.color = color;
        MouseInputListener listener = new TileListener(tiles, this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
        
    public Color getColor() {
        return color;
    }
        
    public void setColor(Color color) {
        this.color = color;
    }
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).draw(g);
        }
    }
}