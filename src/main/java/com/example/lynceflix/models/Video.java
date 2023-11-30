package com.example.lynceflix.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty titulo = new SimpleStringProperty();
    private final SimpleStringProperty descripcion = new SimpleStringProperty();
    private final SimpleStringProperty autor = new SimpleStringProperty();
    private final SimpleStringProperty director = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    private final SimpleStringProperty tipoContenido = new SimpleStringProperty();
    private final SimpleStringProperty nombreGenero = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitulo() {
        return titulo.get();
    }

    public SimpleStringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public SimpleStringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getAutor() {
        return autor.get();
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public void setURL(String url) {
        this.url.set(url);
    }

    public String getTipoContenido() {
        return tipoContenido.get();
    }

    public SimpleStringProperty tipoContenidoProperty() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido.set(tipoContenido);
    }

    public String getNombreGenero() {
        return nombreGenero.get();
    }

    public SimpleStringProperty nombreGeneroProperty() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero.set(nombreGenero);
    }
}
