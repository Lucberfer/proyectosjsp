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
 * Filter to restrict access to administrator-only pages.
 * Redirects unauthorized users to the login page.
 * 
 * @author Lucas
 */
@WebFilter("/admin/*") // Applies to all routes under /admin/
public class AdminFilter implements Filter {

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

        // Check if user is logged in and has admin privileges
        if (session == null || session.getAttribute("usuario") == null ||
            !"admin".equals(session.getAttribute("rol"))) {
            
            res.sendRedirect(req.getContextPath() + "/login.jsp?error=Acceso restringido. Debes ser administrador.");
            return;
        }

        chain.doFilter(request, response); // Allow access if user is admin
    }

    @Override
    public void destroy() {
        // No specific cleanup required
    }
}
