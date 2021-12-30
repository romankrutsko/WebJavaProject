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

@WebServlet(name = "SetStatusServlet", value = "/manager/set/status")
public class SetStatusServlet extends HttpServlet {
    private final OrderService orderService;

    public SetStatusServlet() throws SQLException {
        orderService = new OrderService(new OrderDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/managerSetStatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        try {
            orderService.setStatus(orderId, status);
            response.sendRedirect("/workshop_war_exploded/orders/profile");
        } catch (SQLException throwables) {
            response.sendRedirect("/workshop_war_exploded/manager/set/status");
        }
    }
}
