package com.hanbat.football.Controller;

import com.hanbat.football.Model.Player;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerCtrl implements Initializable {

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
    private AnchorPane imageLayout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Player> players = new ArrayList<>(DatabaseHelper.getPlayerNames());
        ObservableList<Player> rawData = FXCollections.observableArrayList(players);
        FilteredList<Player> filteredList = new FilteredList<Player>(rawData, p -> true);
        list.setItems(filteredList);
        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(player -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return player.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        list.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return new ListCell<Player>() {
                    @Override
                    protected void updateItem(Player t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
                        }
                    }

                };
            }
        });

        playerImageView.setImage(new Image(getClass().getResourceAsStream("/Images/sample.jpg")));

//        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (oldValue != null && (newValue.length() < oldValue.length())) {
//                list.setItems(rawData);
//            }
//            String value = newValue.toUpperCase();
//            ObservableList<String> subentries = FXCollections.observableArrayList();
//            for (Object entry : list.getItems()) {
//                boolean match = true;
//                String entryText = (String) entry;
//                if (!entryText.toUpperCase().contains(value)) {
//                    match = false;
//                }
//                if (match) {
//                    subentries.add(entryText);
//                }
//            }
//            list.setItems(subentries);
//        });

        list.setOnMouseClicked(event -> {
            Player selected = list.getSelectionModel().getSelectedItem();
            setPlayerView(selected);
        });
    }

    public void setPlayerView(Player player) {
        playerName.setText(player.getName());
        playerBirth.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(player.getBirth()));
        playerBody.setText(player.getHeight() + "cm\n/" + player.getWeight() + "kg");
        playerPosition.setText(player.getPosition().toString() + "(" + player.getPosition().getDescription() + ")");
        playerFoot.setText(player.getFootType().getDescription());
    }
}
