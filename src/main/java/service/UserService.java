package service;

import DAO.HibernateDAO;
import DAO.UserDAO;
import DAO.UserDaoFactory;
import DAO.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class UserService {

    private static UserService userService;

    private static  UserDAO userDAO;

    private UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(UserDaoFactory.getFactory());
        }
        return userService;
    }

    public List<User> getAllUser() {
        try {
            return userDAO.getAllUser();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        try {
            if (userDAO.checkUser(user.getName())) {
                return false;
            } else {
                return userDAO.addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteUser(User user) {
        try {
            userDAO.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User newUser) {
        try {
            if(newUser.getName().equals(getUserById(newUser.getId()).getName())){
                return userDAO.updateUser(newUser);
            }else if(userDAO.checkUser(newUser.getName())){
                return false;
            }
            return userDAO.updateUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
