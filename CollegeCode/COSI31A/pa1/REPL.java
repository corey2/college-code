package pa1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class REPL {

	
	static boolean flag;
	//Receives command(s) input by the user and creates threads to run the command(s) 
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		BlockingQueue<String> q = new LinkedBlockingQueue<String>();
		flag = true;
		Runnable runner = null;
		Runnable runner2;
		while (true) {
			System.out.println(">");
			String command = console.next();
			String[] chain = command.split("-");
			
			if (chain[0].equals("cat")) {
				runner = new Cat(q, chain);
				Thread t = new Thread(runner);
				t.start();		
				try {
				    t.join();
				} catch (InterruptedException e) {}	
				for (int i=1; i<chain.length; i++) {
					if (chain[i].equals("|")) {
						Modifiers(chain, q, i);
					}
				}
				
			} else if (chain[0].equals("history")) {
				runner = new History();
			
			} else if (chain[0].equals("!n")) {
				runner = new Repeat();
			} else if (chain[0].equals("pwd")) {
				runner = new Pwd(q);
				Thread t = new Thread(runner);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {}
			} else if (chain[0].equals("ls")) {
				runner = new Ls();
			} else if (chain[0].equals("cd")) {
				runner = new Cd();				
			} else if (chain[0].equals("exit")) {
				runner = new Exit();
				Thread t = new Thread(runner);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {}
			} else if (chain[0].equals(">")) {
				runner = new FileStore();
			}
			
			
			if (runner == null) {
				System.out.println("Invalid Command");
			} else {
				runner2 = new ShellSink(q); 
				Thread t2 = new Thread(runner2);
				t2.start();
				try {
					t2.join();
				} catch (InterruptedException e) {}
			}
			
			
			if (flag == true) {
				System.out.println("true");
			} else {
				break;
			}
		}
			
	}

	public static void Modifiers(String[] s, BlockingQueue q, int progress) {
		Runnable runner = null;
		for (int i=progress; i<s.length; i++) {
			if (s[i+1].equals("grep")) {
				runner = new Grep(q, q, s[i+2]);
				Thread t = new Thread(runner);
				t.start();		
				try {
				    t.join();
				} catch (InterruptedException e) {}	
			} else if (s[i].equals("lc")) {
				runner = new Lc();	
			}
		}
	}



}	



//for (String link: chain) {
//if (link.equals("|")) {
	//t.interrupt();
	//System.out.println("here");
//}
//}



//if (Thread.currentThread().isInterrupted()) {
//System.out.println("I'm interrupted");
//i=i+1000;
//break;
//}
//if (chain[i].equals("|")) {
//System.out.println("I'm interrupted");
//i=i+1000;
//break;