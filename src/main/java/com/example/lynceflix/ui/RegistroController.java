package com.example.lynceflix.ui;

import com.example.lynceflix.db.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenaField;

    @FXML

    private ComboBox<String> tipoSuscripcionComboBox;


    @FXML

    private ComboBox<String> tipoMetodoPagoComboBox;



    @FXML
    private TextField numeroTarjetaField;

    @FXML
    private TextField fechaVencimientoField;

    @FXML
    private Button registrarButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleRegistrarButton() {
        String nombre = nombreField.getText();
        String correo_electronico = correoField.getText();
        String usuario = usuarioField.getText();
        String contraseña = contrasenaField.getText();
        String tipo_Suscripcion = tipoSuscripcionComboBox.getValue();
        String tipoMetodoPago = tipoMetodoPagoComboBox.getValue();
        String numeroTarjeta = numeroTarjetaField.getText();
        String fechaVencimiento = fechaVencimientoField.getText();

        // Lógica para registrar el usuario con la nueva información de método de pago
        boolean registroExitoso = UsuarioDAO.registrarUsuarioConMetodoPago(
                nombre, correo_electronico, contraseña, tipo_Suscripcion, tipoMetodoPago, numeroTarjeta, fechaVencimiento);

        if (registroExitoso) {
            System.out.println("Registro exitoso");
        } else {
            System.out.println("Error en el registro");
        }
    }
}