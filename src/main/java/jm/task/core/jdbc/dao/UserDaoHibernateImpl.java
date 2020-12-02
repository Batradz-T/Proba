package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String usersTable = "users";
    String sqlQuery;
    private SessionFactory sessionFactory;
    private  Session session;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20), LastName VARCHAR(20), Age int)",
                usersTable);
        session = sessionFactory.openSession();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        sqlQuery = String.format("DROP TABLE IF EXISTS %s", usersTable);
        session = sessionFactory.openSession();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        User user = new User(name, lastName, age);
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        sqlQuery = String.format("DELETE FROM %s WHERE ID = %d", usersTable, id);
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        sqlQuery = String.format("SELECT * FROM %s", usersTable);
        return  sessionFactory.openSession().createSQLQuery(sqlQuery).list();
    }

    @Override
    public void cleanUsersTable() {
        sqlQuery = String.format("truncate table %s", usersTable);
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        tx.commit();
        session.close();
    }
}
