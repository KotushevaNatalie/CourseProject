package com.example.courseproject;

import java.sql.*;


public class ConnectionDataBase {

    public static Connection Connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://std-mysql:3306/std_1993_courseproject","std_1993_courseproject","12345678");
            return connect;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public void registration(String name, String surname, String patronymic, String login, String hash_password ){

        String record = "INSERT INTO " + Table.table_user + "(" + Table.table_user_name + "," + Table.table_user_surname + ","
                + Table.table_user_patronymic + "," + Table.table_user_login + "," + Table.table_user_hash_password
                 + ")" + "VALUES(?, ?, ?, ?, ?)";

        try{
            PreparedStatement pS = Connect().prepareStatement(record);// передаем наш запрос

            pS.setString(1, login);
            pS.setString(2, hash_password);
            pS.setString(3, name);// параметры
            pS.setString(4, surname);
            pS.setString(5, patronymic);


            pS.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){// возвращаем результаты запроса в бд
        ResultSet result = null;

        String record = "SELECT * FROM " + Table.table_user + " WHERE " + Table.table_user_login + "=? AND " +
                Table.table_user_hash_password + "=?";

        try{
            PreparedStatement pS = Connect().prepareStatement(record);// передаем наш запрос
            pS.setString(1, user.getLogin());// параметры
            pS.setString(2, user.getHash_password());

            result = pS.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }





}
