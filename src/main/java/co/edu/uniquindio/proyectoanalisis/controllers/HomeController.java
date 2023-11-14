package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HomeController {
    // GUI elements
    @FXML
    private HBox hbLoadedGraph;

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
    void createTestAction(ActionEvent event) {
        app.showCreateTestCaseDialog();
    }

    @FXML
    void testAlgorithmsAction(ActionEvent event) {
        app.initRootLayout();
        app.showTestAlgorithmsView();
    }

    @FXML
    void viewReportAction(ActionEvent event) {
        app.initRootLayout();
        app.showViewReportView();
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    public void setLoadedGraphInfo(int size) {
        hbLoadedGraph.setVisible(true);
        lblLoadedGraph.setText(size + " vertices (" + size + "*" + size + " matrix)");
    }
}