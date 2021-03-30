package pa1;

//this program writes a song called "There Was an Old Lady"
public class ThePoem {
	public static void main(String[] args){
		thePoem () ;
	}
	
	public static void thePoem () {
		System.out.println();
		System.out.println("There was an old lady who swallowed a fly.");
		flyDie () ;
		System.out.println();
		System.out.println("There was an old lady who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		spiderFlyDie () ;
		System.out.println();
		System.out.println("There was an old lady who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		bird () ;
		System.out.println();
		System.out.println("There was an old lady who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		cat () ;
		System.out.println();
		System.out.println("There was an old lady who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		System.out.println("She swallowed the dog to catch the cat,");
		cat () ;
		System.out.println();
		System.out.println("There was an old lady who swallowed a horse,");
		System.out.println("She died of course.");
		}
	
	public static void flyDie () {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
	}
	public static void spiderFlyDie () {
		System.out.println("She swallowed the spider to catch the fly,");
		flyDie () ;
	}
	public static void bird () {
		System.out.println("She swallowed the bird to catch the spider,");
		spiderFlyDie () ;
	}
	public static void cat () {
		System.out.println("She swallowed the cat to catch the bird,");
		bird () ;
	}
}