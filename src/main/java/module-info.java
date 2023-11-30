module com.example.lynceflix {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.example.lynceflix.ui;
    opens com.example.lynceflix to javafx.fxml;
    opens com.example.lynceflix.ui to javafx.fxml;
    exports com.example.lynceflix;
}