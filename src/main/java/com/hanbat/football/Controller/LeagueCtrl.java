package com.hanbat.football.Controller;

import com.hanbat.football.Model.League;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public LeagueCtrl() {
    }

    public LeagueCtrl(League league) {
        this.league = league;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (league != null) {
            setLeagueView(league);
        }
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

            setLeagueView(selected);
        });
    }

    private void setLeagueView(League league) {
        leagueLogoView.setImage(new Image(league.getFilepath()));
        leagueName.setText(league.getEnglishName() == null
                ? league.getName()
                : league.getName() + "\n" + league.getEnglishName());
        leagueFoundation.setText(new SimpleDateFormat("yyyy년 M월 d일").format(league.getFoundationDay()));
        leagueHomepage.setText(league.getHomepageUrl());
    }
}
