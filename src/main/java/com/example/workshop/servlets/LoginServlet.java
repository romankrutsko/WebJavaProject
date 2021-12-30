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
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/loginUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("surname");
        User user = null;
        try {
            if (userService.validUsername(username)) {
                user = userService.login(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!Objects.isNull(user)) {
            request.getSession().setAttribute("users", user);
            response.sendRedirect("/users/profile");
        } else {
            request.setAttribute("error", "Unknown user, please try again");
            response.sendRedirect("/workshop_war_exploded/login");
        }
    }
}
