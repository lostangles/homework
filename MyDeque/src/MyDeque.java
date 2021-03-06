import java.util.NoSuchElementException;

/**
 * A <tt>MyDeque</tt> is a list of items with restrictions on how it can be manipulated.
 * Items can be added to either end of the list but can only be removed from
 * one of the ends. The end where removal can happen is "the front" and
 * the item there "first". We'll call the other end "the back" and the item
 * there "last". The method getFirst returns, but does not remove, the first
 * item on the list. If getFirst or removeFirst is invoked on an empty MyDeque,
 * then a NoSuchElementException is thrown.
 *
 * @author Brandon Byrne
 * @version Feb 22, 2015
 */


public class MyDeque<E> implements IDeque<E> {

	// F I E L D S
	Node<E> head;
	Node<E> tail;
	private int size;

	// C O N S T R U C T O R

	/**
	 * Constructs a <tt>MyDeque</tt> of specified type. 
	 * Takes no parameters but does construct a dummy Node of 
	 * specified type in order to assign a tail and head 	 
	 */
	public MyDeque() {
		Node<E> dummy = new Node<E>();
		this.tail = dummy;
		this.head = this.tail;
		size = 0;
	}

	/**
	 * Adds a <tt>Node</tt> objects to the front of MyDeque
	 *
	 * @param item
	 *            item that the Node represents
	 */
	public void addFirst(E item) {
		Node<E> oldHead = head;
		if (oldHead == null)
			oldHead = new Node<E>();
		head = new Node<E>(item);
		oldHead.addLeft(head);
		if (size == 0) {
			this.tail = head;
		}
		size++;
	}

	/**
	 * Adds a <tt>Node</tt> objects to the back of MyDeque
	 *
	 * @param item
	 *            item that the Node represents
	 */
	public void addLast(E item) {
		Node<E> oldTail = tail;
		tail = new Node<E>(item);
		oldTail.addRight(tail);
		if (size == 0) {
			this.head = tail;
		}
		size++;
	}
	
	/**
	 * Removes the first <tt>Node</tt> object from MyDeque
	 * Throws a NoSuchElementException if the deque is empty
	 * also returns value of removed Node
	 * @return the value of the <tt>Node</tt> that was removed
	 */
	public E removeFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<E> tempHead = head;
		head = head.right();
		size--;
		return tempHead.get();
	}

	/**
	 * Evaluates <tt>MyDeque</tt> object to see if it's empty, returns true if it is
	 *
	 * @return true if <tt>MyDeque</tt> is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**Returns the value at the front of the deque
	 * @return the value of the <tt>Node</tt> at the Front
	 * Throws a NoSuchElementException if the deque is empty
	 */
	public E getFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.get();
	}


	/** Method was used to unit test, commented out since it's not part of IDeque
	public E getLast() {
		return tail.get();
	}
	*/
			
	// U n i t   t e s t s
	public static void main(String[] args) {
		MyDeque<Integer> deck = new MyDeque<Integer>();
		System.out.println(deck.isEmpty());
		deck.addFirst(1);
		System.out.println(!deck.isEmpty());
		System.out.println(deck.getFirst() == 1);
		deck.addFirst(5);
		deck.addFirst(8);
		deck.addFirst(2);
		System.out.println(deck.getFirst() == 2);
		//getLast() was used for unit test, not part of IDeque		System.out.println(deck.getLast() == 1);
		deck.addLast(9);
		//getLast() was used for unit test, not part of IDeque		System.out.println(deck.getLast() == 9);
//		for (int i = 0; i < 5; i++) {
	//		System.out.println(deck.removeFirst());
	//	}
	}
}

/**
 * Helper class for MyDeque.  Nodes know their own value
 * And where the Node to its right and left are located
 *
 * @author Brandon Byrne
 * @version Feb 22, 2015
 */
class Node<E> {

	private Node<E> left;
	private Node<E> right;
	private E item;
	
	//Empty constructor so we can make dummy nodes.
	Node () {
		
	}
	
	Node (E item) {
		this.item = item;		
	}
	
	public String toString() {
		return this.item + "";
	}
	
	E get() {
		return item;
	}
	
	Node<E> right () {
		return right;
	}
	
	Node<E> left () {
		return left;
	}
    void addLeft (Node<E> node) {
		this.left = node;
		node.right = this;
	}
	
	void addRight (Node<E> node) {
		this.right = node;
		node.left = this;
	}

	
}
