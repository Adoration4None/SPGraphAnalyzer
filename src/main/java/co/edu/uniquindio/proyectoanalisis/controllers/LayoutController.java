package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LayoutController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHome;

    @FXML
    private GridPane gpLoadedGraph;

    @FXML
    private Label lblLoadedGraph;

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
    void backAction(ActionEvent event) {

    }

    @FXML
    void homeAction(ActionEvent event) {
        app.showHomeView();
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    public void setLoadedGraphInfo(int size) {
        gpLoadedGraph.setVisible(true);
        lblLoadedGraph.setText(size + " vertices (" + size + "*" + size + "matrix)");
    }
}
