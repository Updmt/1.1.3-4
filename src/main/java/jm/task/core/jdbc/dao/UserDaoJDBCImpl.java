package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    String sql = "INSERT INTO mynewdb (name, lastName, age) VALUES (?, ?, ?)";
    String create = "CREATE TABLE IF NOT EXISTS mynewdb" +
            " (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(45) NOT NULL, " +
            "lastName VARCHAR(45) NOT NULL, " +
            "age TINYINT NOT NULL)";
    String delete = "DROP TABLE IF EXISTS mynewdb";
    String getUsers = "SELECT * FROM mynewdb";
    String deleteUsers = "DELETE FROM mynewdb";
    String removeById = "DELETE FROM mynewdb where id = ?";

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(create)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(removeById)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(getUsers)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteUsers)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
