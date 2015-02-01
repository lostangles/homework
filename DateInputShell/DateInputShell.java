
/*Due Date October 30th*/ 
import java.util.*;

/************************************************************************
 * CSC15 -  										 
 * your name
 *time of your lecture
 * description of the program 									 
 *************************************************************************/  
public class DateInputShell
{
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in); 
		int month, day, year;//declare varaiables for month, day and yera
		String monthString, holiday;
		printDirections();//	call the method 	printDirections;
		System.out.print("How many times do you want to run the code: ");//ask the user how many times to run the code
		int repeat = kb.nextInt();
		
		for (int i = 1; i <= repeat; i++)
		{ 
				
				System.out.print("You may enter the month as:\n * a numeric value (1..12)\nor as\n* an unabbreviated month name (January or February etc....)")//prompt the user to enter the month  
				//check to see if the user is entering an integer for the month
				if(kb.hasNextInt())
				{
					System.out.print("Enter the month: ");
					month = kb.nextInt();	//read the month as an integer 
					monthString = getMonthString(month);	//call the method to convert the month to a string 
				}
				else
				{
					System.out.print("Enter the month: ");
					monthString = kb.nextLine();//read the month as a String 
					month = getMonthNumber(monthString);//call the method to convert it to an integer 
				}
		
				System.out.print("Enter the day: ");//get the day from the userS ;
				day = kb.nextInt();
				System.out.print("Enter the year: ");
				year = kb.nextInt();//get the year from the user; 				
				holiday = getHoliday(month, day, year)// call the method to get the holidays assosiated with the given date		
				System.out.println("\nThe Date is: " + day + "//" + month + "//" + year + "  " + monthString + " " + day + ", " + year + "   " + holiday);//display the desired output in the given format
				
		}									
	}

	/********************************************************************
	 * Method: printDirections
	 * This method prints the directions for entering the data for a date.
	 */
	public static void printDirections()
	{
		System.out.print("This program will ask you for a month, day, and year\nand will print the corresponding date in two standard date formats."); //prints the directions for running the code	
	}
	 	  
	
	/********************************************************************
	 * This method receives an integer representing a month and returns
	 * a String that is the name of the corresponding month.
	 */
	//use switch statments for this method
	public static String getMonthString(int monthNum)
	{
		String temp;
		switch (monthNum) 
			{
			case 1:  temp = "January";
			case 2:  temp = "February";
			case 3:  temp = "March";
			case 4:  temp = "April";
			case 5:  temp = "May";
			case 6:  temp = "June";
			case 7:  temp = "July";
			case 8:  temp = "August";
			case 9:  temp = "September";
			case 10: temp = "October";
			case 11: temp = "November";
			case 12: temp = "December";
			default: temp = "Invalid month";
			}

		 return temp;
   } // end method getMonthString
	
	/********************************************************************
	 * This method receives a String that is the name of a month and 
	 * returns the corresponding integer representing that month.
	 */
	public static int getMonthNumber(String monthStr)
	{
		int temp;
		switch (monthStr) 
		{
		case "January": 	 temp = 1;
		case "February":	 temp = 2;
		case "March":  		 temp = 3;
		case "April":	     temp = 4;
		case "May":  		 temp = 5;
		case "June":  		 temp = 6;
		case "July":  	     temp = 7;
		case "August":  	 temp = 8;
		case "September":    temp = 9;
		case "October": 	 temp = 10;
		case "November": 	 temp = 11;
		case "December": 	 temp = 12;
		default:		  	 temp = 0;
		}	
		return temp;
	} // end method getMonthNumber
	
	
	
	//****************************************************************************
	public static String getHoliday(int month, int day, int year)
	{
	   String holiday ="";
	   switch (month)
	   {
	   case 1: switch (day)
	   		{
	   		case 1:  holiday = "New Year's Day";
	   		case 19: holiday = "Martin Luther King Jr. Day";	   		
	   		}
	   case 2: switch (day)
	   		{
	   		case 2:  holiday = "Ground Hog Day";
	   		case 12: holiday = "Abraham Lincoln's Birthday";
	   		case 14: holiday = "St. Valeninte's Day";
	   		case 22: holiday =  "George Washington's Birthday";
	   		}
	   case 3: switch (day)
	   		{
	   		case 17:  holiday =  "St. Patrick's Day";
	   		}
	   case 4: switch (day)
	   		{
	   		if (isEaster(month, day, year)) holiday = "Easter ";
	   		case 1:  holiday =  "April Fool's Day";
	   		case 10: holiday =  "Arbor Day";
	   		case 22: holiday =  "Earth Day";
	   		}
	   case 5: switch (day)
	   		{
	   		case 1:  holiday =  "May Day";
	   		case 5: holiday =  "Cinco de Mayo";
	   		}
	   case 6: switch (day)
	   		{
	   		}
	   case 7: switch (day)
	   		{
	   		case 4:  holiday =  "Independence Day";
	   		}
	   case 8: switch (day)
	   		{
	   		case 3:  holiday =  "International Friendship Day";
	   		case 13: holiday =  "Columbus Day";
	   		}
	   case 9: switch (day)
	   		{
	   		}
	   case 10: switch (day)
	   		{
	   		case 31:  holiday = "Halloween";
	   		}
	   case 11: switch (day)
	   		{
	   		case 11:  holiday =  "Vereran's Day";
	   		}
	   case 12: switch (day)
	   		{
	   		case 25:  holiday =  "Christmas";
	   		case 31: holiday =  "New Year's Eve"; 
	   		}
	   }
  		if (isEaster(month, day, year)) holiday += " Easter ";

	   }
		//your code 
		
		 		
		return holiday;
	} // end method getHoliday
	
	public static boolean isEaster(int month, int day, int year)
	{
		 int goldenNumber, a, b, c, d;
		 goldenNumber = (year % 19) + 1;
		 a = (24 + 19*(goldenNumber - 1)) % 30;
		 b = a - a/28;
		 c = (year + year/4 + b - 13) % 7;
		 d = b - c;
		 easterMonth = 3 + (d + 40)/44;
		 easterDay = d + 28 - 31*(easterMonth/4);
		 
		 if ((easterMonth == month) && (easterDay == day))
			 return true;
		 else
			 return false;
	} // end method isEaster
}


 		 