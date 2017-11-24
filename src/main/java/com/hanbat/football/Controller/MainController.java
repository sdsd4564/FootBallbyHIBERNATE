package com.hanbat.football.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label myMessage;

    public void onClick(ActionEvent event) {
        System.out.println("haha");
    }
}
