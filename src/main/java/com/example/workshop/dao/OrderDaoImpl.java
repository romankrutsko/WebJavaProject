package com.example.workshop.dao;

import com.example.workshop.entity.Order;
import com.example.workshop.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl {

    private final Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into orders (users_id, status, date, master, price) values (?,?,?,?,?)"
        )) {
            statement.setInt(1, order.getUsers_id());
            statement.setString(2, order.getStatus());
            statement.setDate(3, order.getDate());
            statement.setString(4, order.getMaster());
            statement.setDouble(5, order.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Order> getById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders where id=" + id + ";");
        Order order = new Order();
        if (resultSet.next()) {
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
        }
        return Optional.ofNullable(order);
    }

    public void setMaster(int id, String master) throws SQLException {
        if (getById(id) != null) {
            PreparedStatement statement = connection.prepareStatement("update orders set master ='" + master
                    + "' where (id = " + id + ");");
            statement.executeUpdate();
        }
    }

    public void setPrice(int id, double price) throws SQLException {
        if (getById(id) != null) {
            PreparedStatement statement = connection.prepareStatement("update orders set price ='" + price
                    + "' where (id = " + id + ");");
            statement.executeUpdate();
        }
    }

    public void setStatus(int id, String status) throws SQLException {
        if (getById(id) != null) {
            PreparedStatement statement = connection.prepareStatement("update orders set status ='" + status
                    + "' where (id = " + id + ");");
            statement.executeUpdate();
        }
    }

    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from orders where id = '" + id + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders;");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public List<Order> getAllOrderByDate() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders order by date;");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public List<Order> getAllOrderByStatus() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders order by status;");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public List<Order> getAllOrderByPrice() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders order by price");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public List<Order> filterStatus(String status) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * orders where status = " + status + ";");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public List<Order> filterMaster(String master) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * orders where master = " + master + ";");
        List<Order> result = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUsers_id(resultSet.getInt("users_id"));
            order.setStatus(resultSet.getString("status"));
            order.setDate(resultSet.getDate("date"));
            order.setMaster(resultSet.getString("master"));
            order.setPrice(resultSet.getDouble("price"));
            result.add(order);
        }
        return result;
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
