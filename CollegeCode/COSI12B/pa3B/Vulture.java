//Vultures travel counterclockwise in a circle (actually a square since
//this is a relatively primitive program).

package pa3B;

import java.awt.Color;

public class Vulture extends AbstractBird 
	implements Bird, AviaryConstants {
	
	//The field count allows the bird's movement pattern to repeat itself
	private int count;
	
	public Vulture(int x, int y) {
		setColor(Color.BLACK);
		setPosition(x,y);
		count=1;
	}

	
	//Goes up, left, down, right, and repeats
	public void fly() {
		if (count==1) {
			getPosition().y--;
		}	
		if (count==2) {
			getPosition().x--;
		}
		if (count==3) {
			getPosition().y++;
		}
		if (count==4) {
			getPosition().x++;
			count=1;
			return;
		}
		count++;
		return;
	}
}
