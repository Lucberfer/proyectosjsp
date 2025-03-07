package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Usuario;
import com.mycompany.proyectosjsp.service.UsuarioService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username and password from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials using the service
        Usuario usuario = usuarioService.findUsuarioByCredentials(username, password);

        if (usuario != null) {
            // If the username is "admin", enforce the role "admin"
            if ("admin".equalsIgnoreCase(username)) {
                usuario.setRol("admin");
                // Opcional: actualizar la base de datos si quieres persistir este cambio
                // usuarioService.updateUsuario(usuario);
            } else {
                // Para otros usuarios, asegúrate de que el rol se haya asignado en la BD
                // o, si no, lo asignas como "invitado"
                if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                    usuario.setRol("invitado");
                    // Opcional: usuarioService.updateUsuario(usuario);
                }
            }

            // Store user info in session
            request.getSession().setAttribute("usuario", usuario);
            request.getSession().setAttribute("rol", usuario.getRol());

            // Redirect based on role: admin gets full CRUD access, invitados a vistas limitadas
            if ("admin".equalsIgnoreCase(usuario.getRol())) {
                response.sendRedirect(request.getContextPath() + "/views/admin/admin.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/invitado/invitado.jsp");
            }
        } else {
            // In case of invalid credentials, forward back to login with an error message
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward GET requests to the login page
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
