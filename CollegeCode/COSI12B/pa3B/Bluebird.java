//A Bluebird flies through the aviary in a zig-zag pattern, turning around when it
//hits a wall

package pa3B;

import java.awt.*;

public class Bluebird extends AbstractBird
	implements Bird, AviaryConstants {
	
	//Keeps track of whether the bird will go up or down during its next flight.
	//The field resets whenever the bird reaches the side of the aviary.
	private int count; 
	
	//true=facing right
	private boolean h; 
	
	public Bluebird(int x, int y) {
		setColor(Color.BLUE);
		setPosition(x,y);
		h=true;
	    count=0;
	}

	public void fly() {
		
		//when the bird hits the left wall
		if (getPosition().x==0) {		
			h=true;
			count=0;
		}
		//when the bird hits the right wall
		if (getPosition().x==19) {
			h=false;
			count=0;
		}
		
		count++;
		
			//if the bird is facing right...
		if (h/*==true*/) {
			// it goes up...
			if (count%2 != 0) {
				getPosition().y--;
			//then down...
			} else {
				getPosition().y++;
			}
			//...and right
			getPosition().x++;
			
			//if the bird is facing left... 
		} else {
			//it goes down...
			if (count%2 != 0) {
				getPosition().y++;
			//then up...
			} else {
				getPosition().y--;
			}
			//...and left
			getPosition().x--;
		}
		
	}
			
		









}
