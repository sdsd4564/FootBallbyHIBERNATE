package com.hanbat.football.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
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
    private MenuItem menuItem1, menuItem2, menuItem3, menuItem4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuItem1.setOnAction(event -> {
            openAnothorWindow("../View/add_player.fxml", "선수 정보 추가");
        });

        menuItem2.setOnAction(event -> {
            openAnothorWindow("../View/add_country.fxml", "국가 정보 추가");
        });

        menuItem3.setOnAction(event -> {
            openAnothorWindow("../View/add_team.fxml", "구단 정보 추가");
        });
        menuItem4.setOnAction(event -> {
            openAnothorWindow("../View/add_league.fxml", "리그 정보 추가");
        });

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
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/Team.fxml"));
                Parent parent1 = loader.load();
                Stage stage = new Stage();
                stage.setTitle("구단 검색");
                stage.setScene(new Scene(parent1));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        goCountry.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/country.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("국가 검색");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openAnothorWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
