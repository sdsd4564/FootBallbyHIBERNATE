package com.hanbat.football.Controller;

import com.hanbat.football.Model.Player;
import com.hanbat.football.Model.Team;
import com.hanbat.football.Model.TeamPlayer;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamCtrl implements Initializable {

    Team team = null;

    @FXML
    private AnchorPane teamViewInfo;
    @FXML
    private TextField searchFiled, playerSearchFiled;
    @FXML
    private ImageView teamImageView;
    @FXML
    private ListView<Team> teamList;
    @FXML
    private ListView<Player> playerList;

    @FXML
    private Label teamName;
    @FXML
    private Text teamBirth;
    @FXML
    private Text teamCoach;
    @FXML
    private Text teamRank;
    @FXML
    private AnchorPane leagueLayout;
    @FXML
    private ImageView teamLeagueImageView;
    @FXML
    private Label teamLeagueName;

    public TeamCtrl() {
    }

    public TeamCtrl(Team team) {
        this.team = team;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (team != null) {
            setTeamView(team);
            setPlayerList(team);
        } else
            teamImageView.setImage(new Image(getClass().getResourceAsStream("/Images/logo.jpg")));


        ObservableList<Team> rawData = FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getTeams()));
        FilteredList<Team> filteredList = new FilteredList<>(rawData, data -> true);
        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(team -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                return team.getName().toLowerCase().contains(newValue.toLowerCase());
            });
        });
        teamList.setItems(new SortedList<>(filteredList));

        teamList.setOnMouseClicked((MouseEvent event) -> {
            team = teamList.getSelectionModel().getSelectedItem();
            setTeamView(team);
            setPlayerList(team);
        });

        playerList.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY && event1.getClickCount() == 2) {
                setTeamToAnothorWindow(PlayerCtrl.class, "../View/player.fxml", new PlayerCtrl(playerList.getSelectionModel().getSelectedItem()), "선수 검색");
            }
        });
    }

    private void setTeamView(Team team) {
        if (!teamViewInfo.isVisible()) teamViewInfo.setVisible(true);

        teamImageView.setImage(setImageToView(team.getLogoFilePath()));
        teamName.setText(team.getName());
        teamCoach.setText(team.getCoach());
        teamBirth.setText(new SimpleDateFormat("yyyy년 M월 d일").format(team.getFoundationDay()));
        teamRank.setText(Integer.toString(team.getRank()) + "위");
        leagueLayout.setOnMouseClicked(event -> {
            setTeamToAnothorWindow(LeagueCtrl.class, "../View/league.fxml", new LeagueCtrl(team.getLeague()), "리그 검색");
        });
        teamLeagueImageView.setImage(setImageToView(team.getLeague().getFilepath()));
        teamLeagueName.setText(team.getLeague().getName());
    }

    private void setPlayerList(Team team) {
        ObservableList<Player> playerRawData = FXCollections.observableArrayList();
        for (TeamPlayer teamPlayer : team.getPlayers()) {
            playerRawData.add(teamPlayer.getPlayer());
        }

        FilteredList<Player> filteredPlayer = new FilteredList<>(playerRawData);
        playerSearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPlayer.setPredicate(player -> newValue == null || newValue.isEmpty() || player.getName().toLowerCase().contains(newValue.toLowerCase()));
        });
        SortedList<Player> sortedPlayer = new SortedList<>(filteredPlayer);

        playerList.setItems(sortedPlayer);
    }

    private Image setImageToView(String filepath) {
        InputStream image = getClass().getResourceAsStream(filepath == null ? "/Images/player/sample.jpg" : filepath);
        return new Image(image);
    }

    private void setTeamToAnothorWindow(Class type, String fxmlPath, Initializable controller, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory((Class<?> controllerType) -> {
                if (controllerType == type) {
                    return controller;
                } else {
                    try {
                        return controllerType.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Parent parent = loader.load();
//            Stage stage = new Stage();
            Stage stage = (Stage) teamViewInfo.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}