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
 * URL mapping: /* (applies to all routes except public pages)
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No specific initialization required
    }

    /**
     * Checks if the user is logged in.
     * Allows access to public pages (login, registrar, logout, error, and static resources).
     * If not authenticated, redirects to the login page with an error message.
     */
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
                || req.getRequestURI().endsWith(".css")
                || req.getRequestURI().endsWith(".js")
                || req.getRequestURI().endsWith(".png")
                || req.getRequestURI().endsWith(".jpg");

        if (loggedIn || isPublicPage) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI + "?error=You must log in to access this page.");
        }
    }

    @Override
    public void destroy() {
        // No specific cleanup required
    }
}
