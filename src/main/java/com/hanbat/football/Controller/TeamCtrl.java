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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sun.security.provider.ConfigFile;

import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamCtrl implements Initializable {

    String teamNameStr;

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
    private Text teamName;
    @FXML
    private Text teamBirth;
    @FXML
    private Text teamCoach;
    @FXML
    private Text teamRank;


/*
    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, String> teamCoachColumn;
    @FXML
    private TableColumn<Team, String> teamRankColumn;
*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Team> rawData = FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getTeamData()));
        FilteredList<Team> filteredList = new FilteredList<Team>(rawData, data -> true);
        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(team -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return team.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        teamList.setItems(new SortedList<Team>(filteredList));

        teamList.setOnMouseClicked(event -> {
            if (!teamViewInfo.isVisible()) teamViewInfo.setVisible(true);
            Team selected = teamList.getSelectionModel().getSelectedItem();
            setTeamView(selected);


            ObservableList<Player> playerRawData = FXCollections.observableArrayList();
            for (TeamPlayer teamPlayer : selected.getPlayers()) {
                playerRawData.add(teamPlayer.getPlayer());
            }
            FilteredList<Player> filteredPlayer = new FilteredList<Player>(playerRawData);
            playerSearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredPlayer.setPredicate(player ->  {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

//                    return team.getName().toLowerCase().contains(lowerCaseFilter);
                    return player.getName().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<Player> sortedPlayer = new SortedList<Player>(filteredPlayer);
            playerList.setItems(sortedPlayer);
        });

/*        teamList.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>() {
            @Override
            public ListCell<Team> call(ListView<Team> param) {
                return new ListCell<Team>() {
                    @Override
                    protected void updateItem(Team t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
                        }
                    }
                };
            }
        })*/;

//        playerList.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>() {
//            @Override
//            public ListCell<Team> call(ListView<Team> param) {
//                return new ListCell<Team>() {
//                    @Override
//                    protected void updateItem(Team t, boolean bln) {
//                        super.updateItem(t, bln);
//                        if (t != null) {
//                            setText(t.getPlayers().iterator().next().getPlayer().toString());
//                            teamNameStr = t.getName();
//                            System.out.print(teamNameStr);
//                        }
//                    }
//                };
//            }
//        });

        teamImageView.setImage(new Image(getClass().getResourceAsStream("/Images/logo.jpg")));

    }

    public void setTeamView(Team team) {
        teamName.setText(team.getName());
        teamCoach.setText(team.getCoach());
        teamBirth.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(team.getBirth()));
        teamRank.setText(Integer.toString(team.getRank()));

        for (TeamPlayer teamPlayer : team.getPlayers()) {
        }

    }

/*
    public void setTeamView(Team team) {
        teamNameColumn.setCellFactory(new PropertyValueFactory<>(team.getName()));
        teamCoachColumn.setCellFactory(new PropertyValueFactory<>(team.getCoach()));
        teamRankColumn.setCellFactory(new PropertyValueFactory<>(team.getRank()));
    }
*/
}
