package com.example.workshop.servlets;

import com.example.workshop.dao.UserDaoImpl;
import com.example.workshop.db.DatabaseConnection;
import com.example.workshop.entity.User;
import com.example.workshop.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateManagerServlet", value = "/manager/create")
public class CreateManagerServlet extends HttpServlet {
    private final UserService userService;

    public CreateManagerServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        userService.createManager(new User(1, name, surname));
        response.sendRedirect("/workshop_war_exploded/users/profile");
    }
}
