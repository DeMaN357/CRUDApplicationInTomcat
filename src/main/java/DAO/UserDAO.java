package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUser() throws SQLException;

    boolean addUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    boolean updateUser(User newUser) throws SQLException;

    boolean checkUser(String name) throws SQLException;

    User getUserById(Long id) throws SQLException;
}
