package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class LoadDataController {

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

    public void setMainApp(App app) {
        this.app = app;
    }

    @FXML
    void load512Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(512);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load1024Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(1024);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load2048Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(2048);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load4096Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(4096);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load8192Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(8192);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load16384Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(16384);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load21000Action(ActionEvent event) {
        int[][] matrix = readMatrixFromFile(21000);
        app.setLoadedGraph(matrix);

        showSuccessMessage(matrix.length);
    }

    @FXML
    void load27000Action(ActionEvent event) {
        int matrix[][] = readMatrixFromFile(27000);
        app.setLoadedGraph(matrix);

        /*int matrix[][] = fill(21000);
        saveMatrixToFile(matrix, "input_data/");*/

        showSuccessMessage(matrix.length);
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
    public static int[][] fill(int n) {
        int[][] matrix = new int[n][n];
        Random random = new Random();

        // Min number (0)
        int min = 0;

        // Max number (1000)
        int max = 1000;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
    private static int[][] readMatrixFromFile(int n) {
        Path path = Paths.get("input_data/" + n + ".txt");

        int[][] matrix = new int[n][n];

        try ( BufferedReader br = new BufferedReader( new FileReader(path.toFile()) ) ) {
            String line;

            for (int i = 0; i < n; i++) {
                if ((line = br.readLine()) != null) {
                    String[] columns = line.split("\t");

                    for (int j = 0; j < n; j++) {
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
    public static void saveMatrixToFile(int[][] matrix, String name) {
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

