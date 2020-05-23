package com.example.a2048.model;

import static com.example.a2048.model.Utils.randAmong;
import static com.example.a2048.model.Utils.randInt;

public class Matrix2048 {
    private int size = 4;
    private int[][] matrix;

    public Matrix2048(int size) {
        this.size = size;
        this.matrix = new int[size][size];
        randOneElement();
        randOneElement();
    }

    public void randOneElement() {
        int row, column;
        do {
            row = randInt(size);
            column = randInt(size);
        } while (hasElement(row, column));
        add(row, column, randAmong(2, 4));
    }

    private boolean hasElement(int row, int column) {
        return matrix[row][column] != 0;
    }

    private void add(final int row, final int column, final int value) {
        matrix[row][column] = value;
    }

    public int get(final int row, final int column) {
        return matrix[row][column];
    }

    public void up() {
        for (int k = 0; k < size - 1; k++) {
            for (int column = 0; column < size; column++) {
                for (int i = 0; i < size - 1; i++) {
                    if (!hasElement(i, column) && hasElement(i + 1, column)) {
                        // permutation
                        int tmp = matrix[i][column];
                        matrix[i][column] = matrix[i + 1][column];
                        matrix[i + 1][column] = tmp;
                    }
                    if (hasElement(i, column) && hasElement(i + 1, column) && (matrix[i][column] == matrix[i + 1][column])) {
                        // add
                        matrix[i][column] *= 2;
                        matrix[i + 1][column] = 0;
                        break;
                    }
                }
            }
        }
    }

    public void down() {
        for (int k = size - 1; k > 0; k--) {
            for (int column = 0; column < size; column++) {
                for (int i = size - 1; i > 0; i--) {
                    if (!hasElement(i, column) && hasElement(i - 1, column)) {
                        // permutation
                        int tmp = matrix[i][column];
                        matrix[i][column] = matrix[i - 1][column];
                        matrix[i - 1][column] = tmp;
                    }
                    if (hasElement(i, column) && hasElement(i - 1, column) && (matrix[i][column] == matrix[i - 1][column])) {
                        // add
                        matrix[i][column] *= 2;
                        matrix[i - 1][column] = 0;
                        break;
                    }
                }
            }
        }
    }

    public void right() {
        for (int k = size - 1; k > 0; k--) {
            for (int row = 0; row < size; row++) {
                for (int i = size - 1; i > 0; i--) {
                    if (!hasElement(row, i) && hasElement(row, i - 1)) {
                        // permutation
                        int tmp = matrix[row][i];
                        matrix[row][i] = matrix[row][i - 1];
                        matrix[row][i - 1] = tmp;
                    }
                    if (hasElement(row, i) && hasElement(row, i - 1) && (matrix[row][i] == matrix[row][i - 1])) {
                        // add
                        matrix[row][i] *= 2;
                        matrix[row][i - 1] = 0;
                        break;
                    }
                }
            }
        }

    }

    public void left() {
        for (int k = 0; k < size - 1; k++) {
            for (int row = 0; row < size; row++) {
                for (int i = 0; i < size - 1; i++) {
                    if (!hasElement(row, i) && hasElement(row, i + 1)) {
                        // permutation
                        int tmp = matrix[row][i];
                        matrix[row][i] = matrix[row][i + 1];
                        matrix[row][i + 1] = tmp;
                    }
                    if (hasElement(row, i) && hasElement(row, i + 1) && (matrix[row][i] == matrix[row][i + 1])) {
                        // add
                        matrix[row][i] *= 2;
                        matrix[row][i + 1] = 0;
                        break;
                    }
                }
            }
        }
    }

    public void move() {
        for (int k = 0; k < size - 1; k++) {
            for (int i = 0; i < size; i++) {

            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str.append(String.format("|%d", matrix[i][j]));
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
