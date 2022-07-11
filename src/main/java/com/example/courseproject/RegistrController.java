package com.example.courseproject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    private TextField leader;

    @FXML
    private Button back;

    @FXML
    private TextField secondname;

    @FXML
    void initialize() {
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();

        registration.setOnAction(actionEvent -> {
            String u_name = name.getText();
            String u_secondname = secondname.getText();
            String u_lastname = lastname.getText();
            String u_login = login.getText();
            String u_password = password.getText();

            connectionDataBase.registration(u_lastname, u_login, u_password, u_name,  u_secondname);

        });

        back.setOnAction(actionEvent -> {
            Parent p = null;
            try {
                p = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(p));
        });



    }
/*
    public String hash_password(){
        String u_password = password.getText();
        byte[] bytes = new byte[16];

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(u_password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String hash = new String(bytes, StandardCharsets.UTF_8);;

        return hash;
    }

 */





}


