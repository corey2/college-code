package pa1;

import java.util.concurrent.BlockingQueue;


public class Pwd implements Runnable {

	BlockingQueue out;
	protected volatile boolean done;
	
	public Pwd(BlockingQueue out) {
		this.out = out;
		this.done = false;
	}
	
	public void run() {
		while(!this.done) {
        	String s = System.getProperty("user.dir");
        	try {
        		out.put(s);
        		this.done = true;
        	} catch (InterruptedException e) {
        		System.err.println("error");
        		e.printStackTrace();
        	} 
		}
	}
	
}
