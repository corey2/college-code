package pa4;

//Lists the savings activities of a mystery person's bank account

public class PrintfSample {
	public static void main(String[] args) {
		System.out.println("year  interest   balance  newdeposit  newbalance"); // 1. the top row of the table-like construct/table header
		balances(); // 2. creates the body of each column
	}

	public static void balances() {
		double balance;
		double nbalance = 1000; // 4. helps to determine the initial values of interest and balance
		double interest;
		int nd = 100;
		for (int i = 1; i <= 25; i++) { // 5. "i" serves the sole purpose of printing exactly 25 years worth of data
			interest = nbalance * 0.065; // 6. finds interest
			balance = nbalance + interest; // 7. finds balance
			nbalance = balance + 100; // 8. finds newbalance
			System.out.printf("%4d%10.1f%10.1f%12d%12.1f\n", i, interest, balance, nd, nbalance); // 9. displays results in a spacey fashion
		}
	}
}
