package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import java.sql.*;

public class Util {
    private static SessionFactory sessionFactory = null;
    public static Connection getGDBCConnection() throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/usersdb";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usersdb");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                configuration.setProperty("hibernate.connection.username", "root");
                configuration.setProperty("hibernate.connection.password", "1234");
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addPackage( "jm.task.core.jdbc.model" );
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (HibernateException e ) {
                e.printStackTrace();

            }
        }

        return sessionFactory;

    }

}
