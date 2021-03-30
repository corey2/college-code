//This abstract class holds fields that represent each bird's color and position in
//space. The set and get methods are also here to allow easy, secure access to the fields
package pa3B;

import java.awt.*;

public abstract class AbstractBird {
	private Color c;
	private Point p;
	
	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c=c;		
	}
	
	public Point getPosition() {
		return p;
	}
	
	public void setPosition(int x, int y) {
		p = new Point(x,y);
	}

}


