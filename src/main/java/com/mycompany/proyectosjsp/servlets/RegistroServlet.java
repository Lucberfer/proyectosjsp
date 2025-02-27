package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.models.Usuario;
import com.mycompany.proyectosjsp.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling user registration.
 * Interacts with UsuarioService to create new users.
 * 
 * @author Lucas
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends BaseServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioService = new UsuarioService();
    }

    /**
     * Handles GET requests: Displays the registration form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/registrar.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: Processes user registration.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Validate fields
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("views/registrar.jsp").forward(request, response);
            return;
        }

        // Validate password match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("views/registrar.jsp").forward(request, response);
            return;
        }

        // Create user object
        Usuario usuario = new Usuario(username, password, "usuario");

        // Register user
        boolean success = usuarioService.registrarUsuario(usuario);

        if (success) {
            response.sendRedirect("login?exito=Usuario registrado exitosamente.");
        } else {
            request.setAttribute("error", "El nombre de usuario ya está en uso.");
            request.getRequestDispatcher("views/registrar.jsp").forward(request, response);
        }
    }
}
