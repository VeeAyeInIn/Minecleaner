package me.vann.school;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public class Matrix<E> implements Iterable<E> {

    private E[][] matrix;
    public Matrix() {
        matrix = (E[][]) new Object[0][0];
    }
    public Matrix(int rows, int columns) {
        matrix = (E[][]) new Object[rows][columns];
    }
    //
    // Getters and Setters
    //
    public E get(int row, int column) {
        return matrix[row][column];
    }
    public E[] getRow(int row) {
        return matrix[row];
    }
    public E[] getColumn(int column) {
        E[] array = (E[]) new Object[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            array[row] = matrix[row][column];
        }
        return array;
    }
    public E[][] getMatrix() {
        return matrix;
    }
    public Matrix<E> set(int row, int column, E value) {
        matrix[row][column] = value;
        return this;
    }
    public Matrix<E> setRow(int row, E[] array) {
        if (array.length == matrix[row].length) {
            matrix[row] = array;
        } else {
            throw new IllegalArgumentException("New row must be of equal length to the previous row.");
        }
        return this;
    }
    public Matrix<E> setColumn(int column, E[] array) {
        if (array.length == matrix.length) {
            for (int row = 0; row < matrix[0].length; row++) {
                matrix[row][column] = array[row];
            }
        } else {
            throw new IllegalArgumentException("New column must be of equal length to the previous column.");
        }
        return this;
    }
    public Matrix<E> setMatrix(E[][] matrix) {
        this.matrix = matrix;
        return this;
    }
    //
    // Utility Methods
    //
    public Matrix<E> resize(int rows, int columns, boolean addToPrevious) {

        if (addToPrevious) {
            rows += matrix.length;
            columns += matrix[0].length;
        }

        E[][] newMatrix = (E[][]) new Object[rows][columns];

        for (int row = 0; row < Math.min(matrix.length, newMatrix.length); row++) {
            System.arraycopy(matrix[row], 0, newMatrix[row], 0, Math.min(matrix.length, newMatrix.length));
        }

        matrix = newMatrix;
        return this;
    }
    public Matrix<E> clear() {
        forEach(value -> value = null);
        return this;
    }
    public Matrix<E> invert() {

        E[][] newMatrix = (E[][]) new Object[matrix[0].length][matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                newMatrix[column][row] = matrix[row][column];
            }
        }

        matrix = newMatrix;
        return this;
    }
    public E[] toArray() {
        E[] array = (E[]) new Object[matrix.length * matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            System.arraycopy(matrix[row], 0, array, row * matrix[0].length, matrix[0].length);
        }
        return array;
    }
    public int getRows() {
        return matrix.length;
    }
    public int getColumns() {
        return matrix[0].length;
    }
    public int getSize() {
        return getRows() * getColumns();
    }
    public boolean inBounds(int row, int column) {
        return 0 <= row && row < matrix.length && 0 <= column && column < matrix[0].length;
    }
    //
    // Interface Methods
    //
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iteration = 0;

            @Override
            public boolean hasNext() {
                if (matrix.length * matrix[0].length == 0) return false;
                else return iteration < matrix.length * matrix[0].length;
            }

            @Override
            public E next() {
                return matrix[iteration / matrix.length][iteration++ % matrix[0].length];
            }
        };
    }
    public void forEach(Consumer<? super E> action) {
        for (E value : this) {
            action.accept(value);
        }
    }
    //
    // Overridden Methods
    //
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");
        String separator = "";

        for (E[] row : matrix) {
            sb.append(separator).append(Arrays.toString(row));
            separator = ",\n ";
        }

        return sb.toString() + "]";
    }
}
