package pa4B;

//ShoppingMain provides method main for a simple shopping program.

public class ShoppingMain {
 public static void main(String[] args) {
     Catalog list = new Catalog("CS Gift Catalog");
     list.add(new Item("Silly Putty", 3.95, 10, 19.99));
     list.add(new Item("Silly String", 3.50, 10, 14.95));
     list.add(new Item("Bottle o Bubbles", 0.99));
     list.add(new Item("Nintendo Wii System", 389.99));
     list.add(new Item("Mario Computer Science Party 2 (Wii)", 49.99));
     list.add(new Item("Don Knuth Code Jam Challenge (Wii)", 49.99));
     list.add(new Item("Computer Science Pen", 3.40));
     list.add(new Item("Rubik's Cube", 9.10));
     list.add(new Item("Computer Science Barbie", 19.99));
     list.add(new Item("'Java Rules!' Button", 0.99, 10, 5.0));
     list.add(new Item("'Java Rules!' Bumper Sticker", 0.99, 20, 8.95));

     ShoppingFrame f = new ShoppingFrame(list);
     f.setVisible(true);
    
 }
}