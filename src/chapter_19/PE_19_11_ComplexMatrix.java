package chapter_19;

/**
 * (ComplexMatrix) Use the Complex class introduced in Programming
 * Exercise 13.17 to develop the ComplexMatrix class for performing matrix operations
 * involving complex numbers. The ComplexMatrix class should extend the
 * GenericMatrix class and implement the add, multiple, and zero methods.
 * You need to modify GenericMatrix and replace every occurrence of Number by
 * Object, because Complex is not a subtype of Number. Write a test program that
 * creates the following two matrices and displays the result of addition and multiplication
 * of the matrices by invoking the printResult method.
 */
public class PE_19_11_ComplexMatrix {

    public static final int MATRIX_SIZE = 3;

    public static void main(String[] args) {
        Complex[][] matrix1 = new Complex[MATRIX_SIZE][MATRIX_SIZE];
        Complex[][] matrix2 = new Complex[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix1[i][j] = new Complex(i + 1, j + 5);
                matrix2[i][j] = new Complex(i + 1, j + 6);
            }
        }

        ComplexMatrix complexMatrix = new ComplexMatrix();

        System.out.println("\nmatrix1 + matrix2 is ");
        complexMatrix.printResult(matrix1, matrix2, complexMatrix.addMatrix(matrix1, matrix2), '+');

        System.out.println("\nmatrix1 * matrix2 is ");
        complexMatrix.printResult(matrix1, matrix2, complexMatrix.multiplyMatrix(matrix1, matrix2), '*');
    }

    private static class ComplexMatrix extends GenericMatrix<Complex> {
        @Override
        protected Complex add(Complex o1, Complex o2) {
            return o1.add(o2);
        }

        @Override
        protected Complex multiply(Complex o1, Complex o2) {
            return o1.multiply(o2);
        }

        @Override
        protected Complex zero() {
            return new Complex(0);
        }
    }

    private static class Complex implements Cloneable {
        double a = 0;
        double b = 0;

        public Complex() {
        }

        public Complex(double a) {
            this.a = a;
        }

        public Complex(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double abs() {
            return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        }

        public Complex add(Complex that) {
            double a = this.a + that.a;
            double b = this.b + that.b;
            return new Complex(a, b);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            double a = Math.round(this.a * 10000) / 10000.0;
            double b = Math.round(this.b * 10000) / 10000.0;
            if (b == 0) {
                return a + "";
            } else {
                return "(" + a + " + " + b + "i)";
            }
        }

        public Complex divide(Complex that) {
            double a = ((this.a * that.a) + (this.b * that.b))
                    / (Math.pow(that.a, 2) + Math.pow(that.b, 2));
            double b = ((this.b * that.a) - (this.a * that.b))
                    / (Math.pow(that.a, 2) + Math.pow(that.b, 2));
            return new Complex(a, b);
        }

        public String getImaginaryPart() {
            double b = Math.round(this.b * 10000) / 10000.0;
            return b + "i";
        }

        public double getRealPart() {
            double a = Math.round(this.a * 10000) / 10000.0;
            return a;
        }

        public Complex multiply(Complex that) {
            double a = (this.a * that.a) - (this.b * that.b);
            double b = (this.b * that.a) + (this.a * that.b);
            return new Complex(a, b);
        }

        public Complex subtract(Complex that) {
            double a = this.a - that.a;
            double b = this.b - that.b;
            return new Complex(a, b);
        }
    }

    private static abstract class GenericMatrix<E> {
        /** Add two matrices */
        public E[][] addMatrix(E[][] matrix1, E[][] matrix2) {
            // Check bounds of the two matrices
            if ((matrix1.length != matrix2.length) ||
                    (matrix1[0].length != matrix2[0].length)) {
                throw new RuntimeException(
                        "The matrices do not have the same size");
            }

            E[][] result =
                    (E[][]) new Object[matrix1.length][matrix1[0].length];

            // Perform addition
            for (int i = 0; i < result.length; i++)
                for (int j = 0; j < result[i].length; j++) {
                    result[i][j] = add(matrix1[i][j], matrix2[i][j]);
                }

            return result;
        }

        /** Multiply two matrices */
        public E[][] multiplyMatrix(E[][] matrix1, E[][] matrix2) {
            // Check bounds
            if (matrix1[0].length != matrix2.length) {
                throw new RuntimeException(
                        "The matrices do not have compatible size");
            }

            // Create result matrix
            E[][] result =
                    (E[][]) new Object[matrix1.length][matrix2[0].length];

            // Perform multiplication of two matrices
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = zero();

                    for (int k = 0; k < matrix1[0].length; k++) {
                        result[i][j] = add(result[i][j],
                                multiply(matrix1[i][k], matrix2[k][j]));
                    }
                }
            }
            return result;
        }

        /** Print matrices, the operator, and their operation result */
        public void printResult(
                Object[][] m1, Object[][] m2, Object[][] m3, char op) {
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m1[0].length; j++)
                    System.out.print(" " + m1[i][j]);

                if (i == m1.length / 2)
                    System.out.print("  " + op + "  ");
                else
                    System.out.print("     ");

                for (int j = 0; j < m2.length; j++)
                    System.out.print(" " + m2[i][j]);

                if (i == m1.length / 2)
                    System.out.print("  =  ");
                else
                    System.out.print("     ");

                for (int j = 0; j < m3.length; j++)
                    System.out.print(m3[i][j] + " ");

                System.out.println();
            }
        }

        /** Abstract method for adding two elements of the matrices */
        protected abstract E add(E o1, E o2);

        /** Abstract method for multiplying two elements of the matrices */
        protected abstract E multiply(E o1, E o2);

        /** Abstract method for defining zero for the matrix element */
        protected abstract E zero();
    }
}
