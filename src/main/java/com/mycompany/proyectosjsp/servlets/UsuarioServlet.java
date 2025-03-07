package com.mycompany.proyectosjsp.servlets;

import com.mycompany.proyectosjsp.model.Usuario;
import com.mycompany.proyectosjsp.service.UsuarioService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioServlet extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action from the request parameter
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine the action for POST requests
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                updateUser(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    /**
     * Retrieves the list of users and forwards the request to listaUsuarios.jsp.
     */
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/views/admin/listaUsuarios.jsp").forward(request, response);
    }

    /**
     * Retrieves a specific user by ID and forwards to editarUsuario.jsp for editing.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/views/admin/editarUsuario.jsp").forward(request, response);
    }

    /**
     * Updates the user with new data provided from the form.
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }
        // Retrieve new values from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        // Update user fields
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRol(rol);

        // Persist the changes in the database
        usuarioService.updateUsuario(usuario);

        // Redirect back to the list of users
        response.sendRedirect(request.getContextPath() + "/UsuarioServlet?action=list");
    }

    /**
     * Deletes the specified user and redirects to the user list.
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioService.deleteUsuario(id);
        response.sendRedirect(request.getContextPath() + "/UsuarioServlet?action=list");
    }
}
