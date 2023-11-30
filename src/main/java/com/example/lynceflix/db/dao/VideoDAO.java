package com.example.lynceflix.db.dao;



import com.example.lynceflix.db.MySQLConnection;
import com.example.lynceflix.models.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    public static List<Video> getAllVideos() {
        List<Video> videoList = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.getConnection();

            String query = "SELECT * FROM video";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Video video = new Video();
                    video.setId(resultSet.getInt("ID"));
                    video.setTitulo(resultSet.getString("Titulo"));
                    video.setDescripcion(resultSet.getString("Descripcion"));
                    video.setAutor(resultSet.getString("Autor"));
                    video.setDirector(resultSet.getString("Director"));
                    video.setURL(resultSet.getString("URL"));
                    video.setTipoContenido(resultSet.getString("TipoContenido"));
                    video.setNombreGenero(resultSet.getString("NombreGenero"));

                    videoList.add(video);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoList;
    }
}