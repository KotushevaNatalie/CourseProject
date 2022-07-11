package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.courseproject.Controller.user;

public class SuperUserControllerMyData implements Initializable {
    int index = -1;

    Connection connection =null;
    PreparedStatement pS = null;

    @FXML
    private TextField id;

    @FXML
    private TextField lastname;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField secondname;

    @FXML
    private TableView<User> super_user;

    @FXML
    private TableColumn<User, Integer> super_id;

    @FXML
    private TableColumn<User, String> super_login;

    @FXML
    private TableColumn<User, String> super_name;

    @FXML
    private TableColumn<User, String> super_password;

    @FXML
    private TableColumn<User, String> super_patronymic;

    @FXML
    private TableColumn<User, String> super_surname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MyData();
    }

    @FXML
    private void getSelected (MouseEvent event){
        index = super_user.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        id.setText(super_id.getCellData(index).toString());
        secondname.setText(super_surname.getCellData(index));
        name.setText(super_name.getCellData(index));
        lastname.setText(super_patronymic.getCellData(index));
        login.setText(super_login.getCellData(index));
        password.setText(super_password.getCellData(index));
    }

    public void Update (){
        try {
            connection = ConnectionDataBase.Connect();

            String record = "UPDATE user SET name= '"+ name.getText() +"',surname = '"+ secondname.getText() +"',patronymic = '"+ lastname.getText() +
                    "',login = '"+ login.getText() + "',hash_password = '"+ password.getText() + "' where id ='"+ id.getText();
            pS = connection.prepareStatement(record);
            pS.execute();
            MyData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete(){
        connection = ConnectionDataBase.Connect();
        String record = "DELETE FROM " + Table.table_user + " WHERE " + Table.table_user_id + " = ? ";
        try {
            pS = connection.prepareStatement(record);
            pS.setString(1, super_id.getText());
            pS.execute();
            MyData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Insert (){
        connection = ConnectionDataBase.Connect();
        String record = "INSERT INTO "+ Table.table_user + " (" + Table.table_user_surname + "," + Table.table_user_name + "," +
                Table.table_user_patronymic + "," + Table.table_user_login +"," + Table.table_user_hash_password +" ) VALUES(?,?,?,?,?)";
        try {
            pS = connection.prepareStatement(record);
            pS.setString(1, super_surname.getText());
            pS.setString(2, super_name.getText());
            pS.setString(3, super_patronymic.getText());
            pS.setString(4, super_login.getText());
            pS.setString(5, super_password.getText());
            pS.execute();
            MyData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void MyData(){
        connection = ConnectionDataBase.Connect();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + Table.table_user);
            ResultSet result = ps.executeQuery();
            {
                while (result.next()) {
                    list.add(new User(Integer.parseInt(result.getString(Table.table_user_id)),
                            result.getString(Table.table_user_surname),
                            result.getString(Table.table_user_name),
                            result.getString(Table.table_user_patronymic),
                            result.getString(Table.table_user_login),
                            result.getString(Table.table_user_hash_password)));
                }
            }

            super_user.setItems(list);
            super_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
            super_login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
            super_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
            super_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            super_surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
            super_patronymic.setCellValueFactory(new PropertyValueFactory<User, String>("patronymic"));


        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
