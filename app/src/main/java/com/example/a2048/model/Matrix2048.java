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

    public void up(final int i) {

        for (int j = 0; j < size - 1; j++) {
            if (!hasElement(j, i) && hasElement(j + 1, i)) {
                // permutation
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[j + 1][i];
                matrix[j + 1][i] = tmp;
            }
            if (hasElement(j, i) && hasElement(j + 1, i) && (matrix[j][i] == matrix[j + 1][i])) {
                // add
                matrix[j][i] *= 2;
                matrix[j + 1][i] = 0;
                break;
            }
        }

    }

    public void down(final int i) {

        for (int j = size - 1; j > 0; j--) {
            if (!hasElement(j, i) && hasElement(j - 1, i)) {
                // permutation
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[j - 1][i];
                matrix[j - 1][i] = tmp;
            }
            if (hasElement(j, i) && hasElement(j - 1, i) && (matrix[j][i] == matrix[j - 1][i])) {
                // add
                matrix[j][i] *= 2;
                matrix[j - 1][i] = 0;
                break;
            }
        }

    }

    public void right(final int i) {

        for (int j = size - 1; j > 0; j--) {
            if (!hasElement(i, j) && hasElement(i, j - 1)) {
                // permutation
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][j - 1];
                matrix[i][j - 1] = tmp;
            }
            if (hasElement(i, j) && hasElement(i, j - 1) && (matrix[i][j] == matrix[i][j - 1])) {
                // add
                matrix[i][j] *= 2;
                matrix[i][j - 1] = 0;
                break;
            }
        }


    }

    public void left(final int i) {

        for (int j = 0; j < size - 1; j++) {
            if (!hasElement(i, j) && hasElement(i, j + 1)) {
                // permutation
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][j + 1];
                matrix[i][j + 1] = tmp;
            }
            if (hasElement(i, j) && hasElement(i, j + 1) && (matrix[i][j] == matrix[i][j + 1])) {
                // add
                matrix[i][j] *= 2;
                matrix[i][j + 1] = 0;
                break;
            }
        }
    }

    public void move(final Direction direction) {
        for (int k = 0; k < size; k++) {// size -1
            for (int i = 0; i < size; i++) {
                switch (direction) {
                    case UP:
                        up(i);
                        break;
                    case DOWN:
                        down(i);
                        break;
                    case LEFT:
                        left(i);
                        break;
                    case RIGHT:
                        right(i);
                        break;
                }
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
