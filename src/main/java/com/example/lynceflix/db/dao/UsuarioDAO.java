package com.example.lynceflix.db.dao;

import com.example.lynceflix.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static int autenticarUsuario(String correoElectronico, String contraseña) throws SQLException {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT usuario_id FROM Usuario WHERE correo_electronico = ? AND contraseña = ?")) {

            preparedStatement.setString(1, correoElectronico);
            preparedStatement.setString(2, contraseña);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("usuario_id") : -1;
            }

        } catch (SQLException e) {
            throw new SQLException("Error al autenticar usuario", e);
        }
    }

    public static boolean registrarUsuario(String nombre, String correo_electronico, String usuario, String contraseña, String tipo_Suscripcion) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Usuario (nombre, correo_electronico, usuario, contraseña, tipo_suscripcion) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo_electronico);
            preparedStatement.setString(3, usuario);
            preparedStatement.setString(4, contraseña);
            preparedStatement.setString(5, tipo_Suscripcion);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public static boolean registrarUsuarioConMetodoPago(String nombre, String correo_electronico, String contraseña,
                                                        String tipo_Suscripcion, String tipoMetodoPago, String numeroTarjeta, String fechaVencimiento) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Usuario (nombre, correo_electronico, contraseña, tipo_suscripcion, metodo_pago_tipo) VALUES (?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo_electronico);
            preparedStatement.setString(3, contraseña);
            preparedStatement.setString(4, tipo_Suscripcion);
            preparedStatement.setString(5, tipoMetodoPago);

            preparedStatement.executeUpdate();

            // Obtener el ID generado para el nuevo usuario
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Inserta información del método de pago
                    try (PreparedStatement metodoPagoStatement = connection.prepareStatement(
                            "INSERT INTO MetodoPago (usuario_id, tipo, numero, fecha_vencimiento) VALUES (?, ?, ?, ?)")) {
                        metodoPagoStatement.setInt(1, userId);
                        metodoPagoStatement.setString(2, tipoMetodoPago);
                        metodoPagoStatement.setString(3, numeroTarjeta);

                        // Formatea la fecha antes de establecerla
                        String[] parts = fechaVencimiento.split("/");
                        String formattedDate = "20" + parts[1] + "-" + parts[0] + "-01";
                        metodoPagoStatement.setString(4, formattedDate);

                        metodoPagoStatement.executeUpdate();
                    }

                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario con método de pago: " + e.getMessage());
            return false;
        }
    }
}