package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Usuario;
import com.mycompany.proyectosjsp.service.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroServlet extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the registration JSP page
        request.getRequestDispatcher("/views/registrar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve registration parameters from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Optionally check if the user already exists
        Usuario existingUser = usuarioService.findUsuarioByCredentials(username, password);
        if(existingUser != null) {
            // If user exists, set error message and forward back to registration page
            request.setAttribute("error", "Usuario existente. Prueba con otro.");
            request.getRequestDispatcher("/views/registrar.jsp").forward(request, response);
            return;
        }

        // Create a new user with the default role "invitado"
        Usuario newUser = new Usuario();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRol("invitado");

        // Persist the new user in the database
        usuarioService.createUsuario(newUser);

        // Redirect to the login page after successful registration
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
}
