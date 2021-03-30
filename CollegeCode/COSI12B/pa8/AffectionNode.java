package pa8;

// CSE 143, Winter 2010, Marty Stepp
// Homework 4: Affection
//
// Instructor-provided support class.  You should not modify this code!

/** Each AffectionNode object represents a single node in a linked list
    for a game of Affection. */
public class AffectionNode {
    public String name;        // this person's name
    public String kisser;      // name of who kissed this person (null if alive)
    public AffectionNode next;  // next node in the list (null if none)
    
    /** Constructs a new node to store the given name and no next node. */
    public AffectionNode(String name) {
        this(name, null);
    }

    /** Constructs a new node to store the given name and a reference
        to the given next node. */
    public AffectionNode(String name, AffectionNode next) {
        this.name = name;
        this.kisser = null;
        this.next = next;
    }
}
