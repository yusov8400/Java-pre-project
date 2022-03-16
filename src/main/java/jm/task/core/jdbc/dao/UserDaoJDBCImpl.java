package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table if not exists users (id serial , name varchar(256), lastName varchar(256),age bigint, primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement dropTable = null;
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF  EXISTS  users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name,lastName,age) VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(" User - " + name + " added to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE  users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

