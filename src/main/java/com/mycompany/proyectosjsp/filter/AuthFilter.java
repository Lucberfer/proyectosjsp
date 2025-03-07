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

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtén la URI de la solicitud
        String uri = req.getRequestURI();

        // Permitir el acceso a recursos públicos: login, registro y recursos estáticos
        if (uri.contains("login.jsp")
                || uri.contains("LoginServlet")
                || uri.contains("RegistroServlet")
                || uri.contains("registrar.jsp")
                || uri.contains("css") 
                || uri.contains("js")
                || uri.contains("images")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Verificar si existe la sesión y el atributo "usuario"
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/views/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
