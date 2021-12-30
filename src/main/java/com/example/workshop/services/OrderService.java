package com.example.workshop.services;

import com.example.workshop.dao.OrderDaoImpl;
import com.example.workshop.entity.Order;
import com.example.workshop.entity.Status;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDaoImpl orderDao;

    public OrderService(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    public void deleteOrder(int id) {
        orderDao.delete(id);
    }

    public void setMaster(int id, String master) throws SQLException {
        orderDao.setMaster(id, master);
    }

    public void setPrice(int id, double price) throws SQLException {
        orderDao.setPrice(id, price);
    }

    public void setStatus(int id, String status) throws SQLException {
        orderDao.setStatus(id, status);
    }

    public List<Order> getAll() throws SQLException {
        return orderDao.getAll();
    }

    public List<Order> getAllOrderByDate() throws SQLException {
        return orderDao.getAllOrderByDate();
    }

    public List<Order> getAllOrderByStatus() throws SQLException {
        return orderDao.getAllOrderByStatus();
    }

    public List<Order> getAllOrderByPrice() throws SQLException {
        return orderDao.getAllOrderByPrice();
    }

    public List<Order> filterMaster(String master) throws SQLException {
        return orderDao.filterMaster(master);
    }

    public List<Order> filterStatus(String status) throws SQLException {
        return orderDao.filterStatus(status);
    }
}
