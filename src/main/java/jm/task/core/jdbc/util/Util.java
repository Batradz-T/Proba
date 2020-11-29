package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
        public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/usersdb";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

}
