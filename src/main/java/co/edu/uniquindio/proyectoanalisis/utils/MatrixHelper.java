package co.edu.uniquindio.proyectoanalisis.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MatrixHelper {
    private int size;

    public MatrixHelper(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Fills a n*n matrix with random integer numbers
     * @return matrix full with random integer numbers
     */
    public int[][] generate() {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        // Min number (0)
        int min = 0;

        // Max number (1000)
        int max = 1000;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int randomValue = random.nextInt(max - min) + min;
                matrix[i][j] = randomValue;

                if (i == j) matrix[i][j] = 0;
            }
        }

        return matrix;
    }

    /**
     * Reads a n*n int matrix from a text file
     * @param n
     * @return n*n int matrix
     */
    public int[][] readMatrixFromFile() {
        Path path = Paths.get("input_data/" + size + ".txt");

        int[][] matrix = new int[size][size];

        try ( BufferedReader br = new BufferedReader( new FileReader(path.toFile()) ) ) {
            String line;

            for (int i = 0; i < size; i++) {
                if ((line = br.readLine()) != null) {
                    String[] columns = line.split("\t");

                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = Integer.parseInt(columns[j]);
                    }
                } else {
                    // Handle case where file ends prematurely
                    System.err.println("Error: Insufficient rows in the file.");
                    return null;
                }
            }

            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Saves in a text file the content stored in a matrix
     * @param matrix
     */
    public void saveMatrixToFile(int[][] matrix, String name) {
        try {
            FileWriter writer = new FileWriter( name + matrix.length + ".txt");

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write( String.valueOf(matrix[i][j]) );

                    if (j < matrix[i].length - 1) {
                        writer.write("\t");
                    }
                }

                if (i < matrix.length - 1) {
                    writer.write("\n");
                }
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
