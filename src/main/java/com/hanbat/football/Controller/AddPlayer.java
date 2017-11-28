package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Model.Player;
import com.hanbat.football.Model.Team;
import com.hanbat.football.Model.TeamPlayer;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

public class AddPlayer implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField name, height, weight;
    @FXML
    private CheckBox isFemale;
    @FXML
    private DatePicker birth;
    @FXML
    private ChoiceBox<Team> team;
    @FXML
    private ChoiceBox<Country> country;
    @FXML
    private ChoiceBox<FootType> footType;
    @FXML
    private ChoiceBox<Position> position;
    @FXML
    private Text fileName;
    @FXML
    private Button fileBtn;
    @FXML
    private Button confirm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("선수 사진을 선택해주세요");
        fileChooser.showOpenDialog(new Stage());

        /* 무게, 키 필드 소수만 받을 수 있도록 */
        height.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) height.setText(oldValue);
        });
        weight.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) weight.setText(oldValue);
        });

        ObservableList<Team> teamData = FXCollections.observableArrayList();
        Team nullData = new Team();
        nullData.setName("--선택하지 않음--");
        teamData.add(nullData);
        teamData.addAll(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getTeams())));
        team.setItems(teamData);

        country.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries())));

        footType.setItems(FXCollections.observableArrayList(FootType.values()));

        position.setItems(FXCollections.observableArrayList(Position.values()));

        /*confirm.setOnMouseClicked(event -> {
            Player addedPlayer = new Player(
                    name.getText(),
                    Date.from(Instant.from(birth.getValue().atStartOfDay(ZoneId.systemDefault()))),
                    !isFemale.isSelected(),
                    country.getSelectionModel().getSelectedItem(),
                    footType.getSelectionModel().getSelectedItem(),
                    position.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(height.getText()),
                    Double.parseDouble(weight.getText()),
                    *//*todo*//*null,
                    new HashSet<>()
            );
            addedPlayer.getTeams().add(new TeamPlayer(team.getSelectionModel().getSelectedItem(), addedPlayer));
            DatabaseHelper.saveObject(addedPlayer);
        });*/
    }
}
