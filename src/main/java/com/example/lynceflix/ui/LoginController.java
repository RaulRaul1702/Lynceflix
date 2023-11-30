package com.example.lynceflix.ui;


import com.example.lynceflix.HelloApplication;
import com.example.lynceflix.HelloController;
import com.example.lynceflix.db.dao.UsuarioDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController extends Application {

    private Stage stage;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private int userID;
    private HelloApplication helloController;

    public void setHelloController(HelloApplication helloController) {
        this.helloController = helloController;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML y obtener el controlador asociado
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lynceflix/ui/login.fxml"));
        Parent root = loader.load();

        // Obtener el controlador desde el cargador
        LoginController loginController = loader.getController();
        loginController.setStage(primaryStage);

        // Configurar la escena y mostrar la ventana principal
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("Boton de login presionado");
        String correoElectronico = usernameField.getText();
        String contraseña = passwordField.getText();


        try {
            userID = UsuarioDAO.autenticarUsuario(correoElectronico, contraseña);

            if (userID != -1) {
                System.out.println("Inicio de sesion exitoso");

                // Cargar la nueva vista (hello-view.fxml)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lynceflix/ui/hello-view.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la nueva vista (si es necesario)
                HelloController helloViewController = loader.getController();

                // Configurar datos en el controlador de la nueva vista (si es necesario)
                helloViewController.setUserID(userID);

                // Crear una nueva escena y configurarla
                Scene scene = new Scene(root, 800, 500);

                // Obtener la referencia al escenario actual y establecer la nueva escena
                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.setScene(scene);
            } else {
                System.out.println("Inicio de sesion fallido");
                mostrarAlerta("Error de inicio de sesión", "Correo electrónico o contraseña incorrectos.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error al intentar iniciar sesión.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lynceflix/ui/registro.fxml"));
            Parent root = loader.load();

            Stage registroStage = new Stage();
            registroStage.setTitle("Registro");
            Scene scene = new Scene(root, 800, 600);

            scene.getStylesheets().add(getClass().getResource("/com/example/lynceflix/ui/styles.css").toExternalForm());

            registroStage.setScene(scene);

            RegistroController registroController = loader.getController();
            registroController.setStage(registroStage);

            registroStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   /* @FXML
    private void onHelloButtonClick(ActionEvent event) {

        helloController.showHelloScene();
    } */

    public static void main(String[] args) {
        launch(args);
    }
}