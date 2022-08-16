package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }
    PreparedStatement statement = null;

    String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    String create = "CREATE TABLE IF NOT EXISTS users" +
            " (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(45) NOT NULL, " +
            "lastName VARCHAR(45) NOT NULL, " +
            "age TINYINT NOT NULL)";

    public void createUsersTable() throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(create);){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql);){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
