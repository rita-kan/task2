package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by 1 on 24.10.2016.
 */
public class Matrix implements Serializable {

    int[][] data;

    public Matrix(int[][] data) {
        this.data = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++)
                this.data[i][j] = data[i][j];
        }
    }

    public Matrix(int m, int n) throws MatrixException {
        if ((m > 0) && (n > 0)) {
            this.data = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    data[i][j] = 0;
                }
            }
        } else
            throw new MatrixException("Invalid values of array bounds");
    }

    public int getHeight() {
        return data.length;
    }

    public int getWidth() {
        return data[0].length;
    }

    public int[] getSize() {
        int[] result = new int[2];
        result[0] = this.getHeight();
        result[1] = this.getWidth();
        return result;
    }

    public Matrix transposeMatrix() {
        Matrix result = new Matrix(this.getWidth(), this.getHeight());
        for (int i = 0; i < result.getHeight(); i++) {
            for (int j = 0; j < result.getWidth(); j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }

    public Matrix addMatrix(Matrix matrix) throws MatrixException {
        if (Arrays.equals(this.getSize(), matrix.getSize())) {
            Matrix result = new Matrix(this.data);
            for (int i = 0; i < this.getHeight(); i++) {
                for (int j = 0; j < this.getWidth(); j++)
                    result.data[i][j] += matrix.data[i][j];
            }
            return result;
        } else
            throw new MatrixException("Addition is impossible due to dimensions' mismatching");
    }

    public Matrix multiplyMatrix(Matrix matrix) throws MatrixException {
        if (this.getWidth() == matrix.getHeight()) {
            Matrix result = new Matrix(this.getHeight(), matrix.getWidth());
            for (int i = 0; i < result.getHeight(); i++) {
                for (int j = 0; j < result.getWidth(); j++) {
                    for (int k = 0; k < this.getWidth(); k++) {
                        result.data[i][j] += this.data[i][k] * matrix.data[k][j];
                    }
                }
            }
            return result;
        } else throw new MatrixException("Multiplication is impossible due to dimensions' mismatching");
    }

    public void printMatrix() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++)
                System.out.print(this.data[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
