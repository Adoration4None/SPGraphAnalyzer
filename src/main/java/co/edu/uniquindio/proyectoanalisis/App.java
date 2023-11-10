package co.edu.uniquindio.proyectoanalisis;

import co.edu.uniquindio.proyectoanalisis.controllers.HomeController;
import co.edu.uniquindio.proyectoanalisis.controllers.LayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

public class App extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

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

            LayoutController controller = loader.getController();
            controller.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoadDataView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/loadData.fxml"));
            AnchorPane loadDataView = (AnchorPane) loader.load();

            rootLayout.setCenter(loadDataView);
            /*
            HomeController controller = loader.getController();
            controller.setMainApp(this);
             */

            /*
            primaryStage.setScene(scene);
            primaryStage.show();

             */
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void showRunAlgorithmsView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/runAlgorithms.fxml"));
            AnchorPane runAlgorithmsView = (AnchorPane) loader.load();

            rootLayout.setCenter(runAlgorithmsView);
            /*
            HomeController controller = loader.getController();
            controller.setMainApp(this);
             */

            /*
            primaryStage.setScene(scene);
            primaryStage.show();

             */
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}