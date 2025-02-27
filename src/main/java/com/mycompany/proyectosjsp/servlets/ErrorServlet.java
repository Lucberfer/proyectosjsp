package com.mycompany.proyectosjsp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling application errors.
 * It captures exceptions and forwards the user to an error page.
 * 
 * @author Lucas
 */
@WebServlet(name = "ErrorServlet", urlPatterns = {"/error"})
public class ErrorServlet extends HttpServlet {

    /**
     * Handles GET requests and displays the error page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve error details
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        // Set attributes for error.jsp
        request.setAttribute("statusCode", statusCode);
        request.setAttribute("requestUri", requestUri);
        request.setAttribute("exception", throwable);

        // Forward to the custom error page
        request.getRequestDispatcher("views/error.jsp").forward(request, response);
    }
}
