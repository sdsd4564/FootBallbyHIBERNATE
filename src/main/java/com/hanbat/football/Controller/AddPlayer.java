package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Model.Player;
import com.hanbat.football.Model.Team;
import com.hanbat.football.Model.TeamPlayer;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

public class AddPlayer implements Initializable {

    File file = null;
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
        List<String> exFilter = new ArrayList<String>();
        exFilter.add("*.PNG");
        exFilter.add("*.JPG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG, JPG files (*.png, *.jpg)", exFilter));

        fileBtn.setOnMouseClicked(event -> {
            file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                fileName.setText(file.getName());
            }
        });

        /* 무게, 키 필드 소수만 받을 수 있도록 */
        height.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) height.setText(oldValue);
        });
        weight.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) weight.setText(oldValue);
        });

        team.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getTeams()))));

        country.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries())));

        footType.setItems(FXCollections.observableArrayList(FootType.values()));

        position.setItems(FXCollections.observableArrayList(Position.values()));

        confirm.setOnMouseClicked(event -> {
            if (height.getText().isEmpty() || weight.getText().isEmpty()
                    || name.getText().isEmpty() || birth.getValue() == null
                    || team.getSelectionModel().getSelectedItem() == null
                    || country.getSelectionModel().getSelectedItem() == null
                    || footType.getSelectionModel().getSelectedItem() == null
                    || position.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "작성 양식에 맞게 작성해주세요", ButtonType.CLOSE);
                alert.setTitle("선수 정보 입력 실패");
                alert.show();
            } else {
                if (file != null) {
                    try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("C:/Users/Encho/Desktop/football/src/main/resources/Images/player/" + file.getName()))) {
                        Files.copy(file.toPath(), fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Player addedPlayer = new Player(
                        name.getText(),
                        Date.from(Instant.from(birth.getValue().atStartOfDay(ZoneId.systemDefault()))),
                        !isFemale.isSelected(),
                        country.getSelectionModel().getSelectedItem(),
                        footType.getSelectionModel().getSelectedItem(),
                        position.getSelectionModel().getSelectedItem(),
                        Double.parseDouble(height.getText()),
                        Double.parseDouble(weight.getText()),
                        file == null ? null : "/Images/player/" + file.getName(),
                        new HashSet<>()
                );

                TeamPlayer teamPlayer = new TeamPlayer(team.getSelectionModel().getSelectedItem(), addedPlayer);
                addedPlayer.getTeams().add(teamPlayer);
                team.getSelectionModel().getSelectedItem().getPlayers().add(teamPlayer);
                DatabaseHelper.saveObject(teamPlayer, addedPlayer);

                root.getScene().getWindow().hide();
            }
        });
    }
}
