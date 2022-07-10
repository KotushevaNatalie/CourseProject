package com.example.courseproject;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        authorized.setOnAction(actionEvent -> {
            String auth_login = login.getText().trim();
            String auth_password = password.getText().trim();
            if (!auth_login.equals("") || !auth_password.equals("")){
                User(auth_login, auth_password);
            }
            else{
                System.out.println("Логин или пароль были введены неверно");
            }
        });



    }

    private void User(String auth_login, String auth_password) {
        ConnectionDataBase db = new ConnectionDataBase();
        User user = new User();

        user.setLogin(auth_login);
        user.setHash_password(auth_password);
        ResultSet result = db.getUser(user);

        int k = 0;
        try {
            while (result.next()) {
                k++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(k >= 1){
            System.out.println("Успешный вход");
        }

    }

}
