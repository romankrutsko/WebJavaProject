package com.example.workshop.servlets;

import com.example.workshop.dao.OrderDaoImpl;
import com.example.workshop.db.DatabaseConnection;
import com.example.workshop.entity.Order;
import com.example.workshop.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "OrderSaveServlet", value = "/orders/save")
public class OrderSaveServlet extends HttpServlet {
    private final OrderService orderService;

    public OrderSaveServlet() throws SQLException {
        orderService = new OrderService(new OrderDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("users_id"));
        String status = request.getParameter("status");
        Date date = Date.valueOf(request.getParameter("date"));
        String master = request.getParameter("master");
        double price = Double.parseDouble(request.getParameter("price"));
        orderService.saveOrder(new Order(1, user_id, status, date, master, price));
        response.sendRedirect("/workshop_war_exploded/orders/profile");
    }

}
