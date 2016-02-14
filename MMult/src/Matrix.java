/**
 Matrix class, primarily deals with square matrixes.  Matrix is specifieid by a size (corresponding to an n x n matrix) along
 with a data array that fills in the square matrix from topleft to bottomright.
 Other functions include copying a matrix which can be specified by row/columns to copy as well as 
 merging 4 sub matrixes back into a single matrix
 */

/**
 * @author Brandon
 *
 */

public class Matrix {

	//Fields
	int r; //rows
	int c; //columns
	int data[][];// matrix data
	
	
	//Constructor for dummy matrix, defaults to size 1x1
	public Matrix () {
		r = 1;
		c = 1;
		data = new int[r][c];
		data[0][0] = 1;
	}
	
	//Matrix constructor; takes in a data array and fills it into an n x n matrix
	public Matrix (int values[], int n) {
		int tracker = 0;
		this.r = n;
		this.c = n;
		this.data = new int[r][c];
		for (int i=0 ; i<n ; i++)
			for (int j=0 ; j < n ; j++)
			{
				this.data[i][j] = values[tracker];
				tracker += 1;
			}
	}
	
	//Creates an empty matrix of n by n size
	public Matrix (int n) {
		this.r = n;
		this.c = n;
		this.data = new int[r][c];
	}
	
	//Prints contents of a matrix to console
	public void printMatrix () {
		for (int i = 0 ; i < r; i++) {
			for (int j = 0 ; j < c ; j++) {
				System.out.print(data[i][j] + " ");
				if (j == c-1) System.out.print("\n");
			}
		}
		System.out.println();
	}
	
	//Add two matrix of the same size together, returns a new matrix while leaving
	//the original two untouched.  Specify two matrix followed by their size
	public Matrix addMatrix(Matrix a, Matrix b, int n) {
		Matrix c = new Matrix(n);
		for (int i = 0 ; i< n ; i++) 
			for (int j=0 ; j< n ; j++) {
				c.data[i][j] = a.data[i][j] + b.data[i][j];
			}
		return c;
	}
	
	//Subtract two matrix of the same size together, returns a new matrix while leaving
	//the original two untouched.  Specify two matrix followed by their size
	public Matrix subMatrix(Matrix a, Matrix b, int n) {
		Matrix c = new Matrix(n);
		for (int i = 0 ; i< n ; i++) 
			for (int j=0 ; j< n ; j++) {
				c.data[i][j] = a.data[i][j] - b.data[i][j];
			}
		return c;
	}
	
	//Multiplies a 1x1 matrix; mainly used for the base case in strassen algorithm
	public Matrix multonebyone(Matrix a, Matrix b) {
		Matrix c = new Matrix(1);
		c.data[0][0] = a.data[0][0] * b.data[0][0];
		return c;
	}
	
	//Copies all or a portion of a matrix into a new matrix; mainly used to
	//Divide a square matrix into smaller matrix
	public Matrix copy(Matrix m, int start_row, int start_column) {
		Matrix c = new Matrix(m.r/2);
		for (int i = 0; i < c.r ; i++)
			for (int j = 0 ; j < c.c ; j++) {
				c.data[i][j] = m.data[i + start_row][j + start_column];
			}
		return c;
	}
	
	//Merges 4 matrix together into a single matrix
	public Matrix merge(Matrix topleft, Matrix topright, Matrix bottomleft, Matrix bottomright) {
		Matrix c = new Matrix();
		c.r = topleft.r + bottomright.r;
		c.c = topleft.c + topright.c;
		c.data = new int[c.r][c.c];
		
		for (int i=0; i<c.c ; i++)
			for (int j=0; j<c.r ; j++) {
				if ( (i < topleft.c) && (j < topleft.r )) c.data[i][j] = topleft.data[i][j];
				if ( (i < topleft.c) && (j >= topleft.r )) c.data[i][j] = topright.data[i][j-topleft.r];
				if ( (i >= topleft.c) && (j < topleft.r )) c.data[i][j] = bottomleft.data[i-topleft.r][j];
				if ( (i >= topleft.c) && (j >= topleft.r )) c.data[i][j] = bottomright.data[i-topleft.r][j-topleft.r];

			}
		
		return c;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test matrix, 8 x 8      :  [ 0 1 2 3 4 5 6 7
		//								1 2 3 4 5 6 7 8
		//								2 3 4 5 6 7 8 9
		//								3 4 5 6 7 8 9 10
		//								4 5 6 7 8 9 10 11
		//								5 6 7 8 9 10 11 12
		//								6 7 8 9 10 11 12 13
		//								7 8 9 10 11 12 13 14 
		int array[] = {0,1,2,3,4,5,6,7,1,2,3,4,5,6,7,8,2,3,4,5,6,7,8,9,3,4,5,6,7,8,9,10,4,5,6,7,8,9,10,11,5,6,7,8,9,10,11,12,6,7,8,9,10,11,12,13,7,8,9,10,11,12,13,14};
		int size = 8;
		Matrix a = new Matrix (array, size);
		System.out.println("Testing creation and print of a matrix:");
		a.printMatrix();
		
		Matrix b = new Matrix (array, size);
		Matrix c = new Matrix (size);
		size = 8;
		c = c.addMatrix(a, b, size);
		System.out.println("Testing addition of two matrix:");
		c.printMatrix();
		System.out.println("Testing subtraction of two matrix:");
		c = c.subMatrix(c, b, size);
		c.printMatrix();
		System.out.println();

		
		//Test copy
		System.out.println("Testing copy of a matrix into a smaller matrix:");
		Matrix copy = c.copy(c, 0, 4);
		copy.printMatrix();
		a = a.addMatrix(a, b, size);
		
		int n = 4;
		//Test subdivide
		Matrix A11, A12, A21, A22;
		A11 = a.copy(a, 0, 0);
		A12 = a.copy(a, 0, n);
		A21 = a.copy(a, n, 0);
		A22 = a.copy(a, n, n);
		System.out.println("Testing subdivision of a matrix into four smaller matrix:");
		a.printMatrix();
		A11.printMatrix();
		A12.printMatrix();
		A21.printMatrix();
		A22.printMatrix();
		
		a = a.merge(A11, A12, A21, A22);
		System.out.println("Testing merging of 4 smaller matrix into one larger matrix:");
		a.printMatrix();
		
	}

}
