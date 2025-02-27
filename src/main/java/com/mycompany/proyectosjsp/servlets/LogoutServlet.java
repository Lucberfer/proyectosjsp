package com.mycompany.proyectosjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling user logout requests.
 * It invalidates the session and redirects to the login page.
 * 
 * @author Lucas
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    /**
     * Handles GET requests to log the user out.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the existing session if available

        if (session != null) {
            session.invalidate(); // Destroy session
        }

        response.sendRedirect("login.jsp?exito=Cierre de sesión exitoso."); // Redirect to login with success message
    }
}
