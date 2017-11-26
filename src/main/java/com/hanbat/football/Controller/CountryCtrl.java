package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CountryCtrl implements Initializable {
    @FXML
    private ListView<Country> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setOrientation(Orientation.HORIZONTAL);
        ObservableList<Country> rawDate = FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries()));
        list.setItems(rawDate);
        list.setCellFactory(listView -> new ListCell<Country>() {
            ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                imageView.setImage(new Image(getClass().getResourceAsStream("/Images/player/sample.jpg")));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                setGraphic(imageView);
            }
        });

//        list.setCellFactory(param -> new ListCell<Country>() {
//            ImageView imageView = new ImageView();
//
//            @Override
//            protected void updateItem(Country item, boolean empty) {
//                super.updateItem(item, empty);
//                imageView.setImage(new Image(getClass().getResourceAsStream(item.getFilePath())));
//                imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//                Label label = new Label(item.getName());
//                VBox vBox = new VBox(label);
//                setGraphic(vBox);
//            }
//        });

    }
}
