//A Cardinal initially flies up, and changes direction whenever it hits the top or
//bottom of the Aviary.

package pa3B;

import java.awt.*;

public class Cardinal extends AbstractBird 
	implements Bird, AviaryConstants {
	
	//true=facing up
	private boolean v; 
	
	public Cardinal(int x, int y) {
		setColor(Color.RED);
		setPosition(x,y);
		v=true;
	}

	public void fly() {
		
		//when the bird hits the top wall
		if (getPosition().y==0) {		
			v=false;
		}
		
		//when the bird hits the bottom wall
		if (getPosition().y==19) {
			v=true;
		}
		
		//determines the bird's movement based on the direction it is facing
		if (v==true) {
			//back towards the origin is up
			getPosition().y--; 
		} else {
			getPosition().y++;
		}
	}

}

