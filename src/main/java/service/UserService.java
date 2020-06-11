package service;

import DAO.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class UserService {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUser() {
        try {
            return getBankClientDAO().getAllUser();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        try {
            if (getBankClientDAO().checkUser(user.getName())) {
                return false;
            } else {
                return getBankClientDAO().addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        try {
            User user = getUserById(id);
            if (getBankClientDAO().checkUser(user.getName())) {
                return getBankClientDAO().deleteUser(user);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User getUserById(Long id) {
        try {
            return getBankClientDAO().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(User oldUser, User newUser) {
        try {
            return getBankClientDAO().updateUser(oldUser, newUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Connection getMySqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            Properties properties = new Properties();
            properties.load(UserService.class.getClassLoader().getResourceAsStream("database.properties"));

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getBankClientDAO() {
        return new UserDAO(getMySqlConnection());
    }
}
