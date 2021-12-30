package com.example.workshop.servlets;

import com.example.workshop.dao.OrderDaoImpl;
import com.example.workshop.db.DatabaseConnection;
import com.example.workshop.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DisplayServlet", value = "/orders/display")
public class DisplayServlet extends HttpServlet {
    private final OrderService orderService;

    public DisplayServlet() throws SQLException {
        orderService = new OrderService(new OrderDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("orders", orderService.getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
