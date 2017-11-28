package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCountry implements Initializable {

    File file = null;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField name, capitalName;
    @FXML
    private Button fileBtn, confirm;
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

        confirm.setOnMouseClicked(event -> {
            if (name.getText().isEmpty() || capitalName.getText().isEmpty() || file == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "작성 양식에 맞게 작성해주세요", ButtonType.CLOSE);
                alert.setTitle("국가 정보 입력 실패");
                alert.show();
            } else {
                try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("C:/Users/Encho/Desktop/football/src/main/resources/Images/country/" + file.getName()))) {
                    Files.copy(file.toPath(), fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Country country = new Country(name.getText(), capitalName.getText(), file == null ? null : "/Images/country/" + file.getName());
                DatabaseHelper.saveObject(country);

                root.getScene().getWindow().hide();
            }
        });
    }
}
