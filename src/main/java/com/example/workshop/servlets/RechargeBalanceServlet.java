package com.example.workshop.servlets;

import com.example.workshop.dao.UserDaoImpl;
import com.example.workshop.db.DatabaseConnection;
import com.example.workshop.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RechargeBalanceServlet", value = "/manager/balance")
public class RechargeBalanceServlet extends HttpServlet {
    private final UserService userService;

    public RechargeBalanceServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/rechargeBalance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("id"));
        int sum = Integer.parseInt(request.getParameter("sum"));
        try {
            userService.rechargeBalance(user_id, sum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/workshop_war_exploded/users/profile");
    }
}
