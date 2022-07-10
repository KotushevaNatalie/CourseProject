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
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();

        registration.setOnAction(actionEvent -> {
            String u_name = name.getText();
            String u_secondname = secondname.getText();
            String u_lastname = lastname.getText();
            String u_loginr = loginr.getText();
            String u_passwordr = passwordr.getText();

            User user = new User(u_name, u_secondname, u_lastname, u_loginr, u_passwordr);
            connectionDataBase.registration( user);
        });




    }


}


