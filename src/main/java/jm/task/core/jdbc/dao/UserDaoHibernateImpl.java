package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String usersTable = "users";
    String hqlQuery;
    private SessionFactory sessionFactory;
    private Session session;
    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20), LastName VARCHAR(20), Age int)",
                usersTable);
        session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = String.format("DROP TABLE IF EXISTS %s", usersTable);
        session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        hqlQuery = "DELETE User u where u.id = :id";
        session.createQuery(hqlQuery).setBigInteger("id", BigInteger.valueOf(id )).executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        hqlQuery = "FROM User";
        session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session = sessionFactory.openSession();
        List<User> users = session.createQuery(hqlQuery).list();
        tr.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        hqlQuery = "DELETE FROM User";
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery(hqlQuery).executeUpdate();
        tx.commit();
        session.close();
    }

    public void closeConnection() {
        sessionFactory.close();

    }
}
