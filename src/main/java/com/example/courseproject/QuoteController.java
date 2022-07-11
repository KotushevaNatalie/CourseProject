package com.example.courseproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Connection;

import javax.swing.*;

import static com.example.courseproject.Controller.user;

public class QuoteController implements Initializable {
    int index = -1;

    Connection connection =null;
    PreparedStatement pS = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Quote> Quotes;

    @FXML
    private TableColumn<Quote, String> quote_date;

    @FXML
    private TableColumn<Quote, Integer> quote_id;

    @FXML
    private TableColumn<Quote, String> quote_quote;

    @FXML
    private TableColumn<Quote, String> quote_subject;

    @FXML
    private TableColumn<Quote, String> quote_teacher;

    @FXML
    private TableColumn<Quote, Integer> quote_id_user;

    @FXML
    private Button inform;

    @FXML
    private Button back;

    @FXML
    private TextField date;

    @FXML
    private TextField id;

    @FXML
    private TextField fio;

    @FXML
    private TextField quote;

    @FXML
    private TextField subject;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Quote();

        inform.setOnAction(actionEvent -> {
            FXMLLoader fxmloader = new FXMLLoader();
            fxmloader.setLocation(getClass().getResource("/com/example/courseproject/MyData.fxml"));

            try {
                fxmloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent p = fxmloader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
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

    @FXML
    private void getSelected (MouseEvent event){
        index = Quotes.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        id.setText(quote_id.getCellData(index).toString());
        quote.setText(quote_quote.getCellData(index));
        fio.setText(quote_teacher.getCellData(index));
        subject.setText(quote_subject.getCellData(index));
        date.setText(quote_date.getCellData(index).toString());

    }

    public void Update (){
        try {
            connection = ConnectionDataBase.Connect();

            String record = "UPDATE quote SET quote= '"+ quote.getText() +"',teacher= '"+ fio.getText() +
                    "',subject= '"+ subject.getText() +"',data= '"+ date.getText()  +"' WHERE id='"+
                    id.getText() +"' and access = " +user.getAccess();
            pS= connection.prepareStatement(record);
            pS.execute();

            Quote();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        connection = ConnectionDataBase.Connect();
        String sql = "DELETE FROM " + Table.table_qoute + " WHERE " + Table.table_qoute_id + " = ? AND access ="+user.getAccess();
        try {
            pS = connection.prepareStatement(sql);
            pS.setString(1, id.getText());
            pS.execute();
            Quote();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Insert (){
        connection = ConnectionDataBase.Connect();
        String record = "INSERT INTO "+ Table.table_qoute + " (" + Table.table_qoute_quote + "," + Table.table_quote_teacher +
                "," + Table.table_quote_subject + "," + Table.table_quote_data +"," + Table.table_quote_id_user +")VALUES(?,?,?,?,"+ user.getId() +")";
        try {
            pS = connection.prepareStatement(record);
            pS.setString(1, quote.getText());
            pS.setString(2, fio.getText());
            pS.setString(3, subject.getText());
            pS.setString(4, date.getText());
            pS.execute();

            Quote();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void Quote(){
        connection = ConnectionDataBase.Connect();
        ObservableList<Quote> list = FXCollections.observableArrayList();
        try {
            String record ="SELECT * FROM  quote "  ;
            pS = connection.prepareStatement(record);
            ResultSet result = pS.executeQuery();
            {
                while (result.next()) {
                    list.add(new Quote(Integer.parseInt(result.getString(Table.table_qoute_id)),
                            result.getString(Table.table_qoute_quote), result.getString(Table.table_quote_teacher),
                            result.getString(Table.table_quote_subject), result.getString(Table.table_quote_data),
                            Integer.parseInt(result.getString(Table.table_quote_id_user))));
                }
            }

            Quotes.setItems(list);
            quote_id.setCellValueFactory(new PropertyValueFactory<Quote,Integer>("id"));
            quote_quote.setCellValueFactory(new PropertyValueFactory<Quote,String>("quote"));
            quote_teacher.setCellValueFactory(new PropertyValueFactory<Quote,String>("teacher"));
            quote_subject.setCellValueFactory(new PropertyValueFactory<Quote,String>("subject"));
            quote_date.setCellValueFactory(new PropertyValueFactory<Quote,String>("data"));
            quote_id_user.setCellValueFactory(new PropertyValueFactory<Quote,Integer>("id_user"));

        }
        catch (Exception e) {
        }
    }

}

