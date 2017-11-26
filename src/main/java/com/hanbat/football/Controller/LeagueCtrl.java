package com.hanbat.football.Controller;

import com.hanbat.football.Model.League;
import com.hanbat.football.Model.Team;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeagueCtrl implements Initializable {

    League league = null;
    @FXML
    private TextField searchFilter;
    @FXML
    private ListView<League> list;
    @FXML
    private ImageView leagueLogoView;
    @FXML
    private Label leagueName;
    @FXML
    private Label leagueFoundation;
    @FXML
    private Hyperlink leagueHomepage;
    @FXML
    private TableView<TeamTableModel> teamTable;
    @FXML
    private TableColumn<TeamTableModel, Image> colLogo;
    @FXML
    private TableColumn<TeamTableModel, Integer> colRank;
    @FXML
    private TableColumn<TeamTableModel, String> colName;
    @FXML
    private TableColumn<TeamTableModel, String> colFoundationDay;

    public LeagueCtrl() {
    }

    LeagueCtrl(League league) {
        this.league = league;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (league != null) {
            setLeagueListView(league);
            setLeagueTableView(league);
        }

        leagueHomepage.setOnAction(event -> {
            try {
//                Desktop.getDesktop().browse(new URI(list.getSelectionModel().getSelectedItem().getHomepageUrl()));
                Desktop.getDesktop().browse(new URI(leagueHomepage.getText()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });


        ObservableList<League> data = FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getLeagues()));

        FilteredList<League> filteredList = new FilteredList<>(data, s -> true);

        searchFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(league -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (league.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (league.getEnglishName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<League> sortedList = new SortedList<>(filteredList);
        list.setItems(sortedList);

        list.setOnMouseClicked(event -> {
            League selected = list.getSelectionModel().getSelectedItem();

            setLeagueListView(selected);
            setLeagueTableView(selected);
        });
    }

    private void setLeagueListView(League league) {
        leagueLogoView.setImage(new Image(league.getFilepath()));
        leagueName.setText(league.getEnglishName() == null
                ? league.getName()
                : league.getName() + "\n" + league.getEnglishName());
        leagueFoundation.setText(new SimpleDateFormat("yyyy년 M월 d일").format(league.getFoundationDay()));
        leagueHomepage.setText(league.getHomepageUrl());
    }

    private void setLeagueTableView(League league) {
        ArrayList<TeamTableModel> tableData = new ArrayList<>();
        for (Team team : league.getTeams()) {
            tableData.add(new TeamTableModel(new SimpleStringProperty(team.getName()),
                    new SimpleIntegerProperty(team.getRank()),
                    new SimpleStringProperty(new SimpleDateFormat("yyyy년 M월 d일").format(team.getFoundationDay())),
                    new SimpleObjectProperty(new Image(getClass().getResourceAsStream(team.getLogoFilePath())))));
        }
        ObservableList<TeamTableModel> tableRowData = FXCollections.observableArrayList(tableData);

        colLogo.setCellValueFactory(cellData -> cellData.getValue().logo);
        colLogo.setCellFactory(param -> new TableCell<TeamTableModel, Image>() {
            ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                imageView.setImage(item);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                VBox vBox = new VBox(imageView);
                vBox.setAlignment(Pos.CENTER);
                setGraphic(vBox);
            }
        });
        colRank.setCellValueFactory(cellData -> cellData.getValue().rank.asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().name);
        colFoundationDay.setCellValueFactory(cellData -> cellData.getValue().foundationDay);
        teamTable.setItems(tableRowData);
    }

    private class TeamTableModel {
        StringProperty name;
        IntegerProperty rank;
        StringProperty foundationDay;
        ObjectProperty logo;

        private TeamTableModel(StringProperty name, IntegerProperty rank, StringProperty foundationDay, ObjectProperty logo) {
            this.name = name;
            this.rank = rank;
            this.foundationDay = foundationDay;
            this.logo = logo;
        }
    }
}
