package com.hanbat.football.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCtrl implements Initializable {

    @FXML
    private Button goPlayer;
    @FXML
    private Button goTeam;
    @FXML
    private Button goLeague;
    @FXML
    private Button goCountry;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goPlayer.setOnMouseClicked(event -> {
            System.out.println("선수 선수");
        });

        goTeam.setOnMouseClicked(event -> {
            System.out.println("팀 팀");
        });
    }
}
