package com.hanbat.football.Controller;

import com.hanbat.football.Main;
import com.hanbat.football.Model.Player;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerCtrl implements Initializable {
    Player player = null;
    @FXML
    private AnchorPane playerInfoView;
    @FXML
    private TextField searchFiled;
    @FXML
    private ImageView playerImageView;
    @FXML
    private ListView<Player> list;
    @FXML
    private Text playerName;
    @FXML
    private Text playerBirth;
    @FXML
    private Text playerBody;
    @FXML
    private Text playerPosition;
    @FXML
    private Text playerFoot;
    @FXML
    private Label playerTeam, playerLeague, playerCountry;
    @FXML
    private AnchorPane teamLayout, leagueLayout, countryLayout;
    @FXML
    private ImageView playerTeamView, playerLeagueView, playerCountryView;

    public PlayerCtrl() {

    }

    public PlayerCtrl(Player player) {
        this.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Player> rawData = FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getPlayerNames()));
        FilteredList<Player> filteredList = new FilteredList<Player>(rawData, p -> true);

        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(player -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return player.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        list.setItems(new SortedList<Player>(filteredList));


        // todo : 체크 아무것도 안했을 때 띄워줄 이미지 요망
        playerImageView.setImage(new Image(getClass().getResourceAsStream("/Images/player/sample.jpg")));


        list.setOnMouseClicked(event -> {
            player = list.getSelectionModel().getSelectedItem();
            setPlayerView(player);
        });

        /*
        * 선수의 팀 클릭 이벤트, 팀 검색 윈도우로 이동
        **/
        teamLayout.setOnMouseClicked(event -> {
            setPlayerToAnotherWindow("../View/Team.fxml", new TeamCtrl(player.getTeams().iterator().next().getTeam()), "팀 검색", TeamCtrl.class);
        });

        /*
        * 선수의 리그 클릭 이벤트, 리그 검색 윈도우로 이동
        **/
        leagueLayout.setOnMouseClicked(event -> {
            setPlayerToAnotherWindow("../View/league.fxml", new LeagueCtrl(player.getTeams().iterator().next().getTeam().getLeague()), "리그 검색", LeagueCtrl.class);
        });

        /*
        * 선수의 국가 클릭 이벤트, 국가 검색 윈도우로 이동
        **/
        countryLayout.setOnMouseClicked(event -> {
            setPlayerToAnotherWindow("../View/country.fxml", new CountryCtrl(player.getCountry()), "국가 검색", CountryCtrl.class);
        });

        /*
        * 메인이 아닌 다른 곳에서 선수 검색 창을 불러올 때
        **/
        if (player != null) {
            setPlayerView(player);
        }
    }

    private void setPlayerView(Player player) {
        if (!playerInfoView.isVisible())
            playerInfoView.setVisible(true);

        playerName.setText(player.getName());
        playerBirth.setText(new SimpleDateFormat("yyyy년 M월 d일").format(player.getBirth()));
        playerBody.setText(player.getHeight() + "cm\n/" + player.getWeight() + "kg");
        playerPosition.setText(player.getPosition().toString() + "(" + player.getPosition().getDescription() + ")");
        playerFoot.setText(player.getFootType().getDescription());
        playerImageView.setImage(setImageToView(player.getFilePath()));

        for (TeamPlayer teamPlayer : player.getTeams()) {
            playerTeam.setText(teamPlayer.getTeam().getName());
            playerTeamView.setImage(new Image(getClass().getResourceAsStream(teamPlayer.getTeam().getLogoFilePath())));
        }

        playerLeague.setText(player.getTeams().iterator().next().getTeam().getLeague() == null
                ? "NO ITEM"
                : player.getTeams().iterator().next().getTeam().getLeague().getName());
        playerLeagueView.setImage(setImageToView(player.getTeams().iterator().next().getTeam().getLeague().getFilepath()));
        playerCountry.setText(player.getCountry().getName());
        playerCountryView.setImage(setImageToView(player.getCountry().getFilePath())); //todo : 이미지가 존재하지 않을 때 띄울 사진 요망
    }

    private Image setImageToView(String filepath) {
        try (InputStream fis = new FileInputStream(Main.ABSOLUTE_PATH +filepath)) {
            return new Image(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setPlayerToAnotherWindow(String fxmlPath, Initializable controller, String title, Class type) {
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
            Stage stage = (Stage) teamLayout.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
