/************************************************************************
 * CSC15 -

 * Brandon Byrne
 * Tu/Th 8am
 * Due Date December 12, 2014
 * Creates the Account data type.  Each object holds an account ID, Balance, APR, and date created
 *************************************************************************/

import java.util.Date;

public class Account {

	private double annualInterestRate;
	private int id;
	private double balance;
	private final Date dateCreated;

	// no-arg constructor
	public Account() {

			dateCreated = new java.util.Date();
	}

	// constructor to initialize with ID and Balance
	public Account(int newId, double newBalance) {
			id = newId;
			balance = newBalance;
			dateCreated = new java.util.Date();
	}

	// accessors
	public int getId()
		{
			return id;
		}

	public double getBalance()
		{
			return balance;
		}

	public double getAnnualInterestRate()
		{
			return annualInterestRate;
		}

	public double getMonthlyInterestRate()
		{
			return annualInterestRate / 12;
		}

	// mutators
	public void setId(int newId)
		{
			if (newId > 0) {
				id = newId;
			}
		}

	public void setBalance(double newBalance)
		{
			if (newBalance > 0) {
				balance = newBalance;
			}
		}

	public void setAnnualInterestRate(double newAnnualInterestRate)
		{
			if (newAnnualInterestRate > 0) {
				annualInterestRate = newAnnualInterestRate;
			}
		}

	public Date getDateCreated()
		{
			return dateCreated;
		}

	// Withdraws from balance, can't be a negative. Also returns a boolean true
	// if withdraw was successful
	public boolean withdraw(double amount)
		{
			boolean success = false;

			if ((balance > amount) && (amount > 0)) {
				balance -= amount;
				success = true;
			} else {
				System.out
						.println("\n*******Not enough money in your account*********\n");
			}
			return success;

		}

	// Deposits money to your account, must deposit a positive amount
	public void deposit(double amount)
		{
			if (amount > 0) {
				balance += amount;
			}
		}

	// compares two account ID's together, returns true if match false if no
	// match
	public boolean equals(Account a)
		{
			boolean accountsEqual = false;
			if (a.getId() == this.getId()) {
				accountsEqual = true;
			}
			return accountsEqual;
		}

	// Format for printing an Account object
	@Override
	public String toString()
		{

			String acc = "";
			acc = acc + "ID : " + id;
			acc = acc + "\n Balance : " + roundBalance(balance);
			acc = acc + "\n Annual Interest Rate : " + annualInterestRate;
			acc = acc
					+ "\n Monthly interest : "
					+ roundBalance(((getMonthlyInterestRate() * balance) / 100));
			acc = acc + "\n Date account was created : " + dateCreated + "\n\n";
			return acc;
		}

	// get rid of everything past the first two decimal places,
	// but don't actually overwrite the balance
	private static double roundBalance(double bal)
		{
			final double tempBalance = (int) Math.round(bal * 100)
					/ (double) 100;
			return tempBalance;
		}
}