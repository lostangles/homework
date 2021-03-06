/**
 * The <tt>BigNum</tt> class represents any length non-negative integer, and
 * supports basic operations on them. <tt>BigNum</tt> objects are immutable.
 *
 * @author Ted Krovetz, Brandon Byrne
 * @version Feb 6, 2015
 */

public class BigNum {

	// F i e l d s

	private String num; // Representation of our number
	private boolean isNegative = false;
	// C o n s t r u c t o r s

	/**
	 * Constructs a <tt>BigNum</tt> from a non-empty String of digits.
	 *
	 * @param num
	 *            The string to be interpreted.
	 * @throws IllegalArgumentException
	 *             if num is length zero or has any non-numeric digits
	 * @throws NullPointerException
	 *             if num is null
	 */
	public BigNum(String num) {
		for (int i = 0; i < num.length(); i++) {
			if (!Character.isDigit(num.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		if (num.length() == 0) {
			throw new IllegalArgumentException();
		}
		this.num = num;
	}

	/**
	 * Constructs a <tt>BigNum</tt> from a non-negative integer.
	 *
	 * @param num
	 *            The non-negative integer to be interpreted.
	 * @throws IllegalArgumentException
	 *             if num is negative
	 */
	public BigNum(int num) {
		// If num<0, redirected constructor will throw exception due to "-"
		this("" + num);
	}

	/**
	 * Constructs a <tt>BigNum</tt> with value zero.
	 */
	public BigNum() {
		num = "0";
	}

	// P u b l i c M e t h o d s

	/**
	 * Adds two <tt>BigNum</tt> objects' values together and returns a new
	 * <tt>BigNum</tt> object with the resulting value.
	 *
	 * @param other
	 *            this and other objects get added together
	 * @return a new BigNum with the resulting value
	 */
	public BigNum add(BigNum other) {
		// Make shorter refer to the shorter num, longer to the longer num
		String shorter = other.num;
		String longer = this.num;
		if (this.num.length() < other.num.length()) {
			shorter = this.num;
			longer = other.num;
		}
		// Prepend zeros to make shorter as long as longer
		while (shorter.length() < longer.length()) {
			shorter = "0" + shorter;
		}
		// Add columns like we did in grade school
		int carry = 0;
		String result = "";
		for (int i = shorter.length() - 1; i >= 0; i--) {
			final int temp = Character.getNumericValue(shorter.charAt(i))
					+ Character.getNumericValue(longer.charAt(i)) + carry;
			result = (temp % 10) + result;
			carry = temp / 10;
		}
		// Handle carry-out, if there is one. Return result
		if (carry == 1) {
			result = "1" + result;
		}
		return new BigNum(result);
	}

	/**
	 * Subtracts two <tt>BigNum</tt> objects' values together and returns a new
	 * <tt>BigNum</tt> object with the resulting value.
	 * TODO: Subtract gets iffy because it has the potential to deal with negative 
	 * numbers as a result.  Current iteration of BigNum does not take into account 
	 * sign.  Currently if a - b where b is bigger than a, it will spit out the result
	 * with a negative sign.  This is purely for visualization and is done in toString,
	 * the - sign does not do anything for the value.  ie:  BigNum a = 5 - 10 
	 * will toString and return-5, but any future action on this BigNum will still 
	 * treat it as just a 5. 
	 *
	 * @param other
	 *            this and other objects get added together
	 * @return a new BigNum with the resulting value
	 */
	public BigNum sub(BigNum other) {
		// Make shorter refer to the shorter num, longer to the longer num
		String shorter = other.num;
		String longer = this.num;
		boolean isNeg = false;
		if (this.less(other)) {
			shorter = this.num;
			longer = other.num;
			isNeg = true;
		}
		// Prepend zeros to make shorter as long as longer
		while (shorter.length() < longer.length()) {
			shorter = "0" + shorter;
		}
		// Add columns like we did in grade school
		int borrow = 0;
		String result = "";
		for (int i = shorter.length() - 1; i >= 0; i--) {
			int temp=0;
			int tempLonger = Character.getNumericValue(longer.charAt(i));
			if (borrow == 1) tempLonger -= 1;
			int tempShorter = Character.getNumericValue(shorter.charAt(i));
			if (  ( Character.getNumericValue(longer.charAt(i)) <  Character.getNumericValue(shorter.charAt(i)) ) || (tempLonger < 0 ) )
			{
				temp = (tempLonger +10) - tempShorter;		
				borrow = 1;				
			}
			else
			{
			borrow = 0;
			temp = tempLonger - tempShorter;
			}
			result = (temp) + result;
		}
		BigNum res = new BigNum(result);
		res.isNegative = true;
		return res;
	}
	/**
	 * Multiply two <tt>BigNum</tt> objects' values together and returns a new
	 * <tt>BigNum</tt> object with the resulting value. Some code borrowed from
	 * add method.
	 *
	 * @param other
	 *            this and other objects get multiplied together
	 * @return a BigNum with the resulting value
	 */
	public BigNum mult(BigNum other) {
		// Make shorter refer to the shorter num, longer to the longer num
		String shorter = other.num;
		String longer = this.num;
		BigNum total = new BigNum();

		if (this.num.length() < other.num.length()) {
			shorter = this.num;
			longer = other.num;
		}

		// Multiply each term of shorter individually against the longer, add
		// zeroes every time
		// We switch numbers in the smaller. Add partial terms with zeroes to
		// BigNum total
		int carry = 0;
		int zeroes = 0;
		String result = "";

		for (int i = shorter.length() - 1; i >= 0; i--) {
			result = "";
			carry = 0;


			//appends zeroes for every digit of the shorter number that we are multiplying.  
			//TODO: Probably a better/more efficient way to do this.			
			for (int k = 0; k < zeroes; k++) {
				result += "0";
			}

			for (int j = longer.length() - 1; j >= 0; j--) {
				final int temp = (Character.getNumericValue(shorter.charAt(i)) * Character
						.getNumericValue(longer.charAt(j))) + carry;
				result = (temp % 10) + result;
				carry = temp / 10;
			}
			if (carry >= 1) {
				result = carry + result;
			}
			zeroes++;
			total = total.add(new BigNum(result));
		}
		return total;
	}

	/**
	 * Returns a string representation of the number. No leading zeros will
	 * exist unless the overall value is zero.
	 *
	 * @return String representation of the BigNum
	 */
	@Override
	public String toString() {
		//take care of negative sign
		num = num.replaceFirst("^0+(?!$)", "");
		if (isNegative ) {
			num = "-" + num;
		}
		return num;
	}

	/**
	 * Compare two <tt>BigNum</tt> objects' values together and returns a
	 * boolean if obj is less than compared to.
	 *
	 * @param other
	 *            this and other objects get compared
	 * @return a boolean with the resulting value
	 */
	public boolean less(BigNum other) {
		boolean isLess = false;
		// Skip potentially long for loop if the number is simply a power of 10
		// or more larger.
		if (    (this.num.length() < other.num.length()) || ( this.isNegative == true && other.isNegative == false)  ) {
			isLess = true;
		} else if (this.num.length() == other.num.length()) {
			// Loop through comparing values from MSB to LSB, if any isLess is
			// true
			for (int i = 0; i <= (this.num.length() - 1); i++) {
				if (this.num.length() < other.num.length()) {
					isLess = true;
					i = this.num.length(); // Break the for loop since no reason
											// to continue
				}
			}
		}
		return isLess;
	}

	/** Used only for unit testing. When run, should output only trues. */
	public static void main(String[] args) {
		// Test constructors
		BigNum test = new BigNum("123");
		System.out.println(test.toString().equals("123"));
		test = new BigNum(123);
		System.out.println(test.toString().equals("123"));
		test = new BigNum();
		System.out.println(test.toString().equals("0"));
		// Test addition
		BigNum a = new BigNum();
		BigNum b = new BigNum();
		BigNum c = a.add(b);
		System.out.println(c.toString().equals("0"));
		a = new BigNum("5");
		b = new BigNum("0");
		c = a.add(b);
		System.out.println(c.toString().equals("5"));
		a = new BigNum("237468273643278");
		b = new BigNum("87326487236437826");
		c = a.add(b);
		System.out.println(c.toString().equals("87563955510081104"));
		a = new BigNum("237468273643278");
		b = new BigNum("87326487236437826");
		c = a.mult(b);
		System.out.println(c.toString().equals(
				"20737270167368641268575749833628"));
		System.out.println(a.less(b));
		b = new BigNum("0");
		System.out.println(!a.less(b));
		a = new BigNum("100");
		b = new BigNum("1000");
		c = a.sub(b);
		System.out.println(c);
	}

}