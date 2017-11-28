package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.League;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddLeague implements Initializable {
    File file = null;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField name, englishName, url;
    @FXML
    private DatePicker foundationDay;
    @FXML
    private ChoiceBox<Country> country;
    @FXML
    private Button fileBtn, confirm;
    @FXML
    private Text fileName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("리그 로고 사진을 선택해주세요");
        List<String> exFilter = new ArrayList<>();
        exFilter.add("*.PNG");
        exFilter.add("*.JPG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG, JPG files (*.png, *.jpg)", exFilter));

        fileBtn.setOnMouseClicked(event -> {
            file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                fileName.setText(file.getName());
            }
        });

        country.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries())));

        confirm.setOnMouseClicked(event -> {
            if (name.getText().isEmpty() || englishName.getText().isEmpty() || foundationDay.getValue() == null || url.getText().isEmpty()
                    || country.getSelectionModel().getSelectedItem() == null || file == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "작성 양식에 맞게 작성해주세요", ButtonType.CLOSE);
                alert.setTitle("리그 정보 입력 실패");
                alert.show();
            } else {
                try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("C:/Users/Encho/Desktop/football/src/main/resources/Images/league/" + file.getName()))) {
                    Files.copy(file.toPath(), fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                League league = new League(name.getText(), country.getSelectionModel().getSelectedItem(),
                        Date.from(Instant.from(foundationDay.getValue().atStartOfDay(ZoneId.systemDefault()))),
                        "/Images/league/" + file.getName(), englishName.getText(), url.getText());
                DatabaseHelper.saveObject(league);

                root.getScene().getWindow().hide();
            }
        });
    }
}
