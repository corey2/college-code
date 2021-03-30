package pa1;

public class Cd implements Runnable {

	public void run() {
		System.out.println("hereiam");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("home.dir"));
        System.out.println(System.getProperty("java.class.path"));
	}

}
