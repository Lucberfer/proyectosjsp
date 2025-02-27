package com.mycompany.proyectosjsp.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base servlet that provides common utilities for all servlets.
 * This class can be extended by other servlets to share common behavior.
 * 
 * @author Lucas
 */
public abstract class BaseServlet extends HttpServlet {


    /**
     * Redirects the user to a specified URL with an optional success or error message.
     *
     * @param request   The HTTP request object
     * @param response  The HTTP response object
     * @param url       The destination URL
     * @param exito     The success message (optional, can be null)
     * @param error     The error message (optional, can be null)
     * @throws IOException If an input or output error occurs
     */
    protected void redirectWithMessage(HttpServletRequest request, HttpServletResponse response,
                                       String url, String exito, String error) throws IOException {
        if (exito != null) {
            request.getSession().setAttribute("exito", exito);
        }
        if (error != null) {
            request.getSession().setAttribute("error", error);
        }
        response.sendRedirect(url);
    }

    /**
     * Retrieves a long parameter from the request, handling errors gracefully.
     *
     * @param request  The HTTP request object
     * @param paramName The name of the parameter
     * @return The parsed long value, or null if invalid
     */
    protected Long getLongParameter(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        if (paramValue == null || paramValue.trim().isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(paramValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
