package pa1;

import java.util.concurrent.BlockingQueue;


public class Grep extends Filter {

	 protected BlockingQueue in;
	 protected BlockingQueue out;
	 protected volatile boolean done;
	 protected String searchString;
	
	public Grep(BlockingQueue in, BlockingQueue out, String searchString) {
		super(in,out);
		this.searchString = searchString;
		
		
	}
		
	protected String transform (String l) {
		if (l.contains(searchString)) {
			return l;	
		} else {
			return null;
		}
	}
	

}
