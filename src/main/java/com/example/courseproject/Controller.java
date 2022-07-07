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

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorized;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        registration.setOnAction(actionEvent -> {registration.getScene().getWindow().hide();
            FXMLLoader fxmloader = new FXMLLoader();
            fxmloader.setLocation(getClass().getResource("/com/example/courseproject/registr.fxml"));

            try {
                fxmloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = fxmloader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        });

    }

}
