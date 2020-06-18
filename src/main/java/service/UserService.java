package service;

import DAO.UserDAO;
import factory.UserDaoFactory;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(new UserDaoFactory().getDao());
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
            if (!userDAO.checkUser(user.getName())) {
                return userDAO.addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
            if (newUser.getName().equals(getUserById(newUser.getId()).getName())) {
                return userDAO.updateUser(newUser);
            } else if (userDAO.checkUser(newUser.getName())) {
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

    public User getUserByNameAndPassword(String name, String password) {
        try {
            return userDAO.getUserByNameAndPassword(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
