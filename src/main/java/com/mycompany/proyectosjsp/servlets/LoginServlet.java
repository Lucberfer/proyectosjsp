package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Usuario;
import com.mycompany.proyectosjsp.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to handle user login requests.
 * Authenticates users and manages session creation.
 * 
 * @author Lucas
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioService = new UsuarioService();
    }

    /**
     * Handles GET requests to display the login page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to process login.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = usuarioService.validarUsuario(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.getUsername());
            session.setAttribute("rol", usuario.getRol());

            response.sendRedirect("inicio");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}
