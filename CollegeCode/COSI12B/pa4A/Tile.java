package pa4A;

import java.awt.*;

public class Tile {
	private int x;
	    private int y;
	    private int width;
	    private int height;
	    private Color color;

	    public Tile(int x, int y, int width, int height, Color color) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        if (width < 0) {
	            this.width = -this.width;
	            this.x = this.x - this.width;
	        }
	        if (this.height < 0) {
	            this.height = -this.height;
	            this.y = this.y - this.height;
	        }
	        this.color = color;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public int getHeight() {
	        return height;
	    }

	    public void translate(int deltaX, int deltaY) {
	        x += deltaX;
	        y += deltaY;
	    }

	    public void draw(Graphics g) {
	        g.setColor(color);
	        g.fillRect(x, y, width, height);
	        g.setColor(Color.black);
	        g.drawRect(x, y, width, height);
	    }

	    public boolean inside(int x, int y) {
	        if (x < this.x || x > this.x + width) {
	            return false;
	        } else if (y < this.y || y > this.y + height) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	}