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
 * Servlet for handling user login requests.
 * It interacts with UsuarioService to authenticate users.
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
     * Displays the login page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

    /**
     * Handles the login process.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input fields
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        Usuario usuario = usuarioService.validarUsuario(username, password);

        if (usuario != null) {
            // Create session and store user details
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.getUsername());
            session.setAttribute("rol", usuario.getRol());

            // Redirect based on role
            if ("admin".equals(usuario.getRol())) {
                response.sendRedirect("views/inicio.jsp?exito=Bienvenido administrador.");
            } else {
                response.sendRedirect("views/inicio.jsp?exito=Inicio de sesión exitoso.");
            }
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}
