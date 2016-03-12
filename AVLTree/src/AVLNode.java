
public class AVLNode {

	//Fields
	private Object data;
	private AVLNode left;
	private AVLNode right;
	private int height;

	AVLNode(Object data) {
		this.setData(data);
		this.height = 0;
	}

	AVLNode() {
		this.height = -1;
	}

	void setData(Object data) {
		this.data = data;
	}


	int findHeight(AVLNode node) {
		if (node == null) {
			return -1;
		} else
			return Math.max(findHeight(node.left), findHeight(node.right)) + 1;
	}



	AVLNode rotateLeft(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		k2.height = (Math.max(findHeight(k2.left), findHeight(k2.right)) + 1);
		k1.height = (Math.max(findHeight(k1.left), findHeight(k1.right)) + 1);

		return k1;
	}

	AVLNode rotateRight(AVLNode k1) {
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;

		k1.height = (Math.max(findHeight(k1.left), findHeight(k1.right)) + 1);
		k2.height = (Math.max(findHeight(k2.left), findHeight(k2.right)) + 1);

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
			node.left = insert(node.left, data);
			
			if (Math.abs(findHeight(node.right) - findHeight(node.left)) == 2)
				if ((node.left != null ) && (int)data < (int)node.left.data) {
				node = rotateLeft(node);
				}
			else {
				node = doubleLeft(node);
			}
		} else if ((int) data > (int) node.data) {
			node.right = insert(node.right, data);
			
			if (Math.abs(findHeight(node.left) - findHeight(node.right)) == 2)
				if ((node.right != null ) && (int)data > (int)node.right.data) {
				node = rotateRight(node);
				}
			else {
				node = doubleRight(node);
			}
		}
		else if ((int)data == (int)node.data)
			;
	
			

		node.height = (
				Math.max(findHeight(node.left), findHeight(node.right)) + 1);

		return node;
	}

	public static void main(String[] args) {
		int sampleSize = 100;
		AVLNode tree = new AVLNode(1);
		
		int[] array = new int [sampleSize];
		for (int i = 0; i < sampleSize; i++) {
			array[i] = (int)(Math.random() * sampleSize + 1 );
		}
		
		for (int i = 0;i<sampleSize;i++) {
			tree = tree.insert(tree, array[i])
		}

	}

}
