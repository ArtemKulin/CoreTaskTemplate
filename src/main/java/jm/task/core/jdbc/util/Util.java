package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;

    public static Connection getConnection() {
        Properties settings = new Properties();
        settings.put("user", USER);
        settings.put("password", PASS);
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,settings);
        } catch (ClassNotFoundException | SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
