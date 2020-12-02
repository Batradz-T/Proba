package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        for (int i = 1; i <= 4; i++) {
            String name = "name" + i;
            String lastName = "lastName" + i;
            byte age = (byte) (20 * i);
            userService.saveUser(name, lastName, age);
            System.out.println(new User(name, lastName, age));
        }

//        List<User> users = userService.getAllUsers();
//        for (User user: users) {
//            System.out.println(user);
//        }

        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();

    }
}
