import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <tt>InfixToPostfix</tt> is a class that parses a standard mathematical
 * argument into a "post fix" state that is much easier to work with for
 * computation. This class implements the Shunting yard algorithm (
 * http://en.wikipedia.org/wiki/Shunting-yard_algorithm ) Class usage consists
 * of initializing a InfixToPostfix object with the mathematical equation as the
 * argument
 *
 * @author Brandon Byrne
 * @version Mar 14, 2015
 */
public class InfixToPostfix implements Iterable<String> {

	private Deque<String> postfix = new LinkedList<String>(); // Used as a queue
	// of String
	private Deque<String> opStack = new LinkedList<String>(); // Used as an
	// operator
	// stack
	private String operators = "()+-/%*^";
	private String validArgs = "()+-/%*^0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

	/**
	 * Constructs a <tt>InfixToPostfix</tt> object. Mathematical argument must
	 * be included upon object initialization. Equation in the form of: new
	 * InfixToPostfix("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"); Valid arguments consist
	 * of: 0-9, A-z, ()+-/%*^ operators. If () are mismatched exception will be
	 * thrown.
	 */
	public InfixToPostfix(String infix) {
		testArgs(infix);
		parse(infix);
	}

	private void testArgs(String infix) {
		int numOpen = 0;
		int numClose = 0;
		for (char test : infix.toCharArray()) {
			if (test == '(') {
				numOpen++;
			}
			if (test == ')') {
				numClose++;
			}
			if (!validArgs.contains(test + "")) {
				throw new IllegalArgumentException();
			}
		}
		if (numOpen != numClose) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Allows InfixToPostfix to be iteratable. Creating an iterator will allow
	 * you to cycle through all the Strings inside the postfix queue.
	 */
	@Override
	public Iterator<String> iterator() {
		Deque<String> copy = new LinkedList(postfix);

		return new PostfixIterator(copy);
	}

	private void parse(String infix) {
		String token = "";
		infix = "(" + infix + ")"; // cheap hack that helps/fixes that pesky null pointer when
								   // popping opstack at the end of the parse
		for (int i = 0; i < infix.length(); i++) {
			String currChar = infix.charAt(i) + "";

			if (!operators.contains(currChar)) { // If number, put it in the
												// postfix stack
				if (!currChar.equals(" ")) {
					token = token + currChar;
				}
			}

			else if (operators.contains(currChar)) {
				if (!(token.length() == 0)) { // If token is not empty, drop the
												// number off at postfix
												// Reset token for next number
					if (opStack.peek() == "^") {
						postfix.addLast(opStack.pop());
					}
					postfix.addLast(token);
					token = "";
				}

				if (currChar.equals("(")) {
					opStack.addFirst(currChar);
				}
				if (currChar.equals("*") || currChar.equals("/")
						|| currChar.equals("%")) {
					if (!(opStack.isEmpty())) {
						while (opStack.peek().equals("*")
								|| opStack.peek().equals("/")
								|| opStack.peek().equals("%")
								|| opStack.peek().equals("^")) {

							if (opStack.peek() == "^") {
								postfix.addLast(opStack.pop());
							} else {
								postfix.addLast(opStack.pop());
							}
						}
					}
					opStack.addFirst(currChar);
				}
				if (currChar.equals("+") || currChar.equals("-")) {
					if (!(opStack.isEmpty())) {
						if (opStack.peek() == "^") {
							postfix.addLast(opStack.pop());
						}
					}
					while (opStack.peek().equals("+")
							|| opStack.peek().equals("-")
							|| opStack.peek().equals("*")
							|| opStack.peek().equals("/")
							|| opStack.peek().equals("%")
							|| opStack.peek().equals("^")) {

						if (opStack.peek() == "^") {
							postfix.addLast(opStack.pop());
						} else {
							postfix.addLast(opStack.pop());
						}
					}
					opStack.addFirst(currChar);
				}
				if (currChar.equals("^")) {
					opStack.addFirst(currChar);
				}
			}
			if (currChar.equals(")")) {
				if (!opStack.isEmpty()) {
					while (!(opStack.peek().equals("("))) {
						postfix.addLast(opStack.pop());
					}
					opStack.remove();
				}
			}
		}

		if (!token.isEmpty()) {
			postfix.addLast(token);
		}
		while (!opStack.isEmpty()) {
			postfix.addLast(opStack.pop());
		}

	}

	// Embedded unit test
	public static void main(String[] args) {
		// Simple embedded unit-test for InfixToPostfix
		InfixToPostfix test = new InfixToPostfix(
				"3 + 433 * 2 / ( 1 - 5 ) ^ 2 ^ 3");

		// Test Iterator
		String test2 = "";
		for (String str : test) {
			test2 = test2 + str + " ";
		}
		System.out.println(test2.equals("3 433 2 * 1 5 - 2 3 ^ ^ / + "));

		// Test handling of illegal arguments
		try {
			InfixToPostfix test3 = new InfixToPostfix("$%#(&$*(@");
		} catch (IllegalArgumentException e) {
			System.out
					.println("true - reject invalid args - IllegalArgumentException thrown");
		} catch (Exception e) {
			System.out.println("false - Wrong exception type thrown");
		}

		// Test argument handling of mismatched ()'s
		try {
			InfixToPostfix test3 = new InfixToPostfix("(4+5+3))");
		} catch (IllegalArgumentException e) {
			System.out
					.println("true - # of () mismatch - IllegalArgumentException thrown");
		} catch (Exception e) {
			System.out.println("false - Wrong exception type thrown");
		}
		InfixToPostfix test5 = new InfixToPostfix("(32/2+(1+(1+(1-2))/4))");
		// Test Iterator
		String myStr = "";
		for (String str : test5) {
			myStr = myStr + str + " ";
		}
		System.out.println(myStr);
	}

}

// PostfixIterator implements Iterator<String>, so is an acceptable type
// whenever Iterator<String> is specified as the type (eg, iterator() above).
class PostfixIterator implements Iterator<String> {

	private Deque<String> postfix;

	public PostfixIterator(Deque<String> postfix) {
		this.postfix = postfix;
	}

	@Override
	public boolean hasNext() {
		return !this.postfix.isEmpty();
	}

	@Override
	public void remove() {
		// postfix.pop();
	}

	@Override
	public String next() {
		return this.postfix.pop();
	}

}