package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String usersTable = "users";
    private String sqlQuery;
    Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getGDBCConnection();
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20), LastName VARCHAR(20), Age int)",
                usersTable);

        try {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        sqlQuery = String.format("DROP TABLE IF EXISTS %s", usersTable);
        try {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
             e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        sqlQuery = String.format("INSERT INTO %s (name, lastname, age) values (?, ?, ?)", usersTable);
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        sqlQuery = String.format("DELETE FROM %s WHERE ID = %d", usersTable, id);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        sqlQuery = String.format("SELECT * FROM %s", usersTable);
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getByte(4)));

            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        sqlQuery = String.format("truncate table %s", usersTable);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
