package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_USERNAME = "RootMan";
    private static final String DB_PASSWORD = "123456987";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/firsttask?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Есть связь с БД!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к БД!");
        }
        return connection;
    }
}
