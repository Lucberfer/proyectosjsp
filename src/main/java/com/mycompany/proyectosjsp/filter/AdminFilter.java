package com.mycompany.proyectosjsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialize the filter if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Cast the request and response to HttpServlet versions
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Retrieve the current session if available
        HttpSession session = req.getSession(false);
        
        // Check if the user is logged in and has the role "admin"
        if (session != null && "admin".equals(session.getAttribute("rol"))) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/views/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Cleanup resources if needed
    }
}
