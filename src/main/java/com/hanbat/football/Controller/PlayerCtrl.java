package com.hanbat.football.Controller;

import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerCtrl implements Initializable {

    @FXML
    private TextField searchFiled;
    @FXML
    private ImageView playerImageView;
    @FXML
    private ListView<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> rawData = FXCollections.observableArrayList(DatabaseHelper.getPlayerNames());
        FilteredList<String> filteredList = new FilteredList<>(rawData, data -> true);
        list.setItems(filteredList);

        playerImageView.setImage(new Image(getClass().getResourceAsStream("/Images/sample.jpg")));

        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                list.setItems(rawData);
            }
            String value = newValue.toUpperCase();
            ObservableList<String> subentries = FXCollections.observableArrayList();
            for (Object entry : list.getItems()) {
                boolean match = true;
                String entryText = (String) entry;
                if (!entryText.toUpperCase().contains(value)) {
                    match = false;
                }
                if (match) {
                    subentries.add(entryText);
                }
            }
            list.setItems(subentries);
        });
    }
}
