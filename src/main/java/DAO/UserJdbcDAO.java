package DAO;

import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static UserJdbcDAO userJdbcDAO;

    private Connection connection;

    private UserJdbcDAO(Connection mySqlConnection) {
        this.connection = mySqlConnection;
    }

    public static UserJdbcDAO getInstance() {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO(DBHelper.getInstance().getMySqlConnection());
        }
        return userJdbcDAO;
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String query = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(id, name, password, role);
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public boolean checkUser(String name) throws SQLException {
        String query = "SELECT * FROM user where name =?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        String query = "INSERT INTO user (name, password, role) values (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String query = "DELETE FROM user where name = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        }
    }

    @Override
    public boolean updateUser(User newUser) throws SQLException {
        String query = "UPDATE user SET name=?, password=?, role=? where id=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getRole());
            ps.setLong(4, newUser.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public User getUserById(Long id) throws SQLException {
        String query = "SELECT * from user where id=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            Long id1 = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            return new User(id1, name, password, role);
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        String query = "SELECT * FROM user where name =? AND password =?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            String name1 = resultSet.getString("name");
            String password2 = resultSet.getString("password");
            String role = resultSet.getString("role");
            return new User(id, name1, password2, role);
        }
    }
}
