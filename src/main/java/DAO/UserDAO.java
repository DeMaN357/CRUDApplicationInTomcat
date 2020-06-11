package DAO;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection mySqlConnection) {
        this.connection = mySqlConnection;
    }

    public List<User> getAllUser() throws SQLException {
        String query = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User user = new User(id, name, password);
                userList.add(user);
            }
        }
        return userList;
    }

    public boolean checkUser(String name) throws SQLException {
        String query = "SELECT * FROM user where name =?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        }
    }

    public boolean addUser(User user) throws SQLException {
        String query = "INSERT INTO user (name, password) values (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(User user) throws SQLException {
        String query = "DELETE FROM user where name = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateUser(User oldUser, User newUser) throws SQLException {
        String query = "UPDATE user SET name=?, password=? where name=? AND password=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, oldUser.getName());
            ps.setString(4, oldUser.getPassword());
            return ps.executeUpdate() > 0;
        }
    }

    public User getUserById(Long id) throws SQLException {
        String query = "SELECT * from user where id= ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            Long id1 = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            return new User(id1, name, password);
        }
    }
}
