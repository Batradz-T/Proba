package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String usersTable = "users";
    private String sqlQuery;
    private Statement statement;

    public UserDaoJDBCImpl() {
        try {
            statement = Util.getConnection().createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        sqlQuery = String.format("create table %s (id bigint auto_increment primary key, Name VARCHAR(20), LastName VARCHAR(20), Age int)", usersTable);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        sqlQuery = String.format("drop table %s", usersTable);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            // e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        sqlQuery = String.format("insert into %s (name, lastname, age) values (\'%s\', \'%s\', %d)", usersTable, name, lastName, age);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        sqlQuery = String.format("delete from %s where id = %d", usersTable, id);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        sqlQuery = String.format("select * from %s", usersTable);
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getByte(4)));

            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        sqlQuery = String.format("truncate table %s", usersTable);
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }
}
