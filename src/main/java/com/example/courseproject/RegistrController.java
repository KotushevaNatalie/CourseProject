package com.example.courseproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastname;

    @FXML
    private TextField loginr;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordr;

    @FXML
    private Button registration;

    @FXML
    private TextField secondname;

    @FXML
    void initialize() {
        registration.setOnAction(actionEvent -> {
            registration.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/courseproject/hello-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent p = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
        });
    }
}


