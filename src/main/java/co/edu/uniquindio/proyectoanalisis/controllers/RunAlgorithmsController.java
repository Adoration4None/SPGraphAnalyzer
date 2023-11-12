package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import co.edu.uniquindio.proyectoanalisis.logic.DijkstraAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.*;

public class RunAlgorithmsController {

    @FXML
    private Label lblResult;

    @FXML
    private VBox vbResult;

    @FXML
    private Button btnInspectResult;

    private App app;
    private int[][] loadedGraph;
    private double initTime;
    private double endTime;
    private double execTime;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    @FXML
    void dijkstraAction(ActionEvent event) {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(loadedGraph.length);

        vbResult.setVisible(true);
        lblResult.setText("Running Dijkstra algorithm ...");

        initTime = System.nanoTime();
        algorithm.dijkstra(loadedGraph, 0);
        endTime = System.nanoTime();

        execTime = (endTime - initTime) / 1000000;

        lblResult.setText("Done in " + execTime + " ms.");
        btnInspectResult.setVisible(true);

        saveResultToFile(execTime, "dijkstra");
    }

    @FXML
    void bellmanFordAction(ActionEvent event) {

    }

    @FXML
    void floydWarshallAction(ActionEvent event) {

    }

    @FXML
    void johnsonAction(ActionEvent event) {

    }

    @FXML
    void spDirectedAcyclicAction(ActionEvent event) {

    }

    @FXML
    void dialAction(ActionEvent event) {

    }

    @FXML
    void multistageAction(ActionEvent event) {

    }

    @FXML
    void spUnweightedAction(ActionEvent event) {

    }

    @FXML
    void karpMinimumAction(ActionEvent event) {

    }

    @FXML
    void bfs01Action(ActionEvent event) {

    }

    @FXML
    void minimumWeightCycleAction(ActionEvent event) {

    }

    @FXML
    void inspectResultAction(ActionEvent event) {

    }

    public void setMainApp(App app) {
        this.app = app;
        this.loadedGraph = app.getLoadedGraph();
    }

    // -------------------------------- Persistence utilities ------------------------------------

    private void saveResultToFile(double execTime, String algorithm) {
        String fileName = "results/" + algorithm + "/" + algorithm + loadedGraph.length + ".txt";

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
