package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    // GUI elements
    @FXML
    private Button btnLoadData;

    @FXML
    private Button btnRunAlgorithms;

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
    void loadDataAction(ActionEvent event) {
        app.initRootLayout();
        app.showLoadDataView();
    }

    @FXML
    void runAlgorithmsAction(ActionEvent event) {
        app.initRootLayout();
        app.showRunAlgorithmsView();
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}