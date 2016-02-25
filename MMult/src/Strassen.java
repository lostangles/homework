//CSC130 - Assignment 1
//Brandon Byrne
//February 19 - 2016
//Implements matrix multiplication using Strassen's algorithm.

public class Strassen {

	public static Matrix Strassen(Matrix a, Matrix b, int size) {
		int n = size/2;
		Matrix c = new Matrix();
		
		if (size == 1) {
			c = c.multonebyone(a, b);
			return c;
		}
		
		Matrix A11, A12, A21, A22;
		Matrix B11, B12, B21, B22;
		Matrix C11, C12, C21, C22;
		Matrix P1, P2, P3, P4, P5, P6, P7;
		
		//Subdivide matrixes
		A11 = a.copy(a, 0, 0);
		A12 = a.copy(a, 0, n);
		A21 = a.copy(a, n, 0);
		A22 = a.copy(a, n, n);
		
		B11 = b.copy(b, 0, 0);
		B12 = b.copy(b, 0, n);
		B21 = b.copy(b, n, 0);
		B22 = b.copy(b, n, n);
		
		P1 = Strassen(A11, B12.subMatrix(B12, B22, n), n);
		P2 = Strassen(A11.addMatrix(A11, A12, n), B22, n);
		P3 = Strassen(A21.addMatrix(A21, A22, n), B11, n);
		P4 = Strassen(A22, B21.subMatrix(B21, B11, n), n);
		P5 = Strassen(A11.addMatrix(A11, A22, n), B11.addMatrix(B11, B22, n), n);
		P6 = Strassen(A12.subMatrix(A12, A22, n), B21.addMatrix(B21, B22, n), n);
		P7 = Strassen(A11.subMatrix(A11, A21, n), B11.addMatrix(B11, B12, n), n);
		C11 = P5.addMatrix(P5, P4, n);
		C11 = C11.subMatrix(C11, P2, n);
		C11 = C11.addMatrix(C11, P6, n);
		C12 = P1.addMatrix(P1, P2, n);
		C21 = P3.addMatrix(P3, P4, n);
		C22 = P1.addMatrix(P1, P5, n);
		C22 = C22.subMatrix(C22, P3, n);
		C22 = C22.subMatrix(C22, P7, n);
		
		c = c.merge(C11, C12, C21, C22);
		
		return c;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 1 , 2 , 3, 4,2,3,4,5,3,4,5,6,4,5,6,7 };
		int size = 4;
		Matrix a = new Matrix(array, size);
		Matrix b = new Matrix(array, size);
		System.out.println("Matrix a:");
		a.printMatrix();
		System.out.println("Matrix b:");
		b.printMatrix();
		
		Matrix c = Strassen(a,b,4);
		System.out.println("Matrix a x b using strassen algorithm:");
		c.printMatrix();
	}

}
