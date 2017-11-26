package com.hanbat.football.Controller;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerCtrl implements Initializable {

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
    private Label playerTeam;
    @FXML
    private ImageView playerTeamView;
    @FXML
    private AnchorPane leagueLayout;
    @FXML
    private Label playerLeague;
    @FXML
    private ImageView playerLeagueView;
    @FXML
    private Label playerCountry;
    @FXML
    private ImageView playerCountryView;

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
            if (!playerInfoView.isVisible()) playerInfoView.setVisible(true);
            Player selected = list.getSelectionModel().getSelectedItem();
            setPlayerView(selected);
        });

        leagueLayout.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/league.fxml"));
//                LeagueCtrl ctrl = new LeagueCtrl(list.getSelectionModel().getSelectedItem().getTeams().iterator().next().getTeam().getLeague());
//                loader.setController(ctrl);
                loader.setControllerFactory((Class<?> controllerType) -> {
                    if (controllerType == LeagueCtrl.class) {
                        LeagueCtrl controller = new LeagueCtrl(list.getSelectionModel().getSelectedItem().getTeams().iterator().next().getTeam().getLeague());
                        return controller ;
                    } else {
                        try {
                            return controllerType.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
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

    private void setPlayerView(Player player) {
        playerName.setText(player.getName());
        playerBirth.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(player.getBirth()));
        playerBody.setText(player.getHeight() + "cm\n/" + player.getWeight() + "kg");
        playerPosition.setText(player.getPosition().toString() + "(" + player.getPosition().getDescription() + ")");
        playerFoot.setText(player.getFootType().getDescription());
        playerImageView.setImage(setImageToView(player.getFilePath()));

        for (TeamPlayer teamPlayer : player.getTeams()) {
            playerTeam.setText(teamPlayer.getTeam().getName());
            playerTeamView.setImage(new Image(getClass().getResourceAsStream(teamPlayer.getTeam().getLogoFilePath())));
        }

        playerLeague.setText(player.getTeams().iterator().next().getTeam().getLeague().getName());
        playerLeagueView.setImage(setImageToView(player.getTeams().iterator().next().getTeam().getLeague().getFilepath()));
        playerCountry.setText(player.getCountry().getName());
        playerCountryView.setImage(setImageToView(player.getCountry().getFilePath())); // todo : 이미지가 존재하지 않을 때 띄울 사진 요망
    }

    private Image setImageToView(String filepath) {
        InputStream image = getClass().getResourceAsStream(filepath == null ? "/Images/player/sample.jpg" : filepath);
        return new Image(image);
    }
}
