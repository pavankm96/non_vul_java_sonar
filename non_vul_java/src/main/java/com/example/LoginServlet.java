package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static String generateCSRFToken(HttpSession session) {
        String token = UUID.randomUUID().toString();
        session.setAttribute("csrf_token", token);
        return token;
    }

    public static boolean validateCSRFToken(HttpServletRequest request) {
        String token = request.getParameter("csrf_token");
        String sessionToken = (String) request.getSession().getAttribute("csrf_token");
        return token != null && token.equals(sessionToken);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validateCSRFToken(request)) {
            throw new SecurityException("Invalid CSRF token");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.getWriter().println("Welcome, " + username + "! Your password is " + password);
    }
}