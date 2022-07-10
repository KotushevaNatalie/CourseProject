package com.example.courseproject;

import java.sql.*;


public class ConnectionDataBase extends Server{
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String connect = "jdbc:mysql://" + host + ":" + port + "/" + basedate;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connect, user, password);

        return connection;
    }

    public void registration( User user){

        String record = "INSERT INTO " + Table.table_user + "(" + Table.table_user_name + "," + Table.table_user_surname + ","
                + Table.table_user_patronymic + "," + Table.table_user_login + "," + Table.table_user_hash_password
                 + ")" + "VALUES(?, ?, ?, ?, ?)";// sql запрос, с помощью которого помещаем данные в бд

        try{
            PreparedStatement pS = getConnection().prepareStatement(record);// передаем наш запрос
            pS.setString(3, user.getName());// параметры
            pS.setString(4, user.getSurname());
            pS.setString(5, user.getPatronymic());
            pS.setString(1, user.getLogin());
            pS.setString(2, user.getHash_password());

            pS.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){// возвращаем результаты запроса в бд
        ResultSet result = null;

        String record = "SELECT * FROM " + Table.table_user + " WHERE " + Table.table_user_login + "=? AND " + Table.table_user_hash_password + "=?";

        try{
            PreparedStatement pS = getConnection().prepareStatement(record);// передаем наш запрос
            pS.setString(1, user.getLogin());// параметры
            pS.setString(2, user.getHash_password());

            result = pS.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return result;
    }





}
