/** The <tt>RandomWalker</tt> Creates a RandomWalker and moves it around.
 *
 *  @author Gabriel Pimentel, Brandon Byrne
 *  @version Feb 4, 2015
 */
import java.util.Random;

public class RandomWalker {

	// Fields
	private int x;
	private int y;
	private int numSteps;
	private Random rand;

	/**
	 * Creates a <tt>RandomWalker</tt> object that moves around in a Direction.
	 */
	public RandomWalker() {
		x = 0;
		y = 0;
		numSteps = 0;
		rand = new Random();
	}

	// P u b l i c M e t h o d s

	/**
	 * Moves a <tt>RandomWalker</tt> in a random direction.
	 */
	public void move() {

		int temp = rand.nextInt(4);

		switch (temp) {
		case 0:
			x += 1;
			break;
		case 1:
			y += 1;
			break;
		case 2:
			x -= 1;
			break;
		case 3:
			y -= 1;
			break;
		}
		numSteps += 1;

	}

	/**
	 * Returns the current X coord of the RandomWalker
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the current Y coord of the RandomWalker
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the number of steps of the RandomWalker
	 */
	public int getSteps() {
		return numSteps;
	}

	/** Used only for unit testing. When run, should print X and Y cords with movement and step number */
	public static void main(String[] args) {

		RandomWalker a = new RandomWalker();

		for (int i = 0; i < 20; i++) {
			System.out.println("(" + a.getX() + "," + a.getY()
					+ ") Step: " + a.getSteps());
			a.move();
		}
	}

}