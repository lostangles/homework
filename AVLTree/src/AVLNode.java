
public class AVLNode {

	//Fields
	private Object data;
	private AVLNode left;
	private AVLNode right;
	private int height;

	AVLNode(Object data) {
		this.setData(data);
		this.setHeight(0);
	}

	AVLNode() {
		this.setHeight(-1);
	}

	void setData(Object data) {
		this.data = data;
	}

	void setLeft(AVLNode setleft) {
		this.left = setleft;
	}

	void setRight(AVLNode setright) {
		this.right = setright;
	}

	int findHeight(AVLNode node) {
		if (node == null) {
			return -1;
		} else
			return Math.max(findHeight(node.left), findHeight(node.right)) + 1;
	}

	void setHeight(int height) {
		this.height = height;
	}

	AVLNode rotateLeft(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		k2.setHeight(Math.max(findHeight(k2.left), findHeight(k2.right)) + 1);
		k1.setHeight(Math.max(findHeight(k1.left), findHeight(k1.right)) + 1);

		return k1;
	}

	AVLNode rotateRight(AVLNode k1) {
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;

		k1.setHeight(Math.max(findHeight(k1.left), findHeight(k1.right)) + 1);
		k2.setHeight(Math.max(findHeight(k2.left), findHeight(k2.right)) + 1);

		return k2;

	}

	AVLNode doubleLeft(AVLNode k3) {
		k3.left = rotateRight(k3.left);
		return rotateLeft(k3);
	}

	AVLNode doubleRight(AVLNode k3) {
		k3.right = rotateLeft(k3.right);
		return rotateRight(k3);
	}

	AVLNode insert(AVLNode node, Object data) {
		//base case
		if (node == null) {
			return (new AVLNode(data));
		}

		else if ((int) data < (int) node.data) {
			node.setLeft(insert(node.left, data));
			node.setHeight(
					Math.max(findHeight(node.left), findHeight(node.right)) + 1);
			if (Math.abs(findHeight(node.right) - findHeight(node.left)) == 2)
				if ((node.left != null ) && (int)data < (int)node.left.data) {
				node = rotateLeft(node);
				}
			else {
				node = doubleLeft(node);
			}
		} else {
			node.setRight(insert(node.right, data));
			node.setHeight(
					Math.max(findHeight(node.left), findHeight(node.right)) + 1);
			if (Math.abs(findHeight(node.left) - findHeight(node.right)) == 2)
				if ((node.right != null ) && (int)data > (int)node.right.data) {
				node = rotateRight(node);
				}
			else {
				node = doubleRight(node);
			}
		}

		

		return node;
	}

	public static void main(String[] args) {

		AVLNode tree = new AVLNode(6);
		tree = tree.insert(tree, 2);
		tree = tree.insert(tree, 8);
		tree = tree.insert(tree, 14);
		tree = tree.insert(tree, 4);
		tree = tree.insert(tree, 7);
		tree = tree.insert(tree, 3);
		tree = tree.insert(tree, 9);
		tree = tree.insert(tree, 10);
		tree = tree.insert(tree, 11);
		tree = tree.insert(tree, 12);
		tree = tree.insert(tree, 13);
		tree = tree.insert(tree, 14);
		tree = tree.insert(tree, 15);
		tree = tree.insert(tree, 16);

	}

}
