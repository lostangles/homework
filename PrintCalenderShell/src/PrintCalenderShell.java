/************************************************************************
 * CSC15 -  										 
 * Brandon Byrne
 * Tu/Th 8am
 * Due Date November 20th
 * Calender program, prints either a month or a year based on user input								 
 *************************************************************************/  
import java.util.Scanner;
public class PrintCalenderShell {
	public static void main(String[] args)
		{
			int year = 0;
			int mnth = 0;
			final Scanner kb = new Scanner(System.in);
			//prompt the user to enter the year
			boolean repeat = true;
			while (repeat)//repeat as long as the user wants to
			{
				System.out.println("Press m to print the calender for the month, press y to print the calender for the year m/y \n");
				final String monthOrYear = kb.next();
				//if user choses month, go this route
				if (monthOrYear.equalsIgnoreCase("m")) {
					while (year < 1800) {
						System.out.println("Enter a valid year after 1800");
						//loop as long as the user is entering an input other than integer
						while (!kb.hasNextInt()) {
							System.out.println("Enter a valid year after 1800:");
							kb.next();
						}
						year = kb.nextInt();
						System.out.println("Enter a valid month:");

						while ((mnth < 13) && (mnth > 0)) {
							System.out.println("Enter a valid month:");
							//loop as long as the user is entering an input other than integer
							while (!kb.hasNextInt()) {
								System.out.println("Enter a valid month:");
								kb.next();
							}
						}
						mnth = kb.nextInt();
						printMonth(year, mnth);
					}
				}
				//if user chooses year this is where we go
				else if (monthOrYear.equalsIgnoreCase("y")) {
					//loop as long as the user is entering an invalid year less than 1800
					while (year < 1800) {
						System.out.println("Enter a valid year after 1800");
						//loop as long as the user is entering an input other than integer
						while (!kb.hasNextInt()) {
							System.out.println("Enter a valid year after 1800:");
							kb.next();
						}
						year = kb.nextInt();
					}
					//printing the calender for each month of the year
					for (int i = 1; i <= 12; i++) {
						printMonth(year, i);  //call the method printMonth with the parameters year and i
					}
				}
				System.out.println("Do you have another year to print the calender:yes/no");
				year = 0;  //reset year and month so we can go through the loops again       
				mnth = 0;
				final String answer = kb.next();
				if (answer.equalsIgnoreCase("no"))
					repeat = false;
			}
		}

	//this method prints the body of the calender for the given month
	public static void printMonth(int year, int month)
		{
			printMonthTitle(year, month);//call the method printTitle with the values year and month
			printMonthBody(year, month);//call the method printMonthBody with the values year and month

		}

	//this method prints the title of the days in each week(sunday Mon Tues Wed Thur Fir Sat)
	public static void printMonthTitle(int year, int month)
		{
			System.out.println("        " + getMonthName(month) + " " + year
					+ "        ");
			System.out.println("---------------------------");
			System.out.println("Sun Mon Tue Wed Thu Fri Sat");
		}

	//this method outputs the calender for each month of the year
	public static void printMonthBody(int year, int month)
		{
			getStartDay(year, month);//call the method getStartDay to get the start day for each month of the year

			print(getStartDay(year, month), year, month);//call the method print to print the calender for the month and pass the first day of the month as the parameter

		}

	public static void print(int startDay, int year, int month)
		{
			final int numDaysMonth = getNumberOfDaysInMonth(year, month);// call the method getNumberOfdaysInMonth to finde out the number of the days in the month
			//use a for loop to print spaces up to the start day for each month
			for (int i = 0; i < getStartDay(year, month); i++) {
				System.out.print("    ");
			}
			for (int i = 1; i <= numDaysMonth; i++) {
				if (i < 10)
					System.out.print(i + "   ");
				if (i > 9)
					System.out.print(i + "  ");
				if (((startDay + i) % 7) == 0)
					System.out.print("\n");
			}
			//use another for loop to print the rest of the days in the calender
			//if the month is November calculate the thanksgiving day and output            }
			if (month == 11)
				System.out.println("\n\n**Thanksgiving day is on the "
						+ thanks(getStartDay(year, month)) + "th");
			System.out.println("\n\n");
		}

	//not sure why the shell wanted me to use a switch here, 
	//makes no sense when the formula for thanksgiving is easy
	public static int thanks(int startDay)
		{
			final int thanks = (22 + (11 - startDay) % 7);
			return thanks;
		}

	//converts an int month into a string
	public static String getMonthName(int month)
		{
			String temp;
			switch (month) {
			case 1:
				temp = "January";
				break;
			case 2:
				temp = "February";
				break;
			case 3:
				temp = "March";
				break;
			case 4:
				temp = "April";
				break;
			case 5:
				temp = "May";
				break;
			case 6:
				temp = "June";
				break;
			case 7:
				temp = "July";
				break;
			case 8:
				temp = "August";
				break;
			case 9:
				temp = "September";
				break;
			case 10:
				temp = "October";
				break;
			case 11:
				temp = "November";
				break;
			case 12:
				temp = "December";
				break;
			default:
				temp = "Invalid month";
			}

			return temp;
		}

	//this method returns the satrt day of the month
	public static int getStartDay(int year, int month)
		{
			int start = getTotalNumberOfDays(year, month);//call the method getTotalNumberOfDays and store it in a variable called start
			start += 3;//add 3 to the variable start
			//return start % 7
			return (start % 7);

		}

	//returns the total number of the days since the year 1800 up to the given month in the given year
	public static int getTotalNumberOfDays(int year, int month)
		{
			int currYear = 1800;
			int numDays = 0;
			while (currYear <= (year - 1)) {
				if (isLeapYear(currYear))
					numDays += 366;
				else
					numDays += 365;
				currYear += 1;
			}
			for (int i = 1; i < month; i++) {
				numDays += getNumberOfDaysInMonth(year, (month - i));
			}
			return numDays;
		}

	//gets number of days in a month, returns as int, accounts for leap year
	public static int getNumberOfDaysInMonth(int year, int month)
		{
			int days = 0;
			if (month == 1)
				days = 31;
			if (month == 2 && isLeapYear(year))
				days = 29;
			if (month == 2 && !isLeapYear(year))
				days = 28;
			if (month == 3)
				days = 31;
			if (month == 4)
				days = 30;
			if (month == 5)
				days = 31;
			if (month == 6)
				days = 30;
			if (month == 7)
				days = 31;
			if (month == 8)
				days = 31;
			if (month == 9)
				days = 30;
			if (month == 10)
				days = 31;
			if (month == 11)
				days = 30;
			if (month == 12)
				days = 31;
			return days;

		}

	//determines if a year is a leap year, returns true if it is
	public static boolean isLeapYear(int year)
		{
			if (((year % 400) == 0)
					|| (((year % 4) == 0) && !((year % 100) == 0)))
				return true;
			return false;
		}

}