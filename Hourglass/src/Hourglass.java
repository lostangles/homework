/* Name: Brandon Byrne
 * Date: 30 September 2014
 * Simple program that prints an hourglass of size SIZE, size is number of lines the top and bottom of the hourglass will have
 */


public class Hourglass {

	public static final int SIZE=10;
	
	//main method, call each part of the hourglass to print it.  no System.out.println's in it
	public  void main(String[] args) {
		top();
		middle();
		vertical();
		bottom();
		top();		
	}
	
	public static void top() {
		System.out.print("|");
		for (int i=0;i<((SIZE * 2) + 2);i++)
			System.out.print("\"");
		System.out.println("|");
	}
	
	//build the middle part
	public static void middle() {		
		//Wrapper for number of levels of hourglass
		for (int i=0;i<SIZE;i++){
		//add the spaces per line to make it all centered and neat	
			for (int k=SIZE;k>=(SIZE-i);k--){
				System.out.print(" ");
			}
			
		//start printing what's on each line
			System.out.print("\\");		
			for (int j=0;j>(i-SIZE);j--){
				System.out.print("::");
			}			
			System.out.print("/\n");
		}
	}
	
	//Simple function, just puts in the correct spaces before the || based on constant SIZE
	public static void vertical() {
		for (int i=0;i<=SIZE;i++){
			System.out.print(" ");
		}
		System.out.print("||\n");
	}
	
	//build the bottom part, inverse logic of the middle part.  start out with max spaces and decrement number of spaces as you build lines
	public static void bottom() {
		//Wrapper for levels of the bottom
		for (int i=1;i<(SIZE + 1);i++){
			//add the spaces before each /
			for (int k=(SIZE-i);k>=0;k--){
				System.out.print(" ");
			}
			System.out.print("/");
			
			//place the correct number of :: inside the hourglass based on line number
			for (int j=SIZE;j>(SIZE-i);j--){
				System.out.print("::");
			}
			System.out.print("\\\n");
		}
	}
}
