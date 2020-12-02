package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;

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
//                Configuration cfg = new Configuration()
//                        .addAnnotatedClass(User.class);
//                sessionFactory = cfg.buildSessionFactory();
                        //.addClass(org.hibernate.auction.Bid.class);
//                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(User.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());
//
//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(User.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());



                Configuration configuration = new Configuration().configure();
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
