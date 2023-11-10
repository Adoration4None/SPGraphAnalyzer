package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class LoadDataController {

    @FXML
    private Button btn10000;

    @FXML
    private Button btn1024;

    @FXML
    private Button btn128;

    @FXML
    private Button btn2048;

    @FXML
    private Button btn256;

    @FXML
    private Button btn4096;

    @FXML
    private Button btn512;

    @FXML
    private Button btn8192;

    @FXML
    private VBox vbMatrixLoaded;

    @FXML
    private Button btnInspect;

    @FXML
    private Label lblMatrixLoaded;

    // Reference to the main application
    private App app;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    @FXML
    void load128Action(ActionEvent event) {
        BigInteger[][] randomMatrix = fill(128);
        saveMatrixToFile(randomMatrix, "");


        showSuccessMessage(128);
    }

    @FXML
    void load256Action(ActionEvent event) {
        showSuccessMessage(256);
    }

    @FXML
    void load512Action(ActionEvent event) {
        showSuccessMessage(512);
    }

    @FXML
    void load1024Action(ActionEvent event) {
        showSuccessMessage(1024);
    }

    @FXML
    void load2048Action(ActionEvent event) {
        showSuccessMessage(2048);
    }

    @FXML
    void load4096Action(ActionEvent event) {
        showSuccessMessage(4096);
    }

    @FXML
    void load8192Action(ActionEvent event) {
        showSuccessMessage(8192);
    }

    @FXML
    void load10000Action(ActionEvent event) {
        showSuccessMessage(10000);
    }

    @FXML
    void inspectAction(ActionEvent event) {

    }

    private void showSuccessMessage(int selectedMatrixSize) {
        vbMatrixLoaded.setVisible(true);
        lblMatrixLoaded.setText(selectedMatrixSize + "x" + selectedMatrixSize + " matrix loaded!");
    }

    // -------------------------------- Persistence utilities ------------------------------------

    /**
     * Fills a n*n matrix with random integer numbers
     * @param n
     * @return matrix full with random integer numbers
     */
    public static BigInteger[][] fill(int n) {
        BigInteger[][] matrix = new BigInteger[n][n];
        BigInteger randomValue;

        // Min number (0)
        BigInteger min = BigInteger.ZERO;

        // Max number (100)
        BigInteger max = BigInteger.TEN.pow(3);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                randomValue = new BigInteger(max.bitLength(), new Random());
                randomValue = randomValue.mod(max.subtract(min)).add(min);
                matrix[i][j] = randomValue;

                if(i == j) matrix[i][j] = BigInteger.ZERO;
            }
        }

        return matrix;
    }

    /**
     * Reads a n*n int matrix from a text file
     * @param n
     * @return n*n int matrix
     */
    private static BigInteger[][] readMatrixFromFile(int n, char name) {
        Path path = Paths.get(name + n + ".txt");

        BigInteger[][] matrix = new BigInteger[n][n];

        try {
            String content = Files.readString(path);
            String[] rows  = content.split("\n");

            for(int i = 0; i < n; i++) {
                String[] columns = rows[i].split("\t");

                for(int j = 0; j < n; j++) {
                    matrix[i][j] = new BigInteger(columns[j]);
                }
            }

            return matrix;
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Saves in a text file the content stored in a matrix
     * @param matrix
     */
    public static void saveMatrixToFile(BigInteger[][] matrix, String name) {
        try {
            FileWriter writer = new FileWriter(name + matrix.length + ".txt");

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

