package co.edu.uniquindio.proyectoanalisis.controllers;

import co.edu.uniquindio.proyectoanalisis.App;
import co.edu.uniquindio.proyectoanalisis.utils.MatrixHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class CreateTestCaseController {

    @FXML
    private Spinner<Integer> spnSizeInput;

    // Reference to the main application
    private App app;

    private Stage dialogStage;
    private boolean okClicked = false;

    public void setApp(App app) {
        this.app = app;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 15000);

        valueFactory.setValue(128);
        spnSizeInput.setValueFactory(valueFactory);
    }

    @FXML
    void cancelAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void okAction(ActionEvent event) {
        int enteredSize;

        if ( isInputValid() ) {
            okClicked = true;

            enteredSize = spnSizeInput.getValue();
            MatrixHelper matrixHelper = new MatrixHelper(enteredSize);

            matrixHelper.saveMatrixToFile(matrixHelper.generate(), "input_data/");

            dialogStage.close();
        }
    }

    private boolean isInputValid() {

        if (spnSizeInput.getValue() == null) {
            showMessage("Error", "Invalid input", "Please enter a valid size", AlertType.ERROR);
            return false;
        }

        return true;
    }


    private void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

