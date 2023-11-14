package co.edu.uniquindio.proyectoanalisis;

import co.edu.uniquindio.proyectoanalisis.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private LayoutController layoutController;

    // Loaded graph (adjacency matrix)
    private int[][] loadedGraph;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Graph Analysis App - Samuel Echeverri");

        showHomeView();
    }

    public void showHomeView() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/home.fxml"));
            Scene scene = new Scene(loader.load());

            HomeController controller = loader.getController();
            controller.setMainApp(this);
            if(loadedGraph != null) controller.setLoadedGraphInfo(loadedGraph.length);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            this.layoutController = loader.getController();
            layoutController.setMainApp(this);
            if(loadedGraph != null) layoutController.setLoadedGraphInfo(loadedGraph.length);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTestAlgorithmsView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/testAlgorithms.fxml"));
            AnchorPane runAlgorithmsView = (AnchorPane) loader.load();

            rootLayout.setCenter(runAlgorithmsView);

            TestAlgorithmsController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void showCreateTestCaseDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/createTestCase.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Test Case");
            //dialogStage.getIcons().add( new Image("file:resources/images/edit.png") );
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CreateTestCaseController controller = loader.getController();
            controller.setApp(this);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewReportView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/viewReport.fxml"));
            AnchorPane reportView = (AnchorPane) loader.load();

            rootLayout.setCenter(reportView);

            ViewReportController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    //  ---------------------------------- Graph getter and setter --------------------------------------------

    public int[][] getLoadedGraph() {
        return loadedGraph;
    }

    public void setLoadedGraph(int[][] loadedGraph) {
        this.loadedGraph = loadedGraph;
        layoutController.setLoadedGraphInfo(loadedGraph.length);
    }


}