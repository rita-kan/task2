package com.company;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        int[] vData = new int[7];
        for (int i = 0; i < 7; i++)
            vData[i] = i + 1;

        int[][] mData = new int[7][7];
        final Random random = new Random();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++)
                mData[i][j] = random.nextInt();
        }

        Matrix[] matrix = new Matrix[9];

        matrix[0] = new Vector(vData);
        matrix[0].printMatrix();

        matrix[1] = matrix[0].transposeMatrix();
        matrix[1].printMatrix();

        matrix[2] = new Matrix(mData);
        matrix[2].printMatrix();

        matrix[3] = matrix[2].transposeMatrix();
        matrix[3].printMatrix();

        matrix[4] = matrix[0].multiplyMatrix(matrix[1]); // Getting a ceil
        matrix[4].printMatrix();

        matrix[5] = matrix[1].multiplyMatrix(matrix[0]); //Getting a 7x7 matrix
        matrix[5].printMatrix();

        matrix[6] = matrix[5].addMatrix(matrix[5]);
        matrix[6].printMatrix();

        matrix[7] = matrix[6].multiplyMatrix(matrix[2]);
        matrix[7].printMatrix();

        matrix[8] = matrix[2].multiplyMatrix(matrix[6]);
        matrix[8].printMatrix();

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("tmp.out"))) {
            output.writeObject(matrix);
            output.flush();
            output.close();
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("tmp.out"))) {
                Matrix[] res = (Matrix[]) input.readObject();
                System.out.println("HAVE BEEN DESERIALIZED:");
                for (int i = 0; i < res.length; i++)
                    res[i].printMatrix();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }
}
