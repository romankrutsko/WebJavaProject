package com.example.workshop.services;

import com.example.workshop.dao.OrderDaoImpl;
import com.example.workshop.dao.UserDaoImpl;
import com.example.workshop.entity.Order;
import com.example.workshop.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public void createManager(User user) {
        userDao.createManager(user);
    }

    public void deleteUser(int id) {
        userDao.delete(id);
    }

    public void payOrder(int orderId) throws SQLException {
        userDao.payOrder(orderId);
    }

    public void rechargeBalance(int userId, int sum) throws SQLException {
        userDao.rechargeBalance(userId, sum);
    }

    public List<User> getAll() throws SQLException {
        return userDao.getAll();
    }

    public User login(String name, String surname) {
        return userDao.login(name, surname);
    }

    public boolean validUsername(String username) throws SQLException {
        return userDao.isUsernameValid(username);
    }
}
