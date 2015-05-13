import java.math.BigInteger;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class Evaluator {

	private String operators = "()+-/%*^";
	private String numbers = "0123456789";
	private String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	TreeMap<String, String> variables = new TreeMap<String, String>();
	private Deque<String> total = new LinkedList<String>();

	public Evaluator(String postfix) {

	}

	public Evaluator() {

	}

	private boolean legal(String input) {

		int countEquals = 0;
		int countSpaces = 0;
		boolean previous = false;

		for (int i = 0; i < input.length(); i++) {
			String currChar = input.charAt(i) + "";

			if (currChar.equals("=") && (countSpaces > 0) && !previous) {
				throw new IllegalArgumentException();
			}

			if (currChar.equals(" ")) {
				countSpaces++;
				previous = true;
			} else {
				previous = false;
			}

			if (currChar.contains("=")) {
				countEquals++;
			}

			// Test for illegal args

			if (operators.contains(currChar) && (countEquals == 0)
					&& input.contains("=")) {
				throw new IllegalArgumentException();
			}
			if (uppers.contains(currChar)) {
				throw new IllegalArgumentException();
			}
			if (countEquals > 1) {
				throw new IllegalArgumentException();
			}

		}

		return true;
	}

	// checks for legal arguments, if it's good then it assigns the right side
	// of = to the left side, if it's just an expression it solves it
	public String evaluate(String input) {
		String token = "";
		String tokenValue = "";
		String parsedValue = "";
		int countEquals = 0;
		String output = "";

		try {
			if (legal(input) && input.contains("=")) {
				for (int i = 0; i < input.length(); i++) {
					String currChar = input.charAt(i) + "";

					if ((!currChar.equals(" ") && !currChar.equals("="))
							&& (countEquals == 0)) {
						token = token + currChar;
					}

					if (!currChar.equals(" ") && (countEquals == 1)) {
						tokenValue = tokenValue + currChar;
					}

					if (currChar.equals("=")) {
						countEquals++;
					}
				}

				if (parse(tokenValue).charAt(0) == '-') {
					throw new IllegalArgumentException();
				} else {
					variables.put(token, parse(tokenValue));
					output = variables.get(token);
				}
			} else if (legal(input)) {
				String temp = input.charAt(0) + "";
				if (temp.equals("-")) {
					throw new IllegalArgumentException();
				}
				output = eval(parse(input));
			}
		} catch (Exception e) {
			return "Error";
		}

		return output;
	}

	// convert variables back to numbers
	private String parse(String input) {
		String parsedValue = "";
		String toVariable = "";
		input = input + "$";
		for (int i = 0; i < input.length(); i++) {
			String currChar = input.charAt(i) + "";

			if (!operators.contains(currChar)) {
				if (!currChar.equals("$") && !currChar.equals(" ")
						&& !numbers.contains(currChar)) {
					toVariable = toVariable + currChar;
				}

				if (currChar.equals("$") && variables.containsKey(toVariable)) {
					parsedValue = parsedValue + variables.get(toVariable);
					toVariable = "";
				}
				if (numbers.contains(currChar)) {
					parsedValue = parsedValue + currChar;
				}

			}
			if (operators.contains(currChar)) {
				if (variables.containsKey(toVariable)) {
					parsedValue = parsedValue + variables.get(toVariable);
					toVariable = "";
				}
				parsedValue = parsedValue + currChar;
			}
		}
		return eval(parsedValue);

	}

	private String eval(String infix1) {

		InfixToPostfix expressions = new InfixToPostfix(infix1);
		Iterator<String> itr = expressions.iterator();

		while (itr.hasNext()) {
			String currArg = itr.next();
			if (numbers.contains(currArg.charAt(0) + "")) {
				total.push(currArg);
			} else if (operators.contains(currArg.charAt(0) + "")) {
				if (currArg.equals("*")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1 = new BigInteger(total.pop());
					total.push(arg1.multiply(arg2).toString());

				}
				if (currArg.equals("/")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1 = new BigInteger(total.pop());
					total.push(arg1.divide(arg2).toString());

				}
				if (currArg.equals("+")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1 = new BigInteger(total.pop());
					total.push(arg1.add(arg2).toString());

				}
				if (currArg.equals("-")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1;
					try {
						arg1 = new BigInteger(total.pop());
					} catch (Exception e) {
						arg1 = new BigInteger("0");
					}
					total.push(arg1.subtract(arg2).toString());

				}
				if (currArg.equals("%")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1 = new BigInteger(total.pop());
					total.push(arg1.mod(arg2).toString());

				}
				if (currArg.equals("^")) {
					BigInteger arg2 = new BigInteger(total.pop());
					BigInteger arg1 = new BigInteger(total.pop());
					total.push(arg1.pow(arg2.intValue()).toString());

				}
			}
		}

		return total.pop();
	}

	public static void main(String args[]) {
		Evaluator eval = new Evaluator();
		System.out
		.println(eval.eval("((3 + 4 - 5) * 3 / 2)^2 % 4").equals("1"));

		System.out.println(eval.evaluate("velocity = -100"));
		System.out.println(eval.evaluate("velocity + 2 * velocity"));
	}

}
