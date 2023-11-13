package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import co.edu.uniquindio.proyectoanalisis.logic.DijkstraAlgorithm;
import co.edu.uniquindio.proyectoanalisis.logic.FloydWarshallAlgorithm;
import co.edu.uniquindio.proyectoanalisis.logic.JohnsonAlgorithm;
import co.edu.uniquindio.proyectoanalisis.logic.MultistageGraphAlgorithm;
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




}
