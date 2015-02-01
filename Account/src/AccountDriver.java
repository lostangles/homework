import java.util.Scanner;

public class AccountDriver {
	public static void main(String[] args)
		{
			final Scanner kb = new Scanner(System.in);
			int id = 0;
			double balance;
			double interest;
			final Account[] accounts = new Account[5];

			System.out.print("Enter the annual interest rate: ");
			interest = kb.nextDouble();

			// fill in the array by asking the user information about each account
			for (int i = 0; i < 5; i++) {
				System.out.print("Enter the account number: ");
				id = kb.nextInt();
				System.out.print("Enter the initial amount to deposit: ");
				balance = kb.nextDouble();
				accounts[i] = new Account(id, balance);
				accounts[i].setAnnualInterestRate(interest);
			}

			// Main loop action, first while loop keeps the program open for
			// multiple account access/withdrawl/deposits
			// Second loop is once an account is found the withdraw/deposit actions
			// are available
			boolean repeat = true;
			while (repeat) {
				boolean found = false;
				System.out.println("\n\n*******************************");
				System.out.println("Welcome to the BANK OF AMERICA");
				System.out.println("\n\n*******************************");

				System.out
						.println("Enter the account number to deposit, withdraw money :");
				id = kb.nextInt();
				int i = 0;
				while (!found && (i < 5)) {
					if (id == accounts[i].getId()) {
						System.out
								.print("Here is the account information currently:\n\n "
										+ accounts[i]);

						System.out.print("Enter the amount to deposit : ");
						accounts[i].deposit(kb.nextDouble());
						System.out.print("Enter the amount to withdraw : ");
						accounts[i].withdraw(kb.nextDouble());

						System.out
								.print("Here is the account information after the transaction:\n\n "
										+ accounts[i]);
						found = true;

					} else if ((id != accounts[i].getId()) && (i == 4)) {
						System.out.println("Account not found");

					}
					i++;

				}

				// Exit the loop/program here
				System.out.println("Do you have another account?");
				final String answer = kb.next();
				if (answer.equalsIgnoreCase("no")) {
					repeat = false;
				}

			}
		}
}
