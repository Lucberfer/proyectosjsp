package com.mycompany.proyectosjsp.utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to restrict access to authenticated users only.
 * Redirects unauthorized users to the login page.
 * 
 * @author Lucas
 */
@WebFilter("/*") // Applies to all routes except public pages
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No specific initialization required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Define public pages that do not require authentication
        String loginURI = req.getContextPath() + "/login";
        String registerURI = req.getContextPath() + "/registrar";
        String logoutURI = req.getContextPath() + "/logout";
        String errorURI = req.getContextPath() + "/error";

        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);
        boolean isPublicPage = req.getRequestURI().equals(loginURI)
                || req.getRequestURI().equals(registerURI)
                || req.getRequestURI().equals(logoutURI)
                || req.getRequestURI().equals(errorURI)
                || req.getRequestURI().endsWith(".css") // Allow CSS files
                || req.getRequestURI().endsWith(".js")  // Allow JavaScript files
                || req.getRequestURI().endsWith(".png") // Allow images
                || req.getRequestURI().endsWith(".jpg");

        if (loggedIn || isPublicPage) {
            chain.doFilter(request, response); // Allow access
        } else {
            res.sendRedirect(loginURI + "?error=Debes iniciar sesión para acceder.");
        }
    }

    @Override
    public void destroy() {
        // No specific cleanup required
    }
}
