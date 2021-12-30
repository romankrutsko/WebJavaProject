package com.example.workshop.servlets;

import com.example.workshop.dao.OrderDaoImpl;
import com.example.workshop.dao.UserDaoImpl;
import com.example.workshop.db.DatabaseConnection;
import com.example.workshop.entity.Order;
import com.example.workshop.entity.User;
import com.example.workshop.services.OrderService;
import com.example.workshop.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UserSaveServlet", value = "/users/create")
public class UserSaveServlet extends HttpServlet {
    private final UserService userService;

    public UserSaveServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double account = Double.parseDouble(request.getParameter("account"));
        userService.saveUser(new User(1, name, surname, account));
        response.sendRedirect("/workshop_war_exploded/users/profile");
    }
}
