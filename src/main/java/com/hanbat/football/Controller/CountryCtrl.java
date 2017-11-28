package com.hanbat.football.Controller;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.League;
import com.hanbat.football.Util.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CountryCtrl implements Initializable {
    Country country;
    @FXML
    private ListView<Country> list;
    @FXML
    private ListView<League> leagueList;
    @FXML
    private ImageView countryImage;
    @FXML
    private Label countryName, capitalName;

    public CountryCtrl() {
    }

    public CountryCtrl(Country country) {
        this.country = country;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (country != null) {
            System.out.println("country initialize");
            setCountryLayout(country);
        }

        list.setOrientation(Orientation.HORIZONTAL);
        list.setCellFactory(param -> new ListCell<Country>() {
            ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else {
                    imageView.setImage(new Image(getClass().getResourceAsStream(item.getFilePath() != null ? item.getFilePath() : "/Images/logo.png")));
                    imageView.setFitWidth(120);
                    imageView.setFitHeight(80);
                    Label label = new Label(item.getName());
                    VBox vBox = new VBox(imageView, label);
                    vBox.setAlignment(Pos.CENTER);
                    setGraphic(vBox);
                }
            }
        });
        list.setItems(FXCollections.observableArrayList(new ArrayList<>(DatabaseHelper.getCountries())));

        leagueList.setCellFactory(param -> new ListCell<League>() {
            ImageView imageView = new ImageView();

            @Override
            protected void updateItem(League item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else {
                    imageView.setImage(new Image(getClass().getResourceAsStream(item.getFilepath())));
                    imageView.setFitWidth(48);
                    imageView.setFitHeight(27);
                    Label label = new Label(item.getName());
                    HBox hBox = new HBox(imageView, label);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    HBox.setMargin(imageView, new Insets(8, 16, 8, 8));
                    setGraphic(hBox);
                }
            }
        });

        /* 국가 가로 목록 아이템 클릭 **/
        list.setOnMouseClicked(event -> {
            country = list.getSelectionModel().getSelectedItem();
            setCountryLayout(country);
        });

        leagueList.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/league.fxml"));
                    loader.setControllerFactory((Class<?> controllerType) -> {
                        if (controllerType == LeagueCtrl.class) {
                            return new LeagueCtrl(leagueList.getSelectionModel().getSelectedItem());
                        } else {
                            try {
                                return controllerType.newInstance();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    Parent parent = null;
                    parent = loader.load();
                    Stage stage = (Stage) list.getScene().getWindow();
//                    Stage stage = new Stage();
                    stage.setTitle("리그 검색");
                    stage.setScene(new Scene(parent));
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setCountryLayout(Country country) {
        countryImage.setImage(new Image(getClass().getResourceAsStream(
                country.getFilePath() == null
                        ? "/Images/logo.jpg"
                        : country.getFilePath())));
        countryName.setText(country.getName());
        capitalName.setText(country.getCapital());
        leagueList.setItems(FXCollections.observableArrayList(new ArrayList<>(country.getLeagues())));
    }
}
