package render3d.geometry.math;

import java.util.Arrays;

public class Matrix {

    private double[][] matrix;

    // constructor

    /**
     * creates an identity matrix with rows and cols i
     * @param i size
     */
    public Matrix(int i) {
        this(i, i);
        while(i > 0) {
            i--;
            this.set(i, i, 1);
        }
    }

    /**
     * creates a matrix of 0s of size rows and cols
     * @param rows rows
     * @param cols cols
     */
    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
    }


    // accessor methods

    /**
     * sets the value at row, col to val
     * @param row row
     * @param col col
     * @param val value
     */
    public void set(int row, int col, double val) {
        matrix[row][col] = val;
    }

    /**
     * return the value at row, col
     * @param row row
     * @param col col
     * @return value
     */
    public double get(int row, int col) {
        return matrix[row][col];
    }

    /**
     * returns the width / number of columns in the matrix
     * @return width
     */
    public int getCols() {
        return matrix[0].length;
    }

    /**
     * returns the height / number of rows in the matrix
     * @return height
     */
    public int getRows() {
        return matrix.length;
    }

    /**
     * calculates the sum of all values on the major diagonal
     * @return trace
     */
    public double getTrace() {
        if(this.getRows() != this.getCols()) throw new RuntimeException("cannot calculate trace on non-square matrix");
        double sum = 0;
        for(int i = 0; i < this.getRows(); i++) {
            sum += this.get(i, i);
        }
        return sum;
    }

    /**
     * sets all values in the matrix to the double 2d array
     * @param d new values
     */
    public void set2dArray(double[][] d) {
        if(d.length != this.getRows()) throw new RuntimeException("array not of same size of matrix");
        for(double[] row : d) {
            if(d.length != this.getCols()) throw new RuntimeException("array not of same size of matrix");
        }
        for(int row = 0; row < this.getRows(); row++) {
            matrix[row] = d[row].clone();
        }
    }

    /**
     * represents the matrix as a 2d double array
     * @return 2d array
     */
    public double[][] to2dArray() {
        double[][] output = new double[this.getRows()][this.getCols()];
        for(int row = 0; row < this.getRows(); row++) {
            output[row] = matrix[row].clone();
        }
        return output;
    }

    /**
     * clones the matrix
     * @return matrix clone
     */
    public Matrix clone() {
        Matrix output = new Matrix(this.getRows(), this.getCols());
        for(int row = 0; row < this.getRows(); row++) {
            output.matrix[row] = matrix[row].clone();
        }
        return output;
    }

    // operations

    /**
     * adds to the matrix by a constant c
     * @param c c
     */
    public void add(double c) {
        for(int row = 0; row < this.getRows(); row++) for(int col = 0; col < this.getCols(); col++) {
            matrix[row][col] += c;
        }
    }

    /**
     * adds matrix m to this
     * @param m matrix
     */
    public void add(Matrix m) {
        if(this.getRows() != m.getRows() || this.getCols() != m.getCols()) throw new RuntimeException("cannot add matrices");
        for(int row = 0; row < this.getRows(); row++) for(int col = 0; col < this.getCols(); col++) {
            matrix[row][col] += m.get(row, col);
        }
    }

    /**
     * multiplies the matrix by a constant c
     * @param c c
     */
    public void multiply(double c) {
        for(int row = 0; row < this.getRows(); row++) for(int col = 0; col < this.getCols(); col++) {
            matrix[row][col] *= c;
        }
    }

    /**
     * multiplies this by m2 and sets the result to this
     * @param m2 matrix 2
     */
    public void multiply(Matrix m2) {
        if(this.getCols() != m2.getRows()) throw new RuntimeException("cannot multiply matrices");
        double[][] newMatrix = new double[this.getRows()][m2.getCols()];

        for(int row = 0; row < this.getRows(); row++) {
            for(int col = 0; col < m2.getCols(); col++) {
                double val = 0;
                for(int i = 0; i < this.getCols(); i++) {
                    val += this.get(row, i) * m2.get(i, col);
                }
                newMatrix[row][col] = val;
            }
        }
        this.matrix = newMatrix;
    }

    /**
     * transposes the matrix to m^-1
     */
    public void transpose() {
        double[][] newMatrix = new double[this.getCols()][this.getRows()];
        for(int row = 0; row < this.getRows(); row++) {
            for(int col = 0; col < this.getCols(); col++) {
                newMatrix[col][row] = this.matrix[row][col];
            }
        }
        matrix = newMatrix;
    }


    // comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }


    // other

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix:\n");
        for(double[] row : matrix) {
            for(double val : row) {
                sb.append(val);
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }


}
