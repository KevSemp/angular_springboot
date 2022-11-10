package com.example.simpleapi.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {

    public  Connection getConnection() throws Exception {

        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/banco";
            String username = "root";
            String password = "1234";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }
}
