package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import static com.example.courseproject.Controller.user;

public class MyDataController implements Initializable {
    int index = -1;

    Connection connection =null;
    PreparedStatement pS = null;

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
    private TextField id;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    private TextField secondname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MyData();
    }

    public void Update (){
        try {
            connection = ConnectionDataBase.Connect();

            String record = "UPDATE user SET name= '"+ name.getText() +"',surname = '"+ secondname.getText() +"',patronymic = '"+ lastname.getText() +
                    "',login = '"+ login.getText() + "',hash_password = '"+ password.getText() + "' where id ='"+ id.getText() +
                    "' AND id = " + user.getId();
            pS = connection.prepareStatement(record);
            pS.execute();
            MyData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void MyData(){

        index = user.getId();
        id.setText(user.getId().toString());
        login.setText(user.getLogin());
        password.setText(user.getHash_password());
        name.setText(user.getName());
        secondname.setText(user.getSurname());
        lastname.setText(user.getPatronymic());


    }



}
