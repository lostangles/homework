import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Evaluator eval = new Evaluator();
		String result;
		
		System.out.print("Welcome to BigDigit2000\n>");
		while(in.hasNext()) {
		result = eval.evaluate(in.nextLine());
		System.out.println(result);
		System.out.print(">");

		}
	}

}
