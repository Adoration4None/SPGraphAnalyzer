package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import co.edu.uniquindio.proyectoanalisis.utils.Algorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ViewReportController {

    @FXML
    private BarChart<String, Double> chart;

    @FXML
    private ComboBox<String> cmbGraphSize;

    XYChart.Series<String, Double> series;
    private ArrayList<Algorithm> algorithms;
    private int selectedSize;

    // Reference to the main application
    private App app;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        cmbGraphSize.getItems().addAll( getGraphSizes() );
        this.algorithms = getAlgorithms();


    }

    private ArrayList<String> getGraphSizes() {
        ArrayList<String> sizesList = new ArrayList<>();
        int[] sizes = {128, 256, 512, 1024, 2048, 4096, 8192, 10000};

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
        algorithms.add( new Algorithm(12, "Dijkstra Using Priority Queue",     "dijkstra-priority-queue") );

        return algorithms;
    }

    @FXML
    void changeGraphSize(ActionEvent event) {
        chart.getData().clear();

        ArrayList<String> orderedAlgorithms = new ArrayList<>();
        ArrayList<Double> orderedExecTimes = new ArrayList<>();
        ArrayList[] sortedResults;

        this.selectedSize = Integer.parseInt( cmbGraphSize.getSelectionModel().getSelectedItem() );

        HashMap<Double, String> execTimesMap = readExecTimes(selectedSize);
        sortedResults = sortExecTimes(execTimesMap);

        orderedExecTimes = sortedResults[0];
        orderedAlgorithms = sortedResults[1];

        series = new XYChart.Series<>();
        series.setName("Execution time for size " + selectedSize);

        for(int i = 0; i < orderedExecTimes.size(); i++) {
            series.getData().add( new XYChart.Data<>( getAlgorithmName(orderedAlgorithms.get(i) ), orderedExecTimes.get(i)) );
        }

        chart.getData().add(series);
    }

    private String getAlgorithmName(String fileName) {

        for(Algorithm a : algorithms) {
            if(a.getFileName().equals(fileName)) return a.getName();
        }

        return null;
    }

    private HashMap<Double, String> readExecTimes(int selectedSize) {
        HashMap<Double, String> execTimes = new HashMap<>();
        String[] folderNames = getAlgorithmsFolders();

        for (String folderName : folderNames) {
            File folderFile = new File("results/" + folderName);

            if (folderFile.isDirectory()) {
                File fileToRead = new File(folderFile, folderName + selectedSize + ".txt");

                if (fileToRead.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
                        String line = br.readLine();

                        if (line != null) {
                            String[] parts = line.split("\\s");
                            if (parts.length > 0) {
                                double number = Double.parseDouble(parts[0]);
                                execTimes.put(number, folderName);
                            }
                        }
                    } catch (IOException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return execTimes;
    }

    private ArrayList[] sortExecTimes(HashMap<Double, String> execTimesMap) {
        ArrayList[] result = new ArrayList[2];
        List<Double> execTimesList = new ArrayList<>();
        List<String> algorithmNamesList = new ArrayList<>();

        List<Map.Entry<Double, String>> entryList = new ArrayList<>(execTimesMap.entrySet());
        entryList.sort(Map.Entry.comparingByKey());

        for (Map.Entry<Double, String> entry : entryList) {
            execTimesList.add(entry.getKey());
            algorithmNamesList.add(entry.getValue());
        }

        result[0] = new ArrayList<>(execTimesList);
        result[1] = new ArrayList<>(algorithmNamesList);

        return result;
    }

    private String[] getAlgorithmsFolders() {
        String[] folders = new String[algorithms.size()];

        for(int i = 0; i < algorithms.size(); i++) {
            folders[i] = algorithms.get(i).getFileName();
        }

        return folders;
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
