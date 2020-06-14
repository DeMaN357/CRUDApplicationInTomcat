package DAO;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection mySqlConnection) {
        this.connection = mySqlConnection;
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
                User user = new User(id, name, password);
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
        String query = "INSERT INTO user (name, password) values (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String query = "DELETE FROM user where name = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }
    }

    @Override
    public boolean updateUser(User newUser) throws SQLException {
        String query = "UPDATE user SET name=?, password=? where id=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,newUser.getName());
            ps.setString(2, newUser.getPassword());
            ps.setLong(3, newUser.getId());
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
            return new User(id1, name, password);
        }
    }
}
