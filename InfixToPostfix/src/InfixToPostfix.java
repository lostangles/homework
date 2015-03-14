import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class documentation goes here. Write more than you did last time.
 */
public class InfixToPostfix implements Iterable<String> {

	private final Deque<String> postfix = new LinkedList<String>(); // Used as a queue of String
	private final Deque<String> opStack = new LinkedList<String>(); // Used as an operator stack
	private final String operators = "()+-/%*^";
	private final String validArgs = "()+-/%*^0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";


	public InfixToPostfix(String infix) {
			testArgs(infix);
			parse(infix);
	}

	private void testArgs(String infix)
		{
			int numOpen = 0;
			int numClose = 0;
			for (final char test : infix.toCharArray()) {
				if (test == '(')
					numOpen++;
				if (test == ')')
					numClose++;
				if (!validArgs.contains(test + ""))
					throw new IllegalArgumentException();
			}
			if (numOpen != numClose) {
				throw new IllegalArgumentException();
			}
		}

	/**
	 * Mehtod documentation goes here
	 */
	@Override
	public Iterator<String> iterator()
	{
			return new PostfixIterator(postfix);
	}

	private void parse(String infix)
		{
			String token = "";

			for (int i = 0; i < infix.length(); i++) {
				final String currChar = infix.charAt(i) + "";

				if (!operators.contains(currChar)) {			//If number, put it in the postfix stack
					if (!currChar.equals(" "))
						token = token + currChar;
				}

				else if (operators.contains(currChar)) {
					if (!(token.length() == 0)) 			//If token is not empty, drop the number off at postfix
					{									//Reset token for next number
						postfix.addLast(token);
						token = "";
					}

					if (currChar.equals("("))
						opStack.addFirst(currChar);
					if (currChar.equals("*") || currChar.equals("/")) {
						if (!(opStack.isEmpty()))
							while (opStack.peek().equals("*")
									|| opStack.peek().equals("/")) {
								postfix.addLast(opStack.pop());
							}
						opStack.addFirst(currChar);
					}
					if (currChar.equals("+") || currChar.equals("-")) {
						if (!(opStack.isEmpty()))
							while (opStack.peek().equals("+")
									|| opStack.peek().equals("-")
									|| opStack.peek().equals("*")
									|| opStack.peek().equals("/")) {
								postfix.addLast(opStack.pop());
							}
						opStack.addFirst(currChar);
					}
					if (currChar.equals("^"))
						opStack.addFirst(currChar);

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

	public static void main(String[] args)
		{
			// Simple embedded unit-test for InfixToPostfix
			final InfixToPostfix test = new InfixToPostfix(
					"3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3");


			//Test Iterator
			String test2 = "";
			for (final String str : test) {
				test2 = test2 + str + " ";
			}
			System.out.println(test2.equals("3 4 2 * 1 5 - 2 3 ^ ^ / + "));

			try {
				final InfixToPostfix test3 = new InfixToPostfix("$%#(&$*(@");
			} catch (final IllegalArgumentException e) {
				System.out
						.println("true - reject invalid args - IllegalArgumentException thrown");
			} catch (final Exception e) {
				System.out.println("false - Wrong exception type thrown");
			}

			try {
				final InfixToPostfix test3 = new InfixToPostfix("(4+5+3))");
			} catch (final IllegalArgumentException e) {
				System.out
						.println("true - # of () mismatch - IllegalArgumentException thrown");
			} catch (final Exception e) {
				System.out.println("false - Wrong exception type thrown");
			}
		}

}

// PostfixIterator implements Iterator<String>, so is an acceptable type
// whenever Iterator<String> is specified as the type (eg, iterator() above).
class PostfixIterator implements Iterator<String> {

	private final Deque<String> postfix;

	public PostfixIterator(Deque<String> postfix) {
			this.postfix = postfix;
	}

	@Override
	public boolean hasNext()
		{
			return !this.postfix.isEmpty();
		}

	@Override
	public String next()
		{
			return this.postfix.pop();
		}

}