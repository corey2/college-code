Corey Shapiro
3-13-12
HW#4

	The work in this folder includes Tile, TileFrame, TileList, 
TileListener, TileMain, and TilePanel java files. The file that I am 
responsible for is TileList. TileList holds an ArrayList of tiles and has
several methods that manipulate. Tiles are objects created by the other 
classes which are displayed on the screen in a certain height, width, and 
color. These tiles can be resized and dragged around the screen. 
The moveToBack in TileList checks if a tile is within a given space
(represented by x and y coordinates), and if it is, removes it from its 
current position and adds it to the end of the array. Graphically, this
causes a tile that is dragged on top of another previously set tile to appear
in front. This process is aided by another method, insertBack, which adds
another tile to the end of the TileList. The remaining two methods are 
"size", which returns the size of the TileList as an integer, and a basic
get method that retrieves an individual Tile from an element in the TileList.
  