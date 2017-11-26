package com.hanbat.football.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
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
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/player.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("선수 검색");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        goTeam.setOnMouseClicked(event -> {
            System.out.println("팀 팀");
        });

        goLeague.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/league.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("리그 검색");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
