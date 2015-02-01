/* Name: Brandon Byrne
 * Date: 16 October 2014
 * Simple program that uses Scanner for user input to create a story
 */

import java.util.Scanner;

public class Story {

	static String website;
	static String name; 
	static String month; 
	static String favClass; 
	static String dayOfWeek; 
	static int age; 
	static float priceOfGas; 
	static String verb;
	static String noun; 
	static String encryptedWord;
	static int numTimes=2;
	
	//Main method, queues the input inside a for loop, spits back out a story, repeats it for however many times was chosen
	public static void main(String[] args) {
		runTime();
		for (int i=0;i<numTimes;i++) 
		{
			getInput();
			System.out.println("Hello " + name + ", don't you wish you were at " + noun + "?");
			System.out.println("But instead you are in " + favClass + ".  I see that you are " + age + " years old.");
			System.out.println("Last month, " + month + ", gas was $" + priceOfGas + ".");
			System.out.println("Hopefully next " + dayOfWeek + " it will be the same and not more expensive.");
			System.out.println("Let's " + verb + " on over to your favorite website " + parseWebsite(website) + " and see what's new.");
			System.out.println("Also, your encrypted sentence says: " + decrypt(encryptedWord));
		}
	}
//get the number of times you want to run the program
	private static void runTime() {
		Scanner ns = new Scanner(System.in);
		System.out.println("Enter the number of times you want to run this program: ");
		numTimes = ns.nextInt();
	}
	
//asks a bunch of input from the user and fills in the variables via a scanner object	
	private static void getInput() {
		
		Scanner getstuff = new Scanner(System.in);
		System.out.println("What is your favorite website?");
		website = getstuff.nextLine();
		System.out.println("What is your first name?");
		name = getstuff.nextLine();
		System.out.println("What month is it?");
		month = getstuff.nextLine();
		System.out.println("What is your first class of the week?");
		favClass = getstuff.nextLine();
		System.out.println("What day of the week is it?");
		dayOfWeek = getstuff.nextLine();
		System.out.println("How old are you?");
		age = getstuff.nextInt();
		getstuff.nextLine();
		System.out.println("What is the current price of gas?");
		priceOfGas = getstuff.nextFloat();
		getstuff.nextLine();		
		System.out.println("What is your favorite verb?");
		verb = getstuff.nextLine();
		System.out.println("What is your favorite noun?");
		noun = getstuff.nextLine(); 
		System.out.println("Enter your encrypted sentence: ");
		encryptedWord = getstuff.nextLine();
				
	}	

//takes in an encrypted string, decrypts it so that every even character is returned as a string format	
	private static String decrypt(String word){
		int sentLength = word.length();
		String temp="";
		for (int i=0;i<sentLength;i++)
		{
			if (i % 2 == 0)
			{
				temp += word.charAt(i);
			}
		}
		return temp;
	}
	
//parses a website to remove the www. and .com, only valid input is a website that starts with www. or http://www.  format of facebook.com
//will not work
	private static String parseWebsite(String website) {
		int sentLength = website.length();
		String temp="";
		boolean start = false;
		for (int i=0;i<sentLength;i++)
		{
			if (website.charAt(i) == 'w' && website.charAt(i+1) == 'w' && website.charAt(i+2) == 'w' && website.charAt(i+3) == '.')
			{
				i += 4;
				start = true;
			}
			if (website.charAt(i) == '.')
			{
				start = false;
			}
			if (start == true)
			{
				temp += website.charAt(i);
			}
		}
		return temp;
	}	
}