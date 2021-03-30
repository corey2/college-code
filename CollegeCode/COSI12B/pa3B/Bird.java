//This bird interface contains each method that makes an object a true bird. If one
//wanted to refer to the Bird class in general, they would use this interface.

package pa3B;

import java.awt.*;

public interface Bird {
	public Color getColor();
	public Point getPosition();
	public void fly();
	public void setPosition(int x, int y);
}
