/************************************************************************
 * CSC15 -  

										 
 * Brandon Byrne
 * Tu/Th 8am
 * Due Date December 2nd
 * Records inputed number from the user into an array, then gives you the average/standard deviation								 
 *************************************************************************/  

import java.util.Scanner;
 
public class StandardDeviations
{
 static Scanner scan = new Scanner(System.in);
 public static final int  Max_Number_Scores = 100;
  
 
 //Main class
 public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
	 
    double[] scores = new double[Max_Number_Scores];
	 int count;
	 
	 //Control loop to keep prompting user if they want to run again, also helps control invalid data
	 boolean keepLoop=true;
	 while(keepLoop)
	 {
	 
	 System.out.println("You will be prompted to enter your numbers.  Enter -1 to indicate the end of your input. \n");
	 count = fillArray(scores);
	 System.out.println("You entered: " + arrayToString(scores, count));
	 System.out.println("Average = " + getAverage(scores, count));
	 System.out.println("Variance = " + getVariance(scores, count, getAverage(scores,count)));
	 System.out.println("Standard Deviation = " + getStandardDeviation(getVariance(scores, count, getAverage(scores,count)))); 
	 System.out.println("\n*******************************\n\n");
	 System.out.println("Do you have another set of numbers?");
     String answer = scan.next();
     if (answer.equalsIgnoreCase("no"))
         keepLoop = false;
	 	System.out.println("Goodbye");
	 }
	 
  
  }
  
  
  
  //Takes the array and adds each individual double into a string, takes in a double array and size of array, outputs it into a String
   public static String arrayToString(double[] scores, int count)
	{
	  String arrayString = "";
	  for (int i=0;i<count;i++)
	  {
		  arrayString += scores[i] + " ";
	  }
	  return arrayString;
	}
   
//Takes the double array and fills it with user input, only takes doubles.  Return type is size of array		
	public static int  fillArray(double[] scores)
	{
		int arraySize = 0;
		System.out.print("Enter number 1 ");
	  while (true)
	  {
		  while (!scan.hasNextDouble())
          {
             System.out.print("Enter number "+ (arraySize + 1) + " ");
             scan.next();
          }
		  System.out.print("Enter number " + (arraySize + 2) + " ");
		  
		  scores[arraySize] = scan.nextDouble();
		  if (scores[arraySize] < 0) break;
		  arraySize += 1;
	  }
	   return arraySize;
	}
	
//Gets the average of all values in the array, outputs it as a double
	public static double getAverage(double[] scores, int  count)
	{
		double average = 0;
	   for (int i=0;i<count;i++)
	   {
		   average += scores[i];
	   }
	   return (average / count);
	   
	 }	
	
//Takes in the double array, the size of the array, and the average of the array, and outputs the variance as a double	
	 public static double getVariance(double[] scores, int count, double average)
	 {
		 double variance = 0;
			for (int i=0;i<count;i++)
			{
				variance += ( Math.pow( (scores[i] - average), 2) );
			}
			variance = variance / count;
	    		return variance;
	}
	 
//Returns the standard deviation after taking in the variance	 
	public static double getStandardDeviation(double variance)
	{
		
		return Math.sqrt(variance);
	}	  	
}