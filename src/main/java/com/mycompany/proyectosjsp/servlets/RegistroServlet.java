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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger datos del formulario de registro
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Opcional: Verificar si el usuario ya existe
        Usuario usuarioExistente = usuarioService.findUsuarioByCredentials(username, password);
        if(usuarioExistente != null) {
            request.setAttribute("error", "El usuario ya existe. Intenta con otro.");
            request.getRequestDispatcher("/views/registrar.jsp").forward(request, response);
            return;
        }
        
        // Crear un nuevo usuario con rol por defecto "invitado"
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setRol("invitado");
        
        // Guardar el usuario en la base de datos
        usuarioService.createUsuario(nuevoUsuario);
        
        // Redirigir al login
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al formulario de registro
        request.getRequestDispatcher("/views/registrar.jsp").forward(request, response);
    }
}
