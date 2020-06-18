package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import javax.management.Query;
import org.hibernate.query.Query;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HibernateDAO implements UserDAO {

    private static HibernateDAO hibernateDAO;

    private SessionFactory sessionFactory;

    public HibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static HibernateDAO getInstance() {
        if (hibernateDAO == null) {
            hibernateDAO = new HibernateDAO(DBHelper.getInstance().getMySqlConfiguration().buildSessionFactory());
        }
        return hibernateDAO;
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("from User", User.class).list();
        session.close();
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean updateUser(User newUser) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(newUser);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean checkUser(String name) {
        Session session = sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("from User where name = :name", User.class);
        userQuery.setParameter("name", name);
        List<User> userList = userQuery.getResultList();
        session.close();
        return !userList.isEmpty();
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("from User where id = :id", User.class);
        userQuery.setParameter("id", id);
        List<User> userList = userQuery.getResultList();
        session.close();
        return userList.stream().findFirst().orElse(null);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        Session session = sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("from User where name = :name AND password = :password", User.class);
        userQuery.setParameter("name", name);
        userQuery.setParameter("password", password);
        List<User> userList = userQuery.getResultList();
        session.close();
        return userList.stream().findFirst().orElse(null);
    }
}
