package pa4A;
	
// This class responds to mouse events on a tile.

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.event.*;

	public class TileListener extends MouseInputAdapter {
	    private TileList tiles;
	    private TilePanel parent;
	    private Point firstSpot;
	    private Point lastSpot;
	    private Tile current;

	    public TileListener(TileList tiles, TilePanel parent) {
	        this.tiles = tiles;
	        this.parent = parent;
	    }
	                        
	    public void mouseClicked(MouseEvent e) {
	        parent.repaint();
	    }
	                        
	    public void mousePressed(MouseEvent e) {
	        firstSpot = lastSpot = e.getPoint();
	        current = tiles.moveToBack(e.getX(), e.getY());
	    }
	                        
	    public void mouseReleased(MouseEvent e) {
	        int deltaX = lastSpot.x - firstSpot.x;
	        int deltaY = lastSpot.y - firstSpot.y;
	        Point newSpot = e.getPoint();
	        if (current == null) {
	            if (deltaX != 0 && deltaY != 0) {
	                Tile nextTile = new Tile(firstSpot.x, firstSpot.y, deltaX, 
	                                         deltaY, parent.getColor());
	                tiles.insertBack(nextTile);
	            }
	        } else {
	            current.translate(newSpot.x - firstSpot.x,
	                              newSpot.y - firstSpot.y);
	        }
	        parent.repaint();
	    }
	                        
	    private void drawBorder(Graphics g) {
	        int deltaX = lastSpot.x - firstSpot.x;
	        int deltaY = lastSpot.y - firstSpot.y;
	        if (current != null) {
	            g.drawRect(current.getX() + deltaX, current.getY() + deltaY, 
	                       current.getWidth(), current.getHeight());
	        } else {
	            int cornerX, cornerY;
	            if (deltaX < 0) {
	                cornerX = firstSpot.x + deltaX;
	            } else {
	                cornerX = firstSpot.x;
	            }
	            if (deltaY < 0) {
	                cornerY = firstSpot.y + deltaY;
	            } else {
	                cornerY = firstSpot.y;
	            }
	            g.drawRect(cornerX, cornerY, Math.abs(deltaX), Math.abs(deltaY));
	        }
	    }
	        
	    public void mouseDragged(MouseEvent e) {
	        Graphics g = parent.getGraphics();
	        g.setXORMode(parent.getBackground());
	        drawBorder(g);
	        lastSpot = e.getPoint();
	        drawBorder(g);
	        g.dispose();
	    }
	}

