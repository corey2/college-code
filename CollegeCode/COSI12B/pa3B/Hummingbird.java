//A Hummingbird will transport to a new spot in the aviary whenver it takes flight

package pa3B;

import java.awt.*;
import java.util.*;

public class Hummingbird extends AbstractBird
	implements Bird, AviaryConstants {
	
	private Random rand = new Random();
	
	public Hummingbird(int x, int y) {
		setColor(Color.MAGENTA);
		setPosition(x,y);
	}
	
	//Creates the coordinates of the bird's next point separately. The setPosition
	//method makes these coordinates into the point and sends the bird there.
	public void fly() {
		int x = rand.nextInt(19);
		int y = rand.nextInt(19);
		setPosition(x,y);
		}
	
	}