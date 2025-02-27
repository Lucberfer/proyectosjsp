package com.mycompany.proyectosjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling the home page requests.
 * It ensures that only authenticated users can access the home page.
 * 
 * @author Lucas
 */
@WebServlet(name = "InicioServlet", urlPatterns = {"/inicio"})
public class InicioServlet extends HttpServlet {

    /**
     * Handles GET requests to display the home page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp?error=Debes iniciar sesión para acceder al inicio.");
            return;
        }

        // Get user information
        String username = (String) session.getAttribute("usuario");
        String rol = (String) session.getAttribute("rol");

        request.setAttribute("username", username);
        request.setAttribute("rol", rol);

        request.getRequestDispatcher("views/inicio.jsp").forward(request, response);
    }
}
