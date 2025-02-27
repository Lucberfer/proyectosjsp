package com.mycompany.proyectosjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to handle user login requests.
 * Processes login for regular users (username only) and admin login.
 * For admin login, the username must be "admin".
 *
 * URL mapping: /login
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Handles GET requests by forwarding to the login JSP page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the login JSP using an absolute path
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to process login.
     * For regular login, only the username is required.
     * For admin login, the username must be "admin".
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username from the form
        String username = request.getParameter("username");
        // Retrieve the action parameter to determine which button was pressed
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        
        if ("login_admin".equals(action)) {
            // Validate that the username is "admin" for admin login
            if ("admin".equals(username)) {
                session.setAttribute("usuario", "admin");
                session.setAttribute("rol", "admin");
                response.sendRedirect("inicio.jsp");
            } else {
                request.setAttribute("error", "Invalid admin credentials.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        } else if ("login_user".equals(action)) {
            // Accept any non-empty username as a regular user
            session.setAttribute("usuario", username);
            session.setAttribute("rol", "usuario");
            response.sendRedirect("inicio.jsp");
        } else {
            request.setAttribute("error", "Invalid login action.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
