package com.example.lynceflix;

import com.example.lynceflix.ui.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lynceflix/ui/login.fxml"));



        Parent root = loader.load();


        LoginController loginController = loader.getController();
        loginController.setHelloController(this);

        Scene scene = new Scene(root, 614, 416);
        scene.getStylesheets().add(getClass().getResource("/com/example/lynceflix/ui/styles.css").toExternalForm());


        primaryStage.setTitle("Media Player - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showHelloScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lynceflix/ui/hello-view.fxml"));
            Parent root = loader.load();


            URL cssFile = getClass().getResource("/com/example/lynceflix/ui/styles.css");
            if (cssFile != null) {
                root.getStylesheets().add(cssFile.toExternalForm());
            } else {
                System.out.println("CSS file not found!");
            }

            Stage stage = new Stage();
            stage.setTitle("Media Player - Hello");
            stage.setScene(new Scene(root, 1100, 900));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}