package util;

import model.User;
import org.hibernate.cfg.Configuration;
import service.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getMySqlConnection() {
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

    @SuppressWarnings("UnusedDeclaration")
    public Configuration getMySqlConfiguration() {
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
