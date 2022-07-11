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
    public static User user = new User();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorized;

    @FXML
    private Button guest;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;


    @FXML
    void initialize() {

        registration.setOnAction(actionEvent -> {
            Parent p = null;
            try {
                p = FXMLLoader.load(getClass().getResource("registr.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) registration.getScene().getWindow();
            stage.setScene(new Scene(p));
        });

        authorized.setOnAction(actionEvent -> {
            String auth_login = login.getText().trim();
            String auth_password = password.getText().trim();
            if (!auth_login.equals("") || !auth_password.equals("")){
                try {
                    User(auth_login, auth_password);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                System.out.println("Логин или пароль были введены неверно");
            }
        });

        guest.setOnAction(actionEvent -> {
            Parent p = null;
            try {
                p = FXMLLoader.load(getClass().getResource("quote.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) registration.getScene().getWindow();
            stage.setScene(new Scene(p));
        });

    }

    public void User(String auth_login, String auth_password) throws IOException {
        ConnectionDataBase db = new ConnectionDataBase();
        User user = new User();

        user.setLogin(auth_login);
        user.setHash_password(auth_password);
        ResultSet result = db.getUser(user);

        int k = 0;
        try {
            while (result.next()) {
                this.user = user;
                user.setId(result.getInt(1));
                user.setLogin(result.getString(2));
                user.setHash_password(result.getString(3));
                user.setName(result.getString(4));
                user.setSurname(result.getString(5));
                user.setPatronymic(result.getString(6));
                user.setAccess(result.getInt(7));
                k++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user.getAccess() == 3) {
            Parent root = FXMLLoader.load(getClass().getResource("SuperUser.fxml"));
            Stage stage = (Stage) authorized.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("quote.fxml"));
            Stage stage = (Stage) authorized.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

}
