/*Due Date October 30th*/ 
import java.util.*;

/************************************************************************
 * CSC15 -  										 
 * Brandon Byrne
 * Tu/Th 8am
 * Takes in user input, spits out if input is a holiday 									 
 *************************************************************************/  
public class DateInputShell
{
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in); 
		int month, day, year;//declare varaiables for month, day and yera
		String monthString, holiday;
		printDirections();	//	call the method 	printDirections;
		int repeat = kb.nextInt();
		kb.nextLine();
		System.out.println("You may enter the month as:\n * a numeric value (1..12)\nor as\n* an unabbreviated month name (January or February etc....)\n");//prompt the user to enter the month  

		for (int i = 1; i <= repeat; i++)
		{ 
				
				//check to see if the user is entering an integer for the month
				System.out.print("Enter the month: ");
				if(kb.hasNextInt())
				{					
					month = kb.nextInt();	//read the month as an integer 
					monthString = getMonthString(month);	//call the method to convert the month to a string 
				}
				else
				{
					monthString = kb.nextLine();//read the month as a String 
					month = getMonthNumber(monthString);//call the method to convert it to an integer 
				}
		
				System.out.print("Enter the day: ");//get the day from the userS ;
				day = kb.nextInt();
				System.out.print("Enter the year: ");
				year = kb.nextInt();//get the year from the user; 
				kb.nextLine(); // eat the \n so that multiple runs of the code work 
				holiday = getHoliday(month, day, year);// call the method to get the holidays assosiated with the given date		
				System.out.println("\nThe Date is: " + month + "/" + day + "/" + year + "  " + monthString + " " + day + ", " + year + "   " + holiday + "\n");//display the desired output in the given format
				
		}									
	}

	/********************************************************************
	 * Method: printDirections
	 * This method prints the directions for entering the data for a date.
	 */
	public static void printDirections()
	{
		System.out.println("This program will ask you for a month, day, and year\nand will print the corresponding date in two standard date formats."); //prints the directions for running the code
		System.out.print("How many times do you want to run the code: ");//ask the user how many times to run the code <---------- put this line here instead of in main() because assignment requirements
																		 //said no more than 4-5 System.out.println's in the main method.  
	}
	 	  
	
	/********************************************************************
	 * This method receives an integer representing a month and returns
	 * a String that is the name of the corresponding month.
	 */
	public static String getMonthString(int monthNum)
	{
		String temp;
		switch (monthNum) 
			{
			case 1:  temp = "January"; break;
			case 2:  temp = "February"; break;
			case 3:  temp = "March"; break;
			case 4:  temp = "April"; break;
			case 5:  temp = "May"; break;
			case 6:  temp = "June"; break;
			case 7:  temp = "July"; break;
			case 8:  temp = "August"; break;
			case 9:  temp = "September"; break;
			case 10: temp = "October"; break;
			case 11: temp = "November"; break;
			case 12: temp = "December"; break;
			default: temp = "Invalid month"; 
			}

		 return temp;
   } 
	
	/********************************************************************
	 * This method receives a String that is the name of a month and 
	 * returns the corresponding integer representing that month.
	 */
	public static int getMonthNumber(String monthStr)
	{
		int temp1=0;
		switch (monthStr) 
		{
		case "January": 	 temp1 = 1;
			break;
		case "February":	 temp1 = 2;
			break;
		case "March":  		 temp1 = 3;
			break;
		case "April":	     temp1 = 4;
			break;
		case "May":  		 temp1 = 5;
			break;
		case "June":  		 temp1 = 6;
			break;
		case "July":  	     temp1 = 7;
			break;
		case "August":  	 temp1 = 8;
			break;
		case "September":    temp1 = 9;
			break;
		case "October": 	 temp1 = 10;
			break;
		case "November": 	 temp1 = 11;
			break;
		case "December": 	 temp1 = 12;
			break;
		default:		  	 temp1 = 0;
		}	
		return temp1;
	} 
	
	
	
	/****************************************************************************
		This method takes in the month, day, and year from the user and determines if it is a holiday
	*****************************************************************************/
	public static String getHoliday(int month, int day, int year)
	{
	   String holiday ="";
	   switch (month)
	   {
	   case 1: 
		   switch (day)
	   		{
	   		case 1:  holiday = "New Year's Day"; break;
	   		case 19: holiday = "Martin Luther King Jr. Day"; break;	   		
	   		}
		   break;
	   case 2: switch (day)
	   		{
	   		case 2:  holiday = "Ground Hog Day"; break;
	   		case 12: holiday = "Abraham Lincoln's Birthday"; break;
	   		case 14: holiday = "St. Valeninte's Day"; break;
	   		case 22: holiday =  "George Washington's Birthday"; break;
	   		}
	   	   break;
	   case 3: 
		   switch (day)
	   		{
	   		case 17:  holiday =  "St. Patrick's Day"; break;
	   		}
		   break;
	   case 4:
		   switch (day)
	   		{
	   		case 1:  holiday =  "April Fool's Day"; break;
	   		case 4: holiday =   "Grandma's Birthday"; break;
	   		case 10: holiday =  "Arbor Day"; break;
	   		case 22: holiday =  "Earth Day"; break;
	   		}
		   break;
	   case 5: 
		   switch (day)
	   		{
	   		case 1:  holiday =  "May Day"; break;
	   		case 5: holiday =  "Cinco de Mayo"; break;
	   		}
		   break;
	   case 6: 
		   switch (day)
	   		{
	   		}
		   break;
	   case 7: 
		   switch (day)
	   		{
	   		case 4:  holiday =  "Independence Day"; break;
	   		}
		   break;
	   case 8: 
		   switch (day)
	   		{
	   		case 3:  holiday =  "International Friendship Day"; break;
	   		case 13: holiday =  "Columbus Day"; break;
	   		}
		   break;
	   case 9: 
		   switch (day)
	   		{
	   		}
		   break;
	   case 10:
		   switch (day)
	   		{
	   		case 31:  holiday = "Halloween"; break;
	   		}
		   break;
	   case 11:
		   switch (day)
	   		{
	   		case 11:  holiday =  "Vereran's Day"; break;
	   		}
		   break;
	   case 12: 
		   switch (day)
	   		{
	   		case 25:  holiday =  "Christmas"; break;
	   		case 31: holiday =  "New Year's Eve";  break;
	   		}
	   }

	   
	   //Logic below to append Easter to holiday if it already exists, or just set holiday to Easter if there is no other holiday there
  		if (isEaster(month, day, year)) 
  			{
  			if (holiday.length() != 0) holiday = "Easter and " + holiday;  			
  			else
  				{
  				holiday = "Easter";
  				}	
  			}
  		//Same logic as Easter, though no holiday will ever be on my birthday, so the else statement will always be what's run
  		if ( (month == 5) && (day == 24) ) 
  			if (holiday.length() != 0) holiday += " and Brandon's Birthday";
  			else
  				holiday = "Brandon's Birthday";
		
		 		
		return holiday;
	} 
	
	
	//algorithm for determining if a date is easter.  takes in dates, returns a bool of true if the date is easter, false if it is not
	public static boolean isEaster(int month, int day, int year)
	{
		 int goldenNumber, a, b, c, d, easterMonth, easterDay;
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
	} 
}


 		 