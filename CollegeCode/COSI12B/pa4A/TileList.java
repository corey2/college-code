//This class contains an ArrayList of tiles that can be specially manipulated with
//methods like moveToBack which moves a tile to the ending index of the array and
//insertBack which allows instant expansion at the end of the array.

package pa4A;

import java.util.ArrayList;


public class TileList {
	
	private ArrayList<Tile> tiles;


	//constructs an empty tile list
	public TileList() {
		tiles = new ArrayList<Tile>();
	}

	//searches through the list of tiles and returns a reference (i.e, an object)
	//to the last tile for which (x, y) is inside the tile; returns null if (x, y) 
	//is not inside any tile of the list; moves the found tile to the back of the 
	//list
	public Tile moveToBack(int x, int y) {
		Tile match = null;
		int index = 0;
		for (Tile i: tiles) {
			if (i.inside(x,y)) {
				match = i;
				index = tiles.indexOf(match);
			}
		}
		if(match!=null) {
			tiles.remove(index);
			insertBack(match);
		}
		return match;
	}

	//post: inserts t at the back of the list of tiles
	public void insertBack(Tile t) {
		tiles.add(t);
	}



	//post: returns the number of tiles in this list
	public int size() {
		return tiles.size();
	}



	//post: returns the Tile at the given index
	public Tile get(int index) {
		return tiles.get(index);
		
	}

}