package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import javax.management.Query;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HibernateDAO implements UserDAO {
    private static SessionFactory sessionFactory;

    public HibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("from User", User.class).list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean updateUser(User newUser) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(newUser);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean checkUser(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> userQuery = session.createQuery("from User where name = :name", User.class);
        userQuery.setParameter("name", name);
        List<User> userList = userQuery.getResultList();
        transaction.commit();
        session.close();
        return !userList.isEmpty();
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> userQuery = session.createQuery("from User where id = :id", User.class);
        userQuery.setParameter("id", id);
        List<User> userList = userQuery.getResultList();
        transaction.commit();
        session.close();
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> userQuery = session.createQuery("from User where name = :name AND password = :password", User.class);
        userQuery.setParameter("name", name);
        userQuery.setParameter("password", password);
        List<User> userList = userQuery.getResultList();
        transaction.commit();
        session.close();
        return userList.isEmpty() ? null : userList.get(0);
    }
}
