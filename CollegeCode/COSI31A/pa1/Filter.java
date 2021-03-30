package pa1;

import java.util.concurrent.*;

public abstract class Filter implements Runnable {
    protected BlockingQueue<String> in;
    protected BlockingQueue<String> out;
    protected volatile boolean done;
    
    public Filter(BlockingQueue<String> in, BlockingQueue<String> out) {
        this.in = in;
        this.out = out;
        this.done = false;
    }
    
    public void run() {
        String l = null;
        while(!this.done) {
            try {
				l = in.take();
			} catch (InterruptedException e) {
				System.err.println("error");
				e.printStackTrace();
			}    // read from input queue, may block
            l = transform(l); // allow filter to change message
            try {
				if (l != null) {
					out.put(l);
				}
			} catch (InterruptedException e) {
				System.err.println("error");
				e.printStackTrace();
			}       // forward to output queue
            if (in.size() == 0) {
            	this.done = true;
            }
        }
    }
    
    protected abstract String transform(String o);
}
