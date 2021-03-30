package pa1;

import java.util.concurrent.*;

public class ShellSink implements Runnable {
    
	private BlockingQueue in;
	protected volatile boolean done;
	
	public ShellSink(BlockingQueue in) {
        this.in = in;
        this.done = false;
    }
    
    public void run() {
    	Object s = null;
        while (!this.done) {
        	try {
        		s = in.take();
            } catch (InterruptedException e) {
            	e.printStackTrace();
			}    
            //if (s != null) {
            	System.out.println(s);
            //}
            if (in.size() == 0) {
            	this.done = true;
            }
        }
    }

}
