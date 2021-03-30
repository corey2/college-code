package pa3;

import java.util.*;

public class Exponent {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a number representing how many times 2 is squared.");
		int inumber = console.nextInt();
		System.out.println("1");
		calculations(inumber);
	}

	public static void calculations(int x) {
		int onumber = 1;
		for (int i = 1; i <= x; i++) {
			onumber = 2 * onumber;
			System.out.println(onumber);
		}
	}

}
