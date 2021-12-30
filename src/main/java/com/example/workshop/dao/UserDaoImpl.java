package com.example.workshop.dao;

import com.example.workshop.entity.Order;
import com.example.workshop.entity.Role;
import com.example.workshop.entity.Status;
import com.example.workshop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User getById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where id=" + id + ";");
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAccount(resultSet.getDouble("account"));
            user.setRole(resultSet.getString("role"));
        }
        return user;
    }

    public int getId(int order_id) throws SQLException {
        int user_id = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders where id=" + order_id + ";");
        if (resultSet.next()) {
            user_id = Integer.parseInt(resultSet.getString("users_id"));
        }
        return user_id;
    }

    public double getPrice(int id) throws SQLException {
        double price = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders where id=" + id + ";");
        if (resultSet.next()) {
            price = Double.parseDouble(resultSet.getString("price"));
        }
        return price;
    }

    public double getAccount(int id) throws SQLException {
        double account = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where id=" + id + ";");
        if (resultSet.next()) {
            account = Double.parseDouble(resultSet.getString("account"));
        }
        return account;
    }

    public void saveUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into users (name, surname, account, role) values (?,?,?,?)"
        )) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDouble(3, user.getAccount());
            statement.setString(4, Role.user.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createManager(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into users (name, surname, account, role) values (?,?,?,?)"
        )) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDouble(3, 0);
            statement.setString(4, Role.manager.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rechargeBalance(int userId, int sum) throws SQLException {
        if (getById(userId) != null) {
            double account = getAccount(userId);
            double result = sum + account;

            PreparedStatement statement = connection.prepareStatement("update users set account ='" + result
                    + "' where (id = " + userId + ");");
            statement.executeUpdate();
        }
    }

    public void payOrder(int orderId) throws SQLException {
        if (getById(orderId) != null) {
            int userId = getId(orderId);
            double account = getAccount(userId);
            double price = getPrice(orderId);
            if (account >= price) {
                double result = account - price;

                PreparedStatement statement = connection.prepareStatement("update users set account ='" + result
                        + "' where (id = " + userId + ");");
                statement.executeUpdate();
                statement = connection.prepareStatement("update orders set status ='" + Status.paid
                        + "' where (id = " + orderId + ");");
                statement.executeUpdate();
            }
        }
    }

    public List<User> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users;");
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAccount(resultSet.getDouble("account"));
            user.setRole(resultSet.getString("role"));
            result.add(user);
        }
        return result;
    }

    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from orders where users_id = '" + id + "'");
            statement.execute("delete from users where id = '" + id + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String name, String surname) {
        int userId = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM users WHERE name= '" + name + "'AND surname= '" + surname + "';");
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
            return getById(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean isUsernameValid(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where name = '" + name + "';");
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setRole(resultSet.getString("role"));
        }
        return user != null;
    }

}
