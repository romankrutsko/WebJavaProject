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

@WebServlet(name = "DeleteUserServlet", value = "/users/delete")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService;

    public DeleteUserServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/deleteUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.deleteUser(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/workshop_war_exploded/users/profile");
    }
}
