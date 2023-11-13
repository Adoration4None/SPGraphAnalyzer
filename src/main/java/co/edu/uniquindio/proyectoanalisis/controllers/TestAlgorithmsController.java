package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import co.edu.uniquindio.proyectoanalisis.utils.Algorithm;
import co.edu.uniquindio.proyectoanalisis.utils.MatrixHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.*;
import java.util.ArrayList;

public class TestAlgorithmsController {

    @FXML
    private ComboBox<Algorithm> cmbAlgorithm;

    @FXML
    private ComboBox<String> cmbGraphSize;

    @FXML
    private Label lblFeedback;

    // Reference to the main application
    private App app;

    Algorithm selectedAlgorithm;
    private int selectedSize;
    private int[][] selectedGraph;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        cmbGraphSize.getItems().addAll( getGraphSizes() );
        cmbAlgorithm.getItems().addAll( getAlgorithms() );
        cmbAlgorithm.setConverter(new StringConverter<>() {

            @Override
            public String toString(Algorithm a) {
                return a.getName();
            }

            @Override
            public Algorithm fromString(String string) {
                return null;
            }
        });
    }

    private ArrayList<String> getGraphSizes() {
        ArrayList<String> sizesList = new ArrayList<>();
        int[] sizes = {512, 1024, 2048, 4096, 8192, 16384, 21000, 27000};

        for(int size : sizes) {
            sizesList.add("" + size);
        }

        return sizesList;
    }

    private ArrayList<Algorithm> getAlgorithms() {
        ArrayList<Algorithm> algorithms = new ArrayList<>();

        algorithms.add( new Algorithm(1,  "Dijkstra",                          "dijkstra") );
        algorithms.add( new Algorithm(2,  "Bellman-Ford",                      "bellman-ford") );
        algorithms.add( new Algorithm(3,  "Floyd Warshall",                    "floyd-warshall") );
        algorithms.add( new Algorithm(4,  "Johnson",                           "johnson") );
        algorithms.add( new Algorithm(5,  "SP in Directed Acyclic Graph",      "directed-acyclic-sp") );
        algorithms.add( new Algorithm(6,  "Dial",                              "dial") );
        algorithms.add( new Algorithm(7,  "SP in Multistage Graph",            "multistage-graph-sp") );
        algorithms.add( new Algorithm(8,  "SP in Unweighted Graph",            "unweighted-graph-sp") );
        algorithms.add( new Algorithm(9,  "Karp's Minimum Mean Weight Cycle",  "karp") );
        algorithms.add( new Algorithm(10, "0-1 BFS",                           "0-1-bfs") );
        algorithms.add( new Algorithm(11, "Minimum Cycle in Undirected Graph", "minimum-cycle-undirected") );

        return algorithms;
    }

    public void setMainApp(App app) {
        this.app = app;

        if(app.getLoadedGraph() != null) {
            selectedSize = app.getLoadedGraph().length;
            selectedGraph = app.getLoadedGraph();

            cmbGraphSize.getSelectionModel().select("" + selectedSize);
        }
    }

    @FXML
    void sizeSelectedAction(ActionEvent event) {
        this.selectedSize = Integer.parseInt( cmbGraphSize.getSelectionModel().getSelectedItem() );
        lblFeedback.setText("Loading " + selectedSize + " vertices graph ...");

        MatrixHelper matrixHelper = new MatrixHelper(selectedSize);

        this.selectedGraph = matrixHelper.readMatrixFromFile();
        app.setLoadedGraph(selectedGraph);

        lblFeedback.setText(selectedSize + " vertices graph loaded successfully");

        if(selectedAlgorithm != null) {
            double execTime = selectedAlgorithm.run(selectedGraph);

            lblFeedback.setText(selectedAlgorithm.getName() + " done in " + execTime + " ms.");
            saveResultToFile(execTime, selectedAlgorithm.getFileName());
        }
    }

    @FXML
    void algorithmSelectedAction(ActionEvent event) throws Exception {
        double execTime;
        this.selectedAlgorithm = cmbAlgorithm.getSelectionModel().getSelectedItem();

        if(selectedSize  == 0)    throw new Exception("No graph size selected");
        if(selectedGraph == null) throw new Exception("No graph selected");

        execTime = selectedAlgorithm.run(selectedGraph);

        lblFeedback.setText(selectedAlgorithm.getName() + " done in " + execTime + " ms.");
        saveResultToFile(execTime, selectedAlgorithm.getFileName());
    }

    private void saveResultToFile(double execTime, String algorithm) {
        String fileName = "results/" + algorithm + "/" + algorithm + selectedSize + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName, true);

            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            content.insert(0, execTime + " ms" + System.lineSeparator());

            br.close();

            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content.toString());

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

