package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.League;
import com.hanbat.football.Model.Team;
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

public class AddTeam implements Initializable {
    File file = null;
    @FXML
    private AnchorPane root;
    @FXML
    private Button confirm, fileBtn;
    @FXML
    private DatePicker foundationDay;
    @FXML
    private TextField name, rank, coach;
    @FXML
    private ChoiceBox<League> league;
    @FXML
    private ChoiceBox<Country> country;
    @FXML
    private Text fileName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("국가 사진을 선택해주세요");
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

        rank.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) rank.setText(oldValue);
        });


        league.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getLeagues())));

        country.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries())));

        confirm.setOnMouseClicked(event -> {
            if (name.getText().isEmpty() || rank.getText().isEmpty() || file == null || coach.getText().isEmpty()
                    || league.getSelectionModel().getSelectedItem() == null
                    || country.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "작성 양식에 맞게 작성해주세요", ButtonType.CLOSE);
                alert.setTitle("구단 정보 입력 실패");
                alert.show();
            } else {
                try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("C:/Users/Encho/Desktop/football/src/main/resources/Images/team/" + file.getName()))) {
                    Files.copy(file.toPath(), fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Team team = new Team(name.getText(), coach.getText(), Integer.parseInt(rank.getText()),
                        "/Images/team/" + file.getName(),
                        Date.from(Instant.from(foundationDay.getValue().atStartOfDay(ZoneId.systemDefault()))),
                        country.getSelectionModel().getSelectedItem(),
                        null,
                        league.getSelectionModel().getSelectedItem(),
                        new HashSet<>());

                DatabaseHelper.saveObject(team);
                root.getScene().getWindow().hide();
            }
        });
    }
}
