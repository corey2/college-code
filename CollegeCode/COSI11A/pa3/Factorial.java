package pa3;

public class Factorial {
	public static void main(String[] args) {
		int n = 1;
		for (int i = 1; i <= 3; i++) {
			int r = i * n;
			System.out.println(r);
			n = i;
		}
		System.out.println("24");
		System.out.println("120");

	}
}
