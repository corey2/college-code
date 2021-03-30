package pa4A;

//This class is the top-level frame for a set of colored tiles.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TileFrame extends JFrame {
 private TilePanel panel;

 public TileFrame() {
     setSize(400, 400);
     setTitle("Fun with Tiles");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     panel = new TilePanel(Color.RED);
     add(panel, BorderLayout.CENTER);
     addColorButtons();
 }
     
 private void addColorButtons() {
     JPanel p = new JPanel();
     p.setBackground(Color.CYAN);
     ButtonGroup g = new ButtonGroup();
     addButton(p, g, "Green", false, Color.GREEN);
     addButton(p, g, "Blue", false, Color.BLUE);
     addButton(p, g, "Red", true, Color.RED);
     add(p, BorderLayout.NORTH);
 }
     
 private void addButton(JPanel p, ButtonGroup g, String name,
                        boolean selected, final Color color) {
     JRadioButton button = new JRadioButton(name, selected);
     p.add(button);
     g.add(button);
     button.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
             if (e.getStateChange() == ItemEvent.SELECTED) {
                 panel.setColor(color);
             }
         }
     });
 }
}





