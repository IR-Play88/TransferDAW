package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.annotation.WebServlet;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;


@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

            String nombre = request.getParameter("usuario");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String rol = request.getParameter("rol");

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setContrasena(pass);
            usuario.setRol(rol);

            dao.insertar(usuario);

            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear cuenta " + e.getMessage());
        }
    }
}
