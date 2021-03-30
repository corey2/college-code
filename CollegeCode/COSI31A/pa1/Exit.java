package pa1;

public class Exit implements Runnable {

	
	public void run() {
		System.out.println("Goodbye");
		REPL.flag = false;
	}

}
