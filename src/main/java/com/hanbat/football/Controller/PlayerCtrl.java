package com.hanbat.football.Controller;

import com.hanbat.football.Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerCtrl implements Initializable {

    @FXML
    private TextField searchFiled;
    @FXML
    private ListView<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> rawData = FXCollections.observableArrayList();
//        FilteredList<Player> filteredList = new FilteredList<String>(rawData, data -> true);
//        list.setItems(filteredList);
//        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(data -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseSearch = newValue.toLowerCase();
//                return data.contains(lowerCaseSearch);
//            });
//        });
    }
}
