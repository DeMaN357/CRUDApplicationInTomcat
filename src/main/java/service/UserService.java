package service;

import DAO.HibernateDAO;
import DAO.UserDAO;
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

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserDAO getConnectionToBase() {
        try {
            Properties properties = new Properties();
            properties.load(UserService.class.getClassLoader().getResourceAsStream("selectImplementation.properties"));

            String typeBase = properties.getProperty("typeBase");
            if (typeBase.equals("jdbc")) {
                return getUserJdbcDAO();
            } else if (typeBase.equals("hibernate")) {
                return getHibernateDAO();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
        return null;
    }

    public List<User> getAllUser() {
        try {
            return getConnectionToBase().getAllUser();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        try {
            if (getConnectionToBase().checkUser(user.getName())) {
                return false;
            } else {
                return getConnectionToBase().addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteUser(User user) {
        try {
            getConnectionToBase().deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User newUser) {
        try {
            if(getConnectionToBase().checkUser(newUser.getName())){
                return false;
            }else{
                return getConnectionToBase().updateUser(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(Long id) {
        try {
            return getConnectionToBase().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection getMySqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            Properties properties = new Properties();
            properties.load(UserService.class.getClassLoader().getResourceAsStream("JDBC.properties"));

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

    private static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(getMySqlConnection());
    }

    private static HibernateDAO getHibernateDAO() {
        return new HibernateDAO(getSessionFactory().openSession());
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        try {
            Properties properties = new Properties();
            properties.load(UserService.class.getClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

}
