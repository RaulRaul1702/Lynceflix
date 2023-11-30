package com.example.lynceflix;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;

    }
}